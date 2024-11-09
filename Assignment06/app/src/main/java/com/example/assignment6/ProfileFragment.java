package com.example.assignment6;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.assignment6.databinding.FragmentProfileBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_PARAM_PROFILE = "ARG_PARAM_PROFILE";
    private Profile mProfile;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(Profile profile) {
        ProfileFragment fragment = new ProfileFragment();
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
    FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container, false);
        return binding.getRoot();
    }
    ProfileFragment.ProfileFragmentListener mlistener;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.NAME.setText(mProfile.getName());
        binding.EMAIL.setText(mProfile.getEmail());
        binding.ROLE.setText(mProfile.getRole());
        String name= mProfile.getName();
        String email= mProfile.getEmail();
        String role= mProfile.getRole();
        Profile profile = new Profile(name, email, role);
        binding.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.gotoEditUser(profile);
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener=(ProfileFragmentListener) context;
    }

    ProfileFragmentListener mListener;
    interface ProfileFragmentListener{
        void gotoEditUser(Profile profile);
    }

}