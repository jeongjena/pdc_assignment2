/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author uj265
 */

/**
 * RoomView is responsible for displaying room-related information
 * on the GUI screen using Swing components.
 */
public class RoomView extends View {

    // Displays all available rooms without pricing information.
    public void printRooms(Map<Integer, Room> rooms) {
        String text = "No rooms available.";
        if (!rooms.isEmpty()) {
            text = "<html>&emsp;List of rooms:<br><br>";
            for (Room r : rooms.values()) {
                text += ("&emsp;" + r.printRoom() + "<br>");
            }
            text += "</html>";
        }
        displayRoomView(text); // Render room info as label
    }

    /**
     * Displays available rooms with price calculated based on number of guests and nights.
     * @param rooms a map of roomNumber -> Room object
     * @param numGuests number of guests for price calculation
     * @param nights number of nights for price calculation
     */
    public void printRoomsWithPrice(Map<Integer, Room> rooms, int numGuests, int nights) {
        String text = "No rooms available.";
        if (!rooms.isEmpty()) {
            text = "<html>&emsp;List of rooms:<br><br>";
            for (Room r : rooms.values()) {
                text += ("&emsp;" + r.printRoomWithPrice(numGuests, nights) + "<br>");
            }
            text += "</html>";
        }
        displayRoomViewWithPrice(text);
    }

    /**
     * Displays the room view with main and exit buttons.
     * This version is for when prices are NOT displayed.
     */
    private void displayRoomView(String text) {
        getContentPane().removeAll();
        JLabel label;
        if (text.contains("No rooms available")) {
            label = new JLabel(text, JLabel.CENTER);
            label.setForeground(Color.RED);
            label.setFont(new Font("Arial", Font.BOLD, 20));
        } else {
            label = new JLabel(text);
        }

        add(label, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(mainButton);
        panel.add(exitButton);

        add(panel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }

    /**
     * Displays the room view with pricing info only.
     * This version omits main/exit buttons.
     */
    private void displayRoomViewWithPrice(String text) {
        getContentPane().removeAll();
        JLabel label;
        if (text.contains("No rooms available")) {
            label = new JLabel(text);
            label.setForeground(Color.RED);
            label.setFont(new Font("Arial", Font.BOLD, 20));
        } else {
            label = new JLabel(text);
            label.setFont(new Font("Arial", Font.BOLD, 12));
        }

        add(label, BorderLayout.NORTH);

        revalidate();
        repaint();
    }
}
