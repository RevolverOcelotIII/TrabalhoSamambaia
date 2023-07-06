package com.example.trabalho_samambaia.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.dao.PlantaDAO;
import com.example.trabalho_samambaia.fragments.ListPlantsFragment;
import com.example.trabalho_samambaia.model.Planta;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomePage extends AppCompatActivity {

    private BottomNavigationView navBottom;

    private FloatingActionButton addPlantsButton;
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<String> requestCameraPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        cameraLauncher.launch(takePictureIntent);
                    }
                } else {
                    // A permissão foi negada
                }
            });

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

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // A foto foi tirada com sucesso, você pode acessar a imagem capturada através do objeto Intent
                Intent data = result.getData();
                try {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    Intent intent = new Intent(this, CadastroPlantaActivity.class);
                    intent.putExtra("foto_bitmap", bitmap);
                    startActivity(intent);

                } catch (Exception e) {
                    Log.d("teste", e.getMessage());
                }


            } else {
                // O usuário cancelou a captura da foto ou ocorreu algum erro
                // Lide com isso de acordo com sua lógica de negócio
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        PlantaDAO plantaDAO = new PlantaDAO(this);
        List<Planta> plantas = plantaDAO.getAllPlantas();
        if(plantas.size()>0){
            try {
                ListPlantsFragment fragment = new ListPlantsFragment();
                NavHostFragment navFrag = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);
                NavController navController = navFrag.getNavController();
                navController.navigate(R.id.listPlantsFragment);
            }catch (Exception e){
                Log.d("teste", "onCreateView: " + e.getMessage());
            }
        } else Toast.makeText(this, "Sem planta", Toast.LENGTH_SHORT).show();
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
                    intent.putExtra("register_type", 1);
                    startActivity(intent);
                    return true;
                case R.id.menu_identify:
                    // No método onCreate() ou onCreateView() do seu Fragment

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(this.getPackageManager()) != null) {
                            cameraLauncher.launch(takePictureIntent);
                        }
                    } else {
                        requestCameraPermissionLauncher.launch(Manifest.permission.CAMERA);
                    }
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