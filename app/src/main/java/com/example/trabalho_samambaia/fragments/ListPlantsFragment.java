package com.example.trabalho_samambaia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaListAdapter;

import java.util.Collections;

public class ListPlantsFragment extends Fragment {

    private RecyclerView plantRecyclerView;
    private PlantaListAdapter plantasAdapter;


    public ListPlantsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        plantasAdapter = new PlantaListAdapter(Collections.emptyList());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_planta_item_list, container, false);

        // Encontre a RecyclerView no layout
        plantRecyclerView = view.findViewById(R.id.planta_item_list);

        // Defina o layout manager e o adaptador para a RecyclerView
        plantRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        plantRecyclerView.setAdapter(plantasAdapter);

        return view;
    }

    public void setPlantaListAdapter(PlantaListAdapter adapter) {
        plantasAdapter = adapter;
        if (plantRecyclerView != null) {
            plantRecyclerView.setAdapter(plantasAdapter);
        }
    }
}
