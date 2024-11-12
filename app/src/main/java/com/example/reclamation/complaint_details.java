package com.example.reclamation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class complaint_details extends AppCompatActivity {

    private TextView textViewTitle, textViewDescription, textViewCategory, textViewStatus, textViewPriority, textViewCreateDate, textViewUpdateDate;
    private Button buttonEdit, buttonDelete, buttonGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_details); // Your layout XML file

        // Initialize views
        textViewTitle = findViewById(R.id.textView);
        textViewDescription = findViewById(R.id.textView2);
        textViewCategory = findViewById(R.id.textView4);
        textViewStatus = findViewById(R.id.textView5);
        textViewPriority = findViewById(R.id.textView6);
        textViewCreateDate = findViewById(R.id.textView7);
        textViewUpdateDate = findViewById(R.id.textView8);
        buttonEdit = findViewById(R.id.edit);
        buttonDelete = findViewById(R.id.delete);
        buttonGoBack = findViewById(R.id.goback);

        // Retrieve data passed from the AddComplaintActivity
        String title = getIntent().getStringExtra("TITLE");
        String description = getIntent().getStringExtra("DESCRIPTION");
        String category = getIntent().getStringExtra("CATEGORY");

        // Set the retrieved data to the TextViews
        textViewTitle.setText(title);
        textViewDescription.setText(description);
        textViewCategory.setText(category);

        // Sample static data for status, priority, and dates
        textViewStatus.setText("Pending");
        textViewPriority.setText("High");
        textViewCreateDate.setText("2024-11-12");
        textViewUpdateDate.setText("2024-11-12");

        // Button to go back to the previous screen
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // Close the current activity and return to the previous one
            }
        });

        // Button to edit the complaint
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to another activity to allow editing the complaint
                // For now, just display a message or open a new screen
                // You can use an Intent to go to an Edit Complaint Activity
            }
        });

        // Button to delete the complaint
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Implement complaint deletion logic here (e.g., remove from database)
                // After deleting, return to the complaint management screen
            }
        });
    }
}