package controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import modele.DateCalendrier;
import modele.PlanningCollections;
import modele.Reservation;
import vue.GridPaneFormulaireReservation;
import vue.HBoxRoot;
/**
 * La classe Controleur est responsable de la gestion des événements dans l'application.
 * Elle gère les interactions utilisateur et met à jour les vues en conséquence.
 */
public class Controleur implements EventHandler {
    private DateCalendrier chDate = new DateCalendrier();
    private static PlanningCollections planning;
    /**
     * Constructeur par défaut de la classe Controleur.
     * Initialise le planning des réservations.
     */
    public Controleur(){
        planning = new PlanningCollections();
    }
    /**
     * Méthode de gestion des événements.
     * Gère les actions effectuées par l'utilisateur et met à jour les vues en conséquence.
     * @param event Event : l'événement à gérer.
     */
    @Override
    public void handle(Event event) {
        GridPaneFormulaireReservation reservationPane = HBoxRoot.getReservationPane();
        // la source de event est un ToggleButton du calendrier
        if (event.getSource() instanceof ToggleButton){
            ToggleButton bouton = (ToggleButton) event.getSource();
            chDate = (DateCalendrier) bouton.getUserData();
            reservationPane.setLabelDateJour(chDate);
            HBoxRoot.getAffichagePlanning().setSemaineDateJour(chDate);
            HBoxRoot.getAffichagePlanning().update(chDate, planning);
        }
        // la source de event est le bouton "Enregistrer" du formulaire de réservation
        if (event.getSource() instanceof Button) {
            Reservation r = reservationPane.getReservation();
            planning.ajout(r);
            System.out.println(planning);
            HBoxRoot.getAffichagePlanning().update(chDate, planning);
        }
    }
    /**
     * Méthode permettant d'obtenir le planning des réservations.
     * @return PlanningCollections : le planning des réservations.
     */
    public PlanningCollections getPlanning(){
        return planning;
    }
}