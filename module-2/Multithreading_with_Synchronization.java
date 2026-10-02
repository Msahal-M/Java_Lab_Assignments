/*
5. Multithreading with Synchronization
Develop a Java program that simulates a ticket booking system.
Create multiple threads representing customers attempting to book tickets
from the same limited ticket pool. Use synchronization to ensure that two
customers cannot book the same ticket. Display the booking details and
remaining number of tickets after each transaction.
*/

class TicketBooking {
    int tickets = 5;

    public synchronized void bookTicket(String name, int number) {
        if (tickets >= number) {
            System.out.println(name + " booked " + number + " ticket(s)");

            tickets = tickets - number;

            System.out.println("Remaining tickets: " + tickets);
            System.out.println();
        }
        else {
            System.out.println(name + " - Booking failed");
            System.out.println("Not enough tickets available");
            System.out.println("Remaining tickets: " + tickets);
            System.out.println();
        }
    }
}
class Customer extends Thread {

    TicketBooking booking;
    int number;
    Customer(TicketBooking booking, String name, int number) {
        super(name);
        this.booking = booking;
        this.number = number;
    }
    public void run() {
        booking.bookTicket(getName(), number);
    }
}

public class Multithreading_with_Synchronization {
    public static void main(String[] args) throws InterruptedException {

        TicketBooking booking = new TicketBooking();
        Customer c1 = new Customer(booking, "Customer 1", 2);
        Customer c2 = new Customer(booking, "Customer 2", 2);
        Customer c3 = new Customer(booking, "Customer 3", 2);
        c1.start();
        c2.start();
        c3.start();
        c1.join();
        c2.join();
        c3.join();
        System.out.println("All bookings completed.");
    }
}
