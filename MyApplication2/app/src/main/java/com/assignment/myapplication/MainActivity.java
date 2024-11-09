package com.assignment.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            Log.d("demo","OnCreate: ");

            ImageView imageView= findViewById(R.id.imageView);
            SeekBar seekBarRed=findViewById(R.id.seekBarRed);
            SeekBar seekBarGreen=findViewById(R.id.seekBarGreen);
            SeekBar seekBarBlue=findViewById(R.id.seekBarBlue);

            return insets;
        });
    }
}