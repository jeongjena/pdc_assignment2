/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author uj265
 */

/**
 * AdminController handles admin-related actions such as adding and removing rooms.
 * It listens to the AdminView button events and delegates tasks to the Administrator class.
 * 
 * Part of the Controller layer in the MVC pattern.
 */
public class AdminController extends Controller {
    private AdminView adminView;
    
    // Constructor: attaches action listeners to AdminView's buttons.
    public AdminController(AdminView view){
        super(view);
        adminView = view;
        
        // Register event listeners
        view.addAddRoomListener(new AddRoomListener());
        view.addRemoveRoomListener(new RemoveRoomListener());
    } 
    
    /**
     * Listener for the "Add Room" button.
     * Delegates the room addition task to the Administrator class.
     */
    class AddRoomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add Room Clicked");
            Administrator.addRoom();
        }
    } 
    
    /**
     * Listener for the "Remove Room" button.
     * Delegates the room removal task to the Administrator class.
     */
    class RemoveRoomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Remove Room Clicked");
            Administrator.removeRoom();
        }
    } 
}
