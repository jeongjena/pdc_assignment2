/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author uj265
 */
public class MainGUI {

    public static void main(String[] args) {
        HotelView view = new HotelView();
        Hotel hotel = new Hotel();
        HotelController controller = new HotelController(view, hotel);
    }
}
