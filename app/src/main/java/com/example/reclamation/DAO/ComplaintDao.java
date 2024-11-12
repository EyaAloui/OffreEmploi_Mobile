package com.example.reclamation.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.reclamation.Entities.Complaint;

import java.util.List;

@Dao
public interface ComplaintDao {

    // Insert a new complaint
    @Insert
    void insertComplaint(Complaint complaint);

    // Update an existing complaint
    @Update
    void updateComplaint(Complaint complaint);

    // Delete a complaint
    @Delete
    void deleteComplaint(Complaint complaint);

    // Retrieve all complaints
    @Query("SELECT * FROM complaints")
    List<Complaint> getAllComplaints();

    // Retrieve a specific complaint by its ID (assuming idComplaint is an int)
    @Query("SELECT * FROM complaints WHERE complaint_id = :id LIMIT 1")
    Complaint getComplaintById(int id);

}
