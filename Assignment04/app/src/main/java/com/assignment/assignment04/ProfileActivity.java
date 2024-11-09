package com.assignment.assignment04;

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

    TextView textViewNameOP, textViewEmailOP, textViewRoleOP;
    User user,originalUser;
    //final static public String USERS_KEY="USERS";
    private ActivityResultLauncher<Intent> startEditUserForResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null && result.getData().getParcelableExtra(IntentKeys.USER_KEY)!=null){
                        //Intent data = result.getData();
                        user=(User)  result.getData().getParcelableExtra(IntentKeys.USER_KEY);
                        textViewNameOP.setText(user.name);
                        textViewEmailOP.setText(user.email);
                        textViewRoleOP.setText(user.role);
                    } else if (result.getResultCode() == RESULT_CANCELED) {
                        // Do nothing, retain the original data
                        user=originalUser;
                        textViewNameOP.setText(user.name);
                        textViewEmailOP.setText(user.email);
                        textViewRoleOP.setText(user.role);
                        //Toast.makeText(ProfileActivity.this, "Edit canceled. Original data retained.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewRoleOP), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewNameOP=findViewById(R.id.textViewNameOP);
        textViewEmailOP=findViewById(R.id.textViewEmailOP);
        textViewRoleOP=findViewById(R.id.textViewRoleOP);
        if(getIntent()!=null && getIntent().hasExtra(IntentKeys.USER_KEY)){
            originalUser= getIntent().getParcelableExtra(IntentKeys.USER_KEY);
            user=originalUser;

            textViewNameOP.setText(user.name);
            textViewEmailOP.setText(user.email);
            textViewRoleOP.setText(user.role);

        }
        findViewById(R.id.buttonUpdate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User(textViewNameOP.getText().toString(),textViewEmailOP.getText().toString(),textViewRoleOP.getText().toString());

                Intent intent=new Intent(ProfileActivity.this, EditUserActivity.class);
                //Intent intent =new Intent(CreateUserActivity.this, ProfileActivity.class);
                intent.putExtra(IntentKeys.USER_KEY,user);
                startEditUserForResult.launch(intent);

            }
        });
        setTitle("Profile Activity");
    }
}