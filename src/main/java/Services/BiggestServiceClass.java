package Services;

import BusTickets.TicketType;
import Db.TicketDao;
import TicketServices.Ticket;
import Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BiggestServiceClass {
    private final TicketDao ticketDao;

    @Autowired
    public BiggestServiceClass(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Transactional
    public void createUserAndTicket(User user, Ticket newTicket) {
        ticketDao.saveUser(user);
        ticketDao.saveTicket(newTicket);
    }

    public User getUserById(int userId) {
        return ticketDao.getUserById(userId);
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        return ticketDao.getTicketsByUserId(userId);
    }

    @Transactional
    public void updateUserStatusAndCreateTicket(User user, Ticket newTicket) {
        ticketDao.updateUserStatusAndCreateTicket(user, newTicket);
    }

    public Ticket getTicketById(int ticketId) {
        return ticketDao.getTicketById(ticketId);
    }

    @Transactional
    public void updateTicketType(int ticketId, TicketType newType) {
        ticketDao.updateTicketType(ticketId, newType);
    }

    @Transactional
    public void deleteUserById(int userId) {
        ticketDao.deleteUserById(userId);
    }
}