package com.example.myproj;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.List;

import Database.AppDatabase;
import Entites.Entretien;

public class ListEntretiensActivity extends AppCompatActivity implements EntretienAdapter.OnEntretienActionListener {

    private AppDatabase db;
    private RecyclerView recyclerView;
    private EntretienAdapter entretienAdapter;
    private List<Entretien> entretienList; // Store the list of entretiens
    private Button buttonCreateEntretien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_entretiens_activity); // Ensure this layout file exists

        // Initialize the Room database
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projetMobile").build();

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewEntretiens); // Ensure this ID matches your layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the button for creating an entretien
        buttonCreateEntretien = findViewById(R.id.button_create_entretien);

        // Fetch and display the "entretiens" in a separate thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                // Retrieve the list of "entretiens"
                entretienList = db.entretienDao().getAllEntretiens();

                // Update the UI on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Set up the adapter with the retrieved "entretiens"
                        entretienAdapter = new EntretienAdapter(entretienList, ListEntretiensActivity.this); // Correctly pass the listener (this)
                        recyclerView.setAdapter(entretienAdapter);
                    }
                });
            }
        }).start();

        // Set a click listener on the create entretien button
        buttonCreateEntretien.setOnClickListener(v -> {
            Intent intent = new Intent(ListEntretiensActivity.this, CreateEntretien.class);
            startActivity(intent);
        });
    }

    @Override
    public void onDelete(Entretien entretien) {
        // Delete the entretien from the database in a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                deleteEntretienFromDatabase(entretien);

                // Update the UI on the main thread
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // Remove the entretien from the list
                        entretienList.remove(entretien);

                        // Notify the adapter that the data has changed
                        entretienAdapter.notifyDataSetChanged();
                    }
                });
            }
        }).start();
    }

    private void deleteEntretienFromDatabase(Entretien entretien) {
        // Delete the entretien from the Room database
        db.entretienDao().deleteEntretien(entretien);
    }

    @Override
    public void onModify(Entretien entretien) {
        // Navigate to modify activity
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
