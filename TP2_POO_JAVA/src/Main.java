import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Client> clients = new ArrayList<>();
        Catalogue catalogue = new Catalogue();
        List<Reservation> reservations = new ArrayList<>();

        Hebergement azurCentral = new Hebergement(
                1,
                "Azur Central",
                "Nice",
                "12 Avenue du Port, 06000 Nice",
                2,
                "Hôtel au centre-ville, à 8 min à pied de la plage.",
                8.4,
                8,
                TypeHebergement.MAISON,
                new String[]{"WIFI", "CUISINE"},
                new Periode(new Date(10, 3, 2026), new Date(15, 3, 2026)),
                240
        );

        Hebergement canalSerenity = new Hebergement(
                5,
                "Canal Serenity",
                "Paris",
                "18 Quai de la Loire, 75019 Paris",
                3,
                "Appartement au bord du canal. Salon lumineux, balcon vue canal.",
                8.7,
                4,
                TypeHebergement.APPARTEMENT,
                new String[]{"WIFI", "CUISINE", "MACHINE_A_LAVER", "ASCENSEUR"},
                new Periode(new Date(2, 3, 2026), new Date(7, 3, 2026)),
                155
        );

        Hebergement montagneEtoilee = new Hebergement(
                6,
                "Montagne Étoilée",
                "Cauterets",
                "5 Route des Crêtes, 65110 Cauterets",
                2,
                "Hôtel cosy à 300 m des télécabines. Chambre bois, vue montagne.",
                9.1,
                9,
                TypeHebergement.HOTEL,
                new String[]{"WIFI", "PETIT_DEJEUNER", "PARKING", "MENAGE", "BAR", "LOCAL_SKIS"},
                new Periode(new Date(1, 2, 2026), new Date(6, 2, 2026)),
                118
        );

        Hebergement lagoonEscape = new Hebergement(
                7,
                "Lagoon Escape",
                "Guadeloupe",
                "3 Impasse des Hibiscus, 97118 Saint-François",
                8,
                "Villa tropicale très calme. Piscine privée, grande terrasse, jardin.",
                9.5,
                6,
                TypeHebergement.VILLA,
                new String[]{"PISCINE", "WIFI", "CLIM", "BARBECUE", "PARKING", "CUISINE"},
                new Periode(new Date(5, 4, 2026), new Date(12, 4, 2026)),
                310
        );

        catalogue.ajouterHebergement(azurCentral);
        catalogue.ajouterHebergement(canalSerenity);
        catalogue.ajouterHebergement(montagneEtoilee);
        catalogue.ajouterHebergement(lagoonEscape);

        Menu menu = new Menu(sc, catalogue, clients, reservations);
        menu.lancer();
    }
}