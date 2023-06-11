package com.example.trabalho_samambaia.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trabalho_samambaia.activities.PlaceholderContent;
import com.example.trabalho_samambaia.databinding.FragmentItemBinding;

import java.util.List;

/**
 * Esta classe faz a parte visual do item da recycler view e trata dos dados
 */
public class MyAlertitemRecyclerViewAdapter extends RecyclerView.Adapter<MyAlertitemRecyclerViewAdapter.ViewHolder> {

    private final List<PlaceholderContent.PlaceholderItem> mValues;

    public MyAlertitemRecyclerViewAdapter(List<PlaceholderContent.PlaceholderItem> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdView;
        public final TextView mContentView;
        public PlaceholderContent.PlaceholderItem mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            mIdView = binding.alertTitle;
            mContentView = binding.alertDescription;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}