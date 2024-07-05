package Db;

import Users.User;
import TicketServices.Ticket;
import BusTickets.TicketType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public class TicketDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TicketDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO \"User\" (name, creation_date) VALUES (?, ?)";
        jdbcTemplate.update(sql, user.getName(), Timestamp.valueOf(user.getCreationDate()));
    }

    public User getUserById(int userId) {
        String sql = "SELECT id, name, creation_date FROM \"User\" WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{userId}, (rs, rowNum) ->
                extractUserFromResultSet(rs)
        );
    }

    public List<Ticket> getTicketsByUserId(int userId) {
        String sql = "SELECT id, user_id, ticket_type, creation_date FROM Ticket WHERE user_id = ?";
        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                extractTicketFromResultSet(rs)
        );
    }

    public void saveTicket(Ticket ticket) {
        String sql = "INSERT INTO Ticket (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, ticket.getUserId(), ticket.getTicketType().name(),
                Timestamp.from(Instant.ofEpochMilli(ticket.getCreationDateMillis())));
    }

    public Ticket getTicketById(int ticketId) {
        String sql = "SELECT id, user_id, ticket_type, creation_date FROM Ticket WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{ticketId}, (rs, rowNum) ->
                extractTicketFromResultSet(rs)
        );
    }

    public void updateTicketType(int ticketId, TicketType newType) {
        String sql = "UPDATE Ticket SET ticket_type = ? WHERE id = ?";
        jdbcTemplate.update(sql, newType.name(), ticketId);
    }

    public void deleteUserById(int userId) {
        String deleteUserSql = "DELETE FROM \"User\" WHERE id = ?";
        String deleteTicketsSql = "DELETE FROM Ticket WHERE user_id = ?";
        jdbcTemplate.update(deleteTicketsSql, userId);
        jdbcTemplate.update(deleteUserSql, userId);
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setCreationDate(rs.getTimestamp("creation_date").toLocalDateTime());
        return user;
    }

    private Ticket extractTicketFromResultSet(ResultSet rs) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getInt("id"));
        ticket.setUserId(rs.getInt("user_id"));
        ticket.setTicketType(TicketType.valueOf(rs.getString("ticket_type")));
        return ticket;
    }
}
