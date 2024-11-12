package Entites;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "offreEmploi")
public class Offre {
    @PrimaryKey(autoGenerate = true)
    private int idOffre;
    @ColumnInfo()
    private String description;
    @ColumnInfo()
    private String localisation;
    @ColumnInfo()
    private String typeContrat;
    @ColumnInfo()
    private String titre;
    @ColumnInfo()
    private String competence;

    public Offre() {
    }
    @Ignore
    public Offre(int idOffre, String description, String localisation, String typeContrat, String titre, String competence) {
        this.idOffre = idOffre;
        this.description = description;
        this.localisation = localisation;
        this.typeContrat = typeContrat;
        this.titre = titre;
        this.competence = competence;
    }

    public int getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTypeContrat() {
        return typeContrat;
    }

    public void setTypeContrat(String typeContrat) {
        this.typeContrat = typeContrat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "idOffre=" + idOffre +
                ", description='" + description + '\'' +
                ", localisation='" + localisation + '\'' +
                ", typeContrat='" + typeContrat + '\'' +
                ", titre='" + titre + '\'' +
                ", competence='" + competence + '\'' +
                '}';
    }
}
