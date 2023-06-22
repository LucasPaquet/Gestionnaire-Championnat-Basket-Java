package ClasseGestionBasket;

import java.io.Serializable;

public class EquipeClassement implements Serializable {
    private int victoire;
    private int defaite;
    private int egalite;
    private int point;
    private Equipe equipe;


    public EquipeClassement(Equipe e) {
        this.equipe = e;
        victoire = 0;
        defaite = 0;
        egalite = 0;
        point = 0;
    }

    public int getVictoire() {
        return victoire;
    }

    public void setVictoire(int victoire) {
        this.victoire = victoire;
    }

    public int getDefaite() {
        return defaite;
    }

    public void setDefaite(int defaite) {
        this.defaite = defaite;
    }

    public int getEgalite() {
        return egalite;
    }

    public void setEgalite(int egalite) {
        this.egalite = egalite;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public void ajouteVictoire() {
        victoire += 1;
        point += 3;
    }
    public void ajouteDefaite() {
        defaite += 1;
        point += 1;
    }
    public void ajouteEgalite() {
        egalite += 1;
        point +=2;
    }

    @Override
    public String toString() {
        return  equipe.getNom();
    }
}
