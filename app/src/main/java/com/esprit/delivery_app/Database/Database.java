package com.esprit.delivery_app.Database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.esprit.delivery_app.DAO.CandidatDao;
import com.esprit.delivery_app.DAO.EntretienDao;
import com.esprit.delivery_app.DAO.OffreDAO;
import com.esprit.delivery_app.DAO.UserDao;
import com.esprit.delivery_app.Entity.Candidat;
import com.esprit.delivery_app.Entity.Entretien;
import com.esprit.delivery_app.Entity.Offre;
import com.esprit.delivery_app.Entity.User;
@androidx.room.Database(entities = {Entretien.class, Offre.class, Candidat.class, User.class}, version = 2, exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract EntretienDao entretienDao();

    public abstract CandidatDao candidatDao();

    public abstract OffreDAO offreDao();


    public static Database INSTANCE;
        public static Database getDatabaseInscatnce(Context context) {


        if (INSTANCE == null){
            synchronized (Database.class){
                INSTANCE = Room.databaseBuilder(context, Database.class,"projetMobile" )
                        .allowMainThreadQueries()
                        .addMigrations(new Migration1To2())
                        .build();

            }
        }
        return INSTANCE;
    }







}
