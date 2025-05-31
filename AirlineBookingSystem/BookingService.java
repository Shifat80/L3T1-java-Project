import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing bookings
 */
public class BookingService {
    private List<Booking> bookings;
    private int oneWayTripsCount;    // Counter for one-way trips
    private int roundTripsCount;     // Counter for round trips

    /**
     * Constructor for BookingService
     */
    public BookingService() {
        this.bookings = new ArrayList<>();
        this.oneWayTripsCount = 0;
        this.roundTripsCount = 0;
    }    /**
     * Creates a new one-way booking
     * @throws IllegalArgumentException if flight or passenger details are invalid
     */
    public Booking createOneWayBooking(Flight outboundFlight, String passengerName) {
        // Input validation with exception handling
        if (outboundFlight == null) {
            throw new IllegalArgumentException("Outbound flight cannot be null");
        }
        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty");
        }

        Booking booking = new Booking(outboundFlight, passengerName);
        bookings.add(booking);
        oneWayTripsCount++; // Increment the one-way trips counter
        return booking;
    }

    /**
     * Creates a new round-trip booking
     * @throws IllegalArgumentException if flight or passenger details are invalid
     */
    public Booking createRoundTripBooking(Flight outboundFlight, Flight returnFlight, String passengerName) {
        // Input validation with exception handling
        if (outboundFlight == null || returnFlight == null) {
            throw new IllegalArgumentException("Both outbound and return flights must be specified");
        }
        if (passengerName == null || passengerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Passenger name cannot be empty");
        }

        Booking booking = new Booking(outboundFlight, returnFlight, passengerName);
        bookings.add(booking);
        roundTripsCount++; // Increment the round trips counter
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
    }    /**
     * Cancels (deletes) a booking
     */
    public boolean cancelBooking(int bookingId) {
        Booking booking = findBookingById(bookingId);
        if (booking != null) {
            // Update counters when canceling a booking
            if (booking.isRoundTrip()) {
                roundTripsCount--;
            } else {
                oneWayTripsCount--;
            }
            bookings.remove(booking);
            return true;
        }
        return false;
    }

    /**
     * Gets the count of one-way trips
     */
    public int getOneWayTripsCount() {
        return oneWayTripsCount;
    }

    /**
     * Gets the count of round trips
     */
    public int getRoundTripsCount() {
        return roundTripsCount;
    }

    /**
     * Gets the total number of trips (one-way + round)
     */
    public int getTotalTripsCount() {
        return oneWayTripsCount + roundTripsCount;
    }

    /**
     * Displays statistics about bookings
     */
    public void displayBookingStatistics() {
        System.out.println("\n======== BOOKING STATISTICS ========");
        System.out.println("One-Way Trips: " + oneWayTripsCount);
        System.out.println("Round Trips: " + roundTripsCount);
        System.out.println("Total Trips: " + getTotalTripsCount());
        System.out.println("====================================");
    }}