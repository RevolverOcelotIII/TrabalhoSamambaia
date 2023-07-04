package com.example.trabalho_samambaia.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.adapters.PlantaBaseAdapter;
import com.example.trabalho_samambaia.model.Planta;

import java.util.ArrayList;
import java.util.List;


public class CadastroPlantaBaseFragment extends Fragment {

    private SearchView busca_planta_searchView;
    private RecyclerView lista_plantas_recycler;
    private PlantaBaseAdapter plantaBaseAdapter;

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
        plantaBaseAdapter = new PlantaBaseAdapter(lista_plantas);
        lista_plantas_recycler.setAdapter(plantaBaseAdapter);
        // Inflate the layout for this fragment
        return view;
    }

    private void filterList(String busca) {
        List<Planta> lista_filtrada = new ArrayList<>();
        for (Planta planta : lista_plantas) {
            if (planta.getNome_comum().toLowerCase().contains(busca.toLowerCase()))
                lista_filtrada.add(planta);
        }
        if (lista_filtrada.isEmpty()) Toast.makeText(this.getContext(), R.string.noPlantFound, Toast.LENGTH_SHORT).show();
        else plantaAdapter.filtrarPlantas(lista_filtrada);

    }
}