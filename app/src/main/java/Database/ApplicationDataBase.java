package Database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import DAO.OffreDAO;
import Entites.Offre;


@Database(entities = {Offre.class}, version = 1 , exportSchema = false)
public abstract class ApplicationDataBase extends RoomDatabase {

    private static ApplicationDataBase instance;
    public abstract OffreDAO offreDAO();
    public static ApplicationDataBase getAppDatabase (Context context){
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),ApplicationDataBase.class,"projetMobile")
                    .allowMainThreadQueries().build();
        }
        return instance;
    }

}
