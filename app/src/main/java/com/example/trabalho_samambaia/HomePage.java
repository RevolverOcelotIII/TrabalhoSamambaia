package com.example.trabalho_samambaia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.PopupMenu;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomePage extends AppCompatActivity {

    private BottomNavigationView navBottom;

    private FloatingActionButton addPlantsButton;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configurar o FloatingActionButton
        addPlantsButton = findViewById(R.id.add_plants);
        addPlantsButton.setOnClickListener(v -> showPopupMenu());

        // Configurar o BottomNavigationView
        NavHostFragment navFrag = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        NavController navController = navFrag.getNavController();
        navBottom = (BottomNavigationView) findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(navBottom, navController);



        // Configurar o botão de cadastro
//        Button registerButton = findViewById(R.id.registerButton);
//        registerButton.setOnClickListener(v -> {
//            if (isUserLoggedIn()) {
//                markUserAsLoggedIn();
//            } else {
//                // O usuário não está logado, redirecionar para a página de cadastro
//                Intent intent = new Intent(this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });


    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(this, addPlantsButton);
        popupMenu.getMenuInflater().inflate(R.menu.add_plants_menu, popupMenu.getMenu());
        popupMenu.setForceShowIcon(true);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_manual:
                    // Ação para a opção "Manual"
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

    private boolean isUserLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        return sharedPreferences.getBoolean("user_logged_in", false);
    }

    private void markUserAsLoggedIn() {
        SharedPreferences sharedPreferences = getSharedPreferences("login_prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("user_logged_in", true);
        editor.apply();
    }
}