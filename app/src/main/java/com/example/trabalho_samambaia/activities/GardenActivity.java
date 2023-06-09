package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.fragments.GardenHomeFragment;

public class GardenActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.garden_home);

        Toolbar cadastro_planta_toolbar = findViewById(R.id.cadastro_planta_toolbar);
        setSupportActionBar(cadastro_planta_toolbar);
        getSupportActionBar().hide();

        GardenHomeFragment gardenHomeFragment = new GardenHomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cadastro_planta_fragment, gardenHomeFragment);
        transaction.commit();

    }
}