package com.example.assignment6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.assignment6.databinding.FragmentCreateUserBinding;


public class CreateUserFragment extends Fragment {

    public CreateUserFragment() {
        // Required empty public constructor
    }
    FragmentCreateUserBinding binding;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        binding = FragmentCreateUserBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name= binding.getName.getText().toString();
                String email= binding.getEmail.getText().toString();
                String role= "";
                if(binding.radioGroup.getCheckedRadioButtonId()==R.id.student){
                    role="Student";
                    Log.d("check role","role="+role);
                }
                if(binding.radioGroup.getCheckedRadioButtonId()==R.id.employee){
                    role="Employee";
                    Log.d("check role","role="+role);
                }
                if (binding.radioGroup.getCheckedRadioButtonId()==R.id.other){
                    role="Other";
                    Log.d("check role","role="+role);
                }
                if (name.isEmpty())
                    Toast.makeText(getActivity(),"Enter name", Toast.LENGTH_LONG).show();
                else if (email.isEmpty())
                    Toast.makeText(getActivity(),"Enter email",Toast.LENGTH_LONG).show();
                else if (binding.radioGroup.getCheckedRadioButtonId()==-1)
                    Toast.makeText(getActivity(),"Select a department",Toast.LENGTH_LONG).show();
                else {
                    Profile profile = new Profile(name, email, role);
                    mlistener.gotoProfile(profile);
                }
            }
        });
    }
    CreateUserFragmentListener mlistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener= (CreateUserFragmentListener) context;
    }

    interface CreateUserFragmentListener{
        void gotoProfile(Profile profile);
    }
}