/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
        }
    }

    class CancelBookingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Cancel Booking Clicked");
        }
    }

    class ViewBookingListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("View Booking Clicked");
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
