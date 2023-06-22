package ClasseGestionBasket;

import java.io.Serializable;

public class Delegue extends Personne implements Serializable {
    private int numDelegue;

    public Delegue(String nom, String prenom, int numDelegue) {
        super(nom, prenom);
        this.numDelegue = numDelegue;
    }

    public void setNumDelegue(int numDelegue) {
        this.numDelegue = numDelegue;
    }

    public int getNumDelegue() {
        return numDelegue;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom() + ", n°" + getNumDelegue();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Delegue delegue = (Delegue) o;

        return numDelegue == delegue.numDelegue;
    }

    @Override
    public int hashCode() {
        return numDelegue;
    }

}