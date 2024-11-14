package com.esprit.delivery_app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.widget.SearchView; // Assurez-vous d'importer la bonne classe

import com.esprit.delivery_app.Entity.Candidat;
import com.esprit.delivery_app.R;
import com.esprit.delivery_app.ViewModel.CandidatViewModel;

public class CreateCandidatureActivity extends AppCompatActivity {

    private EditText usernameInput, emailInput, cvInput;
    private ListView listView;
    private CandidatAdapter adapter;
    private List<Candidat> candidats;
    private CandidatViewModel candidatViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createcandidature);

        // Initialisation des vues
        usernameInput = findViewById(R.id.username_input);
        emailInput = findViewById(R.id.Email_input);
        cvInput = findViewById(R.id.CV_input);
        listView = findViewById(R.id.list_view);
        Button applyButton = findViewById(R.id.button_list_candidats);
        SearchView searchView = findViewById(R.id.search_view);

        // Initialiser le ViewModel
        candidatViewModel = new ViewModelProvider(this).get(CandidatViewModel.class);

        // Initialiser la liste et l'adaptateur
        candidats = new ArrayList<>();
        adapter = new CandidatAdapter(this, candidats, candidatViewModel);
        listView.setAdapter(adapter);

        // Observer la liste des candidats pour mettre à jour la liste automatiquement
        candidatViewModel.getAllCandidats().observe(this, new Observer<List<Candidat>>() {
            @Override
            public void onChanged(List<Candidat> candidatList) {
                candidats.clear();
                candidats.addAll(candidatList);
                adapter.notifyDataSetChanged(); // Notify the adapter that the data set has changed
            }
        });

        // Logique du bouton Apply (Soumettre une candidature)
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().trim();
                String email = emailInput.getText().toString().trim();
                String cvPath = cvInput.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || cvPath.isEmpty()) {
                    Toast.makeText(CreateCandidatureActivity.this, "Veuillez remplir tous les champs.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Créer un nouvel objet Candidat
                Candidat newCandidat = new Candidat(username, email, cvPath);
                candidatViewModel.insert(newCandidat); // Insérer dans la base de données
                Toast.makeText(CreateCandidatureActivity.this, "Candidature soumise avec succès!", Toast.LENGTH_SHORT).show();
            }
        });

        // Ajouter la logique de recherche (filtrage des candidats)
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false; // We don't need to handle query submission here
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Appliquer le filtre à la liste des candidats en fonction du texte de recherche
                adapter.getFilter().filter(newText); // Filtering candidates based on search text
                return false;
            }
        });
    }
}
