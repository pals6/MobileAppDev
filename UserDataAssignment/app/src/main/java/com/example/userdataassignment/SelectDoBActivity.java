package com.example.userdataassignment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelectDoBActivity extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;
    int year, month, dayOfMonth;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_do_bactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("Date of Birth");

        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        long maxDate = calendar.getTimeInMillis();
        calendarView.setMaxDate(maxDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int y, int m, int dOM) {
                date = new Date(y-1900, m, dOM);

            }
        });

        findViewById(R.id.dateCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.dateSubmit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String res = new SimpleDateFormat("MM-dd-yyyy").format(date);
                intent.putExtra(IntentKeys.DATE, res);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}