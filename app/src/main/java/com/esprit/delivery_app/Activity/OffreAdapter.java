package com.esprit.delivery_app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.delivery_app.Entity.Offre;
import com.esprit.delivery_app.R;

import java.util.List;

public class OffreAdapter extends RecyclerView.Adapter<OffreAdapter.OffreViewHolder> {

    private List<Offre> offreList;

    public OffreAdapter(List<Offre> offreList) {
        this.offreList = offreList;
    }

    @NonNull
    @Override
    public OffreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_offre, parent, false);
        return new OffreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OffreViewHolder holder, int position) {
        Offre offre = offreList.get(position);
        holder.titleTextView.setText(offre.getTitre());
        holder.locationTextView.setText(offre.getLocalisation());
        holder.typeTextView.setText(offre.getTypeContrat());
        holder.descriptionTextView.setText(offre.getDescription());
    }

    @Override
    public int getItemCount() {
        return offreList.size();
    }

    public void setOffreList(List<Offre> offreList) {
        this.offreList = offreList;
        notifyDataSetChanged();
    }

    public static class OffreViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, locationTextView, typeTextView, descriptionTextView;

        public OffreViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
