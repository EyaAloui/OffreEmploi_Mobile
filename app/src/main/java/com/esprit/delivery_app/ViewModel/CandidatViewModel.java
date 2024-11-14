package com.esprit.delivery_app.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.esprit.delivery_app.DAO.CandidatDao;
import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.Candidat;

import java.util.List;


public class CandidatViewModel extends AndroidViewModel {
    private final CandidatDao candidatDao;
    private final LiveData<List<Candidat>> allCandidats;

    public CandidatViewModel(Application application) {
        super(application);
        Database db = Database.getDatabaseInscatnce(application);
        candidatDao = db.candidatDao();
        allCandidats = candidatDao.getAllCandidats();
    }

    public LiveData<List<Candidat>> getAllCandidats() {
        return allCandidats;
    }

    public void insert(Candidat candidat) {
        new Thread(() -> candidatDao.insert(candidat)).start();
    }
    public void delete(Candidat candidat) {
        new Thread(() -> candidatDao.delete(candidat)).start();
    }


}
