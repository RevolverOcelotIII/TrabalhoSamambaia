package com.example.trabalho_samambaia.activities;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.fragments.GardenHomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GardenActivity extends AppCompatActivity {

    private FloatingActionButton addPlantsButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.garden_home);

        Toolbar cadastro_planta_toolbar = findViewById(R.id.topBar);
        //setSupportActionBar(cadastro_planta_toolbar);
        //getSupportActionBar().hide();

//        // Obtém o FragmentManager
//        FragmentManager fragmentManager = getSupportFragmentManager();
//
//        // Inicia uma transação de fragmento
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        // Substitui o contêiner pelo fragmento desejado
//        GardenHomeFragment fragment = new GardenHomeFragment();
//        transaction.replace(R.id.cadastro_planta_fragment, fragment);
//
//        // Finaliza a transação
//        transaction.commit();

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        NavController navController = navHostFragment.getNavController();
        BottomNavigationView navBottom = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(navBottom,navController);

        addPlantsButton = findViewById(R.id.add_plants);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            addPlantsButton.setOnClickListener(v -> showPopupMenu());
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, addPlantsButton);
        popupMenu.getMenuInflater().inflate(R.menu.add_plants_menu, popupMenu.getMenu());
        popupMenu.setForceShowIcon(true);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_manual:
                    Intent intent = new Intent(this, CadastroPlantaActivity.class);
                    intent.putExtra("register_type",1);
                    startActivity(intent);
                    return true;
                case R.id.menu_identify:
                    // Ação para a opção "Identificar"
                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();
    }
}