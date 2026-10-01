class TicketBooking {
    private int tickets = 5;
    public synchronized void bookTicket(String customer, int number) {
        if (tickets >= number) {
            System.out.println(customer + " is booking " + number + " ticket(s).");
            tickets -= number;
            System.out.println("Booking successful for " + customer);
            System.out.println("Remaining tickets: " + tickets);
            System.out.println();
        } else {
            System.out.println(customer + " - Booking failed. Not enough tickets.");
            System.out.println("Remaining tickets: " + tickets);
            System.out.println();
        }
    }
}

class Customer extends Thread {
    private TicketBooking booking;
    private int numberOfTickets;

    Customer(TicketBooking booking, String name, int numberOfTickets) {
        super(name);
        this.booking = booking;
        this.numberOfTickets = numberOfTickets;
    }
    public void run() {
        booking.bookTicket(getName(), numberOfTickets);
    }
}

public class Main {
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
        System.out.println("All booking transactions completed.");
    }
}
