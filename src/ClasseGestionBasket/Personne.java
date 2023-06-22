package ClasseGestionBasket;

import java.io.Serializable;
import java.util.Objects;

public abstract class Personne implements Serializable {
    private String nom;
    private String prenom;

    public Personne(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
    public Personne(){
        this.nom = "Sans nom";
        this.prenom = "Sans prénom";
    }


    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getPrenom() {
        return prenom;
    }

    @Override
    public String toString() {
        return "ClasseGestionBasket.Personne = " + nom + " " + prenom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Personne personne = (Personne) o;

        if (!Objects.equals(nom, personne.nom)) return false;
        return Objects.equals(prenom, personne.prenom);
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        return result;
    }
}
