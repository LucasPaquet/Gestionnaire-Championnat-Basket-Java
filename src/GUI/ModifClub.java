package GUI;

import ClasseGestionBasket.Arbitre;
import ClasseGestionBasket.Club;
import ClasseGestionBasket.Equipe;
import Controller.Controleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifClub extends JDialog {
    private JPanel panel1;
    private JTextArea tbAdresse;
    private JButton creerUneEquipeButton;
    private JButton btnCreerPersonne;
    private JTextField tfNom;
    private JLabel lbNom;
    private JLabel lbAdresse;
    private JLabel lbEquipe;
    private JLabel lbArbitre;
    private JList listEquipe;
    private JButton supprimerLArbitreButton;
    private JTextField tfNomArbitre;
    private JTextField tfPrenomArbitre;
    private JTextField tfNumArbitre;
    private JList listArbitre;
    private JButton supprimerUneEquipeButton;
    private JButton modifierLeClubButton;

    public ModifClub(){
        setTitle("Modification d'un club");
        setContentPane(panel1);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // pour implementer les fonctions de fenetre de bas (exit, agrandir, ...)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // avoir la taille de l'écran
        pack();
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2); // pour mettre en plein milieu de l'écran

    }
    public void setControleur(Controleur c)
    {
        creerUneEquipeButton.addActionListener(c);
        supprimerLArbitreButton.addActionListener(c);
        supprimerUneEquipeButton.addActionListener(c);
        modifierLeClubButton.addActionListener(c);
        btnCreerPersonne.addActionListener(c);
        listEquipe.addMouseListener(c);
        this.addWindowListener(c);
    }

    public void setFenetre(Club cb) {
        setTabArbitre(cb.getArbitres());
        setTabEquipe(cb.getEquipes());
        setAdresse(cb.getAdresse());
        setNom(cb.getNom());
    }


    // getX Text box
    public String getNomClub(){ return tfNom.getText(); }
    public String getAdresse() {return tbAdresse.getText(); }
    public String getNomArbitre() { return tfNomArbitre.getText();}
    public String getPrenomArbitre() { return tfPrenomArbitre.getText();}
    public int getNumArbitre() { return Integer.parseInt(tfNumArbitre.getText());}

    // getX List
    public int getArbitre(){ return listArbitre.getSelectedIndex(); }
    public int getEquipe(){ return listEquipe.getSelectedIndex(); }

    // getX jlist
    public JList getListEquipe() { return listEquipe; }

    // setX list
    public void setTabEquipe(ArrayList<Equipe> equipes){
        DefaultListModel<Object> listClub = new DefaultListModel<>();
        for (Equipe equipe : equipes) {
            listClub.addElement(equipe.getNom());
        }
        listEquipe.setModel(listClub);
    }
    public void setTabArbitre(ArrayList<Arbitre> arbitres){
        DefaultListModel<Object> listArbitres = new DefaultListModel<>();
        for (Arbitre arbitre : arbitres) {
            listArbitres.addElement(arbitre);
        }
        listArbitre.setModel(listArbitres);
    }

    // set text field

    public void setNom(String nom) { tfNom.setText(nom);}
    public void setAdresse(String adresse) {tbAdresse.setText(adresse);}




    public static void main(String[] args) {
        ModifClub frame = new ModifClub();
        frame.setVisible(true);
    }
}
