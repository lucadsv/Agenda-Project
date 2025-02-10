package modele;

import java.util.Collection;
import java.util.TreeSet;

public class CalendrierDuMois {

    private int mois;
    private int annee;
    private Collection<DateCalendrier> treeSetDate;

    /**
     * Constructeur de la classe CalendrierDuMois.
     * Initialise un calendrier pour un mois et une année donnés.
     * Remplit une collection de dates du mois spécifié.
     *
     * @param mois le mois pour lequel créer le calendrier.
     * @param annee l'année pour laquelle créer le calendrier.
     */
    public CalendrierDuMois(int mois, int annee) {
        this.mois = mois;
        this.annee = annee;
        treeSetDate = new TreeSet<DateCalendrier>();
        DateCalendrier date = new DateCalendrier(1, mois, annee);
        int indiceJour = date.getJourSemaine();

        // Remplissage des dates jusqu'au premier jour du mois
        for (int x = indiceJour; x != 0; x--) {
            treeSetDate.add(date);
            date = date.dateDeLaVeille();
        }

        // Remplissage des dates pour le reste du mois
        date = new DateCalendrier(2, mois, annee);
        indiceJour = indiceJour % 7;
        while (date.getMois() == mois) {
            while (indiceJour < 7) {
                treeSetDate.add(date);
                date = date.dateDuLendemain();
                indiceJour++;
            }
            indiceJour = 0;
        }
    }

    /**
     * Retourne la collection de dates du calendrier du mois.
     *
     * @return une collection de DateCalendrier représentant les dates du mois.
     */
    public Collection<DateCalendrier> getDates() {
        return treeSetDate;
    }

    /**
     * Retourne une représentation sous forme de chaîne du calendrier du mois.
     *
     * @return une chaîne de caractères représentant le nombre de dates
     *         et la liste des dates du calendrier.
     */
    public String toString() {
        return treeSetDate.size() + " " + treeSetDate.toString();
    }

    /**
     * Retourne le mois du calendrier.
     *
     * @return le mois du calendrier.
     */
    public int getMois() {
        return mois;
    }

    /**
     * Retourne l'année du calendrier.
     *
     * @return l'année du calendrier.
     */
    public int getAnnee() {
        return annee;
    }
}