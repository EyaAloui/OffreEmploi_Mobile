package com.example.reclamation.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "complaints")
public class Complaint {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "complaint_id")
    private int id;

    // Commented user_id field, will be used later
    // @ColumnInfo(name = "user_id")
    // private int userId;

    @ColumnInfo(name = "title")
    private String title;

    // Assuming ComplaintCategoryConverter will be implemented later
    // @TypeConverters(ComplaintCategoryConverter.class)
    // @ColumnInfo(name = "complaint_category")
    // private ComplaintCategory complaintCategory;

    @ColumnInfo(name = "description")
    private String description;

    // Using Integer for status instead of String
    @ColumnInfo(name = "status")
    private Integer status;  // Example: 1 for "Open", 2 for "In Progress", 3 for "Closed"

    @ColumnInfo(name = "priority")
    private String priority;  // Options: "Low", "Medium", "High"

    // Set default to current date
//    @ColumnInfo(name = "created_date", defaultValue = "CURRENT_TIMESTAMP")
//    private Date createdDate = new Date(); // Use current date by default

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


}
