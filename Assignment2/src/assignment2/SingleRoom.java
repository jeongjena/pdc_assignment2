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
 * The SingleRoom class is a subclass of the abstract Room class.
 * This class sets the maximum number of guests to 1 and assigns the room type as SINGLE.
 * It overrides the calculatePrice() method, demonstrating polymorphism.
 */
public class SingleRoom extends Room{
    
    public SingleRoom(int roomNumber, double baseRate, double ratePerPerson) {
        this.setMaxGuests(1);
        this.setRoomType(RoomType.SINGLE);
        this.setRoomNumber(roomNumber);
        this.setBaseRate(baseRate);
        this.setRatePerPerson(ratePerPerson);
    }
    
    @Override
    public double calculatePrice(int numGuests) {
        return(getBaseRate() + getRatePerPerson());
    }
}
