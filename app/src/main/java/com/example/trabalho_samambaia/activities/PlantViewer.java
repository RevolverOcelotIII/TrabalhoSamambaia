package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaListAdapter;

import com.example.trabalho_samambaia.model.Planta;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.ImageView;



import com.example.trabalho_samambaia.databinding.ActivityVisualizacaoPlantaBinding;

import java.util.List;

public class PlantViewer extends AppCompatActivity {


    private ActivityVisualizacaoPlantaBinding binding;
    private Planta plants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVisualizacaoPlantaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Bundle args = new Bundle();

        ImageView img = binding.getRoot().findViewById(R.id.imageView2);
        Glide.with(binding.getRoot()).load(PlantaListAdapter.getPlantPosition().getImagem_url()).into(img);

        binding.toolbar.setTitle(PlantaListAdapter.getPlantPosition().getNome_comum());
        binding.toolbar.setSubtitle(PlantaListAdapter.getPlantPosition().getNome_cientifico());
        

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(binding.getRoot().getContext(), PlantEditViewActivity.class);
               binding.getRoot().getContext().startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}