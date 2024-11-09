package com.assignment.assignment08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.assignment.assignment08.databinding.FragmentMaritalStatusBinding;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MaritalStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MaritalStatusFragment extends Fragment {



    public MaritalStatusFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceMaritalStatus) {
        super.onCreate(savedInstanceMaritalStatus);
    }
    FragmentMaritalStatusBinding fragmentMaritalStatusBinding;
    MaritalStatusFragmentListener maritalStatusFragmentListener;
    


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceMaritalStatus) {
        // Inflate the layout for this fragment
        fragmentMaritalStatusBinding=fragmentMaritalStatusBinding.inflate(inflater,container,false);
        RadioGroup radioGroupMaritalStatus=fragmentMaritalStatusBinding.radioGroupMaritalStatus;
        for(String maritalStatus:Data.maritalStatus){
            RadioButton radioButton= new RadioButton(getContext());
            radioButton.setText(maritalStatus);
            radioGroupMaritalStatus.addView(radioButton);
        }
        return fragmentMaritalStatusBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceMaritalStatus) {
        super.onViewCreated(view, savedInstanceMaritalStatus);
        fragmentMaritalStatusBinding.buttonCancelMaritalStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maritalStatusFragmentListener.cancelMaritalStatus();
            }
        });
        fragmentMaritalStatusBinding.buttonSubmitMaritalStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = fragmentMaritalStatusBinding.radioGroupMaritalStatus.getCheckedRadioButtonId(); // Get the ID of the selected RadioButton

                if (selectedId != -1) { // Check if any RadioButton is selected
                    RadioButton radioButton = fragmentMaritalStatusBinding.radioGroupMaritalStatus.findViewById(selectedId); // Find the RadioButton using the ID
                    String maritalStatus = radioButton.getText().toString(); // Get the country name from the selected RadioButton
                    //Toast.makeText(getActivity(), country, Toast.LENGTH_SHORT).show();
                    maritalStatusFragmentListener.sendMaritalStatus(maritalStatus); // Pass the selected country back to the listener
                } else {
                    Toast.makeText(getActivity(), "Please select a MaritalStatus", Toast.LENGTH_SHORT).show(); // Show a message if no selection
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        maritalStatusFragmentListener=(MaritalStatusFragment.MaritalStatusFragmentListener) context;

    }

    public interface MaritalStatusFragmentListener{
        public void cancelMaritalStatus();
        public void sendMaritalStatus(String maritalStatus);
    }



}