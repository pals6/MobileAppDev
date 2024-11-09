package edu.uncc.assignment06;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

import edu.uncc.assignment06.models.AgeGroup;
import edu.uncc.assignment06.models.Mood;
import edu.uncc.assignment06.models.User;

public class MainActivity extends AppCompatActivity implements UsersFragment.UsersListener, AddUserFragment.AddUserListener, SelectAgeGroupFragment.SelectAgeGroupListener, SelectMoodFragment.SelectMoodListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.rootView, new UsersFragment())
                .commit();
    }

    @Override
    public void gotoAddUser() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new AddUserFragment(), "add-user-fragment" )
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoProfile(User user) {

    }

    @Override
    public void doneAddingUser() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void gotoGetAgeGroup() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectAgeGroupFragment() )
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void gotoGetMood() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, new SelectMoodFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void sendSelectedAgeGroup(AgeGroup ageGroup) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if(fragment != null){
            fragment.setSelectedAgeGroup(ageGroup);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelAgeGroupSelection() {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void sendSelectedMood(Mood mood) {
        AddUserFragment fragment = (AddUserFragment) getSupportFragmentManager().findFragmentByTag("add-user-fragment");
        if(fragment != null){
            fragment.setSelectedMood(mood);
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public void cancelMoodSelection() {
        getSupportFragmentManager().popBackStack();
    }
}