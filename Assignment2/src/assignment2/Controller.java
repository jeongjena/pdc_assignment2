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
public class Controller {
    protected View view;

    public Controller(View view) {
        this.view = view;

        this.view.addMainListener(new MainListener());
        this.view.addExitListener(new ExitListener());
    }
    
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

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
