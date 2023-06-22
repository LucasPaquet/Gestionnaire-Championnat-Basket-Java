package ClasseGestionBasket;

import java.io.Serializable;

public class Coach extends Personne implements Serializable {
    private int numCoach;

    public Coach(String nom, String prenom, int numCoach) {
        super(nom, prenom);
        this.numCoach = numCoach;
    }

    public Coach() {
        super("sans nom", "sans prénom");
        this.numCoach = -1;
    }


    @Override
    public String toString() {
        return "Coach : " + getNom() + " " + getPrenom() + ", n°" + numCoach;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coach coach = (Coach) o;

        return numCoach == coach.numCoach;
    }

    @Override
    public int hashCode() {
        return numCoach;
    }

    public int getNumCoach() {
        return numCoach;
    }

    public void setNumCoach(int numCoach) {
        this.numCoach = numCoach;
    }
}


