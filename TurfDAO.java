package turf_booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurfDAO {

    public void addTurf(Turf turf) {
        String query = "INSERT INTO turf (turf_id, turf_name, turf_location, total_slots) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, turf.getTurfId());
            stmt.setString(2, turf.getTurfName());
            stmt.setString(3, turf.getTurfLocation());
            stmt.setInt(4, turf.getTotalSlots());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Turf getTurf(int turfId) {
        String query = "SELECT * FROM turf WHERE turf_id = ?";
        Turf turf = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, turfId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                turf = new Turf(
                        rs.getInt("turf_id"),
                        rs.getString("turf_name"),
                        rs.getString("turf_location"),
                        rs.getInt("total_slots"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turf;
    }

    public List<Turf> getAllTurfs() {
        String query = "SELECT * FROM turf";
        List<Turf> turfs = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Turf turf = new Turf(
                        rs.getInt("turf_id"),
                        rs.getString("turf_name"),
                        rs.getString("turf_location"),
                        rs.getInt("total_slots"));

                turfs.add(turf);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return turfs;
    }

    public void updateTurf(Turf turf) {
        String query = "UPDATE turf SET turf_name = ?, turf_location = ?, total_slots = ? WHERE turf_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, turf.getTurfName());
            stmt.setString(2, turf.getTurfLocation());
            stmt.setInt(3, turf.getTotalSlots());
            stmt.setInt(4, turf.getTurfId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTurf(int turfId) {
        String query = "DELETE FROM turf WHERE turf_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, turfId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
