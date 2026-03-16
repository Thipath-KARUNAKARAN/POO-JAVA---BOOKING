import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private static int NEXT_ID = 1;

    private final int id;
    private StatutReservation statut;
    private final Client client;
    private final Hebergement hebergement;
    private final Periode periode;
    private double prixTotal;

    private Reservation(Client client, Hebergement hebergement, Periode periode) {
        this.id = NEXT_ID++;
        this.client = client;
        this.hebergement = hebergement;
        this.periode = periode;
        this.statut = StatutReservation.EN_ATTENTE;
        this.prixTotal = calculerPrixTotal();
    }

    public static Reservation creer(Client client, Hebergement hebergement, Periode periode) {
        return new Reservation(client, hebergement, periode);
    }

    public Client getClient() { return client; }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    private double calculerPrixTotal() {
        LocalDate d1 = LocalDate.of(periode.debut.annee, periode.debut.mois, periode.debut.jour);
        LocalDate d2 = LocalDate.of(periode.fin.annee, periode.fin.mois, periode.fin.jour);
        long nuits = ChronoUnit.DAYS.between(d1, d2);
        if (nuits < 0) nuits = 0;
        return nuits * hebergement.prixParNuits;
    }

    public String toString() {
        return "Reservation #" + id +
                "\nClient: " + client.prenom + " " + client.nom +
                "\nHébergement: " + hebergement.nom + " (" + hebergement.destination + ")" +
                "\nPériode: " + periode +
                "\nStatut: " + statut +
                "\nPrix total: " + prixTotal + "€";
    }
}