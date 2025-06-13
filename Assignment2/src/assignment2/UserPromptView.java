package assignment2;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * PromptView provides reusable input dialogs for GUI-based input.
 * This class centralizes user input handling with validation for integers, doubles,
 * strings, and dates using JOptionPane dialogs.
 */
public class UserPromptView {
    
/**
 * Some parts of this class were developed with assistance from OpenAI's ChatGPT,
 * particularly in designing the reusable input prompt structure and validation logic.
 *
 * This utility class supports the GUI by providing consistent input dialogs with validation.
 */

    public static Integer promptInt(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message + "\n(Type 'q' to quit):");
            if (input == null) return -1;

            if (input.equalsIgnoreCase("q")) quit();

            try {
                int val = Integer.parseInt(input);
                if (val > 0) return val;
                else showError("Please enter a positive number.");
            } catch (NumberFormatException e) {
                showError("Invalid number. Please enter a valid integer.");
            }
        }
    }

    public static Double promptDouble(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message + "\n(Type 'q' to quit):");
            if (input == null) return -1.00;

            if (input.equalsIgnoreCase("q")) quit();

            try {
                double val = Double.parseDouble(input);
                if (val > 0) return val;
                else showError("Please enter a positive number.");
            } catch (NumberFormatException e) {
                showError("Invalid number. Please enter a valid decimal.");
            }
        }
    }

    public static String promptString(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message + "\n(Type 'q' to quit):");
            if (input == null) return null;

            if (input.equalsIgnoreCase("q")) quit();

            if (!input.trim().isEmpty()) return input.trim();
            showError("Please enter a non-empty string.");
        }
    }

    public static LocalDate promptCheckInDate(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message + " (YYYY-MM-DD)\n(Type 'q' to quit):");
            if (input == null) return null;

            if (input.equalsIgnoreCase("q")) quit();

            try {
                return LocalDate.parse(input.trim());
            } catch (DateTimeParseException e) {
                showError("Invalid format. Please enter date in YYYY-MM-DD format.");
            }
        }
    }

    public static LocalDate promptCheckOutDate(LocalDate checkIn, String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(null, message + " (YYYY-MM-DD)\n(Type 'q' to quit):");
            if (input == null) return null;

            if (input.equalsIgnoreCase("q")) quit();

            try {
                LocalDate checkOut = LocalDate.parse(input.trim());
                if (checkOut.isAfter(checkIn)) return checkOut;
                else showError("Check-out must be after check-in date.");
            } catch (DateTimeParseException e) {
                showError("Invalid format. Please enter date in YYYY-MM-DD format.");
            }
        }
    }
    
    /**
     * Displays a plain message dialog to the user.
     */
    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message, null, JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Displays an error dialog to the user with red icon.
     */
    public static void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Helper method to exit the application with a farewell message.
     */
    private static void quit() {
        JOptionPane.showMessageDialog(null, "Exiting system. Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}
