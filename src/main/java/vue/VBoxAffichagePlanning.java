package vue;

import controleur.Controleur;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import modele.DateCalendrier;
import modele.PlageHoraire;
import modele.PlanningCollections;
import modele.Reservation;

import java.util.ArrayList;
import java.util.TreeSet;
/**
 * La classe VBoxAffichagePlanning représente un conteneur JavaFX utilisé pour afficher le planning des réservations.
 * Il hérite de VBox, ce qui permet de le disposer verticalement dans une interface utilisateur.
 */
public class VBoxAffichagePlanning extends VBox {
    private DateCalendrier chDate = new DateCalendrier();
    private Label chLblNumeroSemaine = new Label("Semaine " + chDate.getNoSemaine());
    private TableView<Reservation> tableDesReservations;
    /**
     * Constructeur par défaut de la classe VBoxAffichagePlanning.
     * Initialise les composants de l'interface utilisateur.
     */
    public VBoxAffichagePlanning() {
        super(20);
        tableDesReservations = new TableView<>();
        TableColumn<Reservation, DateCalendrier> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("chDate"));
        TableColumn<Reservation, String> coursColumn = new TableColumn<>("Cours");
        coursColumn.setCellValueFactory(new PropertyValueFactory<>("chIntitule"));
        TableColumn<Reservation, PlageHoraire> horaireColumn = new TableColumn<>("Horaire");
        horaireColumn.setCellValueFactory(new PropertyValueFactory<>("chPlageHoraire"));
        tableDesReservations.getColumns().addAll(dateColumn, coursColumn, horaireColumn);

        getChildren().addAll(chLblNumeroSemaine, tableDesReservations);
    }
    /**
     * Met à jour la semaine affichée et la date du jour dans l'interface.
     * @param parDate DateCalendrier : la date à afficher.
     */
    public void setSemaineDateJour (DateCalendrier parDate){
        chLblNumeroSemaine.setText("Semaine " + parDate.getNoSemaine());
        chDate = parDate;
    }
    /**
     * Efface toutes les réservations affichées dans le tableau.
     */
    public void clearTable(){
        tableDesReservations.getItems().clear();
    }
    /**
     * Met à jour l'affichage du planning des réservations pour une semaine donnée.
     * @param parDate DateCalendrier : la date de la semaine à afficher.
     * @param planning PlanningCollections : le planning des réservations.
     */
    public void update(DateCalendrier parDate, PlanningCollections planning) {
        int numeroSemaine = parDate.getNoSemaine();
        TreeSet<Reservation> reservationsDeLaSemaine = planning.getReservationDeLaSemaine(numeroSemaine);

        clearTable();

        if (reservationsDeLaSemaine == null) {return;}

        for (Reservation reservation : reservationsDeLaSemaine) {
            tableDesReservations.getItems().add(reservation);
        }
    }
}