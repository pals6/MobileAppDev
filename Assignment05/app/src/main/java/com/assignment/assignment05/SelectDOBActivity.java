package com.assignment.assignment05;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SelectDOBActivity extends AppCompatActivity {
    Button buttonDOBCancel,buttonDOBSubmit;
    User user;
    DatePicker calendarView;
    Calendar date = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("mm-dd-yyyy");
    int selectedMonth;
    long selectedYear;
    int selectedDay;

    //curDate = sdf.format(date.getTime());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_select_dobactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonDOBSubmit=findViewById(R.id.buttonDOBSubmit);
        buttonDOBCancel=findViewById(R.id.buttonDOBCancel);
        calendarView=(DatePicker) findViewById(R.id.calendarView);
        Log.d("Calender", "onCreate:calender "+calendarView);
        //date.set(Calendar.DATE, date.getActualMaximum(Calendar.DATE));

        //Log.d("Calender Date", "onCreate: "+date.getActualMaximum(Calendar.DATE));
        //Display calender date 18 years in the past
        date.add(Calendar.YEAR,-18);
        long eighteenYearsAgo=date.getTimeInMillis();
        calendarView.setMaxDate(eighteenYearsAgo);

        //capturing date selected from calendarView
       /* calendarView.init(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedMonth=monthOfYear+1;
                        selectedYear=year;
                        selectedDay=dayOfMonth;
                        Log.d("Date", "Year=" + year + " Month=" + (monthOfYear + 1) + " day=" + dayOfMonth);
                    }
                });*/

        //Passing the date selected back to createUser on Submit
        buttonDOBSubmit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String Month=String.valueOf(calendarView.getMonth());
                        String day=String.valueOf(calendarView.getDayOfMonth());
                        String Year=String.valueOf(calendarView.getYear());
                        String date=Month+"/"+day+"/"+Year;
                        Log.d("SubmitDate", "onClick: "+date);
                        //User user=new User(date);
                        Intent intent=new Intent();
                        intent.putExtra(IntentKeys.USER_KEY,date);
                        setResult(RESULT_OK,intent);
                        finish();

                    }
                });
        buttonDOBCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
}