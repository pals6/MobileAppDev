package com.assignment.assignment08;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.assignment.assignment08.databinding.FragmentDoBBinding;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoBFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoBFragment extends Fragment {


    public DoBFragment() {
        // Required empty public constructor
    }

    DoBFragmentListener doBFragmentListener;
    FragmentDoBBinding fragmentDoBBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentDoBBinding=FragmentDoBBinding.inflate(inflater,container,false);
        return fragmentDoBBinding.getRoot();
    }


    @SuppressLint("NewApi")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final long[] selectedDateMillis = {0};  // Changed to long array to store milliseconds
        DatePicker calendarView = fragmentDoBBinding.calendarView; // Use CalendarView
        Calendar calendar = Calendar.getInstance();

        // Set max date to 18 years ago from today
        calendar.add(Calendar.YEAR, -18);
        long maxDate = calendar.getTimeInMillis();
        calendarView.setMaxDate(maxDate);


        Calendar selectedDate = Calendar.getInstance();
        //selectedDate.set(year, month, dayOfMonth);
        selectedDateMillis[0] = selectedDate.getTimeInMillis();

        // Set OnClickListener for the Submit button
        fragmentDoBBinding.buttonDOBSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedDateMillis[0] == 0) {
                    // No date is selected
                    Toast.makeText(getActivity(), "Please select a date of birth.", Toast.LENGTH_SHORT).show();
                } else {
                    // Format the selected date as MM/DD/YYYY
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.setTimeInMillis(selectedDateMillis[0]);
                    String Month=String.valueOf(calendarView.getMonth());
                    String day=String.valueOf(calendarView.getDayOfMonth());
                    String Year=String.valueOf(calendarView.getYear());
                    String date=Month+"/"+day+"/"+Year;


                    // Send the selected date string to the listener
                    doBFragmentListener.sendDOB(date);
                }
            }
        });
        fragmentDoBBinding.buttonDOBCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doBFragmentListener.cancelDOB();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        doBFragmentListener=(DoBFragmentListener) context;
    }

    public interface DoBFragmentListener{
        void sendDOB(String dob);
        void cancelDOB();
    }
}