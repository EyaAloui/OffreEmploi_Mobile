package com.esprit.delivery_app.Activity;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.esprit.delivery_app.R;
import com.esprit.delivery_app.ViewModel.CandidatViewModel;

import java.util.List;


public class ListCandidatsActivity extends AppCompatActivity {
    private ListView listView;
    private CandidatViewModel candidatViewModel;
    private CandidatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_candidats);

        listView = findViewById(R.id.list_view_candidats); // Vous devrez avoir un ListView dans le layout XML

        // Initialisation du ViewModel
        candidatViewModel = new ViewModelProvider(this).get(CandidatViewModel.class);

        // Observer les candidats dans le ViewModel
        candidatViewModel.getAllCandidats().observe(this, candidats -> {
            if (adapter == null) {
                // Créer un nouvel adaptateur avec les candidats récupérés
                adapter = new CandidatAdapter(this, candidats, candidatViewModel);
                listView.setAdapter(adapter);
            } else {
                // Mettre à jour l'adaptateur avec la nouvelle liste
                adapter.notifyDataSetChanged();
            }
        });
    }
}
