package BootApp;

import Controllers.TicketController;
import Db.TicketDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketController.class, args);
    }
}
