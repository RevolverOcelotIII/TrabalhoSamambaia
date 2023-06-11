package com.example.trabalho_samambaia.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.trabalho_samambaia.R;
import com.google.android.material.textfield.TextInputEditText;
public class CadastroPlantaFragment extends Fragment {

    private TextView planta_nomeText, tipo_plantaText;
    private ImageView planta_imageView;
    private TextInputEditText planta_nome_editText, planta_adubagem_editText;

    public CadastroPlantaFragment() {
        // Required empty public constructor
    }


    public static CadastroPlantaFragment newInstance(String param1, String param2) {
        CadastroPlantaFragment fragment = new CadastroPlantaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_cadastro_planta, container, false);
        planta_nomeText = view.findViewById(R.id.sua_planta);
        tipo_plantaText = view.findViewById(R.id.tipo_planta);
        planta_imageView = view.findViewById(R.id.planta_imageView);
        planta_nome_editText = view.findViewById(R.id.cadastro_planta_nome_editText);
        planta_adubagem_editText = view.findViewById(R.id.cadastro_planta_regagem_editText);

        planta_nome_editText.setText("Teste");

        planta_imageView.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "Clicou", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}