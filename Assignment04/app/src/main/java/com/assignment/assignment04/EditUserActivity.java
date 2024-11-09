package com.assignment.assignment04;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditUserActivity extends AppCompatActivity {
    TextView editTextNameEditUser;
    TextView editTextEmailEditUser;
    RadioGroup radioGroup;
    RadioButton student;
    RadioButton employee;
    RadioButton other;
    //public static final String USERS_KEY="USERS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextNameEditUser = findViewById(R.id.editTextNameEditUser);
        editTextEmailEditUser = findViewById(R.id.editTextEmailEditUser);
        radioGroup = findViewById(R.id.radioGroup);
        student = findViewById(R.id.radioButtonStudentEditUser);
        employee = findViewById(R.id.radioButtonEmployeeEditUser);
        other = findViewById(R.id.radioButtonOtherEditUser);
        if(getIntent()!=null && getIntent().hasExtra(IntentKeys.USER_KEY)){
            User user= getIntent().getParcelableExtra(IntentKeys.USER_KEY);
            editTextNameEditUser.setText(user.name);
            editTextEmailEditUser.setText(user.email);
            Log.d("EditUserActivity", "User Role: " + user.role);
            if(getString(R.string.Student).equals(user.role)){
                student.setChecked(true);
            } else if (getString(R.string.Employee).equals(user.role)) {
                employee.setChecked(true);
            }else if(getString(R.string.Other).equals(user.role)){
                other.setChecked(true);
            }

        }

        findViewById(R.id.buttonSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String name=editTextNameEditUser.getText().toString();
                String email=editTextEmailEditUser.getText().toString();
                String role="";
                if(checkedId==R.id.radioButtonStudentEditUser){
                    role=getString(R.string.Student);
                }
                else if(checkedId==R.id.radioButtonEmployeeEditUser){
                    role=getString(R.string.Employee);
                }
                else if(checkedId==R.id.radioButtonOtherEditUser){
                    role=getString(R.string.Other);
                }

                if(name.isEmpty()){
                    Toast.makeText(EditUserActivity.this,"Enter Name!!",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(EditUserActivity.this,"Enter Email!!",Toast.LENGTH_SHORT).show();
                }
                else if(role.isEmpty()){
                    Toast.makeText(EditUserActivity.this,"Please select a role!!",Toast.LENGTH_SHORT).show();
                }
                else {

                    User user=new User(name,email,role);
                    Intent intent =new Intent(EditUserActivity.this, ProfileActivity.class);
                    intent.putExtra(IntentKeys.USER_KEY,user);
                    startActivity(intent);
                    setResult(RESULT_OK,intent );
                    finish();
                }

            }
        });
        findViewById(R.id.buttonCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);

                finish();
            }
        });



    }
}