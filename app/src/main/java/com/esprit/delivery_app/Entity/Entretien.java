package com.esprit.delivery_app.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "entretiens")
public class Entretien {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String idProposition;
    public String date;
    public String lieu;
    public String numeroSalle;
    public String nomRecruteur;
    public boolean isConfirmed; // Status button for confirmation



    public Entretien() {
        // Constructeur par défaut
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdProposition() {
        return idProposition;
    }

    public void setIdProposition(String idProposition) {
        this.idProposition = idProposition;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public String getNomRecruteur() {
        return nomRecruteur;
    }

    public void setNomRecruteur(String nomRecruteur) {
        this.nomRecruteur = nomRecruteur;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.isConfirmed = confirmed;
    }
}
