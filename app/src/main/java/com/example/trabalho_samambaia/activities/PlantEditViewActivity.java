package com.example.trabalho_samambaia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;

import androidx.navigation.ui.AppBarConfiguration;


import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaListAdapter;
import com.example.trabalho_samambaia.databinding.ActivityPlantEditViewBinding;

public class PlantEditViewActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityPlantEditViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPlantEditViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar materialToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView img = binding.getRoot().findViewById(R.id.userPlantEdit);
        Glide.with(binding.getRoot()).load(PlantaListAdapter.getPlantPosition().getImagem_url()).into(img);
        TextView plantName = binding.getRoot().findViewById(R.id.userPlantName);
        TextView plantScientificName = binding.getRoot().findViewById(R.id.userPlantCientifcName);
        plantName.setText(PlantaListAdapter.getPlantPosition().getNome_comum());
        plantScientificName.setText(PlantaListAdapter.getPlantPosition().getNome_cientifico());

        findViewById(R.id.saveButton).setOnClickListener(view -> {
            Intent intent = new Intent(this,HomePage.class);
            view.getContext().startActivity(intent);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}