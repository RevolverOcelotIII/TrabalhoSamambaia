package com.example.trabalho_samambaia.fragments;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.dao.PlantaDAO;
import com.example.trabalho_samambaia.model.Planta;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;
import java.util.Random;

public class CadastroPlantaFragment extends Fragment {

    private TextView planta_nomeText, tipo_plantaText;
    private ImageView planta_imageView;
    private TextInputEditText planta_nome_editText, planta_adubagem_editText;

    private Button create_plant_button;

    private Planta planta_base;

    private String proxima_adubagem = "";

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
        try {
            final View view = inflater.inflate(R.layout.fragment_cadastro_planta, container, false);
            Bundle args = getArguments();
            if (args.getParcelable("foto_bitmap") != null) {
                planta_base = Planta.getRandomPlanta(this.getContext());
            } else
                planta_base = Planta.getPlanta(args.getInt("planta_id"), this.getContext());

            planta_nomeText = view.findViewById(R.id.sua_planta);
            planta_nomeText.setText(planta_base.getNome_comum());
            tipo_plantaText = view.findViewById(R.id.tipo_planta);
            tipo_plantaText.setText(planta_base.getNome_cientifico());

            planta_imageView = view.findViewById(R.id.planta_imageView);
            if (args.getParcelable("foto_bitmap") != null)
                planta_imageView.setImageBitmap(args.getParcelable("foto_bitmap"));
            else Glide.with(view).load(planta_base.getImagem_url()).into(planta_imageView);


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

            create_plant_button = view.findViewById(R.id.create_plant_button);

            create_plant_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PlantaDAO plantaDAO = new PlantaDAO(getContext());
                    planta_base.setNome_personalizado(planta_nome_editText.getText().toString());
                    if (args.getParcelable("foto_bitmap") != null) {
                        Bitmap bitmap = (Bitmap) args.getParcelable("foto_bitmap");
                        Random random = new Random();
                        planta_base.setImagem_url(createImageFromBitmap(getContext(), bitmap, "" + random.nextInt()));
                    }
                    //int proxima_adubagem = (Integer) planta_adubagem_editText.getText().toString().substring(0,2);

                    planta_base.setProxima_adubagem(planta_adubagem_editText.getText().toString());

                    plantaDAO.createPlanta(planta_base);

                    getActivity().finish();

                }
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
        } catch (Exception e) {
            Log.d("teste", "fragment: " + e.getMessage());
            return null;
        }
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

    String createImageFromBitmap(Context context, Bitmap bitmap, String filename) {
// Criar um arquivo temporário para salvar a imagem
        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), filename + ".jpg");
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

// Obter o caminho da imagem como uma string
        String imagePath = file.getAbsolutePath();
        return imagePath;
    }
}