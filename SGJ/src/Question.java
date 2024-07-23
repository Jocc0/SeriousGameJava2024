import java.io.Serializable;

public class Question implements Serializable {
    private String texte;
    private String reponse;
    private int points;

    public Question(String texte, String reponse, int points) {
        this.texte = texte;
        this.reponse = reponse;
        this.points = points;
    }

    public String getTexte() {
        return texte;
    }

    public String getReponse() {
        return reponse;
    }

    public int getPoints() {
        return points;
    }

    public boolean estCorrecte(String reponse) {
        return this.reponse.equalsIgnoreCase(reponse);
    }
}
