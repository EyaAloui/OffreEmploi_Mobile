package com.esprit.delivery_app.Activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Filter;  // Add this import for Filter

import androidx.annotation.NonNull;

import com.esprit.delivery_app.Entity.Candidat;
import com.esprit.delivery_app.R;
import com.esprit.delivery_app.ViewModel.CandidatViewModel;

import java.util.ArrayList;
import java.util.List;


public class CandidatAdapter extends ArrayAdapter<Candidat> implements Filterable {
    private final Context context;
    private final CandidatViewModel candidatViewModel;
    private List<Candidat> candidatsOriginal;
    private List<Candidat> candidatsFiltered;

    public CandidatAdapter(Context context, List<Candidat> candidats, CandidatViewModel candidatViewModel) {
        super(context, R.layout.item_candidat, candidats);
        this.context = context;
        this.candidatsOriginal = new ArrayList<>(candidats);  // Make a copy of the original list
        this.candidatsFiltered = candidats;
        this.candidatViewModel = candidatViewModel;
    }



    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_candidat, parent, false);
        }

        // Récupérer l'objet Candidat correspondant à cette position
        Candidat candidat = candidatsFiltered.get(position);

        // Trouver les vues dans le layout item_candidat.xml
        TextView usernameTextView = convertView.findViewById(R.id.username);
        TextView emailTextView = convertView.findViewById(R.id.email);
        Button deleteButton = convertView.findViewById(R.id.delete_button);

        // Remplir les TextViews avec les données du Candidat
        usernameTextView.setText(candidat.getNom());
        emailTextView.setText(candidat.getEmail());

        // Définir le clic pour le bouton "Supprimer"
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                candidatViewModel.delete(candidat); // Supprimer de la base de données
                candidatsFiltered.remove(position); // Supprimer de la liste filtrée
                candidatsOriginal.remove(candidat); // Also remove from original list
                notifyDataSetChanged(); // Mettre à jour l'adaptateur
                Toast.makeText(context, "Candidat supprimé", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    // Filterable implementation to allow searching in the list
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                if (constraint == null || constraint.length() == 0) {
                    results.values = candidatsOriginal;
                    results.count = candidatsOriginal.size();
                } else {
                    List<Candidat> filteredList = new ArrayList<>();
                    for (Candidat c : candidatsOriginal) {
                        if (c.getNom().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            filteredList.add(c);
                        }
                    }
                    results.values = filteredList;
                    results.count = filteredList.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                candidatsFiltered = (List<Candidat>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
