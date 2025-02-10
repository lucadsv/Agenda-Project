package modele;

/**
 * Classe représentant un horaire avec une précision au quart d'heure.
 */
public class Horaire {
    private int chHeure;
    private int chQuartHeure;

    /**
     * Constructeur de la classe Horaire.
     * Initialise un horaire avec une heure et un quart d'heure spécifiques.
     *
     * @param parHeure l'heure.
     * @param parQuartHeure le quart d'heure.
     */
    public Horaire(int parHeure, int parQuartHeure) {
        chHeure = parHeure;
        chQuartHeure = parQuartHeure;
    }

    /**
     * Convertit l'horaire en minutes.
     *
     * @return le nombre total de minutes.
     */
    public int toMinute() {
        return chHeure * 60 + chQuartHeure;
    }

    /**
     * Retourne l'heure.
     *
     * @return l'heure.
     */
    public int getHeure() {
        return chHeure;
    }

    /**
     * Retourne le quart d'heure.
     *
     * @return le quart d'heure.
     */
    public int getQuartHeure() {
        return chQuartHeure;
    }

    /**
     * Définit l'heure.
     *
     * @param parHeure l'heure à définir.
     */
    public void setHeure(int parHeure) {
        chHeure = parHeure;
    }

    /**
     * Définit le quart d'heure.
     *
     * @param parQuartHeure le quart d'heure à définir.
     */
    public void setQuartHeure(int parQuartHeure) {
        chQuartHeure = parQuartHeure;
    }

    /**
     * Retourne une représentation sous forme de chaîne de l'horaire.
     *
     * @return une chaîne de caractères représentant l'horaire.
     */
    @Override
    public String toString() {
        return chHeure + "h" + chQuartHeure;
    }
}