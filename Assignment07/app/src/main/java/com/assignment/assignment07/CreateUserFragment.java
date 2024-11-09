package com.assignment.assignment07;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.assignment.assignment07.databinding.FragmentCreateUserBinding;

public class CreateUserFragment extends Fragment {
    //private static final String ARG_PARAM_COUNTRY = "ARG_PARAM_COUNTRY";
    //private static final String ARG_PARAM_DOB = "ARG_PARAM_DOB";
    private String selectedCountry;
    private String selectedDOB;

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public void setSelectedDOB(String selectedDOB) {
        this.selectedDOB = selectedDOB;
    }

    public CreateUserFragment() {
        // Required empty public constructor
    }

    CreateUserFragmentListener mListener;
    FragmentCreateUserBinding createUserBinding;



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
        if(selectedCountry==null){
            createUserBinding.textViewCountrySelected.setText("N/A");
        }
        else{
            createUserBinding.textViewCountrySelected.setText(selectedCountry);
        }

        if(selectedDOB==null){
            createUserBinding.textViewDOBSelected.setText("N/A");
        }
        else{
            createUserBinding.textViewDOBSelected.setText(selectedDOB);
        }
        createUserBinding.buttonSelectCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoCountry();

            }
        });
        createUserBinding.buttonSelectDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoDoB();
            }
        });
        createUserBinding.buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=createUserBinding.editTextName.getText().toString();
                String email=createUserBinding.editTextEmail.getText().toString();
                String country=createUserBinding.textViewCountrySelected.getText().toString();
                String dob=createUserBinding.textViewDOBSelected.getText().toString();
                if (name.isEmpty())
                    Toast.makeText(getActivity(), "Enter a valid name", Toast.LENGTH_SHORT).show();
                else if (email.isEmpty())
                    Toast.makeText(getActivity(), "Email is missing!!", Toast.LENGTH_SHORT).show();
                else if (country.equals("N/A"))
                    Toast.makeText(getActivity(), "Country is not selected", Toast.LENGTH_SHORT).show();
                else if (dob.equals("N/A"))
                    Toast.makeText(getActivity(), "Date of birth is not selected", Toast.LENGTH_SHORT).show();
                else  {
                try {

                    int age=Integer.valueOf(createUserBinding.editTextAge.getText().toString());
                    User profile=new User( name, email, age, country, dob);
                    mListener.sendProfile(profile);
                }catch (NumberFormatException e){
                    Toast.makeText(getActivity(), "Enter a valid age", Toast.LENGTH_SHORT).show();
                }
                }
            }
        });

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener=(CreateUserFragmentListener) context;
    }

    public interface CreateUserFragmentListener{
        void gotoCountry();
        void gotoDoB();
        void sendProfile(User profile);
    }
}