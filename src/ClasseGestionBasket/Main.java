package ClasseGestionBasket;

import BasketBeans.LogWriterBean;
import Controller.Controleur;
import GUI.*;

import java.beans.Beans;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;


public class Main {
    public static void main(String[] args) {
        AWBB secondDegre = AWBB.getInstance();
        secondDegre.load("awbb.dat");

        // Pour le javaBeans
        LogWriterBean logBeans = null;
        try {
            logBeans = (LogWriterBean) Beans.instantiate(null, "BasketBeans.LogWriterBean");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        SelectionChampionnat fenetre = new SelectionChampionnat();
        CreationChampionnat fenetreCreaChamp = new CreationChampionnat();
        GestChampionnat fenetreChamp = new GestChampionnat(logBeans);
        ModifClub fenetreModifClub = new ModifClub();
        ModifEquipe fenetreModifEquipe = new ModifEquipe();

        logBeans.addWriterListener(fenetreChamp);
        logBeans.addPropertyChangeListener(fenetreChamp);

        Controleur controleur = new Controleur(secondDegre,fenetre,fenetreCreaChamp, fenetreChamp, fenetreModifClub, fenetreModifEquipe);

        // setup du controler
        fenetre.setControleur(controleur);
        fenetreCreaChamp.setControleur(controleur);
        fenetreChamp.setControleur(controleur);
        fenetreModifClub.setControleur(controleur);
        fenetreModifEquipe.setControleur(controleur);

        fenetre.setVisible(true);

    }


}