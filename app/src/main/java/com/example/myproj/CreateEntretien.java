package com.example.myproj;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import Database.AppDatabase;
import Entites.Entretien;

public class CreateEntretien extends AppCompatActivity {

    private AppDatabase db;
    private EditText editTextNomRecruteur;
    private EditText editTextLieu;
    private EditText editTextNumeroDeSalle;
    private EditText editTextIdProposition;
    private EditText editTextDate;
    private RadioButton radioButton;
    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entretien);

        // Initialiser la base de données Room
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projetMobile").build();

        // Initialiser les vues
        editTextNomRecruteur = findViewById(R.id.editTextText9);
        editTextLieu = findViewById(R.id.editTextText12);
        editTextNumeroDeSalle = findViewById(R.id.editTextText10);
        editTextIdProposition = findViewById(R.id.editTextText5);
        editTextDate = findViewById(R.id.editTextDate);
        radioButton = findViewById(R.id.radioButton);

        buttonSave = findViewById(R.id.buttonListeEntretiens);

        // Configurer le bouton pour sauvegarder l'entretien
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntretien();
            }
        });
    }

    private void saveEntretien() {
        String idProposition = editTextIdProposition.getText().toString();
        String date = editTextDate.getText().toString();
        String lieu = editTextLieu.getText().toString();
        String numeroDeSalle = editTextNumeroDeSalle.getText().toString();
        String nomRecruteur = editTextNomRecruteur.getText().toString();

        // Vérifiez si les champs sont remplis
        if (nomRecruteur.isEmpty() || lieu.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Récupérer l'état du RadioButton pour la confirmation
        boolean confirmer = radioButton.isChecked();

        // Créer un nouvel entretien
        Entretien entretien = new Entretien();
        entretien.setNomRecruteur(nomRecruteur);
        entretien.setLieu(lieu);
        entretien.setDate(date);
        entretien.setIdProposition(idProposition);
        entretien.setNumeroSalle(numeroDeSalle);
        entretien.setConfirmed(confirmer);

        // Sauvegarder l'entretien dans un thread séparé
        new Thread(new Runnable() {
            @Override
            public void run() {
                db.entretienDao().insertEntretien(entretien);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CreateEntretien.this, "Entretien créé avec succès", Toast.LENGTH_SHORT).show();
                        finish(); // Ferme l'activité et retourne à MainActivity
                    }
                });
            }
        }).start();
    }

}
