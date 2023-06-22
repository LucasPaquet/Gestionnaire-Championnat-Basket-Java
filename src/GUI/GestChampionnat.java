package GUI;

import BasketBeans.LogWriterBean;
import BasketBeans.WriteEvent;
import BasketBeans.WriteListener;
import ClasseGestionBasket.Championnat;
import ClasseGestionBasket.EquipeClassement;
import ClasseGestionBasket.Match;
import Controller.Controleur;
import Interface.IGestChampionnat;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GestChampionnat extends JDialog implements WriteListener, PropertyChangeListener, IGestChampionnat {
    private JPanel panel1;
    private JTable tabMatch;
    private JTable tabClassement;
    private JButton jouerLeMatchButton;
    private JTextField tfScoreLocal;
    private JTextField tfScoreExt;
    private JLabel labelLocal;
    private JLabel lbLog;
    private JLabel lbMatch;
    private JMenuItem menuItemRename;
    private JMenuItem menuItemFormat;
    private JMenuItem menuItemQuit;
    private transient LogWriterBean logWriterBean;
    private String format;

    public GestChampionnat(LogWriterBean beans){
        setTitle("Gestion du championnat");
        setContentPane(panel1);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // pour implementer les fonctions de fenetre de bas (exit, agrandir, ...)
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize(); // avoir la taille de l'écran

        // partie menu

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menuFichier = new JMenu("Fichier");
        menuBar.add(menuFichier);
        JMenuItem menuItemBackSelect = new JMenuItem("Retourner à la sélection");
        menuFichier.add(menuItemBackSelect);
        menuItemQuit = new JMenuItem("Quitter l'application");
        menuFichier.add(menuItemQuit);

        JMenu menuOption = new JMenu("Option");
        menuBar.add(menuOption);
        menuItemRename = new JMenuItem("Renommer le championnat");
        menuOption.add(menuItemRename);
        menuItemFormat = new JMenuItem("Changer le format d'affichage des rencontres");
        menuOption.add(menuItemFormat);

        // pour les tableau
        tabClassement.setEnabled(false); // pour empcher l'edition
        tabMatch.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // pour empcher plusieurs selction

        pack();

        // pour redimentionner la fenetre
        setSize(1200,800);
        setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2); // pour mettre en plein milieu de l'écran

        // pour javaBeans
        logWriterBean = beans;

        // pour le format d'afficage du mathc
        format = "dd/MM/yyyy HH:mm:ss";

        // menu bar quitter qui quitte
        menuItemBackSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public int getLigneMatch(){ return tabMatch.getSelectedRow();}

    // getX text field
    public String getScoreLocal() { return tfScoreLocal.getText();}
    public String getScoreExterieur() { return tfScoreExt.getText();}
    public JTable getTabMatch() {return tabMatch;}

    // pour getX beans
    public LogWriterBean getBeans() {
        return logWriterBean;
    }

    public void setControleur(Controleur c)
    {
        jouerLeMatchButton.addActionListener(c);
        menuItemRename.addActionListener(c);
        menuItemFormat.addActionListener(c);
        menuItemQuit.addActionListener(c);
        tabMatch.addMouseListener(c);
        this.addWindowListener(c);
    }
    public void setFenetre(Championnat cp) {
        setTabMatch(cp.getMatches());
        setTabClassement(cp.getEquipes());
    }
    // setX string
    public void setFormat(String f) { this.format = f; }
    // setX tab


    public void setTabMatch(ArrayList<Match> ma) {
        DefaultTableModel model = new DefaultTableModel();

        // Définir les noms des colonnes
        model.setColumnIdentifiers(new String[]{"Équipe local", "Équipe extérieur", "Date de rencontre", "Jouer"});

        // Définir le format de date souhaité
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);

        // Ajouter les données des matches au modèle de tableau
        for (Match match : ma) {
            LocalDateTime dateRencontre = match.getDateRencontre();

            // Formater la date selon le format souhaité
            String formattedDate = dateFormatter.format(dateRencontre);

            model.addRow(new Object[]{match.getEquipeLocaux(), match.getEquipeExterieur(), formattedDate, match.isPlayed()});
        }

        // Appliquer le modèle de tableau à la JTable
        tabMatch.setModel(model);
    }


    public void setTabClassement(ArrayList<EquipeClassement> ec) {
        DefaultTableModel model = new DefaultTableModel();

        // Définir les noms des colonnes
        model.setColumnIdentifiers(new String[]{"Équipe", "Victoire", "Défaite", "Égalité", "Point"});

        // Ajouter les données des matches au modèle de tableau
        for (EquipeClassement equipe : ec) {
            model.addRow(new Object[]{equipe.getEquipe(), equipe.getVictoire(), equipe.getDefaite(), equipe.getEgalite(), equipe.getPoint()});
        }
        // Appliquer le modèle de tableau à la JTable
        tabClassement.setModel(model);
    }

    // setX pour label
    public void setLog(String log){
        lbLog.setText(log);
    }
    public void setMatch(String mt){
        lbMatch.setText(mt);
    }


    public void setVisi(boolean bo) {
        setVisible(bo);
    }


    public void setModale(boolean bo) {
        setModal(bo);
    }

    @Override
    public void WriteDetected(WriteEvent e) {
        setLog("Match joué : " + e.getLog());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        LogWriterBean wb = (LogWriterBean) evt.getSource();

        if ("log".equals(evt.getPropertyName())){
            setMatch( "Log enregistré : " + evt.getNewValue());
        }
    }
}
