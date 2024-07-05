package Users;

import java.time.LocalDateTime;

public class User {
    protected String role;
    private int id;
    private String name;
    private LocalDateTime creationDate;

    public void printRole() {
        System.out.println("Role: " + role);
    }

    // Constructors
    public User() {
    }

    public User(String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
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

}
