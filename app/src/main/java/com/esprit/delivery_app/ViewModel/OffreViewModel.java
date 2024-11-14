package com.esprit.delivery_app.ViewModel;

import androidx.lifecycle.ViewModel;

import com.esprit.delivery_app.Repository.OffreRepository;

import java.util.List;


public class OffreViewModel extends ViewModel {
    private OffreRepository repository;

    public OffreViewModel() {
        this.repository = new OffreRepository();
    }

    public List<String> loadLocation() {
        return repository.getLocation();
    }
    public List<String> loadCategory(){
        return repository.getCategory();
    }
}
