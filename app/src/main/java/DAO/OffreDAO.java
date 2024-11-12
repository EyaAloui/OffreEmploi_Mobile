package DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import Entites.Offre;


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
