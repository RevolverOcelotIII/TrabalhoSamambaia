package com.example.trabalho_samambaia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FloatingActionButton addPlantsButton;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(this, HomePage.class);
            startActivity(intent);
        }, 2000);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showHomeScreen() {
        setContentView(R.layout.activity_main);

        // Configurar o FloatingActionButton
        addPlantsButton = findViewById(R.id.add_plants);
        addPlantsButton.setOnClickListener(v -> showPopupMenu());

        // Configurar o BottomNavigationView
        NavHostFragment navFrag = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
        NavController navController = navFrag.getNavController();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);
        //bottomNavigationView = findViewById(R.id.bottomNavigationView);
        //bottomNavigationView.setOnItemSelectedListener((NavigationBarView.OnItemSelectedListener) this);



        // Configurar o botão de cadastro
//        Button registerButton = findViewById(R.id.registerButton);
//        registerButton.setOnClickListener(v -> {
//            if (isUserLoggedIn()) {
//                markUserAsLoggedIn();
//            } else {
//                // O usuário não está logado, redirecionar para a página de cadastro
//                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, addPlantsButton);
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

