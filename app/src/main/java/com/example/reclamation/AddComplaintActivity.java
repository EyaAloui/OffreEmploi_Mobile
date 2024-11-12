package com.example.reclamation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.reclamation.DAO.ComplaintDao;
import com.example.reclamation.Database.AppDatabase;
import com.example.reclamation.Entities.Complaint;


public class AddComplaintActivity extends AppCompatActivity {

    private EditText editTextTitle, editTextDescription;
    private Spinner spinnerCategory;
    private ImageView imageViewGallery;
    private Button buttonAddComplaint;
    private AppDatabase database;
    //private static final String editTextTitle = "title1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complaint); // Set this to your actual layout file name

        Intent intent = new Intent(this, AddComplaintActivity.class);

        database = AppDatabase.getDatabase(this);
        // Initialiser la base de données Room
        //db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projetMobile").build();


        // Initialize views
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDescription = findViewById(R.id.editTextDescription);
//        spinnerCategory = findViewById(R.id.spinner1);
//        imageViewGallery = findViewById(R.id.imgGallery);
        buttonAddComplaint = findViewById(R.id.add_complaint);

        // Button click listener to add the complaint
        //buttonAddComplaint.setOnClickListener(new View.OnClickListener() {
        buttonAddComplaint.setOnClickListener(new View.OnClickListener() {


            // Configurer le bouton pour sauvegarder l'entretien
        buttonAddComplaint.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveComplaint();
                }
            });
    }
        private void saveComplaint() {
            String title = editTextTitle.getText().toString();
            String description = editTextDescription.getText().toString();



            // Créer un nouvel entretien
            Complaint complaint = new Complaint();
            complaint.setTitle(title);
            complaint.setDescription(description);
            //complaint.setCreatedDate(date);

            // Sauvegarder l'entretien dans un thread séparé
            new Thread(new Runnable() {
                @Override
                public void run() {
                    database.complaintDao().insertComplaint(complaint);
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
//    @Override
//    public void onClick(View v) {
//        String title = editTextTitle.getText().toString();
//        String description = editTextDescription.getText().toString();
//
//    }




// Set click listener to navigate to AddComplaintActivity
                /*buttonAddComplaint.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Intent intent = new Intent(complaint_management.this, AddComplaintActivity.class);
                        startActivity(intent);
                    }
                });*/