import java.util.Scanner;

// Node class to represent Ticket
class TicketNode {
    // Attributes
    int ticketID;
    String customerName;
    String movieName;
    String seatNumber;
    String bookingTime;
    TicketNode next;

    // Constructor
    TicketNode(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketID = ticketID;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime;
        this.next = null;
    }
}

// Circular linked list for tickets management
class TicketLinkedList {
    // Attributes
    private TicketNode head = null;
    private TicketNode tail = null;
    private int count = 0;

    // Add new ticket reservation
    public void addTicket(int ticketID, String customerName, String movieName, String seatNumber, String bookingTime) {
        TicketNode newTicket = new TicketNode(ticketID, customerName, movieName, seatNumber, bookingTime);
        if (head==null) {
            head = newTicket;
            tail = newTicket;
            tail.next = head; // Circular linking
        }
        else {
            tail.next = newTicket;
            tail = newTicket;
            tail.next = head; // Maintain circular link
        }
        count++;
        System.out.println("Ticket booked successfully! Ticket ID: " + ticketID);
    }

    // Remove a ticket by Ticket ID
    public void removeTicket(int ticketID) {
        if (head==null) {
            System.out.println("No tickets to remove!");
            return;
        }

        TicketNode current = head, prev = null;

        // Special case: If the ticket to remove is the head
        if (head.ticketID==ticketID) {
            if (head==tail) { // If only one node exists
                head = tail = null;
            } else {
                head = head.next;
                tail.next = head; // Maintain circular link
            }
            count--;
            System.out.println("Ticket " + ticketID + " removed successfully!");
            return;
        }

        // Traverse the list to find the ticket
        do {
            prev = current;
            current = current.next;
            if (current.ticketID==ticketID) {
                prev.next = current.next;
                if (current==tail) {
                    tail = prev; // Update tail if last ticket is removed
                }
                count--;
                System.out.println("Ticket " + ticketID + " removed successfully!");
                return;
            }
        } while (current!=head);

        System.out.println("Ticket ID not found!");
    }

    // Display all tickets
    public void displayTickets() {
        if (head==null) {
            System.out.println("No tickets booked yet!");
            return;
        }

        TicketNode temp = head;
        System.out.println("\nBooked Tickets:");
        do {
            System.out.println("Ticket ID: " + temp.ticketID + ", Customer: " + temp.customerName + ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
            temp = temp.next;
        } while (temp!=head);
    }

    // Search ticket by Customer Name or Movie Name
    public void searchTicket(String searchKey) {
        if (head==null) {
            System.out.println("No tickets booked yet!");
            return;
        }

        TicketNode temp = head;
        boolean found = false;
        do {
            if (temp.customerName.equalsIgnoreCase(searchKey) || temp.movieName.equalsIgnoreCase(searchKey)) {
                System.out.println("Found Ticket -> ID: " + temp.ticketID + ", Customer: " + temp.customerName +
                        ", Movie: " + temp.movieName + ", Seat: " + temp.seatNumber + ", Time: " + temp.bookingTime);
                found = true;
            }
            temp = temp.next;
        } while (temp!=head);

        if (!found) {
            System.out.println("No ticket found for " + searchKey);
        }
    }

    // Get total number of booked tickets
    public void getTotalTickets() {
        System.out.println("Total Tickets Booked: " + count);
    }
}

// Main class
public class TicketReservationSystem {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Create a list of tickets
        TicketLinkedList tickets = new TicketLinkedList();

        // loop to take user input
        while(true) {
            System.out.println("\n1. Book Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. View All Tickets");
            System.out.println("4. Search Ticket");
            System.out.println("5. Total Tickets Count");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch(choice) {
                case 1:
                    System.out.print("Enter Ticket ID: ");
                    int id = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.print("Enter Customer Name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Movie Name: ");
                    String movie = input.nextLine();
                    System.out.print("Enter Seat Number: ");
                    String seat = input.nextLine();
                    System.out.print("Enter Booking Time: ");
                    String time = input.nextLine();
                    tickets.addTicket(id, name, movie, seat, time);
                    break;

                case 2:
                    System.out.print("Enter Ticket ID to cancel: ");
                    int ticketID = input.nextInt();
                    tickets.removeTicket(ticketID);
                    break;

                case 3:
                    tickets.displayTickets();
                    break;

                case 4:
                    System.out.print("Enter Customer Name or Movie Name to search: ");
                    String searchKey = input.nextLine();
                    tickets.searchTicket(searchKey);
                    break;

                case 5:
                    tickets.getTotalTickets();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    input.close();
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
// Sample Output ->
// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 1
// Enter Ticket ID: 101
// Enter Customer Name: Rajesh
// Enter Movie Name: Avengers
// Enter Seat Number: A10
// Enter Booking Time: 6:30 PM
// Ticket booked successfully! Ticket ID: 101

// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 1
// Enter Ticket ID: 102
// Enter Customer Name: Priya
// Enter Movie Name: Inception
// Enter Seat Number: B5
// Enter Booking Time: 7:00 PM
// Ticket booked successfully! Ticket ID: 102

// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 3

// Booked Tickets:
// Ticket ID: 101, Customer: Rajesh, Movie: Avengers, Seat: A10, Time: 6:30 PM
// Ticket ID: 102, Customer: Priya, Movie: Inception, Seat: B5, Time: 7:00 PM

// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 4
// Enter Customer Name or Movie Name to search: Priya
// Found Ticket -> ID: 102, Customer: Priya, Movie: Inception, Seat: B5, Time: 7:00 PM

// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 2
// Enter Ticket ID to cancel: 101
// Ticket 101 removed successfully!

// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 5
// Total Tickets Booked: 1

// 1. Book Ticket
// 2. Cancel Ticket
// 3. View All Tickets
// 4. Search Ticket
// 5. Total Tickets Count
// 6. Exit
// Enter your choice: 6
// Exiting..