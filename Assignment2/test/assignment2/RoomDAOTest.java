/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignment2;

import java.util.Map;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author uj265
 */
public class RoomDAOTest {
    
    public RoomDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of readRoomNumbers method, of class RoomDAO.
     */
    @Test
    public void testReadRoomNumbers() {
        System.out.println("readRoomNumbers");
        Set<Integer> roomNumbers = RoomDAO.readRoomNumbers();
        assertNotNull(roomNumbers);  
    }

    /**
     * Test of getRoom method, of class RoomDAO.
     */
    @Test
    public void testGetRoom() {
        System.out.println("getRoom");
        int roomNumber = -1;
        Room newRoom = new SingleRoom(roomNumber, 100.00, 50.00);
        RoomDAO.addRoom(newRoom);
        Room room = RoomDAO.getRoom(roomNumber);
        assertNotNull(room);  
        assertEquals(newRoom.getRoomType(), room.getRoomType());
        assertEquals(newRoom.getRoomNumber(), room.getRoomNumber());
        assertEquals(newRoom.getMaxGuests(), room.getMaxGuests());
        RoomDAO.removeRoom(roomNumber);

    }

    /**
     * Test of readRooms method, of class RoomDAO.
     */
    @Test
    public void testReadRooms() {
        System.out.println("readRooms");
        Map<Integer, Room> rooms = RoomDAO.readRooms();
        assertNotNull(rooms); 
    }

    /**
     * Test of addRoom method, of class RoomDAO.
     */
    @Test
    public void testAddRoom() {
        System.out.println("addRoom");
        int roomNumber = -1;
        Room newRoom = new DoubleRoom(roomNumber, 100.00, 50.00);
        RoomDAO.addRoom(newRoom);
        Room room = RoomDAO.getRoom(roomNumber);
        assertNotNull(room);  
        assertEquals(newRoom.getRoomType(), room.getRoomType());
        assertEquals(newRoom.getRoomNumber(), room.getRoomNumber());
        assertEquals(newRoom.getMaxGuests(), room.getMaxGuests());
        RoomDAO.removeRoom(roomNumber);
    }

    /**
     * Test of removeRoom method, of class RoomDAO.
     */
    @Test
    public void testRemoveRoom() {
        System.out.println("removeRoom");
        int roomNumber = -1;
        Room newRoom = new DoubleRoom(roomNumber, 100.00, 50.00);
        RoomDAO.addRoom(newRoom);
        Set<Integer> roomNumbers = RoomDAO.readRoomNumbers();
        assertTrue(roomNumbers.contains(roomNumber));
        RoomDAO.removeRoom(roomNumber);
        roomNumbers = RoomDAO.readRoomNumbers();
        assertFalse(roomNumbers.contains(roomNumber));
    }
}
