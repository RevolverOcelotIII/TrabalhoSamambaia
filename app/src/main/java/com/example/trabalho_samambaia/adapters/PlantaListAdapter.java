package com.example.trabalho_samambaia.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.model.Planta;

import java.util.List;

public class PlantaListAdapter extends RecyclerView.Adapter<PlantaListAdapter.PlantaViewHolder> {

    private List<Planta> plantasList;

    public PlantaListAdapter(List<Planta> plantasList) {
        this.plantasList = plantasList;
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_planta_item, parent, false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder holder, int position) {
        Planta planta = plantasList.get(position);
        holder.bind(planta);
    }

    @Override
    public int getItemCount() {
        return plantasList.size();
    }

    public void updatePlantasList(List<Planta> plantasList) {
        this.plantasList = plantasList;
        notifyDataSetChanged();
    }

    public static class PlantaViewHolder extends RecyclerView.ViewHolder {

        private TextView nomePlantaTextView;
        private TextView proximaRegaemTextView;

        public PlantaViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePlantaTextView = itemView.findViewById(R.id.nome_planta_id);
            proximaRegaemTextView = itemView.findViewById(R.id.regagem_date);
        }

        public void bind(Planta planta) {
            nomePlantaTextView.setText(planta.getNome_personalizado());
            proximaRegaemTextView.setText(planta.getRegagem());
        }
    }
}
