package com.example.myproj;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ajoutOffre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_offre);

        // Récupération de l'ImageView avec l'ID btngo
        ImageView btngo = findViewById(R.id.btngo);

        // Configuration du OnClickListener pour l'ImageView
        btngo.setOnClickListener(v -> {
            Log.d("ajoutOffre", "Image clicked!");  // Log pour tester le clic
            Intent intent = new Intent(ajoutOffre.this, MainActivity.class);
            startActivity(intent);
        });


        // Configuration de la fenêtre en plein écran
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_ajout_offre);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });



    }
}