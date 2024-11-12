package ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import db.AppDatabase;
import db.dao.CandidatDao;
import db.entity.Candidat;

public class CandidatViewModel extends AndroidViewModel {
    private final CandidatDao candidatDao;
    private final LiveData<List<Candidat>> allCandidats;

    public CandidatViewModel(Application application) {
        super(application);
        AppDatabase db = AppDatabase.getDatabase(application);
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
