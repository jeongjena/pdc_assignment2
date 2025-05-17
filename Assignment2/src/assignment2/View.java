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
public class View extends JFrame {

    public JButton mainButton = new JButton("Return To Main Page");
    public JButton exitButton = new JButton("Exit");
    
    public View() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 600);
        this.setLocationRelativeTo(null);

        this.setVisible(true);
    }

    public void addExitListener(ActionListener listener) {
        exitButton.addActionListener(listener);
    }

    public void addMainListener(ActionListener listener) {
        mainButton.addActionListener(listener);
    }
}
