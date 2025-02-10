package modele;

/**
 * La classe Reservation représente une réservation.
 */
public class Reservation implements Comparable<Reservation> {
    private Date chDate;
    private PlageHoraire chPlageHoraire;
    private String chIntitule;

    /**
     * Constructeur de la classe Reservation.
     * Initialise une réservation avec une date, une plage horaire et un intitulé.
     *
     * @param parDate        la date de la réservation.
     * @param parPlageHoraire la plage horaire de la réservation.
     * @param parIntitule    l'intitulé de la réservation.
     * @throws ExceptionReservation si la réservation est invalide.
     */
    public Reservation(Date parDate, PlageHoraire parPlageHoraire, String parIntitule) throws ExceptionReservation {
        chDate = parDate;
        chPlageHoraire = parPlageHoraire;
        chIntitule = parIntitule;
        if (!estValide()) {
            throw new ExceptionReservation("Reservation invalide");
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de la réservation.
     *
     * @return une chaîne de caractères représentant la réservation.
     */
    public String toString() {
        return chIntitule + ", " + chDate.toString() + ", " + chPlageHoraire.toString();
    }

    /**
     * Compare cette réservation avec une autre réservation.
     *
     * @param parReservation la réservation à comparer.
     * @return un entier négatif, zéro ou un entier positif si cette réservation est antérieure, égale ou postérieure à l'autre.
     */
    public int compareTo(Reservation parReservation) {
        int compareJour = this.chDate.compareTo(parReservation.chDate);
        int comparePlageHoraire = this.chPlageHoraire.compareTo(parReservation.chPlageHoraire);
        if (compareJour != 0) {
            return compareJour;
        } else {
            return comparePlageHoraire;
        }
    }

    /**
     * Vérifie si la réservation est valide.
     *
     * @return true si la réservation est valide, false sinon.
     */
    public boolean estValide() {
        return chDate.estValide() && chPlageHoraire.estValide();
    }

    /**
     * Retourne la date de la réservation.
     *
     * @return la date de la réservation.
     */
    public Date getChDate() {
        return chDate;
    }

    /**
     * Retourne la plage horaire de la réservation.
     *
     * @return la plage horaire de la réservation.
     */
    public PlageHoraire getChPlageHoraire() {
        return chPlageHoraire;
    }

    /**
     * Retourne l'intitulé de la réservation.
     *
     * @return l'intitulé de la réservation.
     */
    public String getChIntitule() {
        return chIntitule;
    }

    /**
     * Retourne la date de la réservation.
     *
     * @return la date de la réservation.
     */
    public Date getDate() {
        return chDate;
    }

    /**
     * Retourne l'intitulé de la réservation.
     *
     * @return l'intitulé de la réservation.
     */
    public String getTitre() {
        return chIntitule;
    }
}