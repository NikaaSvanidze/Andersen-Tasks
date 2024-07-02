package TicketServices;

import ShareMethod.SharingInformation;
import Users.Admin;
import Users.Client;
import Users.User;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private List<Ticket> tickets;

    public TicketService() {
        this.tickets = new ArrayList<>();
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public static void main(String[] args) {
        //objects
        TicketService ticketService = new TicketService();
        Ticket ticketObject = new Ticket();
        TicketPrinter ticketPrinter = new TicketPrinter();
        SharingInformation sharingInformation = new SharingInformation();
        // Polymorphism example
        User clientUser = new Client();
        User adminUser = new Admin();


        // Empty TicketServices.Ticket
        Ticket emptyTicket = new Ticket();
        ticketService.addTicket(emptyTicket);

        // Full TicketServices.Ticket
        Ticket fullTicket = new Ticket(1, "Sphear", 111, System.currentTimeMillis(), System.currentTimeMillis(), true, 'C', 10.5,100.25);
        ticketService.addTicket(fullTicket);


        // Limited TicketServices.Ticket
        Ticket limitedTicket = new Ticket("Sphear", 122, System.currentTimeMillis());
        ticketService.addTicket(limitedTicket);

        // Homework4-task1 get and set id
        ticketObject.SetId(10);
        System.out.println("Get Id : " + ticketObject.GetId());

        // Homework4-Task2
        ticketPrinter.print("print content in console");

        // Homework4-Task3 Limited TicketServices.Ticket with stadiumsector and time
        Ticket limitedTicket2 = new Ticket('C',System.currentTimeMillis());
        ticketService.addTicket(limitedTicket2);

        // Homework4-task4 Share Method overload
        sharingInformation.share(55231532);

        // Homework4-Task4 Share with Phone and Email
        sharingInformation.share(55231532, "asdwdas@gmail.com");

        // HomeWork4-Task5 Example of method overriding and polymorphism
        clientUser.printRole();
        adminUser.printRole();

        // Unique methods for Client and Admin
        ((Client) clientUser).getTicket();
        ((Admin) adminUser).checkTicket();



        List<Ticket> tickets = ticketService.tickets;
        for(int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            System.out.println(ticket.toString());
        }
    }
}