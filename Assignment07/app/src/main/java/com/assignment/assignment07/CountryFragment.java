package com.assignment.assignment07;

import static com.assignment.assignment07.Data.countries;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.assignment.assignment07.databinding.FragmentCountryBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CountryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CountryFragment extends Fragment {
    private RadioGroup radioGroupCountries;
    public CountryFragment() {
        // Required empty public constructor
    }
    CountryFragmentListener countryFragmentListener;
    FragmentCountryBinding countryBinding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        countryBinding=FragmentCountryBinding.inflate(inflater,container,false);
        radioGroupCountries=countryBinding.radioGroupCountries;
        for (String country : countries) {
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setText(country);
            radioGroupCountries.addView(radioButton);
        }
        return countryBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        countryBinding.buttonCancelCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countryFragmentListener.cancelCountry();;
            }
        });
        countryBinding.buttonSubmitCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroupCountries.getCheckedRadioButtonId(); // Get the ID of the selected RadioButton

                if (selectedId != -1) { // Check if any RadioButton is selected
                    RadioButton radioButton = countryBinding.radioGroupCountries.findViewById(selectedId); // Find the RadioButton using the ID
                    String country = radioButton.getText().toString(); // Get the country name from the selected RadioButton
                    //Toast.makeText(getActivity(), country, Toast.LENGTH_SHORT).show();
                    countryFragmentListener.sendCountry(country); // Pass the selected country back to the listener
                } else {
                    Toast.makeText(getActivity(), "Please select a country", Toast.LENGTH_SHORT).show(); // Show a message if no selection
                }
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        countryFragmentListener=(CountryFragmentListener) context;
    }

    public interface CountryFragmentListener{
        public void cancelCountry();
        public void sendCountry(String country);
    }
}