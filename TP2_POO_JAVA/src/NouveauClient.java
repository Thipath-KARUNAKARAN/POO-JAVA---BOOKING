import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NouveauClient extends Client
{

    public NouveauClient()
    {
        super();
    }

    /// Cette fonction sert juste à ne pas avoir de message d'erreur avec la classe abstraite CLIENT
    public Client connexion(Scanner sc, List<Client> clients)
    {
        System.out.println("Veuillez vous connexion");
        Client c = new NouveauClient();
        return c;
    }


    public void inscription(Scanner sc, List<Client> clients)
    {
        NouveauClient c = new NouveauClient();
        System.out.println("\n=== Inscription ===");
        System.out.print("Prénom ---> ");
        c.prenom = sc.nextLine();
        System.out.print("Nom ---> ");
        c.nom = sc.nextLine();
        System.out.print("Adresse ---> ");
        c.adresse = sc.nextLine();
        System.out.print("Email ---> ");
        c.email = sc.nextLine();
        while (emailExiste(clients, c.email)) {
            System.out.println("Email déjà utilisé. Réessaie.");
            System.out.print("Email ---> ");
            c.email = sc.nextLine();
        }
        System.out.print("Mot de passe ---> ");
        c.motDePasse = sc.nextLine();
        System.out.println("Date de naissance :");
        c.date = Input.lireDate(sc, "Naissance");
        System.out.println("\nRécap : " + c);
        System.out.print("CONFIRMER [1] ---> ");
        int ok = Input.lireInt(sc);
        while (ok != 1) {
            System.out.println("Erreur, tape 1.");
            ok = Input.lireInt(sc);
        }
        clients.add(c);
        System.out.println("Inscription réussie !");
    }

    private boolean emailExiste(List<Client> clients, String email) {
        for (Client c : clients) {
            if (c.email != null && c.email.equalsIgnoreCase(email)) return true;
        }
        return false;
    }
}