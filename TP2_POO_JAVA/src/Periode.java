import java.time.LocalDate;

public class Periode {
    public Date debut;
    public Date fin;

    public Periode(Date debut, Date fin) {
        this.debut = debut;
        this.fin = fin;
    }

    public boolean estValide() {
        LocalDate d1 = LocalDate.of(debut.annee, debut.mois, debut.jour);
        LocalDate d2 = LocalDate.of(fin.annee, fin.mois, fin.jour);
        return d2.isAfter(d1);
    }

    public boolean estInclusDans(Periode dispo) {
        LocalDate d1 = LocalDate.of(debut.annee, debut.mois, debut.jour);
        LocalDate d2 = LocalDate.of(fin.annee, fin.mois, fin.jour);

        LocalDate s1 = LocalDate.of(dispo.debut.annee, dispo.debut.mois, dispo.debut.jour);
        LocalDate s2 = LocalDate.of(dispo.fin.annee, dispo.fin.mois, dispo.fin.jour);

        return ( !d1.isBefore(s1) ) && ( !d2.isAfter(s2) );
    }

    public String toString() {
        return "du " + debut + " au " + fin;
    }
}