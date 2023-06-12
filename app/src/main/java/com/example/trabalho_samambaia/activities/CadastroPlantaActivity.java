package com.example.trabalho_samambaia.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.trabalho_samambaia.fragments.CadastroPlantaBaseFragment;
import com.example.trabalho_samambaia.fragments.CadastroPlantaFragment;
import com.example.trabalho_samambaia.R;

public class CadastroPlantaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_planta);

        Toolbar cadastro_planta_toolbar = findViewById(R.id.cadastro_planta_toolbar);
        setSupportActionBar(cadastro_planta_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CadastroPlantaBaseFragment plantaBaseFragment = new CadastroPlantaBaseFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cadastro_planta_fragment, plantaBaseFragment);
        transaction.commit();
        /*
        CadastroPlantaFragment plantaFragment = new CadastroPlantaFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.cadastro_planta_fragment, plantaFragment);
        transaction.commit();*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}