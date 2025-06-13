/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author uj265
 */
public class HotelController extends Controller {

    private Hotel hotel;
    private HotelView hotelView;

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

    class MakeBookingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Make Booking Clicked");

            Booking newBooking = new Booking();
            int guestNumber = newBooking.askGuestNumber();
            if (guestNumber == -1) {
                return;
            }
            newBooking.setNumberOfGuests(guestNumber);
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

            hotel.findAvailableRooms(newBooking);

            hotelView.setVisible(false);
            RoomView roomView = new RoomView();
            roomView.printRoomsWithPrice(hotel.getRooms(), newBooking.getNumberOfGuests(), newBooking.calculateNights());
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
