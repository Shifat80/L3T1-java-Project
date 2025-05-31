import java.util.List;
import java.util.Scanner;

/**
 * Main class for the Airline Booking System
 */
public class AirlineSystem{
    private static Scanner scanner = new Scanner(System.in);
    private static FlightService flightService = new FlightService();
    private static BookingService bookingService = new BookingService();

    public static void main(String[] args) {
        boolean exit = false;

        System.out.println("Welcome to Airline Booking System ✈️");

        while (!exit) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    viewAllFlights();
                    break;
                case 2:
                    bookOneWayFlight();
                    break;
                case 3:
                    bookRoundTrip();
                    break;
                case 4:
                    viewMyBookings();
                    break;
                case 5:
                    updateBooking();
                    break;
                case 6:
                    cancelBooking();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Thank you for using Airline Booking System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    /**
     * Displays the main menu
     */
    private static void displayMainMenu() {
        System.out.println("\nWelcome to Airline Booking System ✈️");
        System.out.println("\n1. View All Flights");
        System.out.println("2. Book One-Way Flight");
        System.out.println("3. Book Round Trip");
        System.out.println("4. View My Bookings");
        System.out.println("5. Update a Booking");
        System.out.println("6. Cancel a Booking");
        System.out.println("7. Exit");
    }

    /**
     * Gets an integer input from the user
     */
    private static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            System.out.print(prompt);
            scanner.next(); // Consume the invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume the newline
        return input;
    }

    /**
     * Gets a string input from the user
     */
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Shows all available flights
     */
    private static void viewAllFlights() {
        flightService.displayAllFlights();
    }

    /**
     * Books a one-way flight
     */
    private static void bookOneWayFlight() {
        viewAllFlights();

        int flightIndex = getIntInput("Select a flight number: ");
        Flight selectedFlight = flightService.getFlightByIndex(flightIndex);

        if (selectedFlight == null) {
            System.out.println("Invalid flight selection.");
            return;
        }

        String passengerName = getStringInput("Enter passenger name: ");

        Booking booking = bookingService.createOneWayBooking(selectedFlight, passengerName);
        System.out.println("\nBooking confirmed!");
        System.out.println(booking);
    }

    /**
     * Books a round trip
     */
    private static void bookRoundTrip() {
        System.out.println("=== BOOKING ROUND TRIP ===");

        // Selecting outbound flight
        System.out.println("\n=== SELECT OUTBOUND FLIGHT ===");
        viewAllFlights();

        int outboundFlightIndex = getIntInput("Select outbound flight number: ");
        Flight outboundFlight = flightService.getFlightByIndex(outboundFlightIndex);

        if (outboundFlight == null) {
            System.out.println("Invalid flight selection.");
            return;
        }

        // Selecting return flight
        System.out.println("\n=== SELECT RETURN FLIGHT ===");
        // Displaying flights from the destination back to the origin
        List<Flight> returnFlights = flightService.getFlightsByRoute(
            outboundFlight.getDestination(), outboundFlight.getOrigin());

        if (returnFlights.isEmpty()) {
            System.out.println("No return flights available for your selected route.");
            return;
        }

        System.out.println("--------------------------------------------------------------");
        System.out.printf("          RETURN FLIGHTS FROM %s TO %s          \n",
                        outboundFlight.getDestination().toUpperCase(),
                        outboundFlight.getOrigin().toUpperCase());
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < returnFlights.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), returnFlights.get(i));
        }
        System.out.println("--------------------------------------------------------------");

        int returnFlightChoice = getIntInput("Select return flight number: ");
        if (returnFlightChoice < 1 || returnFlightChoice > returnFlights.size()) {
            System.out.println("Invalid flight selection.");
            return;
        }

        Flight returnFlight = returnFlights.get(returnFlightChoice - 1);

        String passengerName = getStringInput("Enter passenger name: ");

        Booking booking = bookingService.createRoundTripBooking(outboundFlight, returnFlight, passengerName);
        System.out.println("\nRound trip booking confirmed!");
        System.out.println(booking);
    }

    /**
     * View all user bookings
     */
    private static void viewMyBookings() {
        bookingService.displayAllBookings();
    }

    /**
     * Update an existing booking
     */
    private static void updateBooking() {
        viewMyBookings();

        if (bookingService.getAllBookings().isEmpty()) {
            return;
        }

        int bookingId = getIntInput("Enter the Booking ID you want to update: ");
        Booking booking = bookingService.findBookingById(bookingId);

        if (booking == null) {
            System.out.println("Booking not found with ID: " + bookingId);
            return;
        }

        System.out.println("\nWhat would you like to update?");
        System.out.println("1. Outbound Flight");
        if (booking.isRoundTrip()) {
            System.out.println("2. Return Flight");
            System.out.println("3. Both Flights");
        }
        System.out.println("0. Cancel");

        int updateChoice = getIntInput("Enter your choice: ");

        switch (updateChoice) {
            case 0:
                System.out.println("Update cancelled.");
                break;

            case 1:
                // Update outbound flight
                viewAllFlights();
                int newOutboundIndex = getIntInput("Select new outbound flight: ");
                Flight newOutboundFlight = flightService.getFlightByIndex(newOutboundIndex);

                if (newOutboundFlight == null) {
                    System.out.println("Invalid flight selection.");
                    return;
                }

                if (bookingService.updateOutboundFlight(bookingId, newOutboundFlight)) {
                    System.out.println("Booking updated successfully.");
                    System.out.println(booking);
                } else {
                    System.out.println("Failed to update booking.");
                }
                break;

            case 2:
                if (booking.isRoundTrip()) {
                    // Update return flight
                    viewAllFlights();
                    int newReturnIndex = getIntInput("Select new return flight: ");
                    Flight newReturnFlight = flightService.getFlightByIndex(newReturnIndex);

                    if (newReturnFlight == null) {
                        System.out.println("Invalid flight selection.");
                        return;
                    }

                    if (bookingService.updateReturnFlight(bookingId, newReturnFlight)) {
                        System.out.println("Booking updated successfully.");
                        System.out.println(booking);
                    } else {
                        System.out.println("Failed to update booking.");
                    }
                } else {
                    System.out.println("Invalid choice for a one-way booking.");
                }
                break;

            case 3:
                if (booking.isRoundTrip()) {
                    // Update both flights
                    viewAllFlights();
                    int newOutIndex = getIntInput("Select new outbound flight: ");
                    Flight newOutFlight = flightService.getFlightByIndex(newOutIndex);

                    if (newOutFlight == null) {
                        System.out.println("Invalid flight selection.");
                        return;
                    }

                    viewAllFlights();
                    int newRetIndex = getIntInput("Select new return flight: ");
                    Flight newRetFlight = flightService.getFlightByIndex(newRetIndex);

                    if (newRetFlight == null) {
                        System.out.println("Invalid flight selection.");
                        return;
                    }

                    if (bookingService.updateBooking(bookingId, newOutFlight, newRetFlight)) {
                        System.out.println("Booking updated successfully.");
                        System.out.println(booking);
                    } else {
                        System.out.println("Failed to update booking.");
                    }
                } else {
                    System.out.println("Invalid choice for a one-way booking.");
                }
                break;

            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Cancel a booking
     */
    private static void cancelBooking() {
        viewMyBookings();

        if (bookingService.getAllBookings().isEmpty()) {
            return;
        }

        int bookingId = getIntInput("Enter the Booking ID you want to cancel: ");

        System.out.print("Are you sure you want to cancel this booking? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("y") || confirmation.equals("yes")) {
            if (bookingService.cancelBooking(bookingId)) {
                System.out.println("Booking successfully cancelled.");
            } else {
                System.out.println("Booking not found with ID: " + bookingId);
            }
        } else {
            System.out.println("Cancellation aborted.");
        }
    }
}
