package com.example.trabalho_samambaia.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.activities.PlantViewer;
import com.example.trabalho_samambaia.model.Planta;

import java.util.List;

public class PlantaListAdapter extends RecyclerView.Adapter<PlantaListAdapter.PlantaViewHolder> {

    private List<Planta> plantasList;
    private static Planta plantPosition;

    public PlantaListAdapter(List<Planta> plantasList) {
        this.plantasList = plantasList;
    }

    @NonNull
    @Override
    public PlantaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_planta_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_planta, parent, false);
        return new PlantaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantaViewHolder holder, int position) {
        plantPosition = plantasList.get(position);
        holder.bind(plantPosition);
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), PlantViewer.class);

            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return plantasList.size();
    }

    public void updatePlantasList(List<Planta> plantasList) {
        this.plantasList = plantasList;
        notifyDataSetChanged();
    }

    public void atualizarDados(List<Planta> atualizado) {
        plantasList = atualizado;
    }

    public static class PlantaViewHolder extends RecyclerView.ViewHolder {
    /*
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
        }*/
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
            tituloTextView.setText(planta.getNome_personalizado());
            String regagem = "";
            switch (planta.getRegagem()){
                case "Frequent":
                    regagem = "3 vez ao dia";
                    break;
                case "Average":
                    regagem = "2 vezes ao dia";
                    break;
                case "Minimum":
                    regagem = "1 vez ao dia";
                    break;
            }
            subtituloTextView.setText(planta.getNome_comum() +" - "+"Regagem: " + regagem);

            Glide.with(itemView).load(planta.getImagem_url()).into(imagemImageView);

        }
    }

    public static Planta getPlantPosition(){
        return plantPosition;
    }
}
