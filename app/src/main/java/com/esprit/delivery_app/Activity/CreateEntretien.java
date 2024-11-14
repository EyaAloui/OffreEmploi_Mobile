package com.esprit.delivery_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.Entretien;
import com.esprit.delivery_app.R;

import android.widget.DatePicker;


public class CreateEntretien extends AppCompatActivity {

    private Database db;
    private EditText editTextNomRecruteur;
    private EditText editTextLieu;
    private EditText editTextNumeroDeSalle;
    private EditText editTextIdProposition;
    private DatePicker datePicker;  // Remplacer EditText par DatePicker
    private RadioButton radioButton;
    private Button buttonSave;
    private Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entretien);

        // Initialiser la base de données Room
        db = Room.databaseBuilder(getApplicationContext(), Database.class, "projetMobile").build();

        // Initialiser les vues
        editTextNomRecruteur = findViewById(R.id.editTextText9);
        editTextLieu = findViewById(R.id.editTextText12);
        editTextNumeroDeSalle = findViewById(R.id.editTextText10);
        editTextIdProposition = findViewById(R.id.editTextText5);
        datePicker = findViewById(R.id.datePicker); // Récupérer le DatePicker
        radioButton = findViewById(R.id.radioButton);
        buttonSave = findViewById(R.id.buttonListeEntretiens);
        buttonBack = findViewById(R.id.buttonBack);

        // Configurer le bouton pour sauvegarder l'entretien
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEntretien();
            }
        });

        // Configurer le bouton pour retourner en arrière
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Termine l'activité pour retourner à la page précédente
            }
        });
    }

    private void saveEntretien() {
        String idProposition = editTextIdProposition.getText().toString();
        String lieu = editTextLieu.getText().toString();
        String numeroDeSalle = editTextNumeroDeSalle.getText().toString();
        String nomRecruteur = editTextNomRecruteur.getText().toString();

        // Récupérer la date sélectionnée à partir du DatePicker
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1; // Les mois dans DatePicker sont de 0 à 11, donc ajouter 1
        int year = datePicker.getYear();
        String date = day + "/" + month + "/" + year;

        // Vérifier si les champs sont vides
        if (nomRecruteur.isEmpty() || lieu.isEmpty() || idProposition.isEmpty() || numeroDeSalle.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Vérifier l'état du RadioButton pour la confirmation
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
                        // Redirect to ListEntretiensActivity
                        Intent intent = new Intent(CreateEntretien.this, ListEntretiensActivity.class);
                        startActivity(intent);
                        finish(); // Ferme l'activité actuelle
                    }
                });
            }
        }).start();
    }

}