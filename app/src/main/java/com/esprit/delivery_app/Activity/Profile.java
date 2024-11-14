package com.esprit.delivery_app.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.delivery_app.Entity.User;
import com.esprit.delivery_app.R;
import com.esprit.delivery_app.Utils.ImageUtils;
import com.esprit.delivery_app.ViewModel.UserModel;
import com.esprit.delivery_app.databinding.ActivityProfileBinding;

public class Profile extends AppCompatActivity {

    Button createEntretienButton;
    Button createCandidatButton;
    Button createOffreButton;
    ImageUtils imageUtils;
    ActivityProfileBinding binding;
    TextView email, phonenum, usernameinfo;
    UserModel userModel;
    ImageView back, delete, profile_pic, edit;
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        usernameinfo = findViewById(R.id.usernameinfo);
        phonenum = findViewById(R.id.phonenum);
        email = findViewById(R.id.email);
        back = findViewById(R.id.back);
        delete = findViewById(R.id.deleteaccount);
        profile_pic = findViewById(R.id.profile_pic);
        edit = findViewById(R.id.edit);
        userModel = new ViewModelProvider(this).get(UserModel.class);
        createEntretienButton = findViewById(R.id.create_entretien_button);
        createCandidatButton = findViewById(R.id.create_candidat_button);
        createOffreButton = findViewById(R.id.create_offre_button);

        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        Long phone = intent.getLongExtra("phone_number", 0);
        String mail = intent.getStringExtra("email");
        byte[] img = intent.getByteArrayExtra("img");

        usernameinfo.setText(username);
        phonenum.setText(String.valueOf(phone));
        email.setText(mail);
        if (img != null) {
            profile_pic.setImageBitmap(imageUtils.byteArrayToBitmap(img));
        } else {
            profile_pic.setImageBitmap(profile_pic.getDrawingCache());
        }

        back.setOnClickListener(v -> {
            Intent i = new Intent(Profile.this, Dashboard.class);
            startActivity(i);
        });

        delete.setOnClickListener(p -> {
            try {
                userModel.deleteuser(username);
                Intent i = new Intent(Profile.this, Login.class);
                Toast.makeText(this, "Account deleted successfully", Toast.LENGTH_SHORT).show();
                startActivity(i);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error deleting account", Toast.LENGTH_SHORT).show();
            }
        });

        profile_pic.setOnClickListener(v -> {
            Intent takepic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takepic.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takepic, REQUEST_IMAGE_CAPTURE);
            }
        });

        // Set up the button to navigate to CreateEntretien
        createEntretienButton.setOnClickListener(v -> {
            Intent createEntretienIntent = new Intent(Profile.this, CreateEntretien.class);
            startActivity(createEntretienIntent);
        });

        // Set up the button to navigate to CreateCandidat
        createCandidatButton.setOnClickListener(v -> {
            Intent createCandidatIntent = new Intent(Profile.this, CreateCandidatureActivity.class);
            startActivity(createCandidatIntent);
        });

        // Set up the button to navigate to CreateOffre
        createOffreButton.setOnClickListener(v -> {
            Intent createOffreIntent = new Intent(Profile.this, AddOffreActivity.class);
            startActivity(createOffreIntent);
        });
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    Bitmap imgBitmap = (Bitmap) extras.get("data");
                    if (imgBitmap != null) {
                        profile_pic.setImageBitmap(imgBitmap);
                        User user = userModel.getUserByName(usernameinfo.getText().toString());
                        byte[] image = imageUtils.bitmapToByteArray(imgBitmap);
                        user.setImg(image);
                        userModel.updateuser(user);
                    }
                }
            }
        }
    }
}
