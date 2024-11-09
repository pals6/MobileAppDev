package com.assignment.assignment07;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener, CreateUserFragment.CreateUserFragmentListener, CountryFragment.CountryFragmentListener, DoBFragment.DoBFragmentListener {
    public static final String TAG="demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                        .add(R.id.containerView,new MainFragment(),"main-fragment").commit();

        Log.d(TAG, "MainActivity: onCreate: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "MainActivity:onResume: ");
    }

    @Override
    public void gotoCreateUser() {
        Log.d(TAG, "gotoCreateUser: ");
         getSupportFragmentManager().beginTransaction()
                 .replace(R.id.containerView,new CreateUserFragment(),"create-user")
                 .commit();

    }

    @Override
    public void sendProfile() {

    }

//    @Override
//    public void sendProfile() {
//
//    }

    @Override
    public void gotoCountry() {
        Log.d(TAG, "gotoCountry: ");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new CountryFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoDoB() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new DoBFragment(),"dob-fragment")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendProfile(User profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();

    }


    @Override
    public void cancelCountry() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendCountry(String country) {
        CreateUserFragment createUserFragment= (CreateUserFragment) getSupportFragmentManager().findFragmentByTag("create-user");
        if (createUserFragment!=null){
            createUserFragment.setSelectedCountry(country);
        }
        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void sendDOB(String dob) {
        CreateUserFragment createUserFragment= (CreateUserFragment) getSupportFragmentManager().findFragmentByTag("create-user");
        if(createUserFragment!=null){
            createUserFragment.setSelectedDOB(dob);
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelDOB() {
        getSupportFragmentManager().popBackStack();
    }
}