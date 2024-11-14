package com.esprit.delivery_app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.esprit.delivery_app.Database.Database;
import com.esprit.delivery_app.Entity.Offre;
import com.esprit.delivery_app.R;

public class AddOffreActivity extends AppCompatActivity {

    private Database db;
    private EditText titre, description, competence, lieu, type;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offre);

        // Initialize the Room database
        db = Room.databaseBuilder(getApplicationContext(), Database.class, "projetMobile").build();

        // Initialize the views
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        competence = findViewById(R.id.competence);
        lieu = findViewById(R.id.lieu);
        type = findViewById(R.id.type);
        addButton = findViewById(R.id.button);

        // Set listeners for the buttons
        addButton.setOnClickListener(v -> saveOffre());
    }

    private void saveOffre() {
        // Retrieve field values
        String jobTitle = titre.getText().toString();
        String jobDescription = description.getText().toString();
        String jobCompetence = competence.getText().toString();
        String jobLocation = lieu.getText().toString();
        String jobType = type.getText().toString();

        // Check if any field is empty
        if (jobTitle.isEmpty() || jobDescription.isEmpty() || jobCompetence.isEmpty() || jobLocation.isEmpty() || jobType.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new job offer
        Offre offre = new Offre();
        offre.setTitre(jobTitle);
        offre.setDescription(jobDescription);
        offre.setCompetence(jobCompetence);
        offre.setLocalisation(jobLocation);
        offre.setTypeContrat(jobType);

        // Save the job offer in a separate thread
        new Thread(() -> {
            db.offreDao().addOffre(offre);
            runOnUiThread(() -> {
                Toast.makeText(AddOffreActivity.this, "Offre ajoutée avec succès", Toast.LENGTH_SHORT).show();
                // Redirect to ListOffresActivity
                Intent intent = new Intent(AddOffreActivity.this, ListOffreActivity.class);
                startActivity(intent);
                finish(); // Close current activity
            });
        }).start();
    }
}
