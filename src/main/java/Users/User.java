package Users;

import TicketServices.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {
    protected String role;
    private int id;
    private String name;
    private LocalDateTime creationDate;
    private List<Ticket> tickets; // One-to-many relationship

    public void printRole() {
        System.out.println("Role: " + role);
    }

    // Constructors
    public User(){

    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.tickets = new ArrayList<>();
    }

    public User(String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }
}
