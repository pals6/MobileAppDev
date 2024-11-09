package com.assignment.assignment08;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.assignment.assignment08.databinding.FragmentCreateUserBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateUserFragment extends Fragment {

    public CreateUserFragment() {
        // Required empty public constructor
    }
    private String selectedState;
    private String selectedMaritalStatus;
    private String selectedDOB;
    private String selectedEduLevel;
    FragmentCreateUserBinding createUserBinding;
    CreateUserFragmentListener createUserFragmentListener;

    public void setSelectedState(String selectedState) {
        this.selectedState = selectedState;
    }

    public void setSelectedMaritalStatus(String selectedMaritalStatus) {
        this.selectedMaritalStatus = selectedMaritalStatus;
    }

    public void setSelectedDOB(String selectedDOB) {
        this.selectedDOB = selectedDOB;
    }

    public void setSelectedEduLevel(String selectedEduLevel) {
        this.selectedEduLevel = selectedEduLevel;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        createUserBinding=FragmentCreateUserBinding.inflate(inflater,container,false);
        return createUserBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(selectedState==null){
            createUserBinding.textViewStateSelected.setText("N/A");
        }
        else{
            createUserBinding.textViewStateSelected.setText(selectedState);
        }
        if(selectedMaritalStatus==null){
            createUserBinding.textViewMaritalStatusSelected.setText("N/A");
        }
        else{
            createUserBinding.textViewMaritalStatusSelected.setText(selectedMaritalStatus);
        }
        if(selectedEduLevel==null){
            createUserBinding.textViewEduLevelSelected.setText("N/A");
        }
        else{
            createUserBinding.textViewEduLevelSelected.setText(selectedEduLevel);
        }
        if(selectedDOB==null){
            createUserBinding.textViewDOBSelected.setText("N/A");
        }
        else{
            createUserBinding.textViewDOBSelected.setText(selectedDOB);
        }
        createUserBinding.buttonSelectState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserFragmentListener.gotoState();
            }
        });
        createUserBinding.buttonSelectDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserFragmentListener.gotoDoB();
            }
        });
        createUserBinding.buttonSelectMaritalStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserFragmentListener.gotoMaritalStatus();
            }
        });
        createUserBinding.buttonSelectEduLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUserFragmentListener.gotoEduLevel();
            }
        });
        createUserBinding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=createUserBinding.editTextName.getText().toString();
                String email=createUserBinding.editTextEmail.getText().toString();
                String state=createUserBinding.textViewStateSelected.getText().toString();
                String phone=createUserBinding.editTextPhone.getText().toString();
                String dob=createUserBinding.textViewDOBSelected.getText().toString();
                String maritalStatus=createUserBinding.textViewMaritalStatusSelected.getText().toString();
                String eduLevel=createUserBinding.textViewEduLevelSelected.getText().toString();
                if (name.isEmpty())
                    Toast.makeText(getActivity(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getActivity(), "Enter a valid email", Toast.LENGTH_SHORT).show();
                }
                else if (phone.isEmpty())
                    Toast.makeText(getActivity(), "Phone number is missing!!", Toast.LENGTH_SHORT).show();
                else if (phone.length() != 10 || !phone.matches("\\d+")) {
                    Toast.makeText(getActivity(), "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show();
                }
                else if (state.equals("N/A"))
                    Toast.makeText(getActivity(), "State is not selected", Toast.LENGTH_SHORT).show();
                else if (dob.equals("N/A"))
                    Toast.makeText(getActivity(), "Date of birth is not selected", Toast.LENGTH_SHORT).show();
                else if (maritalStatus.equals("N/A"))
                    Toast.makeText(getActivity(), "Marital Status is not selected", Toast.LENGTH_SHORT).show();
                else if (eduLevel.equals("N/A"))
                    Toast.makeText(getActivity(), "Edu Level is not selected", Toast.LENGTH_SHORT).show();
                else  {
                        User profile=new User(  name,  email, phone, state,  dob, maritalStatus, eduLevel);
                        createUserFragmentListener.sendProfile(profile);
            }}
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        createUserFragmentListener=(CreateUserFragmentListener) context;
    }

    public interface CreateUserFragmentListener{
        void gotoState();
        void gotoDoB();
        void gotoMaritalStatus();
        void gotoEduLevel();
        void sendProfile(User profile);


    }
}