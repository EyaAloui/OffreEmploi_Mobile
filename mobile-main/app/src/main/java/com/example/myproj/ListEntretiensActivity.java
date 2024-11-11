package com.example.myproj;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import Database.AppDatabase;
import Entites.Entretien;

public class ListEntretiensActivity extends AppCompatActivity implements EntretienAdapter.OnEntretienActionListener {

    private AppDatabase db;
    private RecyclerView recyclerView;
    private EntretienAdapter entretienAdapter;
    private List<Entretien> entretienList;
    private List<Entretien> filteredList; // New list for filtering
    private Button buttonCreateEntretien;
    private Button buttonBack;
    private EditText editTextDate;
    private EditText editTextLieu;
    private EditText editTextNomRecruteur;
    private Spinner spinnerStatus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_entretiens_activity);

        // Initialize the Room database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projetMobile").build();

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewEntretiens);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize buttons for creating and going back
        buttonCreateEntretien = findViewById(R.id.button_create_entretien);
        buttonBack = findViewById(R.id.buttonBack);

        // Initialize search fields
        editTextDate = findViewById(R.id.editTextDate);
        editTextLieu = findViewById(R.id.editTextLieu);
        editTextNomRecruteur = findViewById(R.id.editTextNomRecruteur);

        // Set a click listener on the create entretien button
        buttonCreateEntretien.setOnClickListener(v -> {
            Intent intent = new Intent(ListEntretiensActivity.this, CreateEntretien.class);
            startActivity(intent);
        });

        // Set a click listener on the back button
        buttonBack.setOnClickListener(v -> finish());

        // Load the data initially
        loadEntretiens();

        // Add TextWatchers for search functionality
        setupSearchListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload the list of entretiens when returning to this activity
        loadEntretiens();
    }

    private void loadEntretiens() {
        new Thread(() -> {
            // Retrieve the list of entretiens
            entretienList = db.entretienDao().getAllEntretiens();
            filteredList = new ArrayList<>(entretienList); // Initialize the filtered list with all entretiens

            // Update the UI on the main thread
            runOnUiThread(() -> {
                if (entretienAdapter == null) {
                    // Initialize the adapter if it hasn't been set yet
                    entretienAdapter = new EntretienAdapter(filteredList, ListEntretiensActivity.this);
                    recyclerView.setAdapter(entretienAdapter);
                } else {
                    // Update the adapter with the new list and refresh it
                    entretienAdapter.setEntretienList(filteredList);
                    entretienAdapter.notifyDataSetChanged();
                }
            });
        }).start();
    }

    private void setupSearchListeners() {
        // Listener for date EditText
        editTextDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEntretiens();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Listener for lieu EditText
        editTextLieu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEntretiens();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Listener for nom recruteur EditText
        editTextNomRecruteur.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterEntretiens();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filterEntretiens() {
        String date = editTextDate.getText().toString().toLowerCase();
        String lieu = editTextLieu.getText().toString().toLowerCase();
        String nomRecruteur = editTextNomRecruteur.getText().toString().toLowerCase();

        filteredList.clear();
        for (Entretien entretien : entretienList) {
            boolean matches = true;

            if (!date.isEmpty() && !entretien.getDate().toLowerCase().contains(date)) {
                matches = false;
            }
            if (!lieu.isEmpty() && !entretien.getLieu().toLowerCase().contains(lieu)) {
                matches = false;
            }
            if (!nomRecruteur.isEmpty() && !entretien.getNomRecruteur().toLowerCase().contains(nomRecruteur)) {
                matches = false;
            }

            if (matches) {
                filteredList.add(entretien);
            }
        }

        entretienAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDelete(Entretien entretien) {
        new Thread(() -> {
            deleteEntretienFromDatabase(entretien);
            runOnUiThread(() -> {
                entretienList.remove(entretien);
                filterEntretiens(); // Re-filter the list after deletion
            });
        }).start();
    }

    private void deleteEntretienFromDatabase(Entretien entretien) {
        db.entretienDao().deleteEntretien(entretien);
    }

    @Override
    public void onModify(Entretien entretien) {
        Intent intent = new Intent(this, ModifyEntretienActivity.class);
        intent.putExtra("idProposition", entretien.getIdProposition());
        intent.putExtra("date", entretien.getDate());
        intent.putExtra("lieu", entretien.getLieu());
        intent.putExtra("numeroSalle", entretien.getNumeroSalle());
        intent.putExtra("nomRecruteur", entretien.getNomRecruteur());
        intent.putExtra("isConfirmed", entretien.isConfirmed());
        startActivity(intent);
    }
}
