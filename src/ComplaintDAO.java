/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RODELYN MANZO
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ComplaintDAO {
       // SAVE complaint to database
    public boolean saveComplaint(Complaint complaint) {
    String sql = "INSERT INTO complaints (student_id, location, facility_type, image_url, description, priority, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
        
        pstmt.setString(1, complaint.getStudentId());
        pstmt.setString(2, complaint.getLocation());
        pstmt.setString(3, complaint.getFacilityType());
        pstmt.setString(4, complaint.getImageUrl()); 
        pstmt.setString(5, complaint.getDescription());
        pstmt.setString(6, complaint.getPriority());
        
        // Default to "Pending" if not set
        String status = (complaint.getStatus() == null || complaint.getStatus().isEmpty()) ? "Pending" : complaint.getStatus();
        pstmt.setString(7, status);
        
        int affectedRows = pstmt.executeUpdate();
        
        if (affectedRows > 0) {
            ResultSet generatedKeys = pstmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                complaint.setId(generatedKeys.getInt(1));
            }
            return true;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error saving complaint: " + e.getMessage());
    }
    return false;
}
    
    // GET ALL complaints
    public List<Complaint> getAllComplaints() {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints ORDER BY submission_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt("id"));
                complaint.setStudentId(rs.getString("student_id"));
                complaint.setLocation(rs.getString("location"));
                complaint.setFacilityType(rs.getString("facility_type"));
                complaint.setImageUrl(rs.getString("image_url"));
                complaint.setDescription(rs.getString("description"));
                complaint.setPriority(rs.getString("priority"));
                complaint.setStatus(rs.getString("status"));
                complaint.setSubmissionDate(rs.getTimestamp("submission_date"));
                complaint.setLastUpdated(rs.getTimestamp("last_updated"));
                complaint.setUpdatedBy(rs.getString("updated_by"));
                complaint.setRemarks(rs.getString("remarks"));
                complaints.add(complaint);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaints;
    }
     public List<Complaint> getComplaintsByStudentId(String studentId) {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints WHERE student_id = ? ORDER BY submission_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, studentId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt("id"));
                complaint.setStudentId(rs.getString("student_id"));
                complaint.setLocation(rs.getString("location"));
                complaint.setFacilityType(rs.getString("facility_type"));
                complaint.setImageUrl(rs.getString("image_url"));
                complaint.setDescription(rs.getString("description"));
                complaint.setPriority(rs.getString("priority"));
                complaint.setStatus(rs.getString("status"));
                complaint.setSubmissionDate(rs.getTimestamp("submission_date"));
                complaint.setLastUpdated(rs.getTimestamp("last_updated"));
                complaint.setUpdatedBy(rs.getString("updated_by"));
                complaint.setRemarks(rs.getString("remarks"));
                complaints.add(complaint);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return complaints;
    }
    
    // UPDATE complaint status
    public boolean updateComplaintStatus(int complaintId, String newStatus, String updatedBy, String remarks) {
        String sql = "UPDATE complaints SET status = ?, updated_by = ?, remarks = ?, last_updated = CURRENT_TIMESTAMP WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, newStatus);
            pstmt.setString(2, updatedBy);
            pstmt.setString(3, remarks);
            pstmt.setInt(4, complaintId);
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating status: " + e.getMessage());
        }
        return false;
    }
    public boolean updateComplaintStatus(int complaintId, String newStatus) {
    String sql = "UPDATE complaints SET status = ?, last_updated = CURRENT_TIMESTAMP WHERE id = ?";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, newStatus);
        pstmt.setInt(2, complaintId);
        
        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error updating status: " + e.getMessage());
    }
    return false;
    }
    
    // GET complaint by ID
    public Complaint getComplaintById(int id) {
        String sql = "SELECT * FROM complaints WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt("id"));
                complaint.setStudentId(rs.getString("student_id"));
                complaint.setLocation(rs.getString("location"));
                complaint.setFacilityType(rs.getString("facility_type"));
                complaint.setImageUrl(rs.getString("image_url"));
                complaint.setDescription(rs.getString("description"));
                complaint.setPriority(rs.getString("priority"));
                complaint.setStatus(rs.getString("status"));
                complaint.setSubmissionDate(rs.getTimestamp("submission_date"));
                return complaint;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // DELETE complaint
    public boolean deleteComplaint(int complaintId) {
        String sql = "DELETE FROM complaints WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, complaintId);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting complaint: " + e.getMessage());
        }
        return false;
    }
    public List<Complaint> getComplaintsByStatus(String status) {
    List<Complaint> complaints = new ArrayList<>();
    String sql = "SELECT * FROM complaints WHERE status = ? ORDER BY submission_date DESC";
    
    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setString(1, status);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            Complaint complaint = new Complaint();
            complaint.setId(rs.getInt("id"));
            complaint.setStudentId(rs.getString("student_id"));
            complaint.setLocation(rs.getString("location"));
            complaint.setFacilityType(rs.getString("facility_type"));
            complaint.setDescription(rs.getString("description"));
            complaint.setPriority(rs.getString("priority"));
            complaint.setStatus(rs.getString("status"));
            complaint.setSubmissionDate(rs.getTimestamp("submission_date"));
            complaints.add(complaint);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return complaints;
}
}
