package com.example.news.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.news.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CActivity extends AppCompatActivity {
    BottomNavigationView mBottomNavigationView;
    public DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ch);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /*drawerLayout = findViewById(R.id.Drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/






        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new HomeFragment()).commit();
        mBottomNavigationView.setSelectedItemId(R.id.Home);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()) {
                    case R.id.Home:
                       fragment =new HomeFragment();
                        break;
                    case R.id.Urgent:
                        fragment =new UrgentFragment();
                        break;
                    case R.id.Policy:
                        fragment =new PolicyFragment();
                        break;
                    case R.id.Sport:
                        fragment =new SportFragment();
                        break;
                    case R.id.Economie:
                        fragment =new EconomieFragment();
                        break;
                    
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
                return true;
            }
        });

    }

}
