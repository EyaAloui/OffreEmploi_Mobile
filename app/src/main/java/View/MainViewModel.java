package View;

import androidx.lifecycle.ViewModel;

import java.util.List;

import Repository.MainRepository;

public class MainViewModel extends ViewModel {
    private MainRepository repository;

    public MainViewModel() {
        this.repository = new MainRepository();
    }

    public List<String> loadLocation() {
        return repository.getLocation();
    }
    public List<String> loadCategory(){
        return repository.getCategory();
    }
}
