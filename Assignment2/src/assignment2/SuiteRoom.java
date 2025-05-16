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
 * The SuiteRoom class is a subclass of the abstract Room class.
 * 
 * This class sets the room type to SUITE and allows the administrator
 * to define the maximum number of guests during room creation.
 * 
 * The overridden calculatePrice() method uses custom pricing logic:
 * - If guest number is 3 or fewer, charge for 3 guests
 * - Otherwise, charge per guest
 */
public class SuiteRoom extends Room{
    
    public SuiteRoom(int roomNumber, double baseRate, double ratePerPerson, int maxGuests){
        this.setRoomNumber(roomNumber);
        this.setRoomType(RoomType.SUITE);
        this.setBaseRate(baseRate);
        this.setRatePerPerson(ratePerPerson);
        this.setMaxGuests(maxGuests);
    }
    
    /**
     * Overrides the abstract method from Room.
     * Charges a minimum of 3 guests even if fewer guests are staying.
     *
     * @param numGuests Number of guests staying in the suite
     * @return Calculated total price for the stay
     */
    
    @Override
    public double calculatePrice(int numGuests){
        if (numGuests <= 3) {
            return (getBaseRate() + getRatePerPerson() * 3);
        }
        else {
            return (getBaseRate() + getRatePerPerson() * numGuests);
        }    
    }
}
