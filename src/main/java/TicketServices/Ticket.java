package TicketServices;

import BusTickets.TicketType;
import Interfaces.IGetAndSetInterface;


import java.time.Instant;

public class Ticket implements IGetAndSetInterface{
    private int id;
    private String concertHall;
    private int eventCode;
    private long time;
    private long creationTime;
    private boolean isPromo;
    private char stadiumSector;
    private double maxAllowedBackpackWeight;
    private double ticketPrice;
    private int userId;
    private TicketType ticketType;

    //Get and Set interface methods
    @Override
    public int GetId() {
        return id;
    }

    @Override
    public void SetId(int id) {
        this.id = id;
    }
    // Constructors
    public Ticket(){

    }

    public Ticket(int id, String concertHall, int eventCode, long time, long creationTime, boolean isPromo, char stadiumSector, double maxAllowedBackpackWeight, double ticketPrice) {
        if (id / 1000 > 9) {
            throw new IllegalArgumentException("id should be less than 4 digits");
        }
        if (concertHall.length() >= 10) {
            throw new IllegalArgumentException("max chars of concertHall should be les than 10");
        }
        if (eventCode / 100 > 9) {
            throw new IllegalArgumentException("event code should be less than 4 digits");
        }
        if (stadiumSector > 'C' || stadiumSector < 'A') {
            throw new IllegalArgumentException("sector should be in between of A to C");
        }
        this.id = id;
        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
        this.creationTime = creationTime;
        this.isPromo = isPromo;
        this.stadiumSector = stadiumSector;
        this.maxAllowedBackpackWeight = maxAllowedBackpackWeight;
        this.ticketPrice = ticketPrice; // Initialize ticket price
    }

    public Ticket(String concertHall, int eventCode, long time) {
        if (eventCode / 100 > 9) {
            throw new IllegalArgumentException("event code should be less than 4 digits");
        }
        if (concertHall.length() >= 10) {
            throw new IllegalArgumentException("max chars of concertHall should be les than 10");
        }

        this.concertHall = concertHall;
        this.eventCode = eventCode;
        this.time = time;
    }

    public Ticket(char stadiumSector, long time) {
        this.stadiumSector = stadiumSector;
        this.time = time;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public long getCreationDateMillis() {
        return creationTime;
    }

    public void setCreationDateMillis(long creationDateMillis) {
        this.creationTime = creationDateMillis;
    }

    //
    public String toString() {
        String promoStatus = isPromo ? "Promo" : "Regular";
        String sector = Character.toString(stadiumSector);
        Instant eventInstant = Instant.ofEpochMilli(time);
        String formattedEventTime = eventInstant.toString();
        Instant creationInstant = Instant.ofEpochMilli(creationTime);
        String formattedCreationTime = creationInstant.toString();
        return "TicketServices.Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", eventTime='" + formattedEventTime + '\'' +
                ", creationTime='" + formattedCreationTime + '\'' +
                ", promoStatus='" + promoStatus + '\'' +
                ", stadiumSector='" + sector + '\'' +
                ", maxAllowedBackpackWeight=" + maxAllowedBackpackWeight +
                ", ticketPrice=" + ticketPrice + // Include ticket price in the output
                '}';
    }
}