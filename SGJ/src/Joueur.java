import java.util.List;
import java.util.Scanner;
import java.io.*;

public class Joueur implements Serializable {
    private String nom;
    private Avatar avatar;

    public Joueur(String nom, List<Integer> notes) {
        this.nom = nom;
        this.avatar = new Avatar(nom, notes);
    }

    public String getNom() {
        return nom;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void lancerDefi(Joueur adversaire, List<Question> questions, Scanner scanner) {
        Defi defi = new Defi(this, adversaire, questions);
        defi.commencer(scanner);
    }

    public static Joueur chargerJoueur(String nomFichier) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFichier))) {
            return (Joueur) ois.readObject();
        }
    }

    public void sauvegarderJoueur(String nomFichier) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFichier))) {
            oos.writeObject(this);
        }
    }
}
