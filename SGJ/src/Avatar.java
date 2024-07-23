import java.io.Serializable;
import java.util.List;

public class Avatar implements Serializable {
    private String nom;
    private int pointsDeVie;

    public Avatar(String nom, List<Integer> notes) {
        this.nom = nom;
        this.pointsDeVie = calculerPointsDeVie(notes);
    }

    private int calculerPointsDeVie(List<Integer> notes) {
        return notes.stream().mapToInt(Integer::intValue).sum();
    }

    public String getNom() {
        return nom;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public void modifierPointsDeVie(int points) {
        this.pointsDeVie += points;
    }
}
