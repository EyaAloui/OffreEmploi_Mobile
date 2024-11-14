package com.esprit.delivery_app.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.esprit.delivery_app.Entity.Offre;

import java.util.List;



@Dao
public interface OffreDAO {

    @Insert
   void addOffre (Offre offre);
@Update
   void updateOffre(Offre offre);
@Delete
   void deleteOffre(Offre offre);
@Query("SELECT  * FROM offreEmploi" )
   List<Offre> getAllOffre();
@Query("SELECT * FROM offreEmploi WHERE idOffre = :id ")
   Offre getOffreById(int id);

}
