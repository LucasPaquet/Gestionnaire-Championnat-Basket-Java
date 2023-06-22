package ClasseGestionBasket;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Equipe implements Serializable {
    private ArrayList<Joueur> joueurs;
    private ArrayList<Delegue> delegues;
    private Coach coach;
    private String nom;
    private int niveau;

    public Equipe(ArrayList<Joueur> joueurs, Coach coach, String nom, int niveau, ArrayList<Delegue>delegues) {
        this.joueurs = joueurs;
        this.coach = coach;
        this.nom = nom;
        this.niveau = niveau;
        this.delegues = delegues;
    }

    public Equipe(String nom) {
        this.nom = nom;
        delegues = new ArrayList<>();
        joueurs = new ArrayList<>();
        coach = new Coach();
    }

    public ArrayList<Delegue> getDelegues() {
        return delegues;
    }

    public void setDelegues(ArrayList<Delegue> delegues) {
        this.delegues = delegues;
    }

    public ArrayList<Joueur> getJoueurs() {
        return joueurs;
    }

    public void setJoueurs(ArrayList<Joueur> joueurs) {
        this.joueurs = joueurs;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    @Override
    public String toString() {
        return nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipe equipe = (Equipe) o;

        if (niveau != equipe.niveau) return false;
        if (!Objects.equals(joueurs, equipe.joueurs)) return false;
        if (!Objects.equals(delegues, equipe.delegues)) return false;
        if (!Objects.equals(coach, equipe.coach)) return false;
        return Objects.equals(nom, equipe.nom);
    }

    @Override
    public int hashCode() {
        int result = joueurs != null ? joueurs.hashCode() : 0;
        result = 31 * result + (delegues != null ? delegues.hashCode() : 0);
        result = 31 * result + (coach != null ? coach.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + niveau;
        return result;
    }

    /**
     * Permet d'ajouter un delegue dans la liste d'delegues. la methode verifie que le numero n'est pas deja utilise par un autre delegue de la liste
     * @param delegue le delegue que l'on veut ajouter
     * @return true = le delegue a bien ete enregistrer, false = le delegue n'a pas ete enregistrer
     */
    public boolean addDelegue(Delegue delegue){
        if (!checkDelegue(delegue.getNumDelegue())){
            delegues.add(delegue);
            return true;
        }
        return false;
    }


    /**
     * Permet de savoir si un numero d'delegue est deja prit ou pas dans la liste d'delegues
     * @param numDelegue Le numero que l'on veut verifier
     * @return true = le numero est deja prit, false = le numero n'est attribue a personne
     */
    public boolean checkDelegue(int numDelegue){
        boolean ret = false;
        for (int i = 0; i < delegues.size(); i++) {
            if (numDelegue == delegues.get(i).getNumDelegue()){
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * Permet d'ajouter un joueur dans la liste de joueur. la methode verifie que le numero n'est pas deja utilise par un autre joueur de la liste
     * @param joueur le joueur que l'on veut ajouter
     * @return true = le joueur a bien ete enregistrer, false = le joueur n'a pas ete enregistrer
     */
    public boolean addJoueur(Joueur joueur){
        if (!checkJoueur(joueur.getNumJoueur())){
            joueurs.add(joueur);
            return true;
        }
        return false;
    }

    /**
     * Permet de savoir si un numero de joueur est deja prit ou pas dans la liste de joueur
     * @param numJoueur Le numero que l'on veut verifier
     * @return true = le numero est deja prit, false = le numero n'est attribue a personne
     */
    public boolean checkJoueur(int numJoueur){
        boolean ret = false;
        for (int i = 0; i < joueurs.size(); i++) {
            if (numJoueur == joueurs.get(i).getNumJoueur()){
                ret = true;
                break;
            }
        }
        return ret;
    }

    /**
     * Permet d'enregistrer une liste de joueurs d'un fichier .csv
     * @param filePath Le chemin vers ou l'on veut sauvegarder le fichier .csv
     */
    public void save(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Joueur joueur : this.joueurs) {
                writer.write(joueur.getNom() + "," + joueur.getPrenom() + "," + joueur.getNumJoueur());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Permet de lire une liste de joueurs d'un fichier .csv
     * @param filePath Le chemin vers ou l'on veut lire le fichier .csv
     * @return Liste de joueurs lu dans le fichier .csv
     */
    public void load(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String nom = data[0];
                    String prenom = data[1];
                    int numJoueur = Integer.parseInt(data[2]);
                    this.joueurs.add(new Joueur(nom, prenom, numJoueur));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
