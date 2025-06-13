/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author uj265
 */

/**
 * The View class provides a base GUI window with default Exit and Main buttons.
 * Other view classes (e.g., RoomView, HotelView) inherit from this class.
 */
public class View extends JFrame {

    public JButton mainButton = new JButton("Return To Main Page");
    public JButton exitButton = new JButton("Exit");
    
    /**
     * Constructor for base View.
     * Sets up the window's default behavior and visibility.
     */
    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    // Allows other classes to attach an ActionListener to the exit button.
    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    // Allows other classes to attach an ActionListener to the main button.
    public void addMainListener(ActionListener listener) {
        mainButton.addActionListener(listener);
    }
}
