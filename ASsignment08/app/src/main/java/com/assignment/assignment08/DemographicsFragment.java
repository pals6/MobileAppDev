package com.assignment.assignment08;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.assignment08.databinding.FragmentDemographicsBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DemographicsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DemographicsFragment extends Fragment {

    private static final String ARG_PARAM_DEMO = "ARG_PARAM_DEMO";
    private static final String ARG_PARAM_LABEL="ARG_PARAM_LABEL";
    private User mProfile;
    private String label;
    public static DemographicsFragment newInstance(User profile,String label) {
        DemographicsFragment fragment = new DemographicsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_DEMO, profile);
        args.putString(ARG_PARAM_LABEL, label);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfile = (User) getArguments().getSerializable(ARG_PARAM_DEMO);
            this.label=getArguments().getString(ARG_PARAM_LABEL);
        }
    }
    FragmentDemographicsBinding demographicsBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        demographicsBinding=FragmentDemographicsBinding.inflate(inflater,container,false);
        return demographicsBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        demographicsBinding.textViewDOBOP.setText(mProfile.getDob());
        demographicsBinding.textViewMaritalStatusOP.setText(mProfile.getMaritalStatus());
        demographicsBinding.textViewEduLevelOP.setText(mProfile.getEduLevel());
    }
}