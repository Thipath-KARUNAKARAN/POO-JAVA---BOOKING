import java.util.List;
import java.util.Scanner;

public class Menu
{
    private final Scanner sc;
    private final Catalogue catalogue;
    private final List<Client> clients;
    private final List<Reservation> reservations;

    private Client clientConnecte = null;

    public Menu(Scanner sc, Catalogue catalogue, List<Client> clients, List<Reservation> reservations) {
        this.sc = sc;
        this.catalogue = catalogue;
        this.clients = clients;
        this.reservations = reservations;
    }

    public void lancer() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== BOOING CONSOLE ===");
            System.out.println("1. Se connecter");
            System.out.println("2. S'inscrire (nouveau client)");
            System.out.println("3. Rechercher / afficher les hébergements");
            System.out.println("4. Mes Réservations");
            System.out.println("5. Quitter");
            System.out.print("Choix ---> ");

            int ch = Input.lireInt(sc);

            switch (ch)
            {
                case 1 ->
                {
                    seConnecter();

                }
                case 2 -> {
                    sInscrire();
                }

                case 3 -> rechercherEtReserver();
                case 4 -> afficherMesReservations();
                case 5 -> {
                    System.out.println("BYE!!");
                    running = false;
                    this.clientConnecte = null;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
    }

    private void seConnecter()
    {
        if(clientConnecte != null)
        {
            System.out.println("Tu es deja connecté " + clientConnecte.prenom);
        }
        else
        {
            System.out.println("\n=== CONNEXION ===");
            AncienClient service = new AncienClient();
            Client c = service.connexion(sc, clients);

            if (c == null) {
                System.out.println("Identifiants incorrects / compte inexistant.");
            } else {
                clientConnecte = c;
                System.out.println("Connecté : " + clientConnecte.prenom + " " + clientConnecte.nom);
            }
        }


    }

    private void sInscrire()
    {
        if (clientConnecte != null)
        {
            System.out.println("Tu es déja connecté, donc deconectoi avant");
        }
        else
        {
            System.out.println("\n=== INSCRIPTION ===");
            NouveauClient nc = new NouveauClient();
            nc.inscription(sc, clients);
            clientConnecte = nc;
        }


    }

    private void rechercherEtReserver() {
        System.out.println("\n=== RECHERCHE / CATALOGUE ===");

        System.out.println("1. Afficher tout le catalogue");
        System.out.println("2. Rechercher (destination + personnes + prix max + type optionnel)");
        System.out.print("Choix ---> ");
        int choix = Input.lireInt(sc);

        List<Hebergement> resultats;
        if (choix == 1) {
            resultats = catalogue.getHebergements();
        } else if (choix == 2) {
            System.out.print("Destination (ex: Paris) ---> ");
            String dest = sc.nextLine().trim();

            System.out.print("Nb personnes ---> ");
            int nb = Input.lireInt(sc);

            System.out.print("Prix max / nuit ---> ");
            double prixMax = Input.lireDouble(sc);

            System.out.print("Type (laisser vide si pas de filtre) [HOTEL, APPARTEMENT, VILLA, MAISON, CHAMBRE_HOTE] ---> ");
            String typeStr = sc.nextLine().trim();
            TypeHebergement type = null;
            if (!typeStr.isEmpty()) {
                try {
                    type = TypeHebergement.valueOf(typeStr.toUpperCase());
                } catch (Exception e) {
                    System.out.println("Type invalide, filtre type ignoré.");
                }
            }

            resultats = catalogue.rechercher(dest, nb, prixMax, type);
        } else {
            System.out.println("Choix invalide.");
            return;
        }

        if (resultats.isEmpty()) {
            System.out.println("Aucun hébergement trouvé.");
            return;
        }

        System.out.println("\n--- Résultats ---");
        catalogue.afficherListe(resultats);

        System.out.println("\nSouhaitez-vous réserver ? [1=Oui / 0=Non]");
        int ok = Input.lireInt(sc);
        if (ok != 1) return;

        if (clientConnecte == null) {
            System.out.println("Tu dois te connecter avant de réserver (menu 1).");
            return;
        }

        System.out.print("ID hébergement à réserver ---> ");
        int id = Input.lireInt(sc);

        Hebergement h = catalogue.trouverParId(id);
        if (h == null) {
            System.out.println("ID introuvable.");
            return;
        }

        System.out.println("Période demandée :");
        Date debut = Input.lireDate(sc, "Début");
        Date fin = Input.lireDate(sc, "Fin");

        Periode p = new Periode(debut, fin);

        if (!p.estValide()) {
            System.out.println("Période invalide (fin doit être après début).");
            return;
        }

        if (!h.estDisponible(p)) {
            System.out.println("Pas disponible sur cette période. Disponible: " + h.periodeDisponible);
            return;
        }

        Reservation r = Reservation.creer(clientConnecte, h, p);
        System.out.println("\n--- Récap réservation ---");
        System.out.println(r);

        System.out.print("Confirmer [1=Oui / 0=Non] ---> ");
        int conf = Input.lireInt(sc);
        if (conf == 1) {
            r.setStatut(StatutReservation.CONFIRMEE);
            reservations.add(r);
            h.reserver(p);

            System.out.println("Réservation confirmée !");
        } else {
            r.setStatut(StatutReservation.ANNULEE);
            System.out.println("Réservation annulée.");
        }
    }

    private void afficherMesReservations() {
        System.out.println("\n=== MES RESERVATIONS ===");

        if (clientConnecte == null) {
            System.out.println("Tu dois te connecter d'abord (menu 1).");
            return;
        }

        boolean any = false;
        for (Reservation r : reservations) {
            if (r.getClient().email.equals(clientConnecte.email)) {
                System.out.println("------------------------");
                System.out.println(r);
                any = true;
            }
        }

        if (!any) {
            System.out.println("Aucune réservation.");
        }
    }
}