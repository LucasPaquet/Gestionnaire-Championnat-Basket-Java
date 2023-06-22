package GUI;

import ClasseGestionBasket.Championnat;
import ClasseGestionBasket.Club;
import Controller.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.DefaultListModel;

public class SelectionChampionnat extends JFrame {
    private JButton creerUnChampionnatButton;
    private JPanel selectChamp;
    private JList<Object> jListChamp;
    private JButton quitterButton;
    private JButton supprimerUnClubButton;
    private JButton creerUnClubButton;
    private JList<Object> jlistClub;

    public void setControleur(Controleur c)
    {
        creerUnChampionnatButton.addActionListener(c);
        creerUnClubButton.addActionListener(c);
        quitterButton.addActionListener(c);
        supprimerUnClubButton.addActionListener(c);
        jlistClub.addMouseListener(c);
        jListChamp.addMouseListener(c);
        this.addWindowListener(c);
    }

    public SelectionChampionnat() {
        setTitle("Sélection du championnat ou du club");
        setContentPane(selectChamp);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // pour implementer les fonctions de fenetre de bas (exit, agrandir, ...)
        pack();

        setSize(800,800);
        loadWindowPosition();



        DefaultListModel<Object> clubs = new DefaultListModel<>();
        jlistClub.setModel(clubs);
    }

    public void setTabChampionnat(ArrayList<Championnat> championnats){
        DefaultListModel<Object> listChamp = new DefaultListModel<>();
        for (Championnat championnat : championnats) {
            listChamp.addElement(championnat.getNom());
        }
        jListChamp.setModel(listChamp);
    }

    public void setTabClub(ArrayList<Club> clubs){
        DefaultListModel<Object> listClub = new DefaultListModel<>();
        for (Club club : clubs) {
            listClub.addElement(club.getNom());
        }
        jlistClub.setModel(listClub);
    }

    public int getSelectClub(){ return jlistClub.getSelectedIndex(); }
    public int getSelectedChamp(){ return jListChamp.getSelectedIndex(); }

    // getX JList
    public JList getListChamp() { return jListChamp; };
    public JList getListClub() { return jlistClub; };

    /**
     * Méthode qui permet de lire le fichier window.properties qui contient la position x et y de la fenêtre principal dernièrement utilisé
     */
    private void loadWindowPosition() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream("windows.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Récupérer les coordonnées x et y à partir des propriétés
        String xString = properties.getProperty("window.x");
        String yString = properties.getProperty("window.y");

        if (xString != null && yString != null) {
            try {
                int x = Integer.parseInt(xString);
                int y = Integer.parseInt(yString);
                setLocation(x, y);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SelectionChampionnat frame = new SelectionChampionnat();
        frame.setVisible(true);

    }



}
