import java.util.List;
import java.util.Scanner;

public abstract class Client
{
    public String nom;
    public String prenom;
    public String email;
    public String motDePasse;
    public String adresse;
    public Date date;

    public Client() {}

    public abstract void inscription(Scanner sc, List<Client> clients);
    public abstract Client connexion(Scanner sc, List<Client> clients);

    public String toString()
    {
        return prenom + " " + nom + " (" + email + "), né(e) le " + (date == null ? "?" : date);
    }
}