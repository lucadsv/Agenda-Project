package modele;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.TreeMap;

/**
 * La classe PlanningCollections représente un planning de réservations utilisant des collections.
 */
public class PlanningCollections {
    private ArrayList<Reservation> chListReservations;
    private TreeSet<Reservation> chTreeReservations;
    private TreeMap<Integer, TreeSet<Reservation>> chMap;

    /**
     * Constructeur de la classe PlanningCollections.
     * Initialise les différentes collections utilisées pour stocker les réservations.
     */
    public PlanningCollections() {
        chListReservations = new ArrayList<>();
        chTreeReservations = new TreeSet<>();
        chMap = new TreeMap<>();
    }

    /**
     * Retourne une représentation sous forme de chaîne du planning.
     *
     * @return une chaîne de caractères représentant le contenu du planning.
     */
    public String toString() {
        return chMap.toString();
    }

    /**
     * Ajoute une réservation au planning.
     *
     * @param parReservation la réservation à ajouter.
     */
    public void ajout(Reservation parReservation) {
        int cle = parReservation.getDate().getNoSemaine();
        if (chMap.containsKey(cle)) {
            TreeSet<Reservation> valeur = chMap.get(cle);
            valeur.add(parReservation);
        } else {
            TreeSet<Reservation> nouveau = new TreeSet<>();
            nouveau.add(parReservation);
            chMap.put(cle, nouveau);
        }
    }

    /**
     * Retourne les réservations dont le titre contient la chaîne spécifiée.
     *
     * @param parStr la chaîne à rechercher dans les titres des réservations.
     * @return un ensemble de réservations dont le titre contient la chaîne spécifiée.
     */
    public TreeSet<Reservation> GetReservation(String parStr) {
        TreeSet<Reservation> nouveau = new TreeSet<>();
        for (Reservation R : chTreeReservations) {
            String titre = R.getTitre();
            if (titre.contains(parStr)) {
                nouveau.add(R);
            }
        }
        return nouveau;
    }

    /**
     * Retourne les réservations pour une date donnée.
     *
     * @param numeroSemaine la date pour laquelle rechercher les réservations.
     * @return un ensemble de réservations pour la date spécifiée.
     */
    public TreeSet<Reservation> GetReservationDate(int numeroSemaine) {
        return chMap.get(numeroSemaine);
    }

    /**
     * Retourne les réservations de la semaine en cours.
     *
     * @return un ensemble de réservations pour la semaine en cours.
     */
    public TreeSet<Reservation> getReservationDeLaSemaine(int numeroSemaine) {
        return chMap.get(numeroSemaine);
    }
}