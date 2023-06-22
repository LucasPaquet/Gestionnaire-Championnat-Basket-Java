package GUI;

import ClasseGestionBasket.Equipe;
import Controller.Controleur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreationChampionnat extends JDialog {
    private JPanel panel1;
    private JButton creerLeChampionnatButton;
    private JButton reinitialiserBouton;
    private JButton annulerButton;
    private JTextField nomDuChampionnatTextField;
    private JComboBox<String> niveauDuChampionnatComboBox;
    private JList listEquipe;
    private JLabel nomDuChampionnatLabel;
    private JLabel lbNiveau;
    private boolean ok;
    private java.util.List equipes;

    public CreationChampionnat(){
        setTitle("Création d'un championnat");
        setContentPane(panel1);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // pour implementer les fonctions de fenetre de bas (exit, agrandir, ...)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // avoir la taille de l'écran

        pack();

        setSize(700,500);
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2); // pour mettre en plein milieu de l'écran

        ok = false;

        niveauDuChampionnatComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"Pré-poussin", "Poussin", "Benjamin", "Cadet", "Junior", "P4", "P3", "P2", "P1", "D3","D2","D1"}));
        niveauDuChampionnatComboBox.setSelectedIndex(-1);

        DefaultListModel<Equipe> equipeListModel = new DefaultListModel<>();
        listEquipe.setModel(equipeListModel);



        reinitialiserBouton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               resetChamp();
            }
        });

        annulerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetChamp();
                setVisible(false);
            }
        });

    }
    public void setControleur(Controleur c)
    {
        creerLeChampionnatButton.addActionListener(c);
        reinitialiserBouton.addActionListener(c);
        niveauDuChampionnatComboBox.addActionListener(c);
        annulerButton.addActionListener(c);
        this.addWindowListener(c);
    }

    // getX cb
    public int getNiveau() { return niveauDuChampionnatComboBox.getSelectedIndex();}
    public JComboBox getCBNiveau() { return  niveauDuChampionnatComboBox;}

    // getX tb
    public String getNom() { return nomDuChampionnatTextField.getText(); }

    // getX list
    public ArrayList<Equipe> getEquipes() {
        ArrayList<Equipe> equipes = new ArrayList<>();
        int[] indicesSelectionnes = listEquipe.getSelectedIndices();

        for (int indice : indicesSelectionnes) {
            Equipe equipe = (Equipe) listEquipe.getModel().getElementAt(indice);
            equipes.add(equipe);
        }

        return equipes;
    }


    // setX list
    public void setTabEquipe(ArrayList<Equipe> equipes){
        DefaultListModel<Object> listEquipes = new DefaultListModel<>();
        for (Equipe equipe : equipes) {
            listEquipes.addElement(equipe);
        }
        listEquipe.setModel(listEquipes);
    }
    public boolean isOk() {
        return ok;
    }
    public void resetChamp(){
        listEquipe.clearSelection();
        niveauDuChampionnatComboBox.setSelectedIndex(-1);
        nomDuChampionnatTextField.setText("");
    }
    public static void main(String[] args) {
        CreationChampionnat frame = new CreationChampionnat();


        frame.setVisible(true);
    }
}
