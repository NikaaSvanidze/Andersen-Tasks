package Users;

public class Client extends User {
    public Client() {
        role = "Client";
    }

    public void getTicket() {
        System.out.println("Client is getting a ticket.");
    }
}
