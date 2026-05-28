/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RODELYN MANZO
 */
import java.sql.Timestamp;

public class Complaint {
   private int id;
    private String studentId;
    private String location;
    private String facilityType;
    private String imageUrl;   // ✅ match DB column name
    private String description;
    private String priority;
    private String status;
    private Timestamp submissionDate;
    private Timestamp lastUpdated;
    private String updatedBy;
    private String remarks;
    
    // Constructor for new complaints
    public Complaint(String studentId, String location, String facilityType, 
                     String imageUrl, String description, String priority) {
        this.studentId = studentId;
        this.location = location;
        this.facilityType = facilityType;
        this.imageUrl = imageUrl;   // 
        this.description = description;
        this.priority = priority;
        this.status = "Pending";
    }
    
    // Empty constructor
    public Complaint() {}
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getFacilityType() { return facilityType; }
    public void setFacilityType(String facilityType) { this.facilityType = facilityType; }
    
    public String getImageUrl() { return imageUrl; }   // ✅ consistent
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }   
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public Timestamp getSubmissionDate() { return submissionDate; }
    public void setSubmissionDate(Timestamp submissionDate) { this.submissionDate = submissionDate; }
    
    public Timestamp getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(Timestamp lastUpdated) { this.lastUpdated = lastUpdated; }
    
    public String getUpdatedBy() { return updatedBy; }
    public void setUpdatedBy(String updatedBy) { this.updatedBy = updatedBy; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
}
