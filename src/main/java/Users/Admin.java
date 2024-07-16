package Users;

public class Admin extends User {
    public Admin() {
        role = "Admin";
    }
    public void checkTicket() {
        System.out.println("Admin is checking a ticket.");
    }
}
