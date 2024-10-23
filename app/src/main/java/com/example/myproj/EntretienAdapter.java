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
    private OnEntretienActionListener actionListener; // Correctly reference the listener

    public EntretienAdapter(List<Entretien> entretienList, OnEntretienActionListener actionListener) { // Pass the listener
        this.entretienList = entretienList;
        this.actionListener = actionListener; // Store the listener for use in the adapter
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
        holder.textViewIdProposition.setText(entretien.getIdProposition());
        holder.textViewDate.setText(entretien.getDate()); // Make sure getDate() returns the date
        holder.textViewLieu.setText(entretien.getLieu());
        holder.textViewNumeroDeSalle.setText(entretien.getNumeroSalle());
        holder.textViewNomRecruteur.setText(entretien.getNomRecruteur());
        holder.textViewConfirmer.setText(entretien.isConfirmed() ? "Confirmé" : "Non confirmé");

        // Set delete button click listener
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionListener != null) {
                    actionListener.onDelete(entretien); // Call the listener's onDelete method
                }
            }
        });
        holder.buttonModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actionListener.onModify(entretien);
            }
        });
        // Set item click listener
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to DetailEntretienActivity
                Intent intent = new Intent(holder.itemView.getContext(), DetailEntretienActivity.class);
                intent.putExtra("idProposition", entretien.getIdProposition());
                intent.putExtra("date", entretien.getDate());
                intent.putExtra("lieu", entretien.getLieu());
                intent.putExtra("numeroSalle", entretien.getNumeroSalle());
                intent.putExtra("nomRecruteur", entretien.getNomRecruteur());
                intent.putExtra("isConfirmed", entretien.isConfirmed());
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return entretienList.size();
    }

    public static class EntretienViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNomRecruteur;
        TextView textViewLieu;
        TextView textViewDate;
        TextView textViewIdProposition;
        TextView textViewNumeroDeSalle;
        TextView textViewConfirmer;
        Button buttonDelete; // Declare the delete button
        Button buttonModify; // Bouton modifier


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

            // Initialize the delete button
        }
    }

    // Listener interface for delete and modify actions
    public interface OnEntretienActionListener {
        void onModify(Entretien entretien);
        void onDelete(Entretien entretien);
    }
}
