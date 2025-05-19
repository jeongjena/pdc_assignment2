/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author uj265
 */
public class AdminView extends View {

    public JPasswordField passwordField;
    private JButton loginButton;
    private JLabel label;
    private JButton addRoomButton = new JButton("Add Room");
    private JButton removeRoomButton = new JButton("Remove Room");

    public AdminView() {
        this.setTitle("Admin Page");

        initComponents();
    }

    public void initComponents() {
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        
        //panel.add(passwordPanel);
        panel.add(addRoomButton);
        panel.add(removeRoomButton);
        panel.add(mainButton);
        panel.add(exitButton);

        add(panel, BorderLayout.CENTER);
    }

    public void addAddRoomListener(ActionListener listener) {
        addRoomButton.addActionListener(listener);
    }

    public void addRemoveRoomListener(ActionListener listener) {
        removeRoomButton.addActionListener(listener);
    }

    public String getEnteredPassword() {
        return new String(passwordField.getPassword());
    }

    public void wrongPasswordLabel() {
        label.setText("Password is wrong. Please try again.");
        label.setForeground(Color.RED);
    }
}
