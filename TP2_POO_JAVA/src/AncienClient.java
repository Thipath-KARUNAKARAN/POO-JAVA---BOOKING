import java.util.List;
import java.util.Scanner;

public class AncienClient extends Client
{
    public AncienClient() {}

    public void inscription(Scanner sc, List<Client> clients)
    {
        System.out.println("Tu est déjà inscrit, tu n'as pas besoin de te réinscrire");
    }

    public Client connexion(Scanner sc, List<Client> clients)
    {
        if (clients.isEmpty())
        {
            System.out.println("Aucun client enregistré. Inscris-toi d'abord (menu 2).");
            return null;
        }

        else
        {
            inscription(sc, clients);
        }

        System.out.print("Email ---> ");
        String email = sc.nextLine();
        System.out.print("Mot de passe ---> ");
        String mdp = sc.nextLine();
        for (Client c : clients) {
            if (c.email != null && c.motDePasse != null &&
                    c.email.equalsIgnoreCase(email) &&
                    c.motDePasse.equals(mdp)) {
                return c;
            }
        }
        return null;
    }
}