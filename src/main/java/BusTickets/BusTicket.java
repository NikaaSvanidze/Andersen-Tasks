package BusTickets;

public class BusTicket {
    private TicketType type;
    private String startDate; // Changed to String
    private double price;

    // Constructor
    public BusTicket(TicketType type, String startDate, double price) {
        this.type = type;
        this.startDate = startDate;
        this.price = price;
    }

    // Getters
    public TicketType getType() {
        return type;
    }

    public String getStartDate() {
        return startDate;
    }

    public double getPrice() {
        return price;
    }
}
