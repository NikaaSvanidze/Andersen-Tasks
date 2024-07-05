package Validation;


import BusTickets.BusTicket;
import BusTickets.TicketType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TicketValidator
{
    private static final String[] VALID_TICKET_TYPES = {"DAY", "WEEK", "MONTH", "YEAR"};
    public void validateTicketsFromFile(String filePath) {
        List<BusTicket> tickets = readTicketsFromFile(filePath);
        validateTickets(tickets);
    }

    private boolean isValidTicketType(String ticketType) {
        //Task 3
        for (String validType : VALID_TICKET_TYPES) {
            if (validType.equalsIgnoreCase(ticketType)) {
                return true;
            }
        }
        return false;
    }

    private List<BusTicket> readTicketsFromFile(String filePath) {
        List<BusTicket> tickets = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String ticketTypeStr = parts[0].trim();
                    String startDate = parts[1].trim();
                    String priceStr = parts[2].trim();

                    // Parse ticket type
                    TicketType ticketType = TicketType.valueOf(ticketTypeStr);

                    // Parse price
                    double price = Double.parseDouble(priceStr);

                    // Create ticket object and add to list
                    tickets.add(new BusTicket(ticketType, startDate, price));
                } else {
                    System.out.println("Skipping invalid line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (IllegalArgumentException | NullPointerException e) {
            System.err.println("Error parsing ticket data: " + e.getMessage());
        }
        return tickets;
    }
    public void validateTickets(List<BusTicket> tickets) {
        int totalTickets = tickets.size();
        int validTickets = 0;
        int startDateViolations = 0;
        int priceZeroViolations = 0;
        TicketType mostPopularViolation = null;

        for (BusTicket ticket : tickets) {
            try {
                validateTicket(ticket);
                validTickets++;
            } catch (IllegalArgumentException e) {
                if (e.getMessage().equals("Start date must be specified for DAY, WEEK, and YEAR tickets")) {
                    startDateViolations++;
                } else if (e.getMessage().equals("Price cannot be zero")) {
                    priceZeroViolations++;
                }
            }
        }

        System.out.println("Total = " + totalTickets);
        System.out.println("Valid = " + validTickets);
        if (startDateViolations > priceZeroViolations) {
            mostPopularViolation = TicketType.DAY;
        } else {
            mostPopularViolation = TicketType.PRICE;
        }
        System.out.println("Most popular violation = " + mostPopularViolation);
    }

    private void validateTicket(BusTicket ticket) {
        if (ticket.getType() == TicketType.DAY || ticket.getType() == TicketType.WEEK || ticket.getType() == TicketType.YEAR) {
            if (ticket.getStartDate() == null || ticket.getStartDate().isEmpty()) {
                throw new IllegalArgumentException("Start date must be specified for DAY, WEEK, and YEAR tickets");
            }
        }
        //Task 2 Validate Start date not in future
        try {
            LocalDate startDate = LocalDate.parse(ticket.getStartDate());
            if (startDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Start date cannot be in the future");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for start date");
        }

        if (ticket.getPrice() == 0.0) {
            throw new IllegalArgumentException("Price cannot be zero");
        }
        // Task4 Validate price is even
        if ((int) ticket.getPrice() % 2 != 0) {
            throw new IllegalArgumentException("Price must be even");
        }
    }

    public static void main(String[] args) {
        // Example usage
        BusTicket ticket1 = new BusTicket(TicketType.DAY, "2024-06-18", 10.5);
        BusTicket ticket2 = new BusTicket(TicketType.WEEK, "", 0.0); // Empty string for startDate
        BusTicket ticket3 = new BusTicket(TicketType.YEAR, "2024-06-18", 25.0);

        TicketValidator validator = new TicketValidator();
        validator.validateTickets(List.of(ticket1, ticket2, ticket3));
        //Task 1
//        String filePath = ""; // Replace with your file path
//        TicketValidator fileValidator = new TicketValidator();
//        validator.validateTicketsFromFile(filePath);
    }
}
