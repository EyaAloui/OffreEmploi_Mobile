package com.esprit.delivery_app.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.esprit.delivery_app.Entity.Entretien;

import java.util.List;

@androidx.room.Dao
public interface EntretienDao {

    // Insérer un nouvel entretien
    @Insert
    void insertEntretien(Entretien entretien);

    // Mettre à jour un entretien
    @Update
    void updateEntretien(Entretien entretien);

    // Supprimer un entretien
    @Delete
    void deleteEntretien(Entretien entretien);

    // Récupérer toutes les entretiens
    @Query("SELECT * FROM entretiens")
    List<Entretien> getAllEntretiens();

    // Récupérer un entretien spécifique par son ID
    @Query("SELECT * FROM entretiens WHERE idProposition = :idProposition LIMIT 1")
    Entretien getEntretienById(String idProposition);
    @Query("SELECT * FROM entretiens WHERE date LIKE :date AND lieu LIKE :lieu AND nomRecruteur LIKE :nomRecruteur")
    List<Entretien> filterEntretiens(String date, String lieu, String nomRecruteur);

}
