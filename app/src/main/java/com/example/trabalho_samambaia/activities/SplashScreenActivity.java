package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.trabalho_samambaia.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash_screen);
        new Handler().postDelayed(() -> {
            // Após o tempo definido, inicia a MainActivity
            Intent intent = new Intent(SplashScreenActivity.this, GardenActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}