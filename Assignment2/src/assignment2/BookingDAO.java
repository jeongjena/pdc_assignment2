/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author uj265
 */

/**
 * BookingDAO handles basic database operations for booking data in the BOOKINGS table.
 * It provides methods to read, add, and remove booking records.
 */
public class BookingDAO {

    /**
     * Reads all booking numbers from the BOOKINGS table.
     * Used for validation when checking user input.
     */
    public static Set<Integer> readBookingNumbers() {
        Set<Integer> bookingNumbers = new HashSet<>();

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT BOOKING_NUMBER FROM BOOKINGS");

            while (rs.next()) {
                int bookingNumber = rs.getInt("BOOKING_NUMBER");
                bookingNumbers.add(bookingNumber);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            UserPromptView.showError("Booking Table is not working. Please try it again.");
            System.out.println(e.getMessage());
        }
        return bookingNumbers;
    }

    /**
     * Retrieves a Booking object for the given booking number.
     * Returns null if the booking is not found or an error occurs.
     */
    public static Booking getBooking(int bookingNumber) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKINGS WHERE BOOKING_NUMBER = " + bookingNumber);

            Booking booking = new Booking();
            Guest guest = new Guest();
            while (rs.next()) {
                return buildBookingFromResultSet(rs); // Extract and build Booking object
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            UserPromptView.showError("Booking Table is not working. Please try it again.");
            System.out.println(e.getMessage());
        }
        return null;

    }

    /**
     * Reads all bookings from the database and stores them in a map.
     * Key: booking number, Value: Booking object
     */
    public static Map<Integer, Booking> readBookings() {
        Map<Integer, Booking> bookings = new HashMap<>();

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKINGS");

            while (rs.next()) {
                Booking booking = buildBookingFromResultSet(rs);
                bookings.put(booking.getBookingNumber(), booking);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            UserPromptView.showError("Booking Table is not working. Please try it again.");
            System.out.println(e.getMessage());
        }
        return bookings;
    }

    /**
     * Inserts a new booking record into the BOOKINGS table.
     * Returns true if insertion is successful.
     */
    public static boolean addBooking(Booking booking) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO BOOKINGS VALUES (" + booking.getBookingNumber() + ", '" + booking.getCheckInDate() + "', '" + booking.getCheckOutDate() + "', " + booking.getRoomNumber() + ", " + booking.getNumberOfGuests() + ", '" + booking.getGuest().getFirstName() + "', '" + booking.getGuest().getLastName() + "', '" + booking.getGuest().getPhone() + "', " + booking.getGuest().getPassword() + ")");
            
            return true;
        } catch (SQLException e) {
            UserPromptView.showError("Booking Table is not working. Please try it again.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Deletes a booking record based on booking number.
     * Returns true if the deletion is successful.
     */
    public static boolean removeBooking(int bookingNumber) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM BOOKINGS WHERE BOOKING_NUMBER = " + bookingNumber);
                   
            return true;

        } catch (SQLException e) {
            UserPromptView.showError("Booking Table is not working. Please try it again.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to build a Booking object from a ResultSet row.
     * Maps database columns to Booking and Guest fields.
     */
    private static Booking buildBookingFromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        booking.setBookingNumber(rs.getInt("BOOKING_NUMBER"));
        booking.setCheckInDate(rs.getDate("CHECK_IN").toLocalDate());
        booking.setCheckOutDate(rs.getDate("CHECK_OUT").toLocalDate());
        booking.setRoomNumber(rs.getInt("ROOM_NUMBER"));
        booking.setNumberOfGuests(rs.getInt("NUMBER_OF_GUESTS"));

        Guest guest = new Guest();
        guest.setFirstName(rs.getString("FIRST_NAME"));
        guest.setLastName(rs.getString("LAST_NAME"));
        guest.setPhone(rs.getString("PHONE_NUMBER"));
        guest.setPassword(rs.getInt("PASSWORD"));

        booking.setGuest(guest);
        return booking;
    }

}
