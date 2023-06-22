package ClasseGestionBasket;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Objects;

public class Championnat implements Serializable {
    private ArrayList<EquipeClassement> equipes;
    private ArrayList<Match> matches;
    private String nom;

    public Championnat() {
        matches = new ArrayList<>();
        equipes = new ArrayList<>();
    }

    public Championnat(String nom) {
        matches = new ArrayList<>();
        equipes = new ArrayList<>();
        this.nom = nom;
    }

    public Championnat(String nom, ArrayList<Equipe> equipes ) {
        this.equipes = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            this.equipes.add(new EquipeClassement(equipes.get(i)));
        }
        this.nom = nom;
        matches = new ArrayList<>();
    }

    public ArrayList<EquipeClassement> getEquipes() {
        return equipes;
    }

    public void setEquipes(ArrayList<EquipeClassement> equipes) {
        this.equipes = equipes;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Championnat that = (Championnat) o;

        if (!Objects.equals(equipes, that.equipes)) return false;
        if (!Objects.equals(matches, that.matches)) return false;
        return Objects.equals(nom, that.nom);
    }

    @Override
    public int hashCode() {
        int result = equipes != null ? equipes.hashCode() : 0;
        result = 31 * result + (matches != null ? matches.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return nom + " : equipes=" + equipes + '\n' + matches;
    }


    /**
     * Methode qui permet de jouer un match et de mettre a jour le classement
     * @param m L'object match jouer
     * @param scoreLocal Score de l'equipe local
     * @param scoreExterieur Score de l'equipe exterieur
     */
    public void playMatch(Match m, int scoreLocal, int scoreExterieur){

        if (!m.isPlayed()) { // pour empecher de jouer deux fois le meme match
            // maj des scores dans le ClasseGestionBasket.Match
            m.setScoreLocal(scoreLocal);
            m.setScoreExterieur(scoreExterieur);

            if (scoreLocal > scoreExterieur) {
                // Local gagne
                m.getEquipeLocaux().ajouteVictoire();
                m.getEquipeExterieur().ajouteDefaite();
            } else if (scoreLocal < scoreExterieur) {
                // Local Perd
                m.getEquipeLocaux().ajouteDefaite();
                m.getEquipeExterieur().ajouteVictoire();

            } else {
                // Egalite entre les deux equipes
                m.getEquipeLocaux().ajouteEgalite();
                m.getEquipeExterieur().ajouteEgalite();
            }

            m.setPlayed(true);
        }
    }

    /**
     * Methode qui permet de generer les matchs d'un championnat (seulement 8 equipes 8 equipes)
     */
    public void generateMatches(){

        Delegue[] delegues = new Delegue[5]; // trouver un moyen de recup les listes
        Arbitre[] arbitres = new Arbitre[2];

        for (int i = 0; i < equipes.size() - 1; i++) {
            for (int j = i + 1; j < equipes.size(); j++) {
                this.matches.add(new Match(equipes.get(i), equipes.get(j), arbitres,delegues,false));
            }
        }
        Collections.shuffle(matches);

        LocalDateTime now = LocalDateTime.now();

        // Ajouter un jour pour passer au dimanche de cette semaine
        LocalDateTime sunday = now.with(TemporalAdjusters.next(DayOfWeek.SUNDAY)); // on passe au prochain dimanche

        // Initialiser la liste des dates
        List<LocalDateTime> dates = new ArrayList<>();

        // Générer les dates pour les 4 horaires dans la liste dates
        for (int i = 0; i < (matches.size()/4) + 1; i++) { // on fait 4 date par 4
            // Samedi 10h
            dates.add(sunday.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)).with(LocalTime.of(10, 0)).plusWeeks(i));
            // Samedi 15h
            dates.add(sunday.with(TemporalAdjusters.previous(DayOfWeek.SATURDAY)).with(LocalTime.of(15, 0)).plusWeeks(i));
            // Dimanche 10h
            dates.add(sunday.with(LocalTime.of(10, 0)).plusWeeks(i));
            // Dimanche 15h
            dates.add(sunday.with(LocalTime.of(15, 0)).plusWeeks(i));
        }

        // donner la date et heure pour chaque match
        for (int i = 0; i < matches.size(); i++) {
            matches.get(i).setDateRencontre(dates.get(i));
        }

        /* version moins propre
        //jour 1
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(1)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(2),equipes.get(3)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(4),equipes.get(5)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(6),equipes.get(7)));

        // jour 2
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(2)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(1),equipes.get(3)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(4),equipes.get(6)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(5),equipes.get(7)));

        // jour 3
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(3)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(1),equipes.get(2)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(4),equipes.get(7)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(5),equipes.get(6)));

        // jour 4
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(4)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(1),equipes.get(5)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(3),equipes.get(7)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(2),equipes.get(6)));

        // jour 5
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(5)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(1),equipes.get(4)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(3),equipes.get(6)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(2),equipes.get(7)));

        // jour 6 a refaire
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(1)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(5),equipes.get(4)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(3),equipes.get(2)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(6),equipes.get(7)));

        // jour 7 a refaire
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(0),equipes.get(7)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(5),equipes.get(4)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(3),equipes.get(2)));
        this.matches.add(new ClasseGestionBasket.Match(equipes.get(6),equipes.get(6)));*/
    }




}
