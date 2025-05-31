import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing bookings
 */
public class BookingService {
    private List<Booking> bookings;

    /**
     * Constructor for BookingService
     */
    public BookingService() {
        this.bookings = new ArrayList<>();
    }

    /**
     * Creates a new one-way booking
     */
    public Booking createOneWayBooking(Flight outboundFlight, String passengerName) {
        Booking booking = new Booking(outboundFlight, passengerName);
        bookings.add(booking);
        return booking;
    }

    /**
     * Creates a new round-trip booking
     */
    public Booking createRoundTripBooking(Flight outboundFlight, Flight returnFlight, String passengerName) {
        Booking booking = new Booking(outboundFlight, returnFlight, passengerName);
        bookings.add(booking);
        return booking;
    }

    /**
     * Gets all bookings
     */
    public List<Booking> getAllBookings() {
        return bookings;
    }

    /**
     * Displays all bookings
     */
    public void displayAllBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("======== YOUR BOOKINGS ========");
        for (Booking booking : bookings) {
            System.out.println(booking);
            System.out.println("-----------------------------");
        }
    }

    /**
     * Finds a booking by ID
     */
    public Booking findBookingById(int bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId() == bookingId) {
                return booking;
            }
        }
        return null;
    }

    /**
     * Updates an existing booking
     */
    public boolean updateBooking(int bookingId, Flight newOutboundFlight, Flight newReturnFlight) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            booking.setOutboundFlight(newOutboundFlight);
            booking.setReturnFlight(newReturnFlight);
            return true;
        }
        return false;
    }

    /**
     * Updates only the outbound flight of a booking
     */
    public boolean updateOutboundFlight(int bookingId, Flight newOutboundFlight) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            booking.setOutboundFlight(newOutboundFlight);
            return true;
        }
        return false;
    }

    /**
     * Updates only the return flight of a booking
     */
    public boolean updateReturnFlight(int bookingId, Flight newReturnFlight) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            booking.setReturnFlight(newReturnFlight);
            return true;
        }
        return false;
    }

    /**
     * Cancels (deletes) a booking
     */
    public boolean cancelBooking(int bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            bookings.remove(booking);
            return true;
        }
        return false;
    }
}
