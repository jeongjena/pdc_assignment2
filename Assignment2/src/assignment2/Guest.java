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
 * The Guest class encapsulates the personal details of a guest,
 * such as their name, phone number, and password. It also contains
 * methods for validating input and formatting phone numbers.
 */
public class Guest {

    private String firstName;
    private String lastName;
    private String phone;
    private int password;

    // Default constructor
    public Guest() {
    }

    // Constructor with all fields
    public Guest(String firstName, String lastName, String phone, int password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.password = password;
    }

    // Set the guest's password
    public void setPassword(int password) {
        this.password = password;
    }

    // Get the guest's password
    public int getPassword() {
        return password;
    }

    /**
     * Prompts the user for first name, last name, and phone number.
     * Performs validation and returns false if cancelled at any step.
     */
    public boolean getGuestInfo() {
        String firstName = UserPrompt.promptString("Enter your first name");
        if (firstName == null) {
            return false;
        }
        setFirstName(firstName);
        String lastName = UserPrompt.promptString("Enter your last name");
        if (lastName == null) {
            return false;
        }
        setLastName(lastName);
        if (!checkPhoneNumber()) {
            return false;
        }
        return true;
    }

    /**
     * Prompts user for phone number, validates it, and formats it into standard format.
     * Accepts 10- or 11-digit numbers only. Rejects letters or invalid input.
     */
    public boolean checkPhoneNumber() { // Method generated with the help of ChatGPT for formatting phone number input
        while (true) {
            String phone = UserPrompt.promptString("Enter your phone number e.g. 0271231234");
            if (phone == null) {
                return false;
            }
            if (!phone.matches("\\d+")) {
                System.out.println("Phone number must contain digits only. Please try again.");
            } else {
                if (phone.length() == 10 || phone.length() == 11) {
                    try {
                        if (phone.length() == 10) {
                            setPhone(phone.substring(0, 3) + "-" + phone.substring(3, 6) + "-" + phone.substring(6));
                            return true;
                        } else if (phone.length() == 11) {
                            setPhone(phone.substring(0, 3) + "-" + phone.substring(3, 7) + "-" + phone.substring(7));
                            return true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid phone number.");
                    }
                } else {
                    System.out.println("Phone number must be 10 or 11 digits.");
                }
            }
        }
    }

    /**
     * Prompts the user to set a 4-digit numeric password.
     * Returns the password or -1 if cancelled.
     */
    public int getGuestPassword() {
        while (true) {
            String input = UserPrompt.promptString("Enter a password (4 digits) to make booking");
            if (input == null) {
                return -1;
            }
            if (input.length() == 4) {
                if (input.startsWith("-")) {
                    System.out.println("Please enter a positive number.");
                } else {
                    try {
                        int password = Integer.parseInt(input);
                        setPassword(password);
                        return password;

                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }
            } else {
                System.out.println("Password must be exactly 4 digits.");
            }
        }
    }

    /**
     * Verifies password input from user matches the stored guest password.
     * This is used for booking confirmation or cancellation.
     */
    public boolean checkPassword(String promt) {
        while (true) {
            String input = UserPrompt.promptString(promt);
            if (input == null) {
                return false;
            }
            if (input.length() == 4) {
                if (input.startsWith("-")) {
                    System.out.println("Please enter a positive number.");
                } else {
                    try {
                        int password = Integer.parseInt(input);
                        if (password >= 0) {
                            if (password == this.password) {
                                return true;
                            } else {
                                System.out.println("Incorrect password. Try again");
                            }
                        } else {
                            System.out.println("Please enter a positive number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Please enter a valid number.");
                    }
                }
            } else {
                System.out.println("Password must be exactly 4 digits.");
            }
        }
    }

    // --- Getter and Setter methods ---
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
