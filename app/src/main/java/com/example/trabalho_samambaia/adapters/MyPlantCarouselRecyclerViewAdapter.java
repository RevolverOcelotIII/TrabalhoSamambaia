package com.example.trabalho_samambaia.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trabalho_samambaia.fragments.placeholder.PlaceholderContent.PlaceholderItem;
import com.example.trabalho_samambaia.databinding.FragmentCarouselItemBinding;
import com.example.trabalho_samambaia.model.Planta;

import java.util.List;
import java.util.Set;

/**
 * {@link RecyclerView.Adapter} that can display a {@link PlaceholderItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPlantCarouselRecyclerViewAdapter extends RecyclerView.Adapter<MyPlantCarouselRecyclerViewAdapter.ViewHolder> {

    private final List<Planta> mValues;

    public MyPlantCarouselRecyclerViewAdapter(List<Planta> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentCarouselItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.plantName.setText(mValues.get(position).getNome_comum());
        holder.plantCientificName.setText(mValues.get(position).getNome_cientifico());
        Glide.with(holder.itemView).load(mValues.get(position).getImagem_url()).into(holder.plantImage);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView plantName;
        public final TextView plantCientificName;
        public final ImageView plantImage;
        public Planta mItem;

        public ViewHolder(FragmentCarouselItemBinding binding) {
            super(binding.getRoot());
            plantName = binding.plantName;
            plantCientificName = binding.plantCientificName;
            plantImage = binding.plantImg;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + plantName.getText() + "'";
        }
    }
}