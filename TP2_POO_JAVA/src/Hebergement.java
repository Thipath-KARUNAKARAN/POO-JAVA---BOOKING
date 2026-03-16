import java.util.ArrayList;
import java.util.List;

public class Hebergement {
    public int id;
    public String nom;
    public String destination;
    public String adresse;
    public int nbMaxPersonnes;
    public String description;
    public double moyenne;
    public int nbNotes;

    public Periode periodeDisponible;
    public TypeHebergement typeHebergement;
    public List<String> equipement = new ArrayList<>();
    public double prixParNuits;

    public Hebergement(int id, String nom, String destination, String adresse,
                       int nbMaxPersonnes, String description,
                       double moyenne, int nbNotes,
                       TypeHebergement typeHebergement,
                       String[] Equip,
                       Periode periodeDisponible,
                       double prixParNuits) {
        this.id = id;
        this.nom = nom;
        this.destination = destination;
        this.adresse = adresse;
        this.nbMaxPersonnes = nbMaxPersonnes;
        this.description = description;
        this.moyenne = moyenne;
        this.nbNotes = nbNotes;
        this.typeHebergement = typeHebergement;
        this.periodeDisponible = periodeDisponible;
        this.prixParNuits = prixParNuits;

        if (Equip != null) {
            for (String p : Equip) {
                this.equipement.add(p);
            }
        }
    }

    public boolean estDisponible(Periode p) {
        return p.estValide() && p.estInclusDans(this.periodeDisponible);
    }

    public void reserver(Periode p)
    {
        this.periodeDisponible = new Periode(new Date(1,1,1900), new Date(1,1,1900));
    }

    public String toString() {
        return "Hebergement #" + id + " " + nom + " (" + destination + ") " +
                typeHebergement + " - " + prixParNuits + "€/nuit - dispo " + periodeDisponible;
    }
}