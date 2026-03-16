import java.util.ArrayList;
import java.util.List;

public class Catalogue
{
    private final List<Hebergement> hebergements = new ArrayList<>();

    public void ajouterHebergement(Hebergement h) {
        hebergements.add(h);
    }

    public List<Hebergement> getHebergements() {
        return hebergements;
    }

    public Hebergement trouverParId(int id) {
        for (Hebergement h : hebergements) {
            if (h.id == id) return h;
        }
        return null;
    }

    public void afficherListe(List<Hebergement> list) {
        for (Hebergement h : list) {
            System.out.println("ID: " + h.id + " | " + h.nom +
                    " | " + h.destination +
                    " | " + h.typeHebergement +
                    " | " + h.prixParNuits + "€/nuit" +
                    " | dispo " + h.periodeDisponible);
        }
    }

    public List<Hebergement> rechercher(String destination, int nbPersonnes, double prixMax, TypeHebergement type) {
        List<Hebergement> res = new ArrayList<>();

        for (Hebergement h : hebergements) {
            boolean okDest = h.destination != null && h.destination.equalsIgnoreCase(destination);
            boolean okPers = h.nbMaxPersonnes >= nbPersonnes;
            boolean okPrix = h.prixParNuits <= prixMax;
            boolean okType = (type == null) || h.typeHebergement == type;

            if (okDest && okPers && okPrix && okType) {
                res.add(h);
            }
        }
        return res;
    }
}