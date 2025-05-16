/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author uj265
 */

/*
 * UserPrompt provides reusable input handling methods for the command-line interface.
 * It includes validation, cancellation, and quitting options for robust user experience.
 * Supports input for integers, doubles, strings, and dates with feedback messages.
 */
public class UserPrompt {

    private static final Scanner scan = new Scanner(System.in);

    /**
     * Prompts user for an integer input.
     * @param prompt Message to display to the user
     * @return A positive integer or -1 if cancelled
     */
    public static int promptInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.print(" (type 'x' to cancel, 'q' to quit): ");
            String input = scan.nextLine().replaceAll("\\s", "");
            if (!checkInput(input)) {
                return -1;
            }
            try {
                int choice = Integer.parseInt(input);
                if (choice > 0) {
                    return choice;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Prompts user for a double input.
     * @param prompt Message to display to the user
     * @return A positive double or -1.00 if cancelled
     */
    public static double promptDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.print(" (type 'x' to cancel, 'q' to quit): ");
            String input = scan.nextLine().replaceAll("\\s", "");
            if (!checkInput(input)) {
                return -1.00;
            }
            try {
                double choice = Double.parseDouble(input);
                if (choice > 0) {
                    return choice;
                } else {
                    System.out.println("Please enter a positive number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    /**
     * Prompts user for a non-empty string.
     * @param prompt Message to display to the user
     * @return Trimmed string or null if cancelled
     */
    public static String promptString(String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.print(" (type 'x' to cancel, 'q' to quit): ");
            String input = scan.nextLine().replaceAll("\\s", "");
            if (!checkInput(input)) {
                return null;
            }
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Please enter a non-empty string");
        }
    }

    /**
     * Prompts user for a check-in date in YYYY-MM-DD format.
     * @param prompt Message to display to the user
     * @return LocalDate object or null if cancelled
     */
    public static LocalDate promptCheckInDate(String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.print(" (type 'x' to cancel, 'q' to quit): ");
            String input = scan.nextLine();
            if (!checkInput(input)) {
                return null;
            }
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Please enter in format YYYY-MM-DD.");
            }
        }
    }
    
    /**
     * Prompts user for a check-out date that must be after the given check-in date.
     * @param checkInDate Previously entered check-in date
     * @param prompt Message to display to the user
     * @return LocalDate object or null if cancelled
     */
    public static LocalDate promptCheckOutDate(LocalDate checkInDate, String prompt) {
        while (true) {
            System.out.print(prompt);
            System.out.print(" (type 'x' to cancel, 'q' to quit): ");
            String input = scan.nextLine();
            if (!checkInput(input)) {
                return null;
            }
            // The following date validation logic was added with assistance from ChatGPT
            try { 
                LocalDate checkOut = LocalDate.parse(input);
                if (checkOut.isAfter(checkInDate)) {
                    return checkOut;
                } else {
                    System.out.println("Check-out date must be after check-in date.");
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format. Please use YYYY-MM-DD.");
            }
        }
    }

    /**
     * Handles special command input for 'q' (quit) and 'x' (cancel).
     * @param input User input string
     * @return false if user cancels, true if valid
     */
    public static boolean checkInput(String input) {
        if (input.equalsIgnoreCase("q")) {
            System.out.println("Exiting the system. Goodbye!");
            System.exit(0);
        }
        if (input.equalsIgnoreCase("x")) {
            return false;
        }
        return true;
    }
}
