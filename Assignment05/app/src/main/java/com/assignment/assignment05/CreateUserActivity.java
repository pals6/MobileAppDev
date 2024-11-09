package com.assignment.assignment05;

import static com.assignment.assignment05.Data.countries;
import static com.assignment.assignment05.IntentKeys.USER_KEY;
import com.assignment.assignment05.Data;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class CreateUserActivity extends AppCompatActivity {
    Button buttonSelectDOB,buttonSelectCountry;
    TextView textViewCountrySelected,textViewDOBSelected;
    EditText editTextName,editTextEmail,editTextAge;
    User user;
    String DOB;
    private ActivityResultLauncher<Intent> startSelectDOBForResult=registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    String DOB=result.getData().getStringExtra(USER_KEY);
                    Log.d("CreateUserDate", "onActivityResult: "+DOB);
                    //user=(User)  result.getData().getParcelableExtra(IntentKeys.USER_KEY);
                    if(result.getResultCode() == RESULT_OK && result.getData()!=null){
                        if (DOB!=null) {
                            textViewDOBSelected.setText(DOB);
                        } else {
                            Log.e("CreateUserActivity123", "User object is null");
                            Toast.makeText(CreateUserActivity.this, "Error: No DOB selected", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("CreateUserActivity123", "No data returned from SelectDOBActivity");
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
        buttonSelectDOB=findViewById(R.id.buttonSelectDOB);
        buttonSelectDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //user=new User(textVie)

                Intent intent=new Intent(CreateUserActivity.this, SelectDOBActivity.class);
                //intent.putExtra(USER_KEY,);
                startSelectDOBForResult.launch(intent);
            }
        });
        buttonSelectCountry=findViewById(R.id.buttonSelectCountry);
        textViewCountrySelected=findViewById(R.id.textViewCountrySelected);
        textViewDOBSelected=findViewById(R.id.textViewDOBSelected);
        buttonSelectCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog=new AlertDialog.Builder(CreateUserActivity.this);
                alertDialog.setTitle(R.string.ChooseACountry);
                final int[] selectedCountryIndex={-1};
                alertDialog.setSingleChoiceItems(countries, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedCountryIndex[0]=which;
                        Log.d("which", "which "+which);
//                        Toast.makeText(getApplicationContext(),
//                                "Group Name = "+countries[which], Toast.LENGTH_SHORT).show();
                    }
                })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("which", "whichok: "+selectedCountryIndex[0]);
                        if(selectedCountryIndex[0]!=-1){
                            Log.d("which", "country: "+countries[selectedCountryIndex[0]]);
                            textViewCountrySelected.setText(countries[selectedCountryIndex[0]]);
                        }

                        //dialog.dismiss();// dismiss the alertbox after chose option
                    }
                })
                        .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();

                            }
                        });
                AlertDialog alert = alertDialog.create();
                alert.show();
                //alertDialog.show();
            }
        });
        editTextName=findViewById(R.id.editTextName);
        editTextEmail=findViewById(R.id.editTextEmail);
        editTextAge=findViewById(R.id.editTextAge);
        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editTextName.getText().toString();
                String email=editTextEmail.getText().toString();
                String age=editTextAge.getText().toString();
                if(name.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Enter Name!!",Toast.LENGTH_SHORT).show();
                }
                else if(email.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Enter Email!!",Toast.LENGTH_SHORT).show();
                }
                else if(age.isEmpty()){
                    Toast.makeText(CreateUserActivity.this,"Please select a role!!",Toast.LENGTH_SHORT).show();
                }
                else {
                    User user=new User(name,email,age);
                    Intent intent =new Intent(CreateUserActivity.this, ProfileActivity.class);
                    intent.putExtra(IntentKeys.USER_KEY,user);
                    startActivity(intent);
//                    setResult(RESULT_OK,intent);
//                    finish();

                }

            }
        });

    }
}