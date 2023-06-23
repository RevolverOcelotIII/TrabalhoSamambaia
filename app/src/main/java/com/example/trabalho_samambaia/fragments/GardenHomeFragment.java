package com.example.trabalho_samambaia.fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.activities.CadastroPlantaActivity;
import com.example.trabalho_samambaia.activities.GardenActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.URI;

public class GardenHomeFragment extends Fragment {

    private FloatingActionButton addPlantsButton;
    private BottomNavigationView navBottom;

    private ActivityResultLauncher<Intent> cameraLauncher;

    public GardenHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NewApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_garden_home, container, false);

//        addPlantsButton = view.findViewById(R.id.add_plants);
//        addPlantsButton.setOnClickListener(v -> showPopupMenu());

//        navBottom = view.findViewById(R.id.bottomNavigationView);
//        navBottom.setOnItemSelectedListener(item -> {
//            switch (item.getItemId()) {
//                case R.id.alert:
//                    openAlertItemFragment();
//                    return true;
//                default:
//                    return false;
//            }
//        });

        cameraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                // A foto foi tirada com sucesso, você pode acessar a imagem capturada através do objeto Intent
                Intent data = result.getData();
                try {
                    Bitmap bitmap = data.getParcelableExtra("data");
                    Intent intent = new Intent(getActivity(), CadastroPlantaActivity.class);
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
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    private void showPopupMenu() {
        PopupMenu popupMenu = new PopupMenu(requireContext(), addPlantsButton);
        popupMenu.getMenuInflater().inflate(R.menu.add_plants_menu, popupMenu.getMenu());
        popupMenu.setForceShowIcon(true);

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_manual:
                    Intent intent = new Intent(getActivity(), CadastroPlantaActivity.class);
                    intent.putExtra("register_type", 1);
                    startActivity(intent);
                    return true;
                case R.id.menu_identify:
                    // No método onCreate() ou onCreateView() do seu Fragment

                    if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
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

//    private void openAlertItemFragment() {
//        // Cria uma instância do AlertitemFragment
//        AlertitemFragment alertitemFragment = AlertitemFragment.newInstance(1);
//
//        // Obtém o FragmentManager do AppCompatActivity
//        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
//
//        // Inicia a transação do FragmentManager
//        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//        // Substitui o fragmento atual pelo AlertitemFragment
//        transaction.replace(R.id.container, alertitemFragment);
//
//        // Adiciona a transação à pilha de volta (para permitir voltar ao fragmento anterior)
//        transaction.addToBackStack(null);
//
//        // Executa a transação
//        transaction.commit();
//    }

    private ActivityResultLauncher<String> requestCameraPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                        cameraLauncher.launch(takePictureIntent);
                    }
                } else {
                    // A permissão foi negada
                }
            });
}
