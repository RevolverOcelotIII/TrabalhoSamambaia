package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho_samambaia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.FirebaseAnalytics;

public class MainActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    BottomNavigationView bottomNavigationView;

    HomePage home = new HomePage();

    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.fragment_splash_screen);

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

//        bottomNavigationView = findViewById(R.id.bottomNavigationView);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.container, home).commit();

        setupFirebase();
    }

    private void setupFirebase() {
        FirebaseApp.initializeApp(this);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, "onCreate");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle);
    }
}
