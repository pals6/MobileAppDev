package com.assignment.assignment08;

import static com.assignment.assignment08.Data.maritalStatus;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.assignment.assignment08.databinding.FragmentEduLevelBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EduLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EduLevelFragment extends Fragment {



    public EduLevelFragment() {
        // Required empty public constructor
    }
    FragmentEduLevelBinding fragmentEduLevelBinding;
    EduLevelListener eduLevelListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceMaritalStatus) {
        // Inflate the layout for this fragment
        fragmentEduLevelBinding=fragmentEduLevelBinding.inflate(inflater,container,false);
        RadioGroup radioGroupEduLevel=fragmentEduLevelBinding.radioGroupEduLevel;
        for(String eduLevel: Data.educationLevels){
            RadioButton radioButton= new RadioButton(getContext());
            radioButton.setText(eduLevel);
            radioGroupEduLevel.addView(radioButton);
        }
        return fragmentEduLevelBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceMaritalStatus) {
        super.onViewCreated(view, savedInstanceMaritalStatus);
        fragmentEduLevelBinding.buttonCancelEduLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eduLevelListener.cancelEduLevel();
            }
        });
        fragmentEduLevelBinding.buttonSubmitEduLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = fragmentEduLevelBinding.radioGroupEduLevel.getCheckedRadioButtonId(); // Get the ID of the selected RadioButton

                if (selectedId != -1) { // Check if any RadioButton is selected
                    RadioButton radioButton = fragmentEduLevelBinding.radioGroupEduLevel.findViewById(selectedId); // Find the RadioButton using the ID
                    String maritalStatus = radioButton.getText().toString(); // Get the country name from the selected RadioButton
                    //Toast.makeText(getActivity(), country, Toast.LENGTH_SHORT).show();
                    eduLevelListener.sendEduLevel(maritalStatus); // Pass the selected country back to the listener
                } else {
                    Toast.makeText(getActivity(), "Please select a Education Level", Toast.LENGTH_SHORT).show(); // Show a message if no selection
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        eduLevelListener=(EduLevelListener) context;
    }

    public interface EduLevelListener{
        public void cancelEduLevel();
        public void sendEduLevel(String eduLevel);
    }
}