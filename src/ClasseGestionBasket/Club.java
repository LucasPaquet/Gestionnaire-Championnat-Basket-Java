package ClasseGestionBasket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Club implements Serializable {
    private String nom;
    private String adresse;
    private String img;
    private ArrayList<Equipe> equipes;
    private ArrayList<Arbitre> arbitres;


    public Club(String nom) {
        this.nom = nom;
        equipes = new ArrayList<>();
        arbitres = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ArrayList<Equipe> getEquipes() {
        return equipes;
    }

    public void setEquipes(ArrayList<Equipe> equipes) {
        this.equipes = equipes;
    }

    public ArrayList<Arbitre> getArbitres() {
        return arbitres;
    }

    public void setArbitres(ArrayList<Arbitre> arbitres) {
        this.arbitres = arbitres;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Club club = (Club) o;

        if (!Objects.equals(nom, club.nom)) return false;
        if (!Objects.equals(adresse, club.adresse)) return false;
        if (!Objects.equals(img, club.img)) return false;
        if (!Objects.equals(equipes, club.equipes)) return false;
        return Objects.equals(arbitres, club.arbitres);
    }

    @Override
    public int hashCode() {
        int result = nom != null ? nom.hashCode() : 0;
        result = 31 * result + (adresse != null ? adresse.hashCode() : 0);
        result = 31 * result + (img != null ? img.hashCode() : 0);
        result = 31 * result + (equipes != null ? equipes.hashCode() : 0);
        result = 31 * result + (arbitres != null ? arbitres.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Club{" +
                "nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", img='" + img + '\'' +
                ", equipes=" + equipes +
                ", arbitres=" + arbitres +
                '}';
    }

    /**
     * Permet d'ajouter un arbitre dans la liste d'arbitres. la methode verifie que le numero n'est pas deja utilise par un autre arbitre de la liste
     * @param arbitre l'arbitre que l'on veut ajouter
     * @return true = l'arbitre a bien ete enregistrer, false = l'arbitre n'a pas ete enregistrer
     */
    public boolean addArbitre(Arbitre arbitre){
        if (!checkArbitre(arbitre.getNumArbitre())){
            arbitres.add(arbitre);
            return true;
        }
        return false;
    }


    /**
     * Permet de savoir si un numero d'arbitre est deja prit ou pas dans la liste d'arbitre
     * @param numArbitre Le numero que l'on veut verifier
     * @return true = le numero est deja prit, false = le numero n'est attribue a personne
     */
    public boolean checkArbitre(int numArbitre){
        boolean ret = false;
        for (int i = 0; i < arbitres.size(); i++) {
            if (numArbitre == arbitres.get(i).getNumArbitre()){
                ret = true;
                break;
            }
        }
        return ret;
    }
}
