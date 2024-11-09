package com.assignment.assignment08;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener,CreateUserFragment.CreateUserFragmentListener,StateFragment.StateFragmentListener,
MaritalStatusFragment.MaritalStatusFragmentListener,EduLevelFragment.EduLevelListener, DoBFragment.DoBFragmentListener{
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
    public void gotoState() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new StateFragment(),"state")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoDoB() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new DoBFragment(),"dob")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoMaritalStatus() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new MaritalStatusFragment(),"marital-status")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoEduLevel() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView,new EduLevelFragment(),"edu-level")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void cancelState() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendState(String state) {
        CreateUserFragment createUserFragment= (CreateUserFragment) getSupportFragmentManager().findFragmentByTag("create-user");
        if (createUserFragment!=null){
            createUserFragment.setSelectedState(state);
            //getSupportFragmentManager().popBackStack();
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelMaritalStatus() {
        getSupportFragmentManager().popBackStack();

    }

    @Override
    public void sendMaritalStatus(String maritalStatus) {
        CreateUserFragment createUserFragment= (CreateUserFragment) getSupportFragmentManager().findFragmentByTag("create-user");
        if (createUserFragment!=null){
            createUserFragment.setSelectedMaritalStatus(maritalStatus);
            //getSupportFragmentManager().popBackStack();
        }
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void cancelEduLevel() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendEduLevel(String eduLevel) {
        CreateUserFragment createUserFragment= (CreateUserFragment) getSupportFragmentManager().findFragmentByTag("create-user");
        if (createUserFragment!=null){
            createUserFragment.setSelectedEduLevel(eduLevel);
            //getSupportFragmentManager().popBackStack();
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
    public void sendProfile(User profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();

    }
}