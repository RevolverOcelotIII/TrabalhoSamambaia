package com.example.trabalho_samambaia.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaListAdapter;
import com.example.trabalho_samambaia.dao.PlantaDAO;
import com.example.trabalho_samambaia.databinding.FragmentFirstBinding;
import com.example.trabalho_samambaia.model.Planta;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class FirstFragment extends Fragment {

    private ViewPager2 viewPager;
    private PlantViewAdapter plantViewAdapter;
    private static Planta plant;
    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);


        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        plantViewAdapter = new PlantViewAdapter(this);
        TabLayout tabLayout = view.findViewById(R.id.plant_view_tab);
        viewPager = view.findViewById(R.id.plant_view_page);
        viewPager.setAdapter(plantViewAdapter);
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> tab.setText("Cuidados")).attach();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class PlantViewAdapter extends FragmentStateAdapter{

        public PlantViewAdapter(Fragment fragment){
            super(fragment);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            Fragment fragment = new PlantViewFragmentDescription();
            Bundle args = new Bundle();

            args.putInt(PlantViewFragmentDescription.ARG_OBJECT, position + 1);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public int getItemCount() {
            return 1;
        }

    }

    public static class PlantViewFragmentDescription extends Fragment{
        public static final String ARG_OBJECT = "object";

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            return inflater.inflate(R.layout.fragment_second, container, false);
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            Bundle args = getArguments();
            ((TextView) view.findViewById(R.id.textWatering))
                    .setText(getWateringGuide(PlantaListAdapter.getPlantPosition().getRegagem()));
            ((TextView) view.findViewById(R.id.textSun))
                    .setText(getSunlightGuide(PlantaListAdapter.getPlantPosition().getBanho_sol()));
            ((TextView) view.findViewById(R.id.textFertilize))
                    .setText(PlantaListAdapter.getPlantPosition().getProxima_adubagem());
        }

        private String getWateringGuide(String watering){
            String wateringFinal = "";
            LocalTime time = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                time = LocalTime.now();
            }
            switch (watering){
                case "Frequent":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        time = LocalTime.of(time.getHour(),time.getMinute());
                    }
                    wateringFinal = time.toString();
                    break;
                case "Average":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        time = LocalTime.of(time.getHour(),time.getMinute());
                        time.plusHours(7);
                    }
                    wateringFinal = time.toString();
                    break;
                case "Minimum":
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        time = LocalTime.of(time.getHour(),time.getMinute());
                        time.plusHours(60);
                    }
                    wateringFinal = time.toString();
                    break;
                case "None":
                    break;
            }
            return wateringFinal;
        }

        private String getSunlightGuide(String sunLight){
            String sunLightGuide = "";
            LocalTime startTime = null;
            LocalTime endTime = null;
            if(sunLight.contains("Full shade")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startTime = LocalTime.of(6, 0);
                    endTime = LocalTime.of(10, 00);
                }
                sunLightGuide = startTime.toString() + '~' + endTime.toString();
            }

                else if( sunLight.contains("Part shade")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startTime = LocalTime.of(6, 0);
                    endTime = LocalTime.of(12, 00);
                }
                sunLightGuide = startTime.toString() + '~' + endTime.toString();
            }

                else if(sunLight.contains("Sun part shade")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startTime = LocalTime.of(6, 0);
                    endTime = LocalTime.of(8, 00);
                }
                sunLightGuide = startTime.toString() + '~' + endTime.toString();
            }

                else if(sunLight.contains( "Full sun")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    startTime = LocalTime.of(6, 0);
                    endTime = LocalTime.of(16, 00);
                }
                sunLightGuide = startTime.toString() + '~' + endTime.toString();
            }


            return sunLightGuide;
        }
    }

}