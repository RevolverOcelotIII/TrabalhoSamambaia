package com.example.trabalho_samambaia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.trabalho_samambaia.databinding.FragmentSecondBinding;

public class CareTab extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}