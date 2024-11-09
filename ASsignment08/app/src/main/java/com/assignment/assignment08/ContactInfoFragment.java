package com.assignment.assignment08;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.assignment.assignment08.databinding.FragmentContactInfoBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactInfoFragment extends Fragment {

    private static final String ARG_PARAM_CONTACT = "ARG_PARAM_CONTACT";
    private static final String ARG_PARAM_LABEL="ARG_PARAM_LABEL";
    private User mProfile;
    private String label;
    FragmentContactInfoBinding contactInfoBinding;


    public ContactInfoFragment() {
        // Required empty public constructor
    }

    public static ContactInfoFragment newInstance(User profile,String label) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CONTACT, profile);
        args.putString(ARG_PARAM_LABEL, label);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfile = (User) getArguments().getSerializable(ARG_PARAM_CONTACT);
            this.label=getArguments().getString(ARG_PARAM_LABEL);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        contactInfoBinding=FragmentContactInfoBinding.inflate(inflater,container,false);
        return contactInfoBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        contactInfoBinding.textViewNameOP.setText(mProfile.getName());
        contactInfoBinding.textViewEmailOP.setText(mProfile.getEmail());
        contactInfoBinding.textViewPhoneOP.setText(mProfile.getPhone());
        contactInfoBinding.textViewStateOP.setText(mProfile.getState());
    }
}