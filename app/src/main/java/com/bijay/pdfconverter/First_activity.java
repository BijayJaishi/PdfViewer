package com.bijay.pdfconverter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.bijay.pdfconverter.fragments.Fragment_pdflist;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class First_activity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_activity);

        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navlistner);

        if(savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.containermain,new Fragment_pdflist()).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navlistner =  new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){

                case R.id.action_news:
                        selectedFragment = new Fragment_pdflist();
                        break;


            }
            getSupportFragmentManager().beginTransaction().replace(R.id.containermain,selectedFragment).commit();

            return true;
        }
    };
}
