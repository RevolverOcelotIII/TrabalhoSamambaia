package com.example.trabalho_samambaia.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.MyPlantCarouselRecyclerViewAdapter;
import com.example.trabalho_samambaia.fragments.placeholder.PlaceholderContent;
import com.google.android.material.carousel.CarouselLayoutManager;

/**
 * A fragment representing a list of Items.
 */
public class VaseCarouselFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public VaseCarouselFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static VaseCarouselFragment newInstance(int columnCount) {
        VaseCarouselFragment fragment = new VaseCarouselFragment();
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
            recyclerView.setAdapter(new MyPlantCarouselRecyclerViewAdapter(PlaceholderContent.ITEMS));
        }
        return view;
    }
}