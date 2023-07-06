package com.example.trabalho_samambaia.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.fragments.CadastroPlantaFragment;
import com.example.trabalho_samambaia.model.Planta;

import java.util.List;

public class PlantaBaseAdapter extends RecyclerView.Adapter<PlantaBaseAdapter.PlantaViewHolder> {
    private List<Planta> listaPlantas;

    public PlantaBaseAdapter(List<Planta> listaPlantas) {
        this.listaPlantas = listaPlantas;
    }

    public void filtrarPlantas(List<Planta> listaPlantasFiltrada) {
        this.listaPlantas = listaPlantasFiltrada;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planta, parent, false);
        return new PlantaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder holder, int position) {
        Planta planta = listaPlantas.get(position);
        holder.bind(planta);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(view.getContext(), listaPlantas.get(holder.getAdapterPosition()).getNome_cientifico(), Toast.LENGTH_SHORT).show();
                Bundle args = new Bundle();
                args.putInt("planta_id", listaPlantas.get(holder.getAdapterPosition()).getPlanta_base_id());
                CadastroPlantaFragment fragment = new CadastroPlantaFragment();
                fragment.setArguments(args);
                FragmentManager fragmentManager = ((AppCompatActivity) view.getContext()).getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.cadastro_planta_fragment, fragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPlantas.size();
    }

    public static class PlantaViewHolder extends RecyclerView.ViewHolder {
        private TextView tituloTextView;
        private TextView subtituloTextView;
        private ImageView imagemImageView;

        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.titulo_planta_list);
            subtituloTextView = itemView.findViewById(R.id.subtitulo_planta_list);
            imagemImageView = itemView.findViewById(R.id.imagem_planta_list);
        }

        public void bind(Planta planta) {
            tituloTextView.setText(planta.getNome_cientifico());
            subtituloTextView.setText(planta.getNome_comum());

            Glide.with(itemView).load(planta.getImagem_url()).into(imagemImageView);

        }
    }
}

