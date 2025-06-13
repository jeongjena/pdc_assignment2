/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment2;

import java.util.HashSet;
import java.util.Set;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author uj265
 */

/**
 * RoomDAO handles all database interactions related to rooms.
 * This includes reading, inserting, and deleting room records.
 */
public class RoomDAO {

    /**
     * Reads all room numbers from the ROOMS table.
     * Useful for validation and availability checks.
     */
    public static Set<Integer> readRoomNumbers() {
        Set<Integer> roomNumbers = new HashSet<>();

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT ROOM_NUMBER FROM ROOMS");

            while (rs.next()) {
                int roomNumber = rs.getInt("ROOM_NUMBER");
                roomNumbers.add(roomNumber); // Add each room number to the set
            }
            
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            UserPromptView.showError("Room Table is not working. Please try it again.");
            System.out.println(e.getMessage());
        }

        return roomNumbers;
    }

    // Retrieves a single room from the database by its room number.
    public static Room getRoom(int roomNumber) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ROOMS WHERE ROOM_NUMBER = " + roomNumber);

            while (rs.next()) {
                return buildRoomFromResultSet(rs); // Build and return room object if found
            }
            
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            UserPromptView.showError("Room Table is not working. Please try it again.");
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Reads all room records from the database and returns them in a map.
     * The map uses room number as the key.
     */
    public static Map<Integer, Room> readRooms() {
        Map<Integer, Room> rooms = new HashMap<>();

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ROOMS");

            while (rs.next()) {
                Room room = buildRoomFromResultSet(rs);
                rooms.put(room.getRoomNumber(), room);
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            UserPromptView.showError("Room Table is not working. Please try it again.");
            System.out.println(e.getMessage());
        }
        return rooms;
    }

    // Adds a new room to the ROOMS table using data from a Room object.
    public static boolean addRoom(Room room) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO ROOMS VALUES (" + room.getRoomNumber() + ", '" + room.getRoomType() + "', " + room.getBaseRate() + ", " + room.getRatePerPerson() + ", " + room.getMaxGuests() + ")");

            return true;

        } catch (SQLException e) {
            UserPromptView.showError("Room Table is not working. Please try it again.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    // Removes a room from the ROOMS table by its room number.
    public static boolean removeRoom(int roomNumber) {

        try {
            Statement stmt = Database.getInstance().getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM ROOMS WHERE ROOM_NUMBER = " + roomNumber);

            return true;

        } catch (SQLException e) {
            UserPromptView.showError("Room Table is not working. Please try it again.");
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Helper method to convert a ResultSet row into a Room object.
     * Handles different room types (Single, Double, Suite).
     */
    private static Room buildRoomFromResultSet(ResultSet rs) throws SQLException {
        int roomNumber = rs.getInt("ROOM_NUMBER");
        String roomType = rs.getString("ROOM_TYPE");
        double baseRate = rs.getDouble("BASE_RATE");
        double ratePerPerson = rs.getDouble("RATE_PER_PERSON");
        int maxGuests = rs.getInt("MAX_GUESTS");

        // Determine which Room subclass to instantiate based on roomType
        switch (roomType) {
            case "SINGLE":
                return new SingleRoom(roomNumber, baseRate, ratePerPerson);
            case "DOUBLE":
                return new DoubleRoom(roomNumber, baseRate, ratePerPerson);
            case "SUITE":
                return new SuiteRoom(roomNumber, baseRate, ratePerPerson, maxGuests);
            default:
                throw new SQLException("Unknown room type: " + roomType);
        }
    }
}
