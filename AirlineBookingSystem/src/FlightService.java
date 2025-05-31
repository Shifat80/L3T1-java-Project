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
        availableFlights.add(new Flight("AA100", "New York", "Los Angeles", "09:00", "12:30", 299.99));
        availableFlights.add(new Flight("AA101", "Los Angeles", "New York", "14:00", "22:30", 349.99));
        availableFlights.add(new Flight("DL200", "Chicago", "Miami", "08:15", "11:45", 259.99));
        availableFlights.add(new Flight("DL201", "Miami", "Chicago", "13:30", "16:00", 279.99));
        availableFlights.add(new Flight("UA300", "San Francisco", "Seattle", "10:30", "12:45", 199.99));
        availableFlights.add(new Flight("UA301", "Seattle", "San Francisco", "15:00", "17:15", 189.99));
        availableFlights.add(new Flight("BA400", "London", "Paris", "07:45", "09:15", 159.99));
        availableFlights.add(new Flight("BA401", "Paris", "London", "11:00", "12:30", 149.99));
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
