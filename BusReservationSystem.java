import java.util.Scanner;

class Seat {
    int number;
    boolean isBooked;

    Seat(int number) {
        this.number = number;
        this.isBooked = false;
    }
}

class Bus {
    int busId;
    String origin;
    String destination;
    int fare;
    int numSeats;
    Seat[] seats;

    Bus(int busId, String origin, String destination, int fare, int numSeats) {
        this.busId = busId;
        this.origin = origin;
        this.destination = destination;
        this.fare = fare;
        this.numSeats = numSeats;
        seats = new Seat[numSeats];
        for (int i = 0; i < numSeats; i++) {
            seats[i] = new Seat(i + 1);
        }
    }
}

public class BusReservationSystem {
    static final int MAX_BUSES = 10;
    static Bus[] buses = new Bus[MAX_BUSES];
    static int numBuses = 0;
    static Scanner sc = new Scanner(System.in);

    public static void displayBuses(String origin, String destination) {
        System.out.println("Available buses for " + origin + " to " + destination + " route:");
        int count = 0;
        for (int i = 0; i < numBuses; i++) {
            if (buses[i].origin.equalsIgnoreCase(origin) && buses[i].destination.equalsIgnoreCase(destination)) {
                System.out.println((count + 1) + ". Bus ID: " + buses[i].busId + ", Fare: " + buses[i].fare);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No buses found for " + origin + " to " + destination + " route.");
        }
    }

    public static void addBus() {
        if (numBuses == MAX_BUSES) {
            System.out.println("Maximum number of buses reached.");
            return;
        }
        System.out.print("Enter bus ID: ");
        int busId = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter origin: ");
        String origin = sc.nextLine();

        System.out.print("Enter destination: ");
        String destination = sc.nextLine();

        System.out.print("Enter fare: ");
        int fare = sc.nextInt();

        System.out.print("Enter number of seats: ");
        int numSeats = sc.nextInt();

        buses[numBuses++] = new Bus(busId, origin, destination, fare, numSeats);
        System.out.println("Bus added successfully.");
    }

    public static void bookSeat(int busId, int seatNumber) {
        for (int i = 0; i < numBuses; i++) {
            if (buses[i].busId == busId) {
                if (seatNumber < 1 || seatNumber > buses[i].numSeats) {
                    System.out.println("Invalid seat number.");
                    return;
                }
                if (buses[i].seats[seatNumber - 1].isBooked) {
                    System.out.println("Seat already booked.");
                    return;
                }
                buses[i].seats[seatNumber - 1].isBooked = true;
                System.out.println("Seat " + seatNumber + " booked successfully.");
                return;
            }
        }
        System.out.println("Bus not found.");
    }

    public static void cancelSeat(int busId, int seatNumber) {
        for (int i = 0; i < numBuses; i++) {
            if (buses[i].busId == busId) {
                if (seatNumber < 1 || seatNumber > buses[i].numSeats) {
                    System.out.println("Invalid seat number.");
                    return;
                }
                if (!buses[i].seats[seatNumber - 1].isBooked) {
                    System.out.println("Seat not booked.");
                    return;
                }
                buses[i].seats[seatNumber - 1].isBooked = false;
                System.out.println("Seat " + seatNumber + " cancelled successfully.");
                return;
            }
        }
        System.out.println("Bus not found.");
    }

    public static void displaySeats(int busId) {
        for (int i = 0; i < numBuses; i++) {
            if (buses[i].busId == busId) {
                System.out.println("Available seats for Bus ID " + busId + ":");
                for (int j = 0; j < buses[i].numSeats; j++) {
                    if (!buses[i].seats[j].isBooked) {
                        System.out.println("Seat " + buses[i].seats[j].number);
                    }
                }
                return;
            }
        }
        System.out.println("Bus not found.");
    }

    public static void displayBookedSeats(int busId) {
        for (int i = 0; i < numBuses; i++) {
            if (buses[i].busId == busId) {
                System.out.println("Booked seats for Bus ID " + busId + ":");
                for (int j = 0; j < buses[i].numSeats; j++) {
                    if (buses[i].seats[j].isBooked) {
                        System.out.println("Seat " + buses[i].seats[j].number);
                    }
                }
                return;
            }
        }
        System.out.println("Bus not found.");
    }

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\nEnter your choice:");
            System.out.println("1. Display available buses");
            System.out.println("2. Add a new bus");
            System.out.println("3. Book a seat");
            System.out.println("4. Cancel a seat");
            System.out.println("5. Display available seats for a bus");
            System.out.println("6. Display all booked seats for a bus");
            System.out.println("7. Exit");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter origin: ");
                    String origin = sc.nextLine();
                    System.out.print("Enter destination: ");
                    String destination = sc.nextLine();
                    displayBuses(origin, destination);
                    break;
                case 2:
                    addBus();
                    break;
                case 3:
                    System.out.print("Enter bus ID: ");
                    int busIdBook = sc.nextInt();
                    System.out.print("Enter seat number: ");
                    int seatNumberBook = sc.nextInt();
                    bookSeat(busIdBook, seatNumberBook);
                    break;
                case 4:
                    System.out.print("Enter bus ID: ");
                    int busIdCancel = sc.nextInt();
                    System.out.print("Enter seat number: ");
                    int seatNumberCancel = sc.nextInt();
                    cancelSeat(busIdCancel, seatNumberCancel);
                    break;
                case 5:
                    System.out.print("Enter bus ID: ");
                    int busIdSeats = sc.nextInt();
                    displaySeats(busIdSeats);
                    break;
                case 6:
                    System.out.print("Enter bus ID: ");
                    int busIdBookedSeats = sc.nextInt();
                    displayBookedSeats(busIdBookedSeats);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 7);
    }
}
