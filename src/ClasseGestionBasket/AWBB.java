package ClasseGestionBasket;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class AWBB implements Serializable {
    private ArrayList<Championnat> championnats;
    private ArrayList<Club> clubs;
    private Championnat currentChampionnat;
    private static AWBB instance = null;

    public AWBB() {
        championnats = new ArrayList<>();
        clubs = new ArrayList<>();
    }

    public ArrayList<Championnat> getChampionnats() {
        return championnats;
    }

    public void setChampionnats(ArrayList<Championnat> championnats) {
        this.championnats = championnats;
    }

    public ArrayList<Club> getClubs() {
        return clubs;
    }

    public void setClubs(ArrayList<Club> clubs) {
        this.clubs = clubs;
    }

    public Championnat getCurrentChampionnat() {
        return currentChampionnat;
    }

    public void setCurrentChampionnat(Championnat currentChampionnat) {
        this.currentChampionnat = currentChampionnat;
    }

    public static AWBB getInstance()
    {
        if (instance == null)
            instance = new AWBB();
        return instance;
    }

    @Override
    public String toString() {
        return "AWBB{" +
                "championnats=" + championnats +
                ", clubs=" + clubs +
                ", currentChampionnat=" + currentChampionnat +
                '}';
    }


    // methodes


    /**
     * Verifie si le club passe en parametre a encore une equipe dans son club
     * @param club club que l'on veut verifier
     * @return true = si equipe dans le club, false = pas d'equipe dans le club
     */
    public boolean checkClubActive(Club club){
        if (club.getEquipes().size() == 0){
            return false;
        }
        return true;
    }

    /**
     * Enregistre l'objet AWBB sur le disque
     * @param filePath Le chemin du fichier de sauvegarde
     */
    public void save(String filePath) {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            objectOut.writeObject(this);
            System.out.println("L'objet AWBB a été enregistré sur le disque.");

        } catch (IOException e) {
            System.out.println("Erreur lors de l'enregistrement de l'objet AWBB sur le disque : " + e.getMessage());
        }
    }

    public void load(String cheminFichier) {
        try {
            FileInputStream fileIn = new FileInputStream(cheminFichier);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);

            AWBB awbb = (AWBB) objectIn.readObject();
            this.setChampionnats(awbb.getChampionnats());
            this.setClubs(awbb.getClubs());
            this.setCurrentChampionnat(awbb.getCurrentChampionnat());

            objectIn.close();
            fileIn.close();
            System.out.println("L'objet a été chargé depuis le fichier : " + cheminFichier);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
