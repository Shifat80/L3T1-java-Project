/**
 * Represents a booking (either one-way or round-trip) in the airline booking system
 */
public class Booking {
    private static int nextBookingId = 1000;  // Starting point for booking IDs

    private int bookingId;
    private Flight outboundFlight;
    private Flight returnFlight; // null for one-way trips
    private boolean isRoundTrip;
    private String passengerName;

    /**
     * Constructor for a one-way booking
     */
    public Booking(Flight outboundFlight, String passengerName) {
        this.bookingId = generateBookingId();
        this.outboundFlight = outboundFlight;
        this.returnFlight = null;
        this.isRoundTrip = false;
        this.passengerName = passengerName;
    }    /**
     * Constructor for a round-trip booking
     */
    public Booking(Flight outboundFlight, Flight returnFlight, String passengerName) {
        this.bookingId = generateBookingId();
        this.outboundFlight = outboundFlight;
        this.returnFlight = returnFlight;
        this.isRoundTrip = true;
        this.passengerName = passengerName;
    }

    /**
     * Generates a unique booking ID
     */
    private int generateBookingId() {
        return nextBookingId++;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public Flight getOutboundFlight() {
        return outboundFlight;
    }

    public void setOutboundFlight(Flight outboundFlight) {
        this.outboundFlight = outboundFlight;
    }

    public Flight getReturnFlight() {
        return returnFlight;
    }

    public void setReturnFlight(Flight returnFlight) {
        this.returnFlight = returnFlight;
        this.isRoundTrip = (returnFlight != null);
    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    /**
     * Calculates the total price of the booking
     */
    public double getTotalPrice() {
        if (isRoundTrip) {
            return outboundFlight.getPrice() + returnFlight.getPrice();
        } else {
            return outboundFlight.getPrice();
        }
    }

    /**
     * Returns a string representation of the Booking
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Booking ID: ").append(bookingId).append(" | Passenger: ").append(passengerName).append("\n");
        sb.append("OUTBOUND: ").append(outboundFlight.toString()).append("\n");

        if (isRoundTrip) {
            sb.append("RETURN: ").append(returnFlight.toString()).append("\n");
        }

        sb.append(String.format("Total Price: $%.2f\n", getTotalPrice()));
        return sb.toString();
    }
}
