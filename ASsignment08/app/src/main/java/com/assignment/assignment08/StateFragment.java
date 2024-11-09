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

import com.assignment.assignment08.databinding.FragmentStateBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StateFragment extends Fragment {


    public StateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    FragmentStateBinding fragmentStateBinding;
    StateFragmentListener stateFragmentListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentStateBinding=FragmentStateBinding.inflate(inflater,container,false);
        LinearLayout linearLayoutState=fragmentStateBinding.linearLayoutState;
        RadioGroup radioGroupStates=fragmentStateBinding.radioGroupStates;
        for(String state:Data.states){
            RadioButton radioButton= new RadioButton(getContext());
            radioButton.setText(state);
            radioGroupStates.addView(radioButton);
        }
        return fragmentStateBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragmentStateBinding.buttonCancelState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stateFragmentListener.cancelState();
            }
        });
        fragmentStateBinding.buttonSubmitState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = fragmentStateBinding.radioGroupStates.getCheckedRadioButtonId(); // Get the ID of the selected RadioButton

                if (selectedId != -1) { // Check if any RadioButton is selected
                    RadioButton radioButton = fragmentStateBinding.radioGroupStates.findViewById(selectedId); // Find the RadioButton using the ID
                    String state = radioButton.getText().toString(); // Get the country name from the selected RadioButton
                    //Toast.makeText(getActivity(), country, Toast.LENGTH_SHORT).show();
                    stateFragmentListener.sendState(state); // Pass the selected country back to the listener
                } else {
                    Toast.makeText(getActivity(), "Please select a state", Toast.LENGTH_SHORT).show(); // Show a message if no selection
                }
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        stateFragmentListener=(StateFragmentListener) context;

    }

    public interface StateFragmentListener{
        public void cancelState();
        public void sendState(String state);
    }
}