/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 *
 * @author uj265
 */

/*
 * Main class of the Hotel Booking System project
 * Provides command-line user interface (CUI) for guests and administrators
 * Demonstrates all core OOP principles and uses file-based data persistence
 */
public class HotelBookingSystem {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Booking System");
        boolean exit = false;
        Hotel hotel = new Hotel();

        while (!exit) {
            System.out.println();
            switch (chooseOption()) {
                case "1":
                    // View all available rooms
                    hotel.readRooms();
                    hotel.printRooms();
                    break;
                case "2":
                    // Booking process
                    int guestNumber = UserPrompt.promptInt("\nPlease enter the number of guests");
                    if (guestNumber == -1) {
                        break;
                    }
                    LocalDate checkInDate = UserPrompt.promptCheckInDate("Please enter your check-in date (YYYY-MM-DD)");
                    if (checkInDate == null) {
                        break;
                    }
                    LocalDate checkOutDate = UserPrompt.promptCheckOutDate(checkInDate, "Please enter your check-out date (YYYY-MM-DD)");
                    if (checkOutDate == null) {
                        break;
                    }
                    hotel.readRooms();
                    hotel.readBookings();
                    hotel.removeRoomByGuestNumber(guestNumber); // Filter rooms by guest number
                    hotel.removeRoomByDate(checkInDate, checkOutDate); // Filter rooms by availability
                    hotel.printRoomsWithPrice((int) ChronoUnit.DAYS.between(checkInDate, checkOutDate), guestNumber);
                    int selectedRoomNumber = hotel.selectRoom(); // User selects room
                    if (selectedRoomNumber == -1) {
                        break;
                    }
                    Guest guest = new Guest();
                    if (!guest.getGuestInfo()) {
                        break;
                    }
                    if (guest.getGuestPassword() == -1) {
                        break;
                    }
                    Booking newBooking = new Booking(checkInDate, checkOutDate, selectedRoomNumber, guestNumber, guest);
                    boolean doubleCheck = hotel.doubleCheckBooking(newBooking);
                    if (doubleCheck) {
                        hotel.makeBooking(newBooking); // Finalise and store booking
                    } else {
                        break;
                    }
                    break;
                case "3":
                    // Cancel a booking
                    int bookingNumber = Booking.askBookingNumber();
                    if (bookingNumber == -1) {
                        break;
                    }
                    hotel.readBookings();
                    if (!hotel.cancelBooking(bookingNumber)) {
                        break;
                    }
                    break;
                case "4":
                    // View a booking
                    int myBookingNumber = Booking.askBookingNumber();
                    if (myBookingNumber == -1) {
                        break;
                    }
                    if (!Booking.printBooking(myBookingNumber)) {
                        break;
                    }
                    break;
                case "5":
                    // Exit program
                    System.out.println("Exiting the system. Goodbye!");
                    exit = true;
                    break;
                case "0":
                    // Admin menu
                    if (Administrator.checkAdminPassword()) {
                        if (!adminMenu()) {
                            break;
                        }
                    } else {
                        break;
                    }
                    break;
                default:
                    // Handle invalid input
                    System.out.println("Please enter a valid option from the menu.");
                    break;
            }
        }
    }

    /**
     * Displays the main menu and returns user's menu selection.
     * @return User's chosen menu option as a string
     */
    public static String chooseOption() {

        System.out.println("1. View all rooms");
        System.out.println("2. Make a booking");
        System.out.println("3. Cancel a booking");
        System.out.println("4. View my booking");
        System.out.println("5. Exit");
        System.out.println("0. Administrator login");
        System.out.print("Please enter your choice: ");

        return scan.nextLine();
    }

    /**
     * Displays the administrator menu for adding/removing rooms.
     * @return false if user cancels operation
     */
    public static boolean adminMenu() {
        while (true) {
            System.out.println("\n--- ADMINISTRATOR MENU ---");
            System.out.println("1. Add room");
            System.out.println("2. Remove room");
            String input = UserPrompt.promptString("Enter your choice");
            if (input == null) {
                return false;
            }
            switch (input) {
                case "1":
                    Administrator.addRoom();
                    break;
                case "2":
                    Administrator.removeRoom();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
