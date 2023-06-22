package ClasseGestionBasket;

import java.io.*;
import java.util.Objects;

public class Joueur extends Personne implements Serializable {
    private int numJoueur;

    public Joueur(String nom, String prenom, int numJoueur) {
        super(nom, prenom);
        this.numJoueur = numJoueur;
    }
    public int getNumJoueur() {
        return numJoueur;
    }
    public void setNumJoueur(int numJoueur) {
        this.numJoueur = numJoueur;
    }

    @Override
    public String toString() {
        return getNom() + " " + getPrenom() + ", n°" + getNumJoueur();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Joueur joueur = (Joueur) o;
        return numJoueur == joueur.numJoueur;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numJoueur);
    }



}