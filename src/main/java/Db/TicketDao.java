package Db;
import Users.User;
import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import TicketServices.Ticket;
import BusTickets.TicketType;
import java.util.List;

public class TicketDao {
    public void saveUser(User user) throws SQLException {
        String sql = "INSERT INTO \"User\" (name, creation_date) VALUES (?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, user.getName());
            pstmt.setTimestamp(2, Timestamp.valueOf(user.getCreationDate()));
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        }
    }

    // Fetch User by ID
    public User getUserById(int userId) throws SQLException {
        String sql = "SELECT id, name, creation_date FROM \"User\" WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        }
        return null;
    }

    // Fetch Tickets
    public List<Ticket> getTicketsByUserId(int userId) throws SQLException {
        String sql = "SELECT id, user_id, ticket_type, creation_date FROM Ticket WHERE user_id = ?";
        List<Ticket> tickets = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Ticket ticket = extractTicketFromResultSet(rs);
                tickets.add(ticket);
            }
        }
        return tickets;
    }

    // Save a new Ticket
    public void saveTicket(Ticket ticket) throws SQLException {
        String sql = "INSERT INTO Ticket (user_id, ticket_type, creation_date) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, ticket.getUserId());
            pstmt.setString(2, ticket.getTicketType().name());
            pstmt.setTimestamp(3, Timestamp.from(Instant.ofEpochMilli(ticket.getCreationDateMillis())));
            pstmt.executeUpdate();

            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                ticket.setId(generatedKeys.getInt(1));
            }
        }
    }

    // Fetch Ticket
    public Ticket getTicketById(int ticketId) throws SQLException {
        String sql = "SELECT id, user_id, ticket_type, creation_date FROM Ticket WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, ticketId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return extractTicketFromResultSet(rs);
            }
        }
        return null; // TicketServices.Ticket not found
    }

    // Update Ticket
    public void updateTicketType(int ticketId, TicketType newType) throws SQLException {
        String sql = "UPDATE Ticket SET ticket_type = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newType.name());
            pstmt.setInt(2, ticketId);
            pstmt.executeUpdate();
        }
    }

    // Delete User
    public void deleteUserById(int userId) throws SQLException {
        String deleteUserSql = "DELETE FROM \"User\" WHERE id = ?";
        String deleteTicketsSql = "DELETE FROM Ticket WHERE user_id = ?";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false); // Start transaction

            // Delete tickets first
            try (PreparedStatement pstmtTickets = conn.prepareStatement(deleteTicketsSql)) {
                pstmtTickets.setInt(1, userId);
                pstmtTickets.executeUpdate();
            }

            // Then delete the user
            try (PreparedStatement pstmtUser = conn.prepareStatement(deleteUserSql)) {
                pstmtUser.setInt(1, userId);
                pstmtUser.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            Connection conn = DatabaseConnection.getConnection();
            e.printStackTrace();
            // Rollback transaction if something went wrong
            conn.rollback();
            throw e;
        }
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
