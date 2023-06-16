package com.example.trabalho_samambaia;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import com.example.trabalho_samambaia.databinding.ActivityVisualizacaoPlantaBinding;
import com.google.android.material.tabs.TabLayout;

public class PlantViewer extends AppCompatActivity {


    private ActivityVisualizacaoPlantaBinding binding;
    private TabLayout tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityVisualizacaoPlantaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar materialToolbar = findViewById(R.id.materialToolbar);
        materialToolbar.setSubtitle("Tracheophyta");
        setSupportActionBar(materialToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tab = findViewById(R.id.tab);
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {


            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Fragment fragment = null;

                switch (position) {
                    case 0:
                        fragment = new GeneralTab();
                        break;
                    case 1:
                        fragment = new CareTab();
                        break;
                }

                if (fragment != null) {
                    // Replace the current fragment with the selected one
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView, fragment, null)
                            .commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Handle tab unselected event if needed
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topbar_menu, menu);

        return true;

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void editButtonClick(MenuItem menuItem){
        Intent intent = new Intent(this,PlantEditViewActivity.class);
        startActivity(intent);
    }


}