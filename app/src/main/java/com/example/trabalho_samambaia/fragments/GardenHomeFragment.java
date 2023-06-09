package com.example.trabalho_samambaia.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.trabalho_samambaia.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class GardenHomeFragment extends Fragment {

    FloatingActionButton addPlantsButton;

    public GardenHomeFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_garden_home, container, false);

        addPlantsButton = view.findViewById(R.id.add_plants);
        addPlantsButton.setOnClickListener(v -> showPopupMenu());

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
}
