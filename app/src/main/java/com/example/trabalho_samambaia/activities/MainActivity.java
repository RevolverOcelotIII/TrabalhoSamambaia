package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho_samambaia.Home;
import com.example.trabalho_samambaia.HomePage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.example.trabalho_samambaia.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    Home home = new Home();

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();
    }
}
