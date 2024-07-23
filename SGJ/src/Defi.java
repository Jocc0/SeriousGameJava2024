import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Defi implements Serializable {
    private Joueur initiateur;
    private Joueur adversaire;
    private List<Question> questions;

    public Defi(Joueur initiateur, Joueur adversaire, List<Question> questions) {
        this.initiateur = initiateur;
        this.adversaire = adversaire;
        this.questions = questions;
    }

    public void commencer(Scanner scanner) {
        for (Question question : questions) {
            System.out.println("Question: " + question.getTexte());
            String reponse = scanner.nextLine();
            if (question.estCorrecte(reponse)) {
                adversaire.getAvatar().modifierPointsDeVie(question.getPoints());
                initiateur.getAvatar().modifierPointsDeVie(-question.getPoints());
            } else {
                adversaire.getAvatar().modifierPointsDeVie(-question.getPoints());
                initiateur.getAvatar().modifierPointsDeVie(question.getPoints());
            }
        }
    }
}
