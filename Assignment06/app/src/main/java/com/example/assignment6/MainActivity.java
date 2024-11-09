package com.example.assignment6;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements MainFragment.MainFragmentListener,
        CreateUserFragment.CreateUserFragmentListener,
        ProfileFragment.ProfileFragmentListener, EditUserFragment.EditUserFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        getSupportFragmentManager().beginTransaction()
                .add(R.id.rootView, new MainFragment())
                .commit();
    }

    @Override
    public void gotoRegistration() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView,new CreateUserFragment())
                .commit();
    }

    @Override
    public void gotoProfile(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, ProfileFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void cancelEditUser() {
        getSupportFragmentManager().popBackStack();
    }


    @Override
    public void gotoEditUser(Profile profile) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.rootView, EditUserFragment.newInstance(profile))
                .addToBackStack(null)
                .commit();
    }

}