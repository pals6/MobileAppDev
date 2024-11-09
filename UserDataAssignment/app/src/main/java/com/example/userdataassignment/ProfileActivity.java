package com.example.userdataassignment;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {
    TextView textViewNameOP, textViewEmailOP, textViewAgeOP, textViewCountryOP, textViewDoBOp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewNameOP=findViewById(R.id.textViewNameOP);
        textViewEmailOP=findViewById(R.id.textViewEmailOP);
        textViewAgeOP=findViewById(R.id.textViewAgeOP);
        textViewCountryOP=findViewById(R.id.textViewCountryValueOP);
        textViewDoBOp=findViewById(R.id.textViewDOBValueOP);

        if(getIntent()!=null && getIntent().getExtras() !=null && getIntent().hasExtra(IntentKeys.USER_KEY)){
            User user= getIntent().getParcelableExtra(IntentKeys.USER_KEY);

            assert user != null;
            textViewNameOP.setText(user.name);
            textViewEmailOP.setText(user.email);
            textViewAgeOP.setText(String.valueOf(user.age));
            textViewCountryOP.setText(user.country);
            textViewDoBOp.setText(user.date);

        }
        setTitle("Profile Activity");
    }
}