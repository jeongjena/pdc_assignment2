/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

/**
 *
 * @author uj265
 */

/*
 * The DoubleRoom class is a subclass of the abstract Room class.
 * This class sets the room type to DOUBLE and the max guest limit to 2.
 * It overrides the calculatePrice() method to compute the total price
 * based on the number of guests.
 */
public class DoubleRoom extends Room{
   
    public DoubleRoom(int roomNumber, double baseRate, double ratePerPerson){
        this.setRoomNumber(roomNumber);
        this.setRoomType(RoomType.DOUBLE);
        this.setBaseRate(baseRate);
        this.setRatePerPerson(ratePerPerson);
        this.setMaxGuests(2);
    }
    
     /**
     * Overrides the calculatePrice method from Room.
     * Calculates price based on base rate and number of guests.
     * 
     * @param numGuests Number of guests staying
     * @return Total price for the booking
     */
    
    @Override
    public double calculatePrice(int numGuests){
        return(getBaseRate() + getRatePerPerson() * numGuests);
    }
}
