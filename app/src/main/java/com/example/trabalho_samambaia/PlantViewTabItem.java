package com.example.trabalho_samambaia;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlantViewTabItem#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlantViewTabItem extends Fragment {


    private String mParam1;
    private String mParam2;
    private ViewPager2 viewPager;

    public PlantViewTabItem() {

    }


    public static PlantViewTabItem newInstance(String param1, String param2) {
        PlantViewTabItem fragment = new PlantViewTabItem();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_plant_view_tab_item, container, false);
//    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TabLayout tabLayout = view.findViewById(R.id.plant_view_tab);
        viewPager = viewPager.findViewById(R.id.plant_view_page);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();
    }
}