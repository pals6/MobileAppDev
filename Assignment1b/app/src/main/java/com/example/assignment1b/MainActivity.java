package com.example.assignment1b;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    TextView getTemp;
    TextView displayTemp;
    RadioGroup radioGroup;
    RadioButton CtoF;
    RadioButton FtoC;
    Button Reset;
    Button Calc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getTemp=findViewById(R.id.getTemp);
        displayTemp=findViewById(R.id.displayTemp);
        radioGroup=findViewById(R.id.radioGroup);
        findViewById(R.id.Reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTemp.setText("");
                displayTemp.setText("N/A");
            }
        });
        findViewById(R.id.Calc).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int checkedId=radioGroup.getCheckedRadioButtonId();
                String temp=getTemp.getText().toString();

                try {

                    if (checkedId == R.id.CtoF) {
                        calcTemp(temp, "CtoF");
                    } else if (checkedId == R.id.FtoC)
                        calcTemp(temp, "FtoC");
                }catch(NumberFormatException exception){
                    Toast.makeText(MainActivity.this, "Enter a valid number !!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
    private void calcTemp(String temp, String ch)
    {
        Double t=Double.parseDouble(temp);
        Double f,c;
        try{
            if (ch=="CtoF")
            {
                f=(t*9/5)+32;
                displayTemp.setText(f.toString()+" F");
            }
            else if (ch=="FtoC")
            {
                c=(t-32)*5/9;
                displayTemp.setText(c.toString()+" C");
            }
        }catch (NumberFormatException exception){
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_LONG).show();
        }
    }
}