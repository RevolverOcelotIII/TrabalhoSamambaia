package com.example.trabalho_samambaia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.model.Planta;

import java.util.List;

public class PlantaAdapter extends RecyclerView.Adapter<PlantaAdapter.PlantaViewHolder> {
    private List<Planta> listaPlantas;

    public PlantaAdapter(List<Planta> listaPlantas) {
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
            subtituloTextView.setText(planta.getNome());

            Glide.with(itemView).load(planta.getImagem_url()).into(imagemImageView);

        }
    }
}

