package com.example.trabalho_samambaia.fragments;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.model.Planta;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Optional;

public class CadastroPlantaFragment extends Fragment {

    private TextView planta_nomeText, tipo_plantaText;
    private ImageView planta_imageView;
    private TextInputEditText planta_nome_editText, planta_adubagem_editText;

    private Planta planta_base;

    private ActivityResultLauncher<String> mGetContentLauncher;

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
        Bundle args = getArguments();
        planta_base =  Planta.getPlanta(args.getInt("planta_id"), this.getContext());
        planta_nomeText = view.findViewById(R.id.sua_planta);
        planta_nomeText.setText(planta_base.getNome());
        tipo_plantaText = view.findViewById(R.id.tipo_planta);
        tipo_plantaText.setText(planta_base.getNome_cientifico());
        planta_imageView = view.findViewById(R.id.planta_imageView);
        Glide.with(view).load(planta_base.getImagem_url()).into(planta_imageView);
        planta_nome_editText = view.findViewById(R.id.cadastro_planta_nome_editText);
        planta_nome_editText.setText("Sambinha");
        planta_adubagem_editText = view.findViewById(R.id.cadastro_planta_regagem_editText);

        planta_adubagem_editText.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    getContext(),
                    datePickerListener,
                    calendar.get(calendar.YEAR), calendar.get(calendar.MONTH), calendar.get(calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });

        mGetContentLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        // Lógica para tratar o resultado da seleção do arquivo
                        if (result != null) {
                            planta_imageView.setImageURI(result);
                        }
                    }
                });

        planta_imageView.setOnClickListener(v -> {
            mGetContentLauncher.launch("image/*");
        });

        return view;
    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            // Aqui você pode realizar qualquer ação com a data selecionada
            // Formate a data como desejar (ddmmaaaa) e defina-a de volta no EditText
            String dataFormatada = String.format("%02d/%02d/%04d", dayOfMonth, monthOfYear + 1, year);
            planta_adubagem_editText.setText(dataFormatada);
        }
    };
}