package com.example.trabalho_samambaia.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trabalho_samambaia.R;
import com.example.trabalho_samambaia.databinding.FragmentItemBinding;
import com.example.trabalho_samambaia.model.Planta;

import java.time.LocalTime;
import java.util.List;

/**
 * Esta classe faz a parte visual do item da recycler view e trata dos dados
 */
public class MyAlertitemRecyclerViewAdapter extends RecyclerView.Adapter<MyAlertitemRecyclerViewAdapter.ViewHolder> {

    private final List<com.example.trabalho_samambaia.model.Planta> mValues;
    private OnItemTouchListener onItemTouchListener;

    public MyAlertitemRecyclerViewAdapter(List<com.example.trabalho_samambaia.model.Planta> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.alertTitle.setText(mValues.get(position).getNome_comum());
        holder.alertDescription.setText(plantWateringGuide(mValues.get(position).getRegagem()));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalTime now = LocalTime.now();
            LocalTime hoursMinutes = LocalTime.of(now.getHour(),now.getMinute());
            holder.alertTime.setText(hoursMinutes.toString());
        }
//        holder.itemView.setOnTouchListener(new View.OnTouchListener(){
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (onItemTouchListener != null) {
//                    onItemTouchListener.onItemTouch(v, event, position);
//                }
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public interface OnItemTouchListener {
        void onItemTouch(View view, MotionEvent event, int position);
    }
    public void setOnItemTouchListener(OnItemTouchListener onItemTouchListener) {
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView alertTitle;
        public final TextView alertTime;
        public final TextView alertDescription;
        public Planta mItem;




        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            alertTitle = binding.alertTitle;
            alertTime = binding.alertTime;
            alertDescription = binding.alertDescription;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + alertTime.getText() + "'";
        }
    }

    private String plantWateringGuide(String watering){
        String wateringText = "";
        switch (watering){
            case "Frequent":
                wateringText = String.valueOf(R.string.frequentWateringText);
                break;
            case "Average":
                wateringText =  String.valueOf(R.string.averageWateringText);
                break;
            case "Minimum":
                wateringText =  String.valueOf(R.string.minimumWateringText);
                break;
            case "None":
                wateringText = String.valueOf(R.string.noneWateringText);
                break;
        }
        return wateringText;
    }
}