package com.example.trabalho_samambaia.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
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

        // Obtém o FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();

        // Inicia uma transação de fragmento
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Substitui o contêiner pelo fragmento desejado
        GardenHomeFragment fragment = new GardenHomeFragment();
        transaction.replace(R.id.cadastro_planta_fragment, fragment);

        // Finaliza a transação
        transaction.commit();
    }
}