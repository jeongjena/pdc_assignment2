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
 * The Room class is an abstract superclass that defines the basic
 * structure and properties of a hotel room. It uses the principle of
 * abstraction by leaving the price calculation logic to be implemented
 * by concrete subclasses (SingleRoom, DoubleRoom, SuiteRoom).
 * 
 * This class includes encapsulated fields with accessors and mutators,
 * and provides helper methods to display room information.
 */
public abstract class Room {
    private int roomNumber;
    private RoomType roomType; // Type of the room (SINGLE, DOUBLE, SUITE)
    private double baseRate; // Base rate for the room
    private double ratePerPerson; // Additional rate per guest
    private int maxGuests; // Maximum number of guests allowed in the room
    
    public enum RoomType {
        SINGLE, DOUBLE, SUITE
    }
    
    /**
     * Abstract method that must be implemented by subclasses to calculate
     * the price of the room based on the number of guests.
     */
    public abstract double calculatePrice(int numGuests);
    
    
    // Prints basic room information including room number, type, and max guests.
    public String printRoom() {
        return ("Room Number: " + roomNumber + "&emsp;|&emsp; Room Type: " + roomType + "&emsp;|&emsp; Maximum Guests Allowed: " + maxGuests);
    } 
    
    // Prints room information along with total price for a given number of guests and nights.
    public String printRoomWithPrice(int numGuests, int nights) {
        return("Room Number: " + roomNumber + "&emsp;|&emsp; Room Type: " + roomType + "&emsp;|&emsp; Maximum Guests Allowed: " + maxGuests
                    + "&emsp;|&emsp; Total Price For " + numGuests + " Guests: $" + calculatePrice(numGuests) * nights);
    }

    // --- Getter and Setter methods ---
    /**
     * @return the roomNumber
     */
    public int getRoomNumber() {
        return roomNumber;
    }

    /**
     * @param roomNumber the roomNumber to set
     */
    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    /**
     * @return the baseRate
     */
    public double getBaseRate() {
        return baseRate;
    }

    /**
     * @param baseRate the baseRate to set
     */
    public void setBaseRate(double baseRate) {
        this.baseRate = baseRate;
    }

    /**
     * @return the ratePerPerson
     */
    public double getRatePerPerson() {
        return ratePerPerson;
    }

    /**
     * @param ratePerPerson the ratePerPerson to set
     */
    public void setRatePerPerson(double ratePerPerson) {
        this.ratePerPerson = ratePerPerson;
    }
    
    /**
     * @return the maxGuests
     */
    public int getMaxGuests() {
        return maxGuests;
    }

    /**
     * @param maxGuests the maxGuests to set
     */
    public void setMaxGuests(int maxGuests) {
        this.maxGuests = maxGuests;
    }

    /**
     * @return the roomType
     */
    public RoomType getRoomType() {
        return roomType;
    }

    /**
     * @param roomType the roomType to set
     */
    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
