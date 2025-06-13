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

/**
 * The Controller class provides basic navigation controls (Main and Exit) 
 * that are shared by all views. 
 * It sets up listeners for the "Main" and "Exit" buttons in the given view.
 */
public class Controller {
    protected View view;

    // Constructor binds the shared view and attaches action listeners for main and exit actions.
    public Controller(View view) {
        this.view = view;

        this.view.addMainListener(new MainListener());
        this.view.addExitListener(new ExitListener());
    }
    
    // MainListener handles redirection to the main Hotel view when "Main" is clicked.
    class MainListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Main Clicked");
            
            view.setVisible(false);
            view.dispose();
            
            HotelView view = new HotelView();
            Hotel hotel = new Hotel();
            HotelController controller = new HotelController(view, hotel);
        }
    }

    // ExitListener closes the application when "Exit" is clicked.
    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
