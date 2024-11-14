package com.esprit.delivery_app.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "candidates")
public class Candidat {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private String email;
    private String cvPath;

    public Candidat(String nom, String email, String cvPath) {
        this.nom = nom;
        this.email = email;
        this.cvPath = cvPath;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCvPath() {
        return cvPath;
    }

    public void setCvPath(String cvPath) {
        this.cvPath = cvPath;
    }
}
