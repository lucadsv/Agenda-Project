package vue;

import controleur.Controleur;
import javafx.scene.layout.HBox;
import modele.PlanningCollections;

/**
 * La classe HBoxRoot représente une boîte horizontale racine de l'application.
 * Elle contient les différents panneaux de l'application.
 */
public class HBoxRoot extends HBox {
    private static PlanningCollections planning;
    private static Controleur controleur;
    private static VBoxAgenda calendrierPane;
    private static GridPaneFormulaireReservation reservationPane;
    private static VBoxAffichagePlanning affichagePlanning;

    /**
     * Constructeur de la classe HBoxRoot.
     * Initialise une boîte horizontale racine de l'application.
     * Initialise les différents panneaux de l'application.
     */
    public HBoxRoot() {
        super(40);
        planning = new PlanningCollections();
        controleur = new Controleur();
        calendrierPane = new VBoxAgenda();
        reservationPane = new GridPaneFormulaireReservation();
        affichagePlanning = new VBoxAffichagePlanning();
        getChildren().addAll(calendrierPane, reservationPane, affichagePlanning);
    }

    /**
     * Getter pour l'objet PlanningCollections.
     * @return L'objet PlanningCollections.
     */
    public static PlanningCollections getPlanning() {
        return planning;
    }

    /**
     * Getter pour l'objet Controleur.
     * @return L'objet Controleur.
     */
    public static Controleur getControleur() {
        return controleur;
    }

    /**
     * Getter pour le panneau de calendrier.
     * @return Le panneau de calendrier.
     */
    public static VBoxAgenda getCalendrierPane() {
        return calendrierPane;
    }

    /**
     * Getter pour le panneau de formulaire de réservation.
     * @return Le panneau de formulaire de réservation.
     */
    public static GridPaneFormulaireReservation getReservationPane() {
        return reservationPane;
    }

    /**
     * Getter pour le panneau d'affichage du planning.
     * @return Le panneau d'affichage du planning.
     */
    public static VBoxAffichagePlanning getAffichagePlanning() {
        return affichagePlanning;
    }
}