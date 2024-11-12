package com.example.myproj;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

public class AddOffreActivity extends AppCompatActivity {
    private TextView off;
    private EditText titre, description, competence, lieu, type ;
    private Button addButton;
    private ImageView imageView;
    private ProgressBar progressBarCategory;
    private RecyclerView recyclerViewCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_offre); // Set your layout

        // Initialize each view
        off = findViewById(R.id.off);
        titre = findViewById(R.id.titre);
        description = findViewById(R.id.description);
        competence = findViewById(R.id.competence);
        lieu = findViewById(R.id.lieu);
        type = findViewById(R.id.type);   // Replace with actual ID if different
        // Replace with actual ID if different
        addButton = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);

        // Optional: Additional elements like progress bar and RecyclerView if used in this activity


        // Set listeners or further setup if needed
        addButton.setOnClickListener(v -> addJobOffer());
    }

    // Function to handle adding job offer logic
    private void addJobOffer() {
        // Récupérer les valeurs des champs
        String jobTitle = titre.getText().toString();
        String jobDescription = description.getText().toString();
        String jobSalary = competence.getText().toString();
        String jobLocation = lieu.getText().toString();
        String jobType = type.getText().toString();

        // Log pour vérifier les valeurs
        Log.d("AddJobOffer", "Title: " + jobTitle);
        Log.d("AddJobOffer", "Description: " + jobDescription);
        Log.d("AddJobOffer", "Salary: " + jobSalary);
        Log.d("AddJobOffer", "Location: " + jobLocation);
        Log.d("AddJobOffer", "Type: " + jobType);

        // Logique pour ajouter l'offre (simulée ici)
        boolean isAddedSuccessfully = addToDatabase(jobTitle, jobDescription, jobSalary, jobLocation, jobType);

        // Afficher un message en fonction du succès de l'ajout
        if (isAddedSuccessfully) {
            Toast.makeText(this, "Offre ajoutée avec succès", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Erreur lors de l'ajout de l'offre", Toast.LENGTH_SHORT).show();
        }
    }

    // Fonction simulée pour ajouter à la base de données (remplacez-la avec votre logique)
    private boolean addToDatabase(String title, String description, String competence, String location, String type) {
        // Exemple d'ajout réussi, remplacez par la logique réelle d'ajout
        return true;
    }
}