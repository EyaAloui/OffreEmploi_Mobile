package com.example.reclamation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class complaint_management extends AppCompatActivity {

    private ImageView addComplaintbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_complaint_management);
        addComplaintbutton = findViewById(R.id.addComplaintb);

        // Set click listener to navigate to AddComplaintActivity
        addComplaintbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(complaint_management.this, AddComplaintActivity.class);
                startActivity(intent);
            }
        });
    }

}