# Airline Booking System

## 📋 Project Overview

This is a terminal-based Airline Booking System implemented in Java. The application allows users to manage flight bookings with comprehensive CRUD (Create, Read, Update, Delete) functionalities, supporting both one-way and round-trip bookings.

![Java](https://img.shields.io/badge/Java-17-orange)
![Status](https://img.shields.io/badge/Status-Complete-green)
![License](https://img.shields.io/badge/License-MIT-blue)

## 🛫 Features

- **View Available Flights**: Display all available flights with details
- **Book One-Way Flights**: Create bookings for one-way journeys
- **Book Round-Trip Flights**: Create round-trip bookings with outbound and return flights
- **View Bookings**: Display all current bookings
- **Update Bookings**: Modify existing bookings (change outbound or return flights)
- **Cancel Bookings**: Remove bookings from the system
- **View Booking Statistics**: Display statistics about one-way and round-trip bookings

## 🧱 Project Structure

The project follows an object-oriented design with the following key classes:

### Core Classes

| Class | Description |
|-------|-------------|
| `Flight` | Represents a flight with details like flight number, origin, destination, times, and price |
| `Booking` | Represents a booking (one-way or round-trip) linked to flights and passenger |
| `FlightService` | Manages flight data and provides methods to search and retrieve flights |
| `BookingService` | Handles all booking operations including creation, retrieval, updates, and cancellations |
| `AirlineSystem` | Main class with the UI menu and program flow control |

### Class Relationships

- `AirlineSystem` uses `FlightService` and `BookingService` to perform operations
- `BookingService` manages a collection of `Booking` objects
- `Booking` contains references to `Flight` objects (one or two flights depending on trip type)
- `FlightService` manages a collection of `Flight` objects

## 🚀 How It Works

### Program Flow

1. The application starts by displaying a menu of available options to the user
2. User selects an action by entering a number corresponding to their choice
3. The system processes the request and shows the result
4. The menu is displayed again for further actions
5. This continues until the user chooses to exit

### Main Menu Options

```
Welcome to Airline Booking System ✈️

1. View All Flights
2. Book One-Way Flight
3. Book Round Trip
4. View My Bookings
5. Update a Booking
6. Cancel a Booking
7. View Booking Statistics
8. Exit
```

### Detailed Functionality

#### 1. View All Flights

Displays a list of all available flights with their details:
- Flight Number
- Origin and Destination
- Departure and Arrival Times
- Price

**Implementation Logic:**
- `AirlineSystem` calls `flightService.displayAllFlights()`
- `FlightService` iterates through its collection of flights and prints them

#### 2. Book One-Way Flight

Allows booking a one-way flight:

**Implementation Logic:**
- Displays all available flights
- User selects a flight by number
- User enters passenger name
- System validates inputs (flight selection, passenger name)
- Creates a new `Booking` object with the selected flight
- Increments one-way trip counter
- Returns booking confirmation with details

#### 3. Book Round Trip

Facilitates booking a round trip with separate outbound and return flights:

**Implementation Logic:**
- Displays all available flights
- User selects outbound flight
- System filters and displays potential return flights (from destination back to origin)
- User selects return flight
- User enters passenger name
- System validates all inputs
- Creates a new round-trip `Booking` with both flights
- Increments round-trip counter
- Returns booking confirmation with details

#### 4. View My Bookings

Shows all current bookings:

**Implementation Logic:**
- `AirlineSystem` calls `bookingService.displayAllBookings()`
- `BookingService` iterates through all bookings and displays each one

#### 5. Update a Booking

Allows modifying an existing booking:

**Implementation Logic:**
- Displays all bookings
- User selects a booking by ID
- System offers update options (outbound flight, return flight, or both)
- User selects flight(s) to change
- System applies changes and confirms the update

#### 6. Cancel a Booking

Removes a booking from the system:

**Implementation Logic:**
- Displays all bookings
- User selects a booking to cancel by ID
- System asks for confirmation
- If confirmed, removes booking from the collection
- Updates respective trip counter (one-way or round-trip)
- Confirms cancellation

#### 7. View Booking Statistics

Shows statistics about bookings:

**Implementation Logic:**
- `AirlineSystem` calls `bookingService.displayBookingStatistics()`
- `BookingService` displays counters for one-way trips, round trips, and total trips

## 💻 Error Handling

The system implements robust error handling to ensure stability:

- **Input Validation**: Validates all user inputs (flight selection, passenger name)
- **Exception Handling**: Try-catch blocks to gracefully handle exceptions
- **Defensive Programming**: Null checks and boundary validations

## 🔄 Data Management

- All data is stored in memory using `ArrayList` collections
- `Flight` data is pre-populated with sample flights
- `Booking` data is dynamically created and managed during runtime
- Unique booking IDs are auto-generated for each new booking

## 🧮 Counters and Statistics

The system maintains counters to track:
- Number of one-way bookings
- Number of round-trip bookings

These counters are:
- Incremented when new bookings are created
- Decremented when bookings are canceled
- Used to generate booking statistics

## 🛠️ How to Run

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE)

### Compilation and Execution

```bash
# Compile the Java files
cd e:/java/AirlineBookingSystem
javac -d bin src/*.java

# Run the application
java -cp bin AirlineSystemNew
```

## 📝 Future Enhancements

Potential improvements for future versions:
- Persistent data storage using files or a database
- User authentication and multiple user profiles
- Flight search by date, price, or duration
- Seat selection functionality
- Payment processing simulation
- Email confirmation for bookings

## 📜 License

This project is released under the MIT License. See the LICENSE file for details.

## 👥 Contributors

Created by Mehedi Hasan