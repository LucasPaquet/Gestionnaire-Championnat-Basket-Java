package GUI;

import ClasseGestionBasket.Coach;
import ClasseGestionBasket.Delegue;
import ClasseGestionBasket.Equipe;
import ClasseGestionBasket.Joueur;
import Controller.Controleur;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ModifEquipe extends JDialog{
    private JPanel panel1;
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JTextField tfNumero;
    private JRadioButton joueurRadioButton;
    private JRadioButton delegueRadioButton;
    private JButton ajouterLaPersonneButton;
    private JButton modifierLeCoachButton;
    private JPanel JPanelCoach;
    private JList<Object> listDelegue;
    private JList<Object> listJoueur;
    private JPanel JPanelCreationPersonne;
    private JButton supprimmerLeDelegueButton;
    private JButton supprimmerLeJoueurButton;
    private JTextField tfNomCoach;
    private JTextField tfPrenomCoach;
    private JTextField tfNumeroCoach;
    private JComboBox niveauEquipeComboBox;
    private JButton chargerUneListeDeButton;
    private JButton enregistrerLaListeDeButton;


    public ModifEquipe(){
        setTitle("Modification d'une équipe");
        setContentPane(panel1);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // pour implementer les fonctions de fenetre de bas (exit, agrandir, ...)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // avoir la taille de l'écran
        pack();
        setSize(1200,500);
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2); // pour mettre en plein milieu de l'écran

        // pour combobox
        niveauEquipeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Pré-poussin", "Poussin", "Benjamin", "Cadet", "Junior", "P4", "P3", "P2", "P1", "D3","D2","D1"}));
        niveauEquipeComboBox.setSelectedIndex(-1);

        // pour les boutons radio
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(joueurRadioButton);
        buttonGroup.add(delegueRadioButton);
        joueurRadioButton.setSelected(true);

    }

    public void setControleur(Controleur c)
    {
        delegueRadioButton.addActionListener(c);
        joueurRadioButton.addActionListener(c);
        ajouterLaPersonneButton.addActionListener(c);
        supprimmerLeJoueurButton.addActionListener(c);
        modifierLeCoachButton.addActionListener(c);
        supprimmerLeDelegueButton.addActionListener(c);
        niveauEquipeComboBox.addActionListener(c);
        chargerUneListeDeButton.addActionListener(c);
        enregistrerLaListeDeButton.addActionListener(c);
        this.addWindowListener(c);
    }

    // getX textbox
    public String getNomCoach() {return tfNomCoach.getText();}
    public String getPrenomCoach() {return tfPrenomCoach.getText();}
    public String getNumCoach() {return tfNumeroCoach.getText();}
    public String getPrenom() {return tfPrenom.getText();}
    public String getNom() {return tfNom.getText();}
    public String getNum() {return tfNumero.getText();}

    // getX list
    public int getDelegue() { return listDelegue.getSelectedIndex();}
    public int getJoueur() { return listJoueur.getSelectedIndex();}

    // getX combobox
    public int getNiveau() { return niveauEquipeComboBox.getSelectedIndex();}
    public JComboBox getCBNiveau() { return niveauEquipeComboBox;}

    // getX radio
    // true = joueur, false = delegue
    public boolean getRadio(){
        if (delegueRadioButton.isSelected())
            return false;
        return true;
    }

    // setX list
    public void setTabJoueur(ArrayList<Joueur> joueurs){
        DefaultListModel<Object> listJoueurs = new DefaultListModel<>();
        for (Joueur joueur : joueurs) {
            listJoueurs.addElement(joueur);
        }
        listJoueur.setModel(listJoueurs);
    }
    public void setTabDelegue(ArrayList<Delegue> delegues){
        DefaultListModel<Object> listDelegues = new DefaultListModel<>();
        for (Delegue delegue : delegues) {
            listDelegues.addElement(delegue);
        }
        listDelegue.setModel(listDelegues);
    }

    // setx coach
    public void setCoach(Coach c) {
        tfPrenomCoach.setText(c.getPrenom());
        tfNomCoach.setText(c.getPrenom());
        tfNumeroCoach.setText(c.getNumCoach() + "");
    }

    // setX pour le niveau(combobox)
    public void setNiveau(int i) {
        niveauEquipeComboBox.setSelectedIndex(i);
        System.out.println(i);
    }

    // set fenetre
    public void setFenetre(Equipe eq) {
        setTabDelegue(eq.getDelegues());
        setTabJoueur(eq.getJoueurs());
        setCoach(eq.getCoach());
        setNiveau(eq.getNiveau());
    }

    public void resetChamp() {
        tfNumero.setText("");
        tfNom.setText("");
        tfPrenom.setText("");
        niveauEquipeComboBox.setSelectedIndex(-1);

    }

    public static void main(String[] args) {
        ModifEquipe frame = new ModifEquipe();
        frame.setVisible(true);
    }
}
