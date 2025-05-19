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
public class RoomView extends View {

    public void printRooms(Map<Integer, Room> rooms) {
        String text = "No rooms available.";
        if (!rooms.isEmpty()) {
            text = "<html>&emsp;List of rooms:<br><br>";
            for (Room r : rooms.values()) {
                text += ("&emsp;" + r.printRoom() + "<br>");
            }
            text += "</html>";
        }
        displayRoomView(text);
    }

    public void printRoomsWithPrice(Map<Integer, Room> rooms, int numGuests, int nights) {
        String text = "No rooms available.";
        if (!rooms.isEmpty()) {
            text = "<html>&emsp;List of rooms:<br><br>";
            for (Room r : rooms.values()) {
                text += ("&emsp;" + r.printRoomWithPrice(numGuests, nights) + "<br>");
            }
            text += "</html>";
        }
        displayRoomView(text);
    }

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

        add(label, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(mainButton);
        panel.add(exitButton);

        add(panel, BorderLayout.SOUTH);

        revalidate();
        repaint();
    }
}
