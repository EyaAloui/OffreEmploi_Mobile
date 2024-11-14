package com.esprit.delivery_app.Activity;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.Offre;
import com.esprit.delivery_app.R;

import java.util.ArrayList;
import java.util.List;

public class ListOffreActivity extends AppCompatActivity {

    private Database db;
    private RecyclerView recyclerView;
    private OffreAdapter offreAdapter;
    private List<Offre> offreList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_offre);

        // Initialize the Room database
        db = Room.databaseBuilder(getApplicationContext(), Database.class, "projetMobile")
                .fallbackToDestructiveMigration() // Only if you want to handle migrations this way
                .build();

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recycler111); // Ensure this ID matches your XML
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Load job offers
        loadOffres();
    }

    private void loadOffres() {
        new Thread(() -> {
            // Retrieve the list of job offers from the database
            offreList = db.offreDao().getAllOffre();
            if (offreList == null) {
                offreList = new ArrayList<>();
            }

            // Update the UI on the main thread
            runOnUiThread(() -> {
                if (offreAdapter == null) {
                    // Initialize the adapter if it hasn't been set yet
                    offreAdapter = new OffreAdapter(offreList);
                    recyclerView.setAdapter(offreAdapter);
                } else {
                    // Update the adapter with the new list and refresh it
                    offreAdapter.setOffreList(offreList);
                }
                if (offreList.isEmpty()) {
                    Toast.makeText(this, "No job offers available", Toast.LENGTH_SHORT).show();
                }
            });
        }).start();
    }
}
