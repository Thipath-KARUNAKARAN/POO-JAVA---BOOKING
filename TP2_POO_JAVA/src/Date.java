public class Date {
    public int jour;
    public int mois;
    public int annee;

    public Date() {}

    public Date(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }

    public String toString() {
        return jour + "/" + mois + "/" + annee;
    }
}