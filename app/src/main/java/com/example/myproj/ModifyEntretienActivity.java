package com.example.myproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import Database.AppDatabase;
import Entites.Entretien;

public class ModifyEntretienActivity extends AppCompatActivity {
    private EditText editTextIdProposition;
    private EditText editTextDate;
    private EditText editTextLieu;
    private EditText editTextNumeroSalle;
    private EditText editTextNomRecruteur;
    private Button buttonSaveChanges;

    private AppDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_entretien);

        // Initialisation des éléments UI
        editTextIdProposition = findViewById(R.id.editTextText5);
        editTextDate = findViewById(R.id.editTextDate);
        editTextLieu = findViewById(R.id.editTextText12);
        editTextNumeroSalle = findViewById(R.id.editTextText10);
        editTextNomRecruteur = findViewById(R.id.editTextText9);
        buttonSaveChanges = findViewById(R.id.button_create_entretien);

        // Initialisation de la base de données Room
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projetMobile").build();

        // Récupération des données passées via l'intention
        String idProposition = getIntent().getStringExtra("idProposition");
        String date = getIntent().getStringExtra("date");
        String lieu = getIntent().getStringExtra("lieu");
        String numeroSalle = getIntent().getStringExtra("numeroSalle");
        String nomRecruteur = getIntent().getStringExtra("nomRecruteur");

        // Pré-remplissage des champs de texte avec les données de l'entretien
        editTextIdProposition.setText(idProposition);
        editTextDate.setText(date);
        editTextLieu.setText(lieu);
        editTextNumeroSalle.setText(numeroSalle);
        editTextNomRecruteur.setText(nomRecruteur);

        // Action lors du clic sur le bouton de sauvegarde
        buttonSaveChanges.setOnClickListener(v -> {
            // Mise à jour des informations de l'entretien avec les nouvelles valeurs
            String newIdProposition = editTextIdProposition.getText().toString();
            String newDate = editTextDate.getText().toString();
            String newLieu = editTextLieu.getText().toString();
            String newNumeroSalle = editTextNumeroSalle.getText().toString();
            String newNomRecruteur = editTextNomRecruteur.getText().toString();

            // Lancement d'un thread pour mettre à jour l'entretien dans la base de données
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // Rechercher l'entretien d'origine par son ID
                    Entretien entretien = db.entretienDao().getEntretienById(idProposition);
                    if (entretien != null) {
                        // Mise à jour des champs avec les nouvelles valeurs
                        entretien.setIdProposition(newIdProposition);
                        entretien.setDate(newDate);
                        entretien.setLieu(newLieu);
                        entretien.setNumeroSalle(newNumeroSalle);
                        entretien.setNomRecruteur(newNomRecruteur);

                        // Mise à jour dans la base de données
                        db.entretienDao().updateEntretien(entretien);

                        // Retourner à l'activité précédente une fois la mise à jour effectuée
                        runOnUiThread(() -> {
                            Toast.makeText(ModifyEntretienActivity.this, "Entretien mis à jour avec succès", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ModifyEntretienActivity.this, ListEntretiensActivity.class);
                            startActivity(intent);
                            finish();
                        });
                    } else {
                        // Afficher un message d'erreur si l'entretien n'est pas trouvé
                        runOnUiThread(() -> {
                            Toast.makeText(ModifyEntretienActivity.this, "Erreur : Entretien introuvable", Toast.LENGTH_SHORT).show();
                        });
                    }
                }
            }).start();
        });
    }
}
