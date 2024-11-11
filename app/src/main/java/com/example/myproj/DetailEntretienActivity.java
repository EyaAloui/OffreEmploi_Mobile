package com.example.myproj;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailEntretienActivity extends AppCompatActivity {

    private TextView textViewNomRecruteur;
    private TextView textViewLieu;
    private TextView textViewDate;
    private TextView textViewIdProposition;
    private TextView textViewNumeroDeSalle;
    private TextView textViewConfirmer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_entretien);

        // Récupérer les vues
        textViewNomRecruteur = findViewById(R.id.detailNomRecruteur);
        textViewLieu = findViewById(R.id.detailLieu);
        textViewDate = findViewById(R.id.detailDate);
        textViewIdProposition = findViewById(R.id.detailIdProposition);
        textViewNumeroDeSalle = findViewById(R.id.detailNumeroSalle);
        textViewConfirmer = findViewById(R.id.detailStatutConfirmation);

        // Récupérer les données passées dans l'intent
        String nomRecruteur = getIntent().getStringExtra("nomRecruteur");
        String lieu = getIntent().getStringExtra("lieu");
        String date = getIntent().getStringExtra("date");
        String idProposition = getIntent().getStringExtra("idProposition");
        String numeroSalle = getIntent().getStringExtra("numeroSalle");
        boolean isConfirmed = getIntent().getBooleanExtra("isConfirmed", false);

        // Mettre à jour les TextViews avec les données
        textViewNomRecruteur.setText(nomRecruteur);
        textViewLieu.setText(lieu);
        textViewDate.setText(date);
        textViewIdProposition.setText(idProposition);
        textViewNumeroDeSalle.setText(numeroSalle);
        textViewConfirmer.setText(isConfirmed ? "Confirmé" : "Non confirmé");
    }
}
