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
public class AdminController extends Controller {
    private AdminView adminView;
    
    public AdminController(AdminView view){
        super(view);
        adminView = view;
        
        view.addAddRoomListener(new AddRoomListener());
        view.addRemoveRoomListener(new RemoveRoomListener());
    } 
    
    class AddRoomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add Room Clicked");
            Administrator.addRoom();
        }
    } 
    
    class RemoveRoomListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Remove Room Clicked");
            Administrator.removeRoom();
        }
    } 
}
