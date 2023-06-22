package Interface;

import BasketBeans.LogWriterBean;
import ClasseGestionBasket.Championnat;
import ClasseGestionBasket.EquipeClassement;
import ClasseGestionBasket.Match;
import Controller.Controleur;

import javax.swing.*;
import java.util.ArrayList;

public interface IGestChampionnat {
    int getLigneMatch();
    String getScoreLocal();
    String getScoreExterieur();
    JTable getTabMatch();
    LogWriterBean getBeans();
    void setControleur(Controleur c);
    void setFenetre(Championnat cp);
    void setFormat(String f);
    void setTabMatch(ArrayList<Match> ma);
    void setTabClassement(ArrayList<EquipeClassement> ec);
    void setLog(String log);
    void setMatch(String mt);
    void setVisi(boolean bo);
    void setModale(boolean bo);

}
