package com.example.trabalho_samambaia.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.trabalho_samambaia.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GardenHomeFragment extends Fragment {

    private FloatingActionButton addPlantsButton;
    private BottomNavigationView navBottom;

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

        addPlantsButton = view.findViewById(R.id.add_plants);
        addPlantsButton.setOnClickListener(v -> showPopupMenu());

        navBottom = view.findViewById(R.id.bottomNavigationView);
        navBottom.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.alert:
                    openAlertItemFragment();
                    return true;
                default:
                    return false;
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

    private void openAlertItemFragment() {
        // Cria uma instância do AlertitemFragment
        AlertitemFragment alertitemFragment = AlertitemFragment.newInstance(1);

        // Obtém o FragmentManager do AppCompatActivity
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        // Inicia a transação do FragmentManager
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Substitui o fragmento atual pelo AlertitemFragment
        transaction.replace(R.id.container, alertitemFragment);

        // Adiciona a transação à pilha de volta (para permitir voltar ao fragmento anterior)
        transaction.addToBackStack(null);

        // Executa a transação
        transaction.commit();
    }

}
