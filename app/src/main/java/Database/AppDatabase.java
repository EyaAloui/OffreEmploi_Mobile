package Database;

import Entites.Entretien;
import Entites.EntretienDao;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Entretien.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // Déclaration du DAO
    public abstract EntretienDao entretienDao();

    // Singleton pour éviter la création multiple de l'instance de base de données
    private static volatile AppDatabase INSTANCE;

    // Méthode pour obtenir l'instance de la base de données
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "projetMobile")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
