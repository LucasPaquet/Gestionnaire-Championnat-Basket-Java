package ClasseGestionBasket;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Match implements Serializable {
    private LocalDateTime dateRencontre;
    private int scoreLocal;
    private int scoreExterieur;
    private Arbitre[] arbitres;
    private Delegue[] delegues;
    private EquipeClassement equipeLocaux;
    private EquipeClassement equipeExterieur;
    private boolean played;

    public Match(EquipeClassement equipeLocaux, EquipeClassement equipeExterieur, Arbitre[] arbitres, Delegue[] delegues, boolean played) {
        this.arbitres = arbitres;
        this.delegues = delegues;
        this.equipeLocaux = equipeLocaux;
        this.equipeExterieur = equipeExterieur;
        this.played = played;
    }

    public LocalDateTime getDateRencontre() {
        return dateRencontre;
    }

    public void setDateRencontre(LocalDateTime dateRencontre) {
        this.dateRencontre = dateRencontre;
    }

    public int getScoreLocal() {
        return scoreLocal;
    }

    public void setScoreLocal(int scoreLocal) {
        this.scoreLocal = scoreLocal;
    }

    public int getScoreExterieur() {
        return scoreExterieur;
    }

    public void setScoreExterieur(int scoreExterieur) {
        this.scoreExterieur = scoreExterieur;
    }

    public Arbitre[] getArbitres() {
        return arbitres;
    }

    public void setArbitres(Arbitre[] arbitres) {
        this.arbitres = arbitres;
    }

    public Delegue[] getDelegues() {
        return delegues;
    }

    public void setDelegues(Delegue[] delegues) {
        this.delegues = delegues;
    }

    public EquipeClassement getEquipeLocaux() {
        return equipeLocaux;
    }

    public void setEquipeLocaux(EquipeClassement equipeLocaux) {
        this.equipeLocaux = equipeLocaux;
    }

    public EquipeClassement getEquipeExterieur() {
        return equipeExterieur;
    }

    public void setEquipeExterieur(EquipeClassement equipeExterieur) {
        this.equipeExterieur = equipeExterieur;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return scoreLocal == match.scoreLocal && scoreExterieur == match.scoreExterieur && played == match.played && Objects.equals(dateRencontre, match.dateRencontre) && Arrays.equals(arbitres, match.arbitres) && Arrays.equals(delegues, match.delegues) && Objects.equals(equipeLocaux, match.equipeLocaux) && Objects.equals(equipeExterieur, match.equipeExterieur);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(dateRencontre, scoreLocal, scoreExterieur, equipeLocaux, equipeExterieur, played);
        result = 31 * result + Arrays.hashCode(arbitres);
        result = 31 * result + Arrays.hashCode(delegues);
        return result;
    }

    @Override
    public String toString() {
        String strArbitre = "";
        String strDelegue = "";
        for (int i = 0; i < 2; i++) {
            strArbitre = strArbitre + arbitres[i] + " ";
        }
        for (int i = 0; i < 5; i++) {
            strDelegue = strDelegue + delegues[i] + " ";
        }
        return "Match : " + equipeLocaux + " VS " + equipeExterieur + " Delegues : " + strDelegue + " arbitres : " + strArbitre + "\n";
    }
}
