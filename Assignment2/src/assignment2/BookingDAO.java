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
public class BookingDAO {

    public Set<Integer> readBookingNumbers() {
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
            System.out.println(e.getMessage());
        }

        return bookingNumbers;
    }

    public Booking getBooking(int bookingNumber) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM BOOKINGS WHERE BOOKING_NUMBER = " + bookingNumber);

            Booking booking = new Booking();
            Guest guest = new Guest();
            while (rs.next()) {
                return buildBookingFromResultSet(rs);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;

    }

    public Map<Integer, Booking> readBookings() {
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
            System.out.println(e.getMessage());
        }
        return bookings;
    }
    
    public boolean writeBookings(Booking booking) {
        
        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO BOOKINGS VALUES ("+ booking.getBookingNumber() + ", '" + booking.getCheckInDate() + "', '" + booking.getCheckOutDate() + "', " + booking.getRoomNumber() + ", " + booking.getNumberOfGuests() + ", '" + booking.getGuest().getFirstName() + "', '" + booking.getGuest().getLastName() + "', '" + booking.getGuest().getPhone() + "', " + booking.getGuest().getPassword() +")");
           
            return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private Booking buildBookingFromResultSet(ResultSet rs) throws SQLException {
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
