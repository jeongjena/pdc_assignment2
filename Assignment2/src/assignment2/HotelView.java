/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author uj265
 */
public class HotelView extends View {

    private JButton viewRoomsButton;
    private JButton makeBookingButton;
    private JButton cancelBookingButton;
    private JButton viewBookingButton;
    private JButton adminButton;

    public HotelView() {
        this.setTitle("Hotel Booking System");

        initComponents();
    }

    private void initComponents() {

        JLabel label = new JLabel("Welcome to Hotel Booking System!", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));
        add(label, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 10, 15));

        viewRoomsButton = new JButton("View All Rooms");
        makeBookingButton = new JButton("Make A Booking");
        cancelBookingButton = new JButton("Cancel A Booking");
        viewBookingButton = new JButton("View My Booking");
        adminButton = new JButton("Administrator Login");

        panel.add(viewRoomsButton);
        panel.add(makeBookingButton);
        panel.add(cancelBookingButton);
        panel.add(viewBookingButton);
        panel.add(adminButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
    }

    public void addViewRoomsListener(ActionListener listener) {
        viewRoomsButton.addActionListener(listener);
    }

    public void addMakeBookingListener(ActionListener listener) {
        makeBookingButton.addActionListener(listener);
    }

    public void addCancelBookingListener(ActionListener listener) {
        cancelBookingButton.addActionListener(listener);
    }

    public void addViewBookingListener(ActionListener listener) {
        viewBookingButton.addActionListener(listener);
    }

    public void addAdminLoginListener(ActionListener listener) {
        adminButton.addActionListener(listener);
    }
}
