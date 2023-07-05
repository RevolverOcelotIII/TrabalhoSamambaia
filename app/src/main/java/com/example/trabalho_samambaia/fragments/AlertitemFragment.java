package com.example.trabalho_samambaia.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import com.example.trabalho_samambaia.adapters.MyAlertitemRecyclerViewAdapter;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.dao.PlantaDAO;
import com.example.trabalho_samambaia.model.Planta;


import java.util.ArrayList;
import java.util.List;

/**
 * Classe para renderizar recycler view dos alertas
 *
 */
public class AlertitemFragment extends Fragment {


    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    private PlantaDAO plantaDAO;
    private float startX;
    private float startY;



    public AlertitemFragment() {
    }


    @SuppressWarnings("unused")
    public static AlertitemFragment newInstance(int columnCount) {

        AlertitemFragment fragment = new AlertitemFragment();
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

            View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        try{

            plantaDAO = new PlantaDAO(this.getContext());
            MyAlertitemRecyclerViewAdapter adapter = new MyAlertitemRecyclerViewAdapter(plantaDAO.getAllPlantas());


            // Set the adapter
            if (view instanceof RecyclerView) {
                Context context = view.getContext();
                RecyclerView recyclerView = (RecyclerView) view;
                if (mColumnCount <= 1) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(context));
                } else {
                    recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
                }
                recyclerView.setAdapter(adapter);

                adapter.setOnItemTouchListener((view1, event, position) -> {
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getX();
                            startY = event.getY();

                        case MotionEvent.ACTION_UP:
                            float endX = event.getX();
                            float endY = event.getY();
                            float deltaX = endX - startX;
                            float deltaY = endY - startY;

                            if (Math.abs(deltaX) > Math.abs(deltaY) && deltaX < 0) {
                                view1.setVisibility(View.GONE);
                            }
                    }
                });
            }




            return view;
        }
        catch (Exception e){
            view.setVisibility(View.GONE);
            return view;
        }
    }




}