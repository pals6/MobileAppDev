package com.example.assignment1a;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView editemperature;
    Button CtoF;
    Button FtoC;
    Button reset;
    TextView displayTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        editemperature = findViewById(R.id.editTemperature);
        displayTemp=findViewById(R.id.displayTemp);

        findViewById(R.id.CtoF).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp=editemperature.getText().toString();
                try{
                calcTemp(temp,"CtoF");
                } catch (NumberFormatException exception){
                    Toast.makeText(MainActivity.this, "Enter a valid number", Toast.LENGTH_LONG).show();
                }
            }
        });
        findViewById(R.id.FtoC).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String temp=editemperature.getText().toString();
                try{
                calcTemp(temp, "FtoC");
                } catch (NumberFormatException exception){
                    Toast.makeText(MainActivity.this, "Enter a valid number", Toast.LENGTH_LONG).show();
            }
            }
        });
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editemperature.setText("");
                displayTemp.setText("");
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    private void calcTemp(String a, String cf)
    {
        Double t= Double.parseDouble(a);
        Double f,c;
        try{if (cf=="CtoF")
        {
            f=(t*9/5)+32;
            Log.d("test","double issue"+f);
            displayTemp.setText(f.toString()+" F");
        }
        else {
            c = (t - 32) * 5 / 9;
            displayTemp.setText(c.toString()+" C");
        }
        }
        catch   (NumberFormatException exception){
            Toast.makeText(this, "Enter a valid number", Toast.LENGTH_LONG).show();
        }


    }
}