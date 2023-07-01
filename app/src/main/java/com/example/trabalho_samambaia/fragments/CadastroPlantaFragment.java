package com.example.trabalho_samambaia.fragments;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.Looper;
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
import com.example.trabalho_samambaia.adapters.PlantaListAdapter;
import com.example.trabalho_samambaia.dao.PlantaDAO;
import com.example.trabalho_samambaia.model.Planta;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Random;

public class CadastroPlantaFragment extends Fragment {

    private TextView planta_nomeText, tipo_plantaText, cidadeText;
    private ImageView planta_imageView;
    private TextInputEditText planta_nome_editText, planta_adubagem_editText;

    private Button create_plant_button;

    private List<Planta> genericListPlant= new ArrayList<>();
    public PlantaListAdapter plantaList;

    private Planta planta_base;

    private String proxima_adubagem = "";


    private ActivityResultLauncher<String> mGetContentLauncher;

    // private LocationManager locationManager;
    // private LocationListener locationListener;

    private FusedLocationProviderClient fusedLocationClient;

    private static final int REQUEST_LOCATION_PERMISSION = 123;

    public CadastroPlantaFragment() {
        // Required empty public constructor
    }

    public static CadastroPlantaFragment newInstance(String param1, String param2) {
        CadastroPlantaFragment fragment = new CadastroPlantaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    private void requestLocationPermission() {

        registerForActivityResult(new ActivityResultContracts.RequestPermission(), result -> {
            if (result) {
                // Permissão concedida, registre o LocationCallback
                getLastKnownLocation();
            } else {
                // Permissão negada, lide com isso de acordo com sua lógica de negócio
            }
        }).launch(Manifest.permission.ACCESS_FINE_LOCATION);
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
            } else if (args.getInt("planta_id") >= 0) {
                planta_base = Planta.getPlanta(args.getInt("planta_id"), this.getContext());
            } else
                planta_base = Planta.getPlanta(args.getInt("planta_id"), this.getContext());

            planta_nomeText = view.findViewById(R.id.sua_planta);
            planta_nomeText.setText(planta_base.getNome_comum());
            tipo_plantaText = view.findViewById(R.id.tipo_planta);
            tipo_plantaText.setText(planta_base.getNome_cientifico());
            cidadeText = view.findViewById(R.id.cidade_textview);

            planta_imageView = view.findViewById(R.id.planta_imageView);
            if (args.getParcelable("foto_bitmap") != null)
                planta_imageView.setImageBitmap(args.getParcelable("foto_bitmap"));
            else Glide.with(view).load(planta_base.getImagem_url()).into(planta_imageView);


            planta_nome_editText = view.findViewById(R.id.cadastro_planta_nome_editText);
            create_plant_button = view.findViewById(R.id.create_plant_button);

            if (args.getInt("plantaedit_id") > 0) {
                Log.d("teste", "onCreateView: "+ args.getInt("plantaedit_id"));
                planta_nome_editText.setText(planta_base.getNome_personalizado());
                create_plant_button.setText("Editar Planta");
                create_plant_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        PlantaDAO plantaDAO = new PlantaDAO(getContext());
                        planta_base.setNome_personalizado(planta_nome_editText.getText().toString());
                        if (planta_base.getImagem_url()=="") {
                            Bitmap bitmap = (Bitmap) args.getParcelable("foto_bitmap");
                            Random random = new Random();
                            planta_base.setImagem_url(createImageFromBitmap(getContext(), bitmap, "" + random.nextInt()));
                        }
                        //int proxima_adubagem = (Integer) planta_adubagem_editText.getText().toString().substring(0,2);

                        planta_base.setProxima_adubagem(planta_adubagem_editText.getText().toString());

                        plantaDAO.editPlanta(planta_base);

                        getActivity().finish();

                    }
                });
            }
            else {
                planta_nome_editText.setText("Sambinha");
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

                        plantaDAO.createPlanta(planta_base);
                        genericListPlant.add(plantaDAO.getPlantaFromId(planta_base.getId()));

                        plantaList = new PlantaListAdapter(genericListPlant);

                        ListPlantsFragment listPlantsFragment = (ListPlantsFragment) getParentFragmentManager().findFragmentById(R.id.planta_item_list);
                        if (listPlantsFragment != null) {
                            listPlantsFragment.setPlantaListAdapter(plantaList);
                        }

                        getActivity().finish();

                    }
                });
            }
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
                                planta_base.setImagem_url("");
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

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());
            // Solicite a permissão de localização em tempo de execução
            if (checkLocationPermission()) {
                // Permissão já concedida, obtenha a localização estática do usuário
                getLastKnownLocation();
            } else {
                // Solicite a permissão de localização
                requestLocationPermission();
            }
        } catch (Exception e) {
            Log.d("teste", e.getMessage());
        }

    }

    DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

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

    private boolean checkLocationPermission() {
        return ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }


    private String getCityFromCoordinates(double latitude, double longitude) {
        try {
            Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (!addresses.isEmpty()) {
                Address address = addresses.get(0);

                Log.d("teste", "getCityFromCoordinates: " + addresses.get(0).getAdminArea());
                return address.getSubAdminArea() + ", " + address.getAdminArea();
            }
        } catch (Exception e) {
            Log.d("teste", e.getMessage());
        }
        return null;
    }

    private void getLastKnownLocation() {
        try {

            if (checkLocationPermission()) {
                fusedLocationClient.getLastLocation()
                        .addOnSuccessListener(requireActivity(), location -> {
                            if (location != null) {
                                double latitude = location.getLatitude();
                                double longitude = location.getLongitude();

                                String city = getCityFromCoordinates(latitude, longitude);

                                // Atualize o TextView com o nome da cidade
                                Log.d("teste", "cidade: " + city);
                                cidadeText.setText(city);
                            }
                        })
                        .addOnFailureListener(requireActivity(), e -> {
                            // Lide com a falha de obter a localização do usuário
                        });
            }
        } catch (Exception e) {
            Log.d("teste", e.getMessage());
        }
    }
}