/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignment2;

import java.time.LocalDate;
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
public class HotelTest {

    private Hotel hotel;

    public HotelTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        hotel = new Hotel();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of findNextBookingNumber method, of class Hotel.
     */
    @Test
    public void testFindNextBookingNumber() {
        System.out.println("findNextBookingNumber");
        hotel.readBookings();
        int nextNumber = hotel.findNextBookingNumber();
        assertTrue(nextNumber > 0);
    }

    /**
     * Test of makeBooking method, of class Hotel.
     */
    @Test
    public void testMakeBooking() {
        System.out.println("makeBooking");
        hotel.readBookings();
        int bookingNumber = hotel.findNextBookingNumber();
        Guest guest = new Guest("Jena", "Jeong", "0212468788", 7777);
        Booking newBooking = new Booking(bookingNumber, LocalDate.parse("2025-07-20"), LocalDate.parse("2025-07-21"), 1, 1, guest);
        hotel.makeBooking(newBooking);
        Booking booking = BookingDAO.getBooking(bookingNumber);
        assertNotNull(booking);  
        assertEquals(newBooking.getBookingNumber(), booking.getBookingNumber());
        assertEquals(newBooking.getCheckInDate(), booking.getCheckInDate());
        assertEquals(newBooking.getCheckOutDate(), booking.getCheckOutDate());
        assertEquals(newBooking.getRoomNumber(), booking.getRoomNumber());
        assertEquals(newBooking.getNumberOfGuests(), booking.getNumberOfGuests());
        assertEquals(newBooking.getGuest().getFirstName(), booking.getGuest().getFirstName());
        assertEquals(newBooking.getGuest().getLastName(), booking.getGuest().getLastName());
        assertEquals(newBooking.getGuest().getPhone(), booking.getGuest().getPhone());
        BookingDAO.removeBooking(bookingNumber);
    }

    /**
     * Test of getRooms method, of class Hotel.
     */
    @Test
    public void testGetRooms() {
        System.out.println("getRooms");
        hotel.readRooms();
        assertNotNull(hotel.getRooms());  
    }

    /**
     * Test of getBookings method, of class Hotel.
     */
    @Test
    public void testGetBookings() {
        System.out.println("getBookings");
        hotel.readBookings();
        assertNotNull(hotel.getBookings());   
    }
}
