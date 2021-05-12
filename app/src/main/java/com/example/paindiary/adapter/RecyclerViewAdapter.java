package com.example.paindiary.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.paindiary.databinding.RvLayoutBinding;
import com.example.paindiary.entity.Pain;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter <RecyclerViewAdapter.ViewHolder> {
    private List<Pain> pains;
    public RecyclerViewAdapter(List<Pain> pains) {
        this.pains = pains;
    }
    //This method creates a new view holder that is constructed with a new View, inflated from a layout
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RvLayoutBinding binding=
                RvLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }
    // this method binds the view holder created with data that will be displayed
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder viewHolder, int position) {
        final Pain pain = pains.get(position);
        viewHolder.binding.tvPainLevel.setText(pain.getPainLevel());
        viewHolder.binding.tvPainLocation.setText(pain.getPainLocation());
        viewHolder.binding.tvMoodLevel.setText(pain.getMoodLevel());
        viewHolder.binding.tvPhysicalStep.setText((Integer.toString(pain.getPhysicalStep())));
    }
    @Override
    public int getItemCount() {
        return pains.size();
    }
    public void addPains(List<Pain> mpains) {
        pains = mpains;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private RvLayoutBinding binding;
        public ViewHolder(RvLayoutBinding binding){
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
