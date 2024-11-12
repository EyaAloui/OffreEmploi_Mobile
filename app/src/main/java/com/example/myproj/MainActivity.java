package com.example.myproj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import Database.AppDatabase;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser la base de données Room
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "projetMobile").build();

        // Configurer le bouton pour créer un nouvel entretien
        findViewById(R.id.button_create_entretien).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers l'activité CreateEntretien
                Intent intent = new Intent(MainActivity.this, CreateEntretien.class);
                startActivity(intent);
            }
        });

        // Configurer le bouton pour afficher la liste des entretiens
        findViewById(R.id.button_list_entretiens).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Naviguer vers l'activité ListEntretiens
                Intent intent = new Intent(MainActivity.this, ListEntretiensActivity.class); // Assuming ListEntretiensActivity is your list activity
                startActivity(intent);
            }
        });
    }
}
