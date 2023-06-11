package com.example.trabalho_samambaia.fragments;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaAdapter;
import com.example.trabalho_samambaia.model.Planta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
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

    public List<Planta> gerarListaPlaceholder() {
        List<Planta> plantas = new ArrayList<Planta>();
        AssetManager assetManager = this.getContext().getAssets();


        try {
            InputStream inputStream = assetManager.open("species-list-traduzido.json");
            int json_tamanho = inputStream.available();
            byte[] buffer = new byte[json_tamanho];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");
            Log.d("testes", json);

            Gson gson = new Gson();
            JsonArray jsonArray = gson.fromJson(json, JsonObject.class).getAsJsonArray("data");
            for (JsonElement element : jsonArray) {
                JsonObject planta_json = element.getAsJsonObject();
                Planta planta = new Planta(planta_json.get("common_name").getAsString(),
                        planta_json.get("scientific_name").getAsJsonArray().get(0).getAsString(),
                        planta_json.getAsJsonObject("default_image").get("small_url").getAsString());
                Log.d("testes", planta.toString());
                plantas.add(planta);
            }
            // String smallUrl = jsonObject.getAsJsonObject("default_image").get("small_url").getAsString();

        } catch (IOException e) {
            Log.d("testes", "deu ruim" + e.toString());
        }
        return plantas;
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
        lista_plantas = gerarListaPlaceholder();
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
            if (planta.getNome().toLowerCase().contains(busca.toLowerCase()))
                lista_filtrada.add(planta);
        }
        if (lista_filtrada.isEmpty()) Toast.makeText(this.getContext(), "Nenhuma planta encontrada...", Toast.LENGTH_SHORT).show();
        else plantaAdapter.filtrarPlantas(lista_filtrada);

    }
}