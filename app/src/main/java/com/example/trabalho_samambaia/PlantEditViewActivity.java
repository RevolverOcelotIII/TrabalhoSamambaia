package com.example.trabalho_samambaia;

import android.os.Bundle;



import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;

import androidx.navigation.ui.AppBarConfiguration;


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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}