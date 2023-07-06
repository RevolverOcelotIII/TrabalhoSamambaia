package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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

        Intent intent = getIntent();

        Toolbar cadastro_planta_toolbar = findViewById(R.id.cadastro_planta_toolbar);
        setSupportActionBar(cadastro_planta_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (intent.getParcelableExtra("foto_bitmap") != null) {
            try {
                getSupportActionBar().setTitle(R.string.customizeYourPlant);
                Bundle args = new Bundle();
                args.putParcelable("foto_bitmap", intent.getParcelableExtra("foto_bitmap"));
                CadastroPlantaFragment fragment = new CadastroPlantaFragment();
                fragment.setArguments(args);
                FragmentManager fragmentManager = (this.getSupportFragmentManager());
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.cadastro_planta_fragment, fragment);
                fragmentTransaction.commit();

            } catch (Exception e) {
                Log.d("teste", e.getMessage());
            }

        } else if (intent.getIntExtra("plantaedit_id", -1) != -1) {
            getSupportActionBar().setTitle(R.string.editYourPlant);
            Log.d("teste", "onCreate: "+intent.getIntExtra("plantaedit_id", -1));
            Bundle args = new Bundle();
            args.putInt("plantaedit_id",intent.getIntExtra("plantaedit_id", -1));
            CadastroPlantaFragment plantaBaseFragment = new CadastroPlantaFragment();
            plantaBaseFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.cadastro_planta_fragment, plantaBaseFragment);
            transaction.commit();
        } else {
            getSupportActionBar().setTitle(R.string.selectYourPlant);
            CadastroPlantaBaseFragment plantaBaseFragment = new CadastroPlantaBaseFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.cadastro_planta_fragment, plantaBaseFragment);
            transaction.commit();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                Fragment currentFragment = fragmentManager.findFragmentById(R.id.cadastro_planta_fragment);
                if (currentFragment instanceof CadastroPlantaBaseFragment) {
                    getSupportActionBar().setTitle(R.string.selectYourPlant);
                } else if (currentFragment instanceof CadastroPlantaFragment) {
                    getSupportActionBar().setTitle(R.string.customizeYourPlant);
                }
            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}