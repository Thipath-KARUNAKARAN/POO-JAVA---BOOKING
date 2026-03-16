import java.util.Scanner;

public class Input {

    public static int lireInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.println("Entier attendu.");
            sc.nextLine();
            System.out.print("> ");
        }
        int v = sc.nextInt();
        sc.nextLine(); // consomme \n
        return v;
    }

    public static double lireDouble(Scanner sc) {
        while (!sc.hasNextDouble()) {
            System.out.println("Nombre attendu (ex: 12.5).");
            sc.nextLine();
            System.out.print("> ");
        }
        double v = sc.nextDouble();
        sc.nextLine();
        return v;
    }

    public static Date lireDate(Scanner sc, String label) {
        System.out.println(label + " :");
        System.out.print("  Jour ---> ");
        int j = lireInt(sc);
        System.out.print("  Mois ---> ");
        int m = lireInt(sc);
        System.out.print("  Année ---> ");
        int a = lireInt(sc);
        return new Date(j, m, a);
    }
}