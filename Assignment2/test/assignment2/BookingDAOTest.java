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
public class BookingDAOTest {

    public BookingDAOTest() {
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
     * Test of readBookingNumbers method, of class BookingDAO.
     */
    @Test
    public void testReadBookingNumbers() {
        System.out.println("readBookingNumbers");
        Set<Integer> bookingNumbers = BookingDAO.readBookingNumbers();
        assertNotNull(bookingNumbers);  
    }

    /**
     * Test of getBooking method, of class BookingDAO.
     */
    @Test
    public void testGetBooking() {
        System.out.println("getBooking");
        Hotel hotel = new Hotel();
        hotel.readBookings();
        int bookingNumber = hotel.findNextBookingNumber();
        Guest guest = new Guest("Jena", "Jeong", "0212468788", 7777);
        Booking newBooking = new Booking(bookingNumber, LocalDate.parse("2025-07-20"), LocalDate.parse("2025-07-21"), 1, 1, guest);
        BookingDAO.addBooking(newBooking);
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
     * Test of readBookings method, of class BookingDAO.
     */
    @Test
    public void testReadBookings() {
        System.out.println("readBookings");
        Map<Integer, Booking> bookings = BookingDAO.readBookings();
        assertNotNull(bookings);  
    }

    /**
     * Test of addBooking method, of class BookingDAO.
     */
    @Test
    public void testAddBooking() {
        System.out.println("addBooking");
        Hotel hotel = new Hotel();
        hotel.readBookings();
        int bookingNumber = hotel.findNextBookingNumber();
        Guest guest = new Guest("Jena", "Jeong", "0212468788", 7777);
        Booking newBooking = new Booking(bookingNumber, LocalDate.parse("2025-07-20"), LocalDate.parse("2025-07-21"), 1, 1, guest);
        BookingDAO.addBooking(newBooking);
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
     * Test of removeBooking method, of class BookingDAO.
     */
    @Test
    public void testRemoveBooking() {
        System.out.println("removeBooking");
        Hotel hotel = new Hotel();
        hotel.readBookings();
        int bookingNumber = hotel.findNextBookingNumber();
        Guest guest = new Guest("Jena", "Jeong", "0212468788", 7777);
        Booking newBooking = new Booking(bookingNumber, LocalDate.parse("2025-07-20"), LocalDate.parse("2025-07-21"), 1, 1, guest);
        BookingDAO.addBooking(newBooking);
        Set<Integer> bookingNumbers = BookingDAO.readBookingNumbers();
        assertTrue(bookingNumbers.contains(bookingNumber));
        BookingDAO.removeBooking(bookingNumber);
        bookingNumbers = BookingDAO.readBookingNumbers();
        assertFalse(bookingNumbers.contains(bookingNumber));
    }
}
