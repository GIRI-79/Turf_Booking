package turf_booking;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    public void addBooking(Booking booking) {
        String query = "INSERT INTO booking (booking_candidate, turf_id, booking_date, slot) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, booking.getBookingCandidate());
            stmt.setInt(2, booking.getTurfId());
            stmt.setDate(3, new java.sql.Date(booking.getBookingDate().getTime()));
            stmt.setInt(4, booking.getSlot());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Booking getBooking(int turfId, String candidate) {
        String query = "SELECT * FROM booking WHERE turf_id = ? AND booking_candidate = ?";
        Booking booking = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, turfId);
            stmt.setString(2, candidate);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                        rs.getString("booking_candidate"),
                        rs.getInt("turf_id"),
                        rs.getDate("booking_date"),
                        rs.getInt("slot"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return booking;
    }

    public List<Booking> getAllBookings() {
        String query = "SELECT * FROM booking";
        List<Booking> bookings = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Booking booking = new Booking(
                        rs.getString("booking_candidate"),
                        rs.getInt("turf_id"),
                        rs.getDate("booking_date"),
                        rs.getInt("slot"));

                bookings.add(booking);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookings;
    }
}