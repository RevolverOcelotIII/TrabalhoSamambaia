package com.example.trabalho_samambaia.fragments;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaAdapter;
import com.example.trabalho_samambaia.model.Planta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class CadastroPlantaBaseFragment extends Fragment {

    private SearchView busca_planta_searchView;
    private RecyclerView lista_plantas_recycler;
    private PlantaAdapter plantaAdapter;

    private List<Planta> lista_plantas;

    public CadastroPlantaBaseFragment() {
        // Required empty public constructor
    }



    public static CadastroPlantaBaseFragment newInstance(String param1, String param2) {
        CadastroPlantaBaseFragment fragment = new CadastroPlantaBaseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lista_plantas = Planta.gerarListaPlaceholder(this.getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_cadastro_planta_base, container, false);
        busca_planta_searchView = view.findViewById(R.id.planta_busca_searchView);
        lista_plantas_recycler = view.findViewById(R.id.planta_lista_recyclerView);

        busca_planta_searchView.clearFocus();
        busca_planta_searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return false;
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        lista_plantas_recycler.setLayoutManager(layoutManager);
        plantaAdapter = new PlantaAdapter(lista_plantas);
        lista_plantas_recycler.setAdapter(plantaAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    private void filterList(String busca) {
        List<Planta> lista_filtrada = new ArrayList<>();
        for (Planta planta : lista_plantas) {
            if (planta.getNome_comum().toLowerCase().contains(busca.toLowerCase()))
                lista_filtrada.add(planta);
        }
        if (lista_filtrada.isEmpty()) Toast.makeText(this.getContext(), "Nenhuma planta encontrada...", Toast.LENGTH_SHORT).show();
        else plantaAdapter.filtrarPlantas(lista_filtrada);

    }
}