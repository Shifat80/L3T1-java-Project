import java.util.ArrayList;
import java.util.List;

/**
 * Service class for managing flights
 */
public class FlightService {
    private List<Flight> availableFlights;

    /**
     * Constructor that initializes with sample flight data
     */
    public FlightService() {
        availableFlights = new ArrayList<>();
        initializeFlights();
    }

    /**
     * Initializes the system with sample flight data
     */
    private void initializeFlights() {
        // Adding sample flights
        availableFlights.add(new Flight("BG101", "Dhaka", "Chittagong", "08:00", "09:00", 50.00));
        availableFlights.add(new Flight("BG102", "Chittagong", "Dhaka", "10:00", "11:00", 50.00));
        availableFlights.add(new Flight("US-BD201", "Dhaka", "Sylhet", "09:30", "10:30", 45.00));
        availableFlights.add(new Flight("US-BD202", "Sylhet", "Dhaka", "12:00", "13:00", 45.00));
        availableFlights.add(new Flight("NOVOAIR301", "Dhaka", "Cox's Bazar", "07:45", "08:45", 60.00));
        availableFlights.add(new Flight("NOVOAIR302", "Cox's Bazar", "Dhaka", "15:00", "16:00", 60.00));
        availableFlights.add(new Flight("REGENT401", "Dhaka", "Jessore", "13:00", "13:45", 40.00));
        availableFlights.add(new Flight("REGENT402", "Jessore", "Dhaka", "17:00", "17:45", 40.00));
    }

    /**
     * Returns all available flights
     */
    public List<Flight> getAllFlights() {
        return availableFlights;
    }

    /**
     * Displays all available flights
     */
    public void displayAllFlights() {
        System.out.println("--------------------------------------------------------------");
        System.out.println("                    AVAILABLE FLIGHTS                         ");
        System.out.println("--------------------------------------------------------------");
        for (int i = 0; i < availableFlights.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), availableFlights.get(i));
        }
        System.out.println("--------------------------------------------------------------");
    }

    /**
     * Finds a flight by its index in the list
     */
    public Flight getFlightByIndex(int index) {
        if (index >= 1 && index <= availableFlights.size()) {
            return availableFlights.get(index - 1);
        }
        return null;
    }

    /**
     * Filters flights by origin
     */
    public List<Flight> getFlightsByOrigin(String origin) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : availableFlights) {
            if (flight.getOrigin().equalsIgnoreCase(origin)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    /**
     * Filters flights by destination
     */
    public List<Flight> getFlightsByDestination(String destination) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : availableFlights) {
            if (flight.getDestination().equalsIgnoreCase(destination)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    /**
     * Filters flights by both origin and destination
     */
    public List<Flight> getFlightsByRoute(String origin, String destination) {
        List<Flight> filteredFlights = new ArrayList<>();
        for (Flight flight : availableFlights) {
            if (flight.getOrigin().equalsIgnoreCase(origin) &&
                flight.getDestination().equalsIgnoreCase(destination)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

    /**
     * Displays flights filtered by route
     */
    public void displayFlightsByRoute(String origin, String destination) {
        List<Flight> filteredFlights = getFlightsByRoute(origin, destination);
        System.out.println("--------------------------------------------------------------");
        System.out.printf("          FLIGHTS FROM %s TO %s          \n", origin.toUpperCase(), destination.toUpperCase());
        System.out.println("--------------------------------------------------------------");
        if (filteredFlights.isEmpty()) {
            System.out.println("No flights found for this route.");
        } else {
            for (int i = 0; i < filteredFlights.size(); i++) {
                System.out.printf("%d. %s\n", (i + 1), filteredFlights.get(i));
            }
        }
        System.out.println("--------------------------------------------------------------");
    }
}