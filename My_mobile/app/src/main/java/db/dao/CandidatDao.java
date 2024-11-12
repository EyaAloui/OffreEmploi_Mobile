package db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import db.entity.Candidat;

@Dao
public interface CandidatDao {
    @Insert
    void insert(Candidat candidat);
    @Delete
    void delete(Candidat candidat); // Ajouter cette ligne

    @Query("SELECT * FROM candidates")
    LiveData<List<Candidat>> getAllCandidats();


    @Update
    void updatecandidat(Candidat candidat);
}
