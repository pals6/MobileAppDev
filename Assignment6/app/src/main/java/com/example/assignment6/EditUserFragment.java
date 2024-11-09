package com.example.assignment6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment6.databinding.FragmentEditUserBinding;
import com.example.assignment6.databinding.FragmentProfileBinding;


public class EditUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM_PROFILE = "ARG_PARAM_PROFILE";
    private Profile mProfile;

    public EditUserFragment() {
        // Required empty public constructor
    }

    public static EditUserFragment newInstance(Profile profile) {
        EditUserFragment fragment = new EditUserFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PROFILE, profile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfile = (Profile) getArguments().getSerializable(ARG_PARAM_PROFILE);
        }
    }
    FragmentEditUserBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.name.setText(mProfile.getName());
        binding.email.setText(mProfile.getEmail());
        String role="";
        if (mProfile.getRole().equals("Student") ) {
            binding.radioButtonStudentEdit.setChecked(true);
            role="Student";
        } else if (mProfile.getRole().equals("Employee")) {
            binding.radioButtonEmployeeEdit.setChecked(true);
            role="Employee";
        } else {
            binding.radioButtonOtherEdit.setChecked(true);
            role="Other";
        }
        binding.Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.cancelEditUser();
            }
        });
        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=binding.name.getText().toString();
                String email=binding.email.getText().toString();
                String role = "";
                if (binding.radioGroupEditUser.getCheckedRadioButtonId() == R.id.radioButtonStudentEdit) {
                    role = "Student";
                    Log.d("check role", "role=" + role);
                }
                if (binding.radioGroupEditUser.getCheckedRadioButtonId() == R.id.radioButtonEmployeeEdit) {
                    role = "Employee";
                    Log.d("check role", "role=" + role);
                }
                if (binding.radioGroupEditUser.getCheckedRadioButtonId() == R.id.radioButtonOtherEdit) {
                    role = "Other";
                    Log.d("check role", "role=" + role);
                }
                Profile profile = new Profile(name, email, role);
                mlistener.gotoProfile(profile);
            }
        });

    }
    EditUserFragmentListener mlistener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditUserBinding.inflate(inflater,container, false);
        return binding.getRoot();
        // Inflate the layout for this fragment
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener=(EditUserFragmentListener) context;
    }
    interface EditUserFragmentListener{
        void gotoProfile(Profile profile);
        void cancelEditUser();
    }

}