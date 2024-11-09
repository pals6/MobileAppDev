package com.assignment.assignment05;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    TextView textViewNameOP, textViewEmailOP, textViewAgeOP;
    User user,originalUser;
    //final static public String USERS_KEY="USERS";

    @SuppressLint("MissingInflatedId")
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
        if(getIntent()!=null && getIntent().hasExtra(IntentKeys.USER_KEY)){
            originalUser= getIntent().getParcelableExtra(IntentKeys.USER_KEY);
            user=originalUser;

            textViewNameOP.setText(user.name);
            textViewEmailOP.setText(user.email);
            textViewAgeOP.setText(user.age);

        }
        /*findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(textViewNameOP.getText().toString(),textViewEmailOP.getText().toString(),textViewRoleOP.getText().toString());

                Intent intent=new Intent(ProfileActivity.this, EditUserActivity.class);
                //Intent intent =new Intent(CreateUserActivity.this, ProfileActivity.class);
                intent.putExtra(IntentKeys.USER_KEY,user);
                startEditUserForResult.launch(intent);

            }
        });*/
        setTitle("Profile Activity");
    }
}