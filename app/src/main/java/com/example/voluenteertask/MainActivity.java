package com.example.voluenteertask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.voluenteertask.Fragment.HomeFragment;
//import com.example.voluenteertask.Fragment.NotificationFragment;
import com.example.voluenteertask.Fragment.ProfileFragment;
import com.example.voluenteertask.Fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView=findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        Bundle intent=getIntent().getExtras();
        if (intent!=null)
        {
            String publisher=intent.getString("publisherid");
            SharedPreferences.Editor editor=getSharedPreferences("PREFS",MODE_PRIVATE).edit();
            editor.putString("profileId",publisher);
            editor.apply();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new ProfileFragment()).commit();
        }

        else {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener=
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId())
                    {
                        case R.id.nav_home:

                            selectedFragment=new HomeFragment();

                            break;

                        case R.id.nav_search:

                            selectedFragment=new SearchFragment();
                            break;

                        case R.id.nav_add:

                            selectedFragment=null;
                            startActivity(new Intent(MainActivity.this,PostActivity.class));
                            break;

//                        case R.id.nav_favourite:
//
//                            selectedFragment=new NotificationFragment();
//                            break;

                        case R.id.nav_profile:

                            SharedPreferences.Editor editor=getSharedPreferences("PREFS",MODE_PRIVATE).edit();
                            editor.putStringSet("profileId", Collections.singleton(FirebaseAuth.getInstance().getCurrentUser().getUid()));
                            editor.apply();
                            selectedFragment=new ProfileFragment();
                            break;
                    }

                    if (selectedFragment!=null)
                    {
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                    }
                    return true;
                }
            };
}
