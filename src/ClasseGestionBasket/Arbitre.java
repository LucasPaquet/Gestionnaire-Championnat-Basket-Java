package ClasseGestionBasket;

import java.io.Serializable;

public class Arbitre extends Personne implements Serializable {
    private int numArbitre;

    public Arbitre(String nom, String prenom, int numArbitre) {
        super(nom, prenom);
        this.numArbitre = numArbitre;
    }

    public void setNumArbitre(int numArbitre) {
        this.numArbitre = numArbitre;
    }

    public int getNumArbitre() {
        return numArbitre;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom() + ", n°" + getNumArbitre();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Arbitre arbitre = (Arbitre) o;

        return numArbitre == arbitre.numArbitre;
    }

    @Override
    public int hashCode() {
        return numArbitre;
    }
}