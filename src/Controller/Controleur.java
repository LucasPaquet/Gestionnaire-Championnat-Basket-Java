package Controller;

import ClasseGestionBasket.*;
import GUI.*;
import Interface.IGestChampionnat;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

import static javax.swing.JOptionPane.showInputDialog;

public class Controleur extends WindowAdapter implements ActionListener, MouseListener, WindowListener {
    private AWBB awbb;
    private SelectionChampionnat fenetrePrincipale;
    private CreationChampionnat fenetreCreaChamp;
    private IGestChampionnat fenetreChamp;
    private ModifClub fenetreModifClub;
    private ModifEquipe fenetreModifEquipe;
    private Club currentClub;
    private Championnat currentChampionnat;
    private Equipe currentEquipe;

    public Controleur(AWBB awbb, SelectionChampionnat viewSecondSegre,CreationChampionnat fenCC, GestChampionnat fenGC, ModifClub fenMC, ModifEquipe fenME) {
        this.awbb = awbb;

        // pour les fenetres
        this.fenetrePrincipale = viewSecondSegre;
        this.fenetreCreaChamp = fenCC;
        this.fenetreChamp = fenGC;
        this.fenetreModifClub = fenMC;
        this.fenetreModifEquipe = fenME;

        // pour mettre a jour les listes
        updateClubList();
        updateChampList();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());

        // fenetre principal
        if (e.getActionCommand().equals("Quitter"))
            onQuitter();
        if (e.getActionCommand().equals("Supprimer un club"))
            onSuprClub();
        if (e.getActionCommand().equals("Créer un championnat"))
            onCreerChamp();
        if (e.getActionCommand().equals("Créer un club"))
            onCreateClub();

        // fenetre de creation de championnat
        if (e.getSource() == fenetreCreaChamp.getCBNiveau())
            onChangeComboboxNiveau();
        if (e.getActionCommand().equals("Créer le championnat"))
            onCreateLeChampionnat();


        // partie modif club
        if (e.getActionCommand().equals("Modifier le club"))
            onModifInfoClub();
        if (e.getActionCommand().equals("Ajouter l'arbitre"))
            onCreateArbitre();
        if (e.getActionCommand().equals("Supprimer l'arbitre"))
            onDeleteArbitre();
        if (e.getActionCommand().equals("Créer une équipe"))
            onCreateEquipe();
        if (e.getActionCommand().equals("Supprimer une équipe"))
            onDeleteEquipe();

        // partie modif equipe
        if (e.getActionCommand().equals("Modifier le coach"))
            onUpdateCoach();
        if (e.getActionCommand().equals("Ajouter la personne"))
            onAddPersonne();
        if (e.getActionCommand().equals("Supprimer le joueur"))
            onDeleteJoueur();
        if (e.getActionCommand().equals("Supprimer le délégué"))
            onDeleteDelegue();
        if (e.getActionCommand().equals("Enregistrer la liste de joueurs"))
            onSaveJoueur();
        if (e.getActionCommand().equals("Charger une liste de joueurs"))
            onLoadJoueur();
        if (e.getSource() == fenetreModifEquipe.getCBNiveau())
            onComboboxChangeEquipe();

        // partie gestion championnat
        if (e.getActionCommand().equals("Jouer le match"))
            onPlayGame();
        if (e.getActionCommand().equals("Renommer le championnat"))
            onRenameChamp();
        if (e.getActionCommand().equals("Changer le format d'affichage des rencontres"))
            onChangeFormat();
        if (e.getActionCommand().equals("Quitter l'application"))
            onQuitter();

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            System.out.println("zaezae");

            // fenetre principal
            if (e.getSource() == fenetrePrincipale.getListClub() && fenetrePrincipale.getSelectClub() != -1) {
                onModifClub();
            }
            if (e.getSource() == fenetrePrincipale.getListChamp() && fenetrePrincipale.getSelectedChamp() != -1) {
                onGestChamp();
            }

            // fenetre modif club
            if (e.getSource() == fenetreModifClub.getListEquipe() && fenetreModifClub.getEquipe() != -1) {
                onUpdateEquipe();
            }

            //fenetre gestion de championnat
            if (e.getSource() == fenetreChamp.getTabMatch() && fenetreChamp.getLigneMatch() != -1) {
                onSelectMatch();
            }

        }

    }
    @Override
    public void windowClosing(WindowEvent e) {
        if (e.getWindow().getName().equals("frame0"))
            onQuitter();
    }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // methode de mise a jour des listes
    public void updateClubList() {
        this.fenetrePrincipale.setTabClub(this.awbb.getClubs());
    }

    public void updateChampList() {
        this.fenetrePrincipale.setTabChampionnat(this.awbb.getChampionnats());
    }

    // fenetre principal
    private void onQuitter()
    {
        int ret = JOptionPane.showConfirmDialog(null,"Êtes-vous certain de vouloir quitter ?");
        if (ret == JOptionPane.YES_OPTION){
            awbb.save("awbb.dat");

            // pour enregistrer la position de la fenetre dans un fichiers properties
            Properties properties = new Properties();
            properties.setProperty("window.x", String.valueOf(fenetrePrincipale.getLocation().x));
            properties.setProperty("window.y", String.valueOf(fenetrePrincipale.getLocation().y));

            try (OutputStream output = new FileOutputStream("windows.properties")) {
                properties.store(output, "Window Position");
                System.out.println(fenetrePrincipale.getLocation().x);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // pour fermer le programme
            System.exit(0);




        }

    }

    private void onSuprClub(){
        int index = fenetrePrincipale.getSelectClub();
        if (index == -1)
            JOptionPane.showMessageDialog(null, "Veuillez sélectionnez un club", "Erreur de suppression",JOptionPane.ERROR_MESSAGE);
        else {
            if (!awbb.checkClubActive(awbb.getClubs().get(index))) {
                awbb.getClubs().remove(index);
                this.fenetrePrincipale.setTabClub(this.awbb.getClubs());
                JOptionPane.showMessageDialog(null, "Le club a bien été supprimer", "Supprimer un club",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(null, "Le club n'a pas été supprimer car il contient encore des équipes", "Supprimer un club",JOptionPane.ERROR_MESSAGE);
            }


        }

    }

    private void onCreerChamp(){
        fenetreCreaChamp.setModal(true);
        fenetreCreaChamp.resetChamp();
        fenetreCreaChamp.setVisible(true);
        if (fenetreCreaChamp.isOk())
        {
            JOptionPane.showMessageDialog(null, fenetrePrincipale.getSelectClub(), "Supprimer un club",JOptionPane.QUESTION_MESSAGE);
        }
        fenetreCreaChamp.resetChamp();
        fenetrePrincipale.setTabChampionnat(awbb.getChampionnats()); // mettre a jour la liste
    }

    private void onModifClub(){
        currentClub = awbb.getClubs().get(fenetrePrincipale.getSelectClub());
        fenetreModifClub.setModal(true);
        fenetreModifClub.setFenetre(currentClub);
        fenetreModifClub.setVisible(true);
        updateClubList();
    }
    private void onCreateClub(){
        String nom =  showInputDialog(null, "Comment voulez-vous nommer le club ?", "Créer un club",JOptionPane.QUESTION_MESSAGE);
        if (!nom.isEmpty()) {
            awbb.getClubs().add(new Club(nom));
            currentClub = awbb.getClubs().get(awbb.getClubs().size()-1);

            // pour ouvrir la fenetre avec les caracts
            fenetreModifClub.setModal(true);
            fenetreModifClub.setFenetre(currentClub);
            fenetreModifClub.setVisible(true);

            updateClubList(); // mettre a jour la liste
        }
    }

    // partie Creation championnat

    private void onCreateLeChampionnat() {

        ArrayList<Equipe> tmp = fenetreCreaChamp.getEquipes();
        if (tmp.size() == 8 && !fenetreCreaChamp.getNom().isEmpty()) { // changer en 8

            awbb.getChampionnats().add(new Championnat(fenetreCreaChamp.getNom(), tmp));
            awbb.getChampionnats().get(awbb.getChampionnats().size()-1).generateMatches();


            JOptionPane.showMessageDialog(null, "Le championnat à été créer !", "Championnat créer", JOptionPane.INFORMATION_MESSAGE);
            fenetreCreaChamp.setVisible(false);
        }
        else {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionnez 8 équipes et un nom", "Erreur d'encodage", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void onChangeComboboxNiveau() {
        ArrayList<Equipe> equipes = new ArrayList<>();
        int niveau = fenetreCreaChamp.getNiveau();
        for (Club club : awbb.getClubs()) {
            for (Equipe equipe : club.getEquipes()) {
                if (equipe.getNiveau() == niveau) {
                    equipes.add(equipe);
                }
            }
        }

        fenetreCreaChamp.setTabEquipe(equipes);
    }

    // partie fenetre modif club

    private void onModifInfoClub() {
        currentClub.setNom(fenetreModifClub.getNomClub());
        currentClub.setAdresse(fenetreModifClub.getAdresse());
    }
    private void onCreateArbitre() {
        currentClub.getArbitres().add(new Arbitre(fenetreModifClub.getNomArbitre(), fenetreModifClub.getPrenomArbitre(), fenetreModifClub.getNumArbitre()));
        fenetreModifClub.setTabArbitre(currentClub.getArbitres());
    }
    private void onDeleteArbitre() {
        currentClub.getArbitres().remove(fenetreModifClub.getArbitre());
        fenetreModifClub.setTabArbitre(currentClub.getArbitres());
    }
    private void onCreateEquipe(){
        String nom =  showInputDialog(null, "Comment voulez-vous nommer l'équipe ?", "Créer une équipe",JOptionPane.QUESTION_MESSAGE);
        if (!nom.isEmpty()) {

            // pour reset les champs (on le met après et on unllink le currentequipe pour pas declencher l'event de combobox)
            currentEquipe = null;
            fenetreModifEquipe.resetChamp();

            currentClub.getEquipes().add(new Equipe(nom));
            currentEquipe = currentClub.getEquipes().get(currentClub.getEquipes().size()-1);

            // pour ouvrir la fenetre avec les caracts
            fenetreModifEquipe.setModal(true);
            fenetreModifEquipe.setFenetre(currentEquipe);
            fenetreModifEquipe.setVisible(true);




            fenetreModifClub.setTabEquipe(currentClub.getEquipes()); // mettre a jour la liste
        }
    }
    private void onUpdateEquipe(){
        // pour reset les champs (unllink l'ancien currentequipe pour pas declencher l'event de combobox)
        currentEquipe = null;
        fenetreModifEquipe.resetChamp();

        currentEquipe = currentClub.getEquipes().get(fenetreModifClub.getEquipe());

        // pour ouvrir la fenetre avec les caracts
        fenetreModifEquipe.setModal(true);
        fenetreModifEquipe.setFenetre(currentEquipe);
        fenetreModifEquipe.setVisible(true);

        fenetreModifClub.setTabEquipe(currentClub.getEquipes()); // mettre a jour la liste
    }
    private void onDeleteEquipe(){
        currentClub.getEquipes().remove(fenetreModifClub.getEquipe());
        fenetreModifClub.setTabEquipe(currentClub.getEquipes());
    }

    // partie modif equipe

    private void onUpdateCoach() {
        try {
            currentEquipe.getCoach().setNumCoach(Integer.parseInt(fenetreModifEquipe.getNumCoach()));
            currentEquipe.getCoach().setNom(fenetreModifEquipe.getNomCoach());
            currentEquipe.getCoach().setPrenom(fenetreModifEquipe.getPrenomCoach());
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Le numéro de coach n'est pas valide", "Erreur de modification",JOptionPane.ERROR_MESSAGE);
        }

    }
    private void onAddPersonne() {
        // true = joueur, false = delegue
        // si on entre un string a la place de chiffre
        try {
            if (fenetreModifEquipe.getRadio()) {
                // add joueur
                currentEquipe.getJoueurs().add(new Joueur(fenetreModifEquipe.getNom(),fenetreModifEquipe.getPrenom(),Integer.parseInt(fenetreModifEquipe.getNum())));
                fenetreModifEquipe.setTabJoueur(currentEquipe.getJoueurs());
            }
            else {
                currentEquipe.getDelegues().add(new Delegue(fenetreModifEquipe.getNom(),fenetreModifEquipe.getPrenom(),Integer.parseInt(fenetreModifEquipe.getNum())));
                fenetreModifEquipe.setTabDelegue(currentEquipe.getDelegues());
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Le numéro de la personne n'est pas valide", "Erreur",JOptionPane.ERROR_MESSAGE);

        }

    }
    private void onDeleteJoueur(){
        currentEquipe.getJoueurs().remove(fenetreModifEquipe.getJoueur());
        fenetreModifEquipe.setTabJoueur(currentEquipe.getJoueurs());
    }
    private void onDeleteDelegue(){
        currentEquipe.getDelegues().remove(fenetreModifEquipe.getDelegue());
        fenetreModifEquipe.setTabDelegue(currentEquipe.getDelegues());
    }
    private void onComboboxChangeEquipe() {
        if (currentEquipe != null)
            currentEquipe.setNiveau(fenetreModifEquipe.getNiveau());
    }

    private void onLoadJoueur() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sélectionner un fichier CSV");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Fichiers CSV (*.csv)", "csv"));

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            currentEquipe.load(filePath);
        }

        fenetreModifEquipe.setTabJoueur(currentEquipe.getJoueurs());
    }
    private void onSaveJoueur() {
        currentEquipe.save("player.csv");

    }

    // fenetre de gestion de championnat
    private void onGestChamp() {
        currentChampionnat = awbb.getChampionnats().get(fenetrePrincipale.getSelectedChamp());
        fenetreChamp.setModale(true);
        fenetreChamp.setFenetre(currentChampionnat);
        fenetreChamp.setVisi(true);
        fenetrePrincipale.setTabChampionnat(awbb.getChampionnats());
    }

    private void onPlayGame() {
        if (!fenetreChamp.getScoreLocal().isEmpty() && !fenetreChamp.getScoreExterieur().isEmpty()) {
            try {
                int scoreLocal = Integer.parseInt(fenetreChamp.getScoreLocal());
                int scoreExterieur = Integer.parseInt(fenetreChamp.getScoreExterieur());

                if (scoreLocal >= 0 && scoreExterieur >= 0) {
                    Match cm = currentChampionnat.getMatches().get(fenetreChamp.getLigneMatch());
                    currentChampionnat.playMatch(currentChampionnat.getMatches().get(fenetreChamp.getLigneMatch()), scoreLocal, scoreExterieur);
                    fenetreChamp.getBeans().writeLog(currentChampionnat.getNom() + " -> " + cm.getEquipeLocaux() + " a joué contre " + cm.getEquipeExterieur() +
                            ". Résultat = " + cm.getScoreLocal() + " : "+ cm.getScoreExterieur());
                    fenetreChamp.setFenetre(currentChampionnat);
                } else {
                    JOptionPane.showMessageDialog(null, "Le score d'une équipe ne peut pas être négatif", "Erreur",JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer une valeur correct", "Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Veuillez entrer deux valeur", "Erreur",JOptionPane.ERROR_MESSAGE);
    }

    private void onRenameChamp() {
        String newName = JOptionPane.showInputDialog(null, "Comment voulez-vous renommer le championnat ?", "Renommer le championnat",JOptionPane.QUESTION_MESSAGE);
        currentChampionnat.setNom(newName);
    }
    private void onChangeFormat() {
        String newFormat = JOptionPane.showInputDialog(null, "Quelle format d'affichage pour la date date voulez-vous ?", "Format d'affichage",JOptionPane.QUESTION_MESSAGE);
        fenetreChamp.setFormat(newFormat);
        fenetreChamp.setTabMatch(currentChampionnat.getMatches());
    }
    private void onSelectMatch() {
        JOptionPane.showMessageDialog(null,currentChampionnat.getMatches().get(fenetreChamp.getLigneMatch()), "Information du match", JOptionPane.INFORMATION_MESSAGE);
    }
}
