/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author uj265
 */

/**
 * AdminView represents the admin interface.
 * It provides buttons to add or remove rooms, and navigates back to the main menu or exits the app.
 * 
 * This class is part of the View layer in the MVC pattern.
 */
public class AdminView extends View {

    public JPasswordField passwordField;
    private JButton addRoomButton = new JButton("Add Room");
    private JButton removeRoomButton = new JButton("Remove Room");

    // Constructs the admin view window and initializes the components.
    public AdminView() {
        this.setTitle("Admin Page");

        initComponents();
    }

    
     // Initializes and arranges the GUI components in a grid layout.
        public void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        
        // Add admin action buttons and inherited navigation buttons
        panel.add(addRoomButton);
        panel.add(removeRoomButton);
        panel.add(mainButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
    }

    // Registers an action listener for the Add Room button.    
    public void addAddRoomListener(ActionListener listener) {
        addRoomButton.addActionListener(listener);
    }

    // Registers an action listener for the Remove Room button.
    public void addRemoveRoomListener(ActionListener listener) {
        removeRoomButton.addActionListener(listener);
    }
}
