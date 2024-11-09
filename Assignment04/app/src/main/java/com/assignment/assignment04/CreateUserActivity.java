package com.assignment.assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateUserActivity extends AppCompatActivity {

    EditText editTextName,editTextEmail;
    TextView next;
    RadioGroup radioGroupRole;
    RadioButton buttonStudent;
    RadioButton buttonEmployee;
    RadioButton buttonOther;
    //final static public String USERS_KEY="USERS";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user_info);
        setTitle("Create User Info");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.textViewWelcome), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        radioGroupRole=findViewById(R.id.radioGroupRole);
        editTextName=findViewById(R.id.editTextName);
        editTextEmail=findViewById(R.id.editTextEmail);
        //buttonStudent=findViewById(R.id.radioButtonStudent);
        //buttonEmployee=findViewById(R.id.radioButtonEmployee);
        //buttonOther=findViewById(R.id.radioButtonOther);
        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroupRole.getCheckedRadioButtonId();
                String name=editTextName.getText().toString();
                String email=editTextEmail.getText().toString();
                String role = "";
                if(checkedId==R.id.radioButtonStudent){
                    role=getString(R.string.Student);
                }
                else if(checkedId==R.id.radioButtonEmployee){
                    role=getString(R.string.Employee);
                }
                else if(checkedId==R.id.radioButtonOther){
                    role=getString(R.string.Other);
                }

                if(name.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Enter Name!!",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Enter Email!!",Toast.LENGTH_SHORT).show();
                }
                else if(role.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Please select a role!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    User user=new User(name,email,role);
                    Log.d("nameis", "onClick: "+name);
                    Intent intent =new Intent(CreateUserActivity.this, ProfileActivity.class);
                    intent.putExtra(IntentKeys.USER_KEY,user);
                    startActivity(intent);
                    setResult(RESULT_OK,intent);
                    finish();

                }

            }
        });
    }
}