import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Charger ou créer des joueurs
        Joueur joueur1 = chargerOuCreerJoueur(scanner, "joueur1.txt");
        Joueur joueur2 = chargerOuCreerJoueur(scanner, "joueur2.txt");

        // Questions pré-enregistrées
        List<Question> questions = Arrays.asList(
                new Question("Quelle est la capitale de la France?", "Paris", 10),
                new Question("Combien font 5+7?", "12", 5));

        // Lancer les défis
        boolean jeuEnCours = true;
        while (jeuEnCours) {
            System.out.println("Joueur 1 lance un défi à Joueur 2");
            joueur1.lancerDefi(joueur2, questions, scanner);
            sauvegarderJoueur(joueur1, "joueur1.txt");
            sauvegarderJoueur(joueur2, "joueur2.txt");

            System.out.println("Joueur 2 lance un défi à Joueur 1");
            joueur2.lancerDefi(joueur1, questions, scanner);
            sauvegarderJoueur(joueur1, "joueur1.txt");
            sauvegarderJoueur(joueur2, "joueur2.txt");

            // Afficher les points de vie des avatars
            System.out.println("Points de vie de " + joueur1.getNom() + ": " + joueur1.getAvatar().getPointsDeVie());
            System.out.println("Points de vie de " + joueur2.getNom() + ": " + joueur2.getAvatar().getPointsDeVie());

            // Vérifier si l'un des avatars a perdu tous ses points de vie
            if (joueur1.getAvatar().getPointsDeVie() <= 0 || joueur2.getAvatar().getPointsDeVie() <= 0) {
                jeuEnCours = false;
                System.out.println("Le jeu est terminé!");
            } else {
                System.out.println("Voulez-vous continuer le jeu? (oui/non)");
                String reponse = scanner.nextLine();
                if (!reponse.equalsIgnoreCase("oui")) {
                    jeuEnCours = false;
                }
            }
        }

        scanner.close();
    }

    private static Joueur creerJoueur(Scanner scanner) {
        System.out.println("Entrez le nom du joueur:");
        String nom = scanner.nextLine();
        System.out.println("Entrez les notes (séparées par des espaces):");
        String[] notesStr = scanner.nextLine().split(" ");
        List<Integer> notes = Arrays.stream(notesStr).map(Integer::parseInt).toList();
        return new Joueur(nom, notes);
    }

    private static Joueur chargerOuCreerJoueur(Scanner scanner, String nomFichier) {
        try {
            return Joueur.chargerJoueur(nomFichier);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Aucun joueur trouvé. Création d'un nouveau joueur.");
            return creerJoueur(scanner);
        }
    }

    private static void sauvegarderJoueur(Joueur joueur, String nomFichier) {
        try {
            joueur.sauvegarderJoueur(nomFichier);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde du joueur: " + e.getMessage());
        }
    }
}
