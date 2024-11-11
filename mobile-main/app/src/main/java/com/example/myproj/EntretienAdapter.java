package com.example.myproj;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Entites.Entretien;

public class EntretienAdapter extends RecyclerView.Adapter<EntretienAdapter.EntretienViewHolder> {

    private List<Entretien> entretienList;
    private OnEntretienActionListener actionListener;

    public EntretienAdapter(List<Entretien> entretienList, OnEntretienActionListener actionListener) {
        this.entretienList = entretienList;
        this.actionListener = actionListener;
    }

    @NonNull
    @Override
    public EntretienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_entretien, parent, false);
        return new EntretienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EntretienViewHolder holder, int position) {
        Entretien entretien = entretienList.get(position);
        holder.textViewIdProposition.setText(entretien.getIdProposition() != null ? entretien.getIdProposition() : "N/A");
        holder.textViewDate.setText(entretien.getDate() != null ? entretien.getDate() : "N/A");
        holder.textViewLieu.setText(entretien.getLieu() != null ? entretien.getLieu() : "N/A");
        holder.textViewNumeroDeSalle.setText(entretien.getNumeroSalle() != null ? entretien.getNumeroSalle() : "N/A");
        holder.textViewNomRecruteur.setText(entretien.getNomRecruteur() != null ? entretien.getNomRecruteur() : "N/A");
        holder.textViewConfirmer.setText(entretien.isConfirmed() ? "Confirmé" : "Non confirmé");

        // Set delete button click listener
        holder.buttonDelete.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onDelete(entretien);
            }
        });

        // Set modify button click listener
        holder.buttonModify.setOnClickListener(v -> {
            if (actionListener != null) {
                actionListener.onModify(entretien);
            }
        });

        // Set item click listener for navigation to DetailEntretienActivity
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DetailEntretienActivity.class);
            intent.putExtra("idProposition", entretien.getIdProposition());
            intent.putExtra("date", entretien.getDate());
            intent.putExtra("lieu", entretien.getLieu());
            intent.putExtra("numeroSalle", entretien.getNumeroSalle());
            intent.putExtra("nomRecruteur", entretien.getNomRecruteur());
            intent.putExtra("isConfirmed", entretien.isConfirmed());
            holder.itemView.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return entretienList.size();
    }

    // Update data and refresh the view
    public void setEntretienList(List<Entretien> entretiens) {
        this.entretienList = entretiens;
        notifyDataSetChanged();
    }

    public static class EntretienViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIdProposition, textViewDate, textViewLieu, textViewNumeroDeSalle, textViewNomRecruteur, textViewConfirmer;
        Button buttonDelete, buttonModify;

        public EntretienViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIdProposition = itemView.findViewById(R.id.entretienProposition);
            textViewDate = itemView.findViewById(R.id.entretienDate);
            textViewLieu = itemView.findViewById(R.id.entretienLieu);
            textViewNumeroDeSalle = itemView.findViewById(R.id.entretienNumeroSalle);
            textViewNomRecruteur = itemView.findViewById(R.id.entretienNomRecruteur);
            textViewConfirmer = itemView.findViewById(R.id.entretienStatutConfirmation);
            buttonDelete = itemView.findViewById(R.id.button_supprimer);
            buttonModify = itemView.findViewById(R.id.button_modifier);
        }
    }

    // Listener interface for delete and modify actions
    public interface OnEntretienActionListener {
        void onModify(Entretien entretien);
        void onDelete(Entretien entretien);
    }
}
