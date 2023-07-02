package com.example.trabalho_samambaia.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.MyPlantCarouselRecyclerViewAdapter;
import com.example.trabalho_samambaia.fragments.placeholder.PlaceholderContent;
import com.example.trabalho_samambaia.model.Planta;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A fragment representing a list of Items.
 */
public class PlantCarouselFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PlantCarouselFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static PlantCarouselFragment newInstance(int columnCount) {
        PlantCarouselFragment fragment = new PlantCarouselFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carousel_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new CarouselLayoutManager());
            recyclerView.setAdapter(new MyPlantCarouselRecyclerViewAdapter(getPlantSugestion()));
        }
        return view;
    }

    private List<Planta> getPlantSugestion(){
        List<Planta> plantsList = new ArrayList<>();

        for(int i = 0; i<5;i++){
            plantsList.add(Planta.getRandomPlanta(this.getContext()));
        }

        return plantsList;
    }
}