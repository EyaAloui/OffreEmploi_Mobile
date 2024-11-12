package Repository;

import java.util.Arrays;
import java.util.List;

public class MainRepository {


    public List<String> getLocation() {
        return Arrays.asList("Tunis", "Belgique", "France", "Allemagne");
    }
    public List<String> getCategory(){
        return Arrays.asList("Tous" , "Comptable","Programmeur","Écrivain");
    }

}
