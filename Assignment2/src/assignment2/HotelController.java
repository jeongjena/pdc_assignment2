/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

/**
 *
 * @author uj265
 */

/**
 * HotelController handles user interaction from HotelView,
 * acts as a bridge between the view and the Hotel model,
 * and dispatches logic for room viewing, booking, and admin tasks.
 */
public class HotelController extends Controller {

    private Hotel hotel;
    private HotelView hotelView;

    // Constructor initializes listeners for all hotel user actions.
    public HotelController(HotelView view, Hotel hotel) {
        super(view);
        this.hotel = hotel;
        hotelView = view;

        view.addViewRoomsListener(new ViewRoomsListener());
        view.addMakeBookingListener(new MakeBookingListener());
        view.addCancelBookingListener(new CancelBookingListener());
        view.addViewBookingListener(new ViewBookingListener());
        view.addAdminLoginListener(new AdminLoginListener());
    }

    /**
     * Handles "View Rooms" button click.
     * Loads room data and shows it in a new RoomView window.
     */
    class ViewRoomsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            hotel.readRooms();
            RoomView roomView = new RoomView();
            hotelView.setVisible(false);
            hotelView.dispose();
            roomView.printRooms(hotel.getRooms());
            Controller controller = new Controller(roomView);
        }
    }

    /**
     * Handles the process of making a new booking:
     * - Asks for guest count and dates
     * - Displays available rooms
     * - Asks for room selection and guest info
     * - Confirms booking and persists it
     */
    class MakeBookingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Make Booking Clicked");

            Booking newBooking = new Booking();
            
            // Step 1: Ask number of guests
            int guestNumber = newBooking.askGuestNumber();
            if (guestNumber == -1) {
                return;
            }
            newBooking.setNumberOfGuests(guestNumber);
            
            // Step 2: Ask check-in/out dates
            LocalDate checkInDate = newBooking.askCheckInDate();
            if (checkInDate == null) {
                return;
            }
            newBooking.setCheckInDate(checkInDate);
            LocalDate checkOutDate = newBooking.askCheckOutDate();
            if (checkOutDate == null) {
                return;
            }
            newBooking.setCheckOutDate(checkOutDate);

            // Step 3: Display available rooms with pricing
            hotel.findAvailableRooms(newBooking);

            hotelView.setVisible(false);
            RoomView roomView = new RoomView();
            roomView.printRoomsWithPrice(hotel.getRooms(), newBooking.getNumberOfGuests(), newBooking.calculateNights());
            
            // Step 4: Room selection and guest details
            int selectedRoomNumber = hotel.selectRoom(); // User selects room
            
            roomView.setVisible(false);
            roomView.dispose();
            hotelView.setVisible(true);
            if (selectedRoomNumber == -1) {
                return;
            }
            newBooking.setRoomNumber(selectedRoomNumber);
            
            Boolean getGuestInfo = newBooking.askGuestInfo();
            if (!getGuestInfo) {
                return;
            }

            boolean doubleCheck = hotel.doubleCheckBooking(newBooking);
            if (!doubleCheck) {
                return;
            }
            hotel.makeBooking(newBooking);
        }
    }

    // Handles booking cancellation by booking number.
    class CancelBookingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel Booking Clicked");
            // Cancel a booking
            int bookingNumber = Booking.askBookingNumber();
            if (bookingNumber == -1) {
                return;
            }
            hotel.readBookings();
            if (!hotel.cancelBooking(bookingNumber)) {
                return;
            }
        }
    }

    // Displays booking details based on the booking number provided by user.
    class ViewBookingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("View Booking Clicked");
            int myBookingNumber = Booking.askBookingNumber();
            if (myBookingNumber == -1) {
                return;
            }
            if (!Booking.printBooking(myBookingNumber)) {
                return;
            }
        }
    }

    // Handles administrator login and opens admin view upon successful authentication.
    class AdminLoginListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Admin Login Clicked");

            if (Administrator.checkAdminPassword()) {
                view.setVisible(false);
                view.dispose();
                AdminView adminView = new AdminView();
                AdminController adminController = new AdminController(adminView);
            }
        }
    }
}
