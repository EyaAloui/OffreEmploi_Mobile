package com.example.reclamation.Database;


import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.reclamation.DAO.ComplaintDao;
import com.example.reclamation.Entities.Complaint;

@Database(entities = { Complaint.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    // DAO declarations
    public abstract ComplaintDao complaintDao();

    // Singleton to prevent multiple instances of the database
    private static volatile AppDatabase INSTANCE;

    // Method to get the database instance
    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "projetMobile")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration() // For version changes, change as needed
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    //public abstract ComplaintDao ComplaintDao();

}
