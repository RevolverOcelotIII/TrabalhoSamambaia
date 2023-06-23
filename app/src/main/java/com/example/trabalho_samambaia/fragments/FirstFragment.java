package com.example.trabalho_samambaia.fragments;

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
import com.example.trabalho_samambaia.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    ViewPager2 viewPager;
    PlantViewAdapter plantViewAdapter;

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
        viewPager = view.findViewById(R.id.plant_view_page);
        viewPager.setAdapter(plantViewAdapter);
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
            return 100;
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
            ((TextView) view.findViewById(android.R.id.text1))
                    .setText(Integer.toString(args.getInt(ARG_OBJECT)));
        }
    }

}