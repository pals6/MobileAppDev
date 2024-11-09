package com.example.userdataassignment;

import android.content.DialogInterface;
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
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CreateUserActivity extends AppCompatActivity {


    EditText editTextName,editTextEmail, editTextAge;
    TextView countryText, dobText;
    private final ActivityResultLauncher<Intent> selectDobIntent = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getData() != null && result.getData().hasExtra(IntentKeys.DATE)) {
                        dobText.setText(result.getData().getStringExtra(IntentKeys.DATE));
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_user);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextName=findViewById(R.id.editTextName);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextAge=findViewById(R.id.editTextAge);

        countryText = findViewById(R.id.textViewCountryValue);
        dobText = findViewById(R.id.textViewDOBValue);

        countryText.setText("N/A");
        dobText.setText("N/A");

        findViewById(R.id.countrySelectButton).setOnClickListener(v -> {

            AlertDialog.Builder countryBuilder = new AlertDialog.Builder(CreateUserActivity.this);
            View dialogView = getLayoutInflater().inflate(R.layout.dialog_country_selection, null);
            countryBuilder.setView(dialogView);
            RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroupCountries);

            for (String country : Data.countries) {
                RadioButton radioButton = new RadioButton(CreateUserActivity.this);
                radioButton.setText(country);
                radioGroup.addView(radioButton);
            }

            countryBuilder.setTitle("Choose a country")
                    .setPositiveButton("Ok", (dialog, which) -> {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        if (selectedId != -1) {
                            RadioButton selectedRadioButton = dialogView.findViewById(selectedId);
                            String selectedCountry = selectedRadioButton.getText().toString();
                            countryText.setText(selectedCountry);
                        }
                    })
                    .setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            countryBuilder.create().show();
        });

        findViewById(R.id.dateSelectButton).setOnClickListener(v -> {
            Intent intent = new Intent(CreateUserActivity.this, SelectDoBActivity.class);
            selectDobIntent.launch(intent);
        });

        findViewById(R.id.createUserSubmit).setOnClickListener(v -> {
            String name=editTextName.getText().toString();
            String email=editTextEmail.getText().toString();
            String age = editTextAge.getText().toString();
            String date = dobText.getText().toString();
            String country = countryText.getText().toString();

            Log.d("demo", "date:"+date);
            Log.d("demo", "country: "+country);
            if(name.isEmpty()){
                Toast.makeText(CreateUserActivity.this,"Enter Name!!",Toast.LENGTH_SHORT).show();
            }
            else if(email.isEmpty()){
                Toast.makeText(CreateUserActivity.this,"Enter Email!!",Toast.LENGTH_SHORT).show();
            }
            else if(age.isEmpty()){
                Toast.makeText(CreateUserActivity.this,"Enter age!!",Toast.LENGTH_SHORT).show();
            }
            else if(country.isEmpty() || country.equals("N/A")){
                Toast.makeText(CreateUserActivity.this,"Please select country!!",Toast.LENGTH_SHORT).show();
            }
            else if(date.isEmpty() || date.equals("N/A")){
                Toast.makeText(CreateUserActivity.this,"Please select date!!",Toast.LENGTH_SHORT).show();
            }
            else {
                User user=new User(name,email,Integer.parseInt(age), date, country);
                Intent intent =new Intent(CreateUserActivity.this, ProfileActivity.class);
                intent.putExtra(IntentKeys.USER_KEY,user);
                startActivity(intent);
            }
        });
    }
}