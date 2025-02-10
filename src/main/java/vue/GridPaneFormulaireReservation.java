package vue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import modele.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static modele.ConstanteCalendrier.JOURS_SEMAINE;
import static modele.ConstanteCalendrier.MOIS;
/**
 * La classe GridPaneFormulaireReservation représente un formulaire pour la réservation.
 * Elle permet à l'utilisateur de saisir les détails de la réservation.
 */
public class GridPaneFormulaireReservation extends GridPane {

    private TextField txtCours;
    private RadioButton rbDebutant;
    private RadioButton rbMoyen;
    private RadioButton rbAvance;
    private RadioButton rbExpert;
    private ComboBox<String> cboHeuresDebut;
    private ComboBox<String> cboMinutesDebut;
    private ComboBox<String> cboHeuresFin;
    private ComboBox<String> cboMinutesFin;
    private DateCalendrier chDate = new DateCalendrier();
    private Label lblDateDuJour = new Label("Date du jour : " + new DateCalendrier());
    /**
     * Constructeur de la classe GridPaneFormulaireReservation.
     * Initialise un formulaire pour la réservation.
     */
    public GridPaneFormulaireReservation() {
        // Ne pas afficher les lignes du GridPane
        this.setGridLinesVisible(false);

        // Déclaration et instanciation des composants
        Label lblCours = new Label("Cours :");
        txtCours = new TextField();
        txtCours.setPromptText("Nom du cours");
        Platform.runLater(()->txtCours.requestFocus());

        Label lblNiveau = new Label("Niveau :");
        ToggleGroup toggleGroupNiveau = new ToggleGroup();
        rbDebutant = new RadioButton("Débutant");
        rbDebutant.setToggleGroup(toggleGroupNiveau);
        rbMoyen = new RadioButton("Moyen");
        rbMoyen.setToggleGroup(toggleGroupNiveau);
        rbAvance = new RadioButton("Avancé");
        rbAvance.setToggleGroup(toggleGroupNiveau);
        rbExpert = new RadioButton("Expert");
        rbExpert.setToggleGroup(toggleGroupNiveau);

        Label lblHoraireDebut = new Label("Horaire de");
        ObservableList<String> heures = FXCollections.observableArrayList(
                "00","01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"
        );
        ObservableList<String> minutes = FXCollections.observableArrayList(
                "00", "15", "30", "45"
        );
        cboHeuresDebut = new ComboBox<>(heures);
        cboMinutesDebut = new ComboBox<>(minutes);

        Label lblHoraireFin = new Label("à");
        cboHeuresFin = new ComboBox<>(heures);
        cboMinutesFin = new ComboBox<>(minutes);

        // Initialisation des valeurs par défaut pour les ComboBoxes des heures de début et de fin
        cboHeuresDebut.getSelectionModel().selectFirst();  // Sélectionne la première heure (08) par défaut
        cboHeuresFin.getSelectionModel().selectFirst();     // Sélectionne la dernière heure (19) par défaut

        // Initialisation des valeurs par défaut pour les ComboBoxes des minutes de début et de fin
        cboMinutesDebut.getSelectionModel().selectFirst();  // Sélectionne la première minute (00) par défaut
        cboMinutesFin.getSelectionModel().selectFirst();     // Sélectionne la dernière minute (45) par défaut


        Button btnAnnuler = new Button("Annuler");

        Button btnEnregistrer = new Button("Enregistrer");


        // Styles et espacements
        this.setHgap(10);
        this.setVgap(10);
        this.setStyle("-fx-padding: 15px; -fx-border-color: #ccc; -fx-border-radius: 5px; -fx-background-radius: 5px;");

        // Ajout des composants au conteneur ligne après ligne
        this.add(lblDateDuJour, 0, 0, 2, 1); // Ligne 0

        Separator separator_date = new Separator();
        this.add(separator_date, 0, 1, 2, 1);

        // Ajout des composants au conteneur ligne après ligne
        this.add(lblCours, 0, 2);
        this.add(txtCours, 1, 2);

        VBox vboxNiveau = new VBox(5, lblNiveau, rbDebutant, rbMoyen, rbAvance, rbExpert);
        this.add(vboxNiveau, 0, 3, 2, 1);

        HBox hboxHoraireDebut = new HBox(5, lblHoraireDebut, cboHeuresDebut, new Label("h"), cboMinutesDebut, new Label("mn"));
        this.add(hboxHoraireDebut, 0, 4, 2, 1);

        HBox hboxHoraireFin = new HBox(5, lblHoraireFin, cboHeuresFin, new Label("h"), cboMinutesFin, new Label("mn"));
        this.add(hboxHoraireFin, 1, 5, 2, 1);

        Separator separator = new Separator();
        this.add(separator, 0, 6, 2, 1);

        HBox hboxBoutons = new HBox(10, btnAnnuler, btnEnregistrer);
        this.add(hboxBoutons, 0, 7, 2, 1);

        // Donner le focus au TextField pour le nom du cours à l'ouverture
        txtCours.requestFocus();
        btnEnregistrer.setOnAction(HBoxRoot.getControleur());

        btnAnnuler.setOnAction(evt ->{
            reset();
        });
    }
    /**
     * Réinitialise les champs du formulaire.
     */
    public void reset(){
        txtCours.clear();

        // Désélectionner tous les boutons radio
        rbDebutant.setSelected(true);
        rbMoyen.setSelected(false);
        rbAvance.setSelected(false);
        rbExpert.setSelected(false);

        cboHeuresDebut.getSelectionModel().selectFirst();
        cboMinutesDebut.getSelectionModel().selectFirst();
        cboHeuresFin.getSelectionModel().selectFirst();
        cboMinutesFin.getSelectionModel().selectFirst();
    }
    /**
     * Récupère les informations saisies dans le formulaire et crée une réservation.
     *
     * @return Une réservation contenant les informations saisies.
     */
    public Reservation getReservation(){
        // Récupérer les valeurs sélectionnées
        String cours = txtCours.getText();
        String niveau = getNiveauSelectionne();
        int heureDebut = cboHeuresDebut.getSelectionModel().getSelectedIndex();
        int minuteDebut = cboMinutesDebut.getSelectionModel().getSelectedIndex()*15;
        int heureFin = cboHeuresFin.getSelectionModel().getSelectedIndex();
        int minuteFin = cboMinutesFin.getSelectionModel().getSelectedIndex()*15;

        try {
            Reservation r = new Reservation(chDate, new PlageHoraire(new Horaire(heureDebut, minuteDebut), new Horaire(heureFin, minuteFin)), cours);
            return r;
        }
        catch (ExceptionReservation ex){
            System.err.println(ex.getMessage());
            return null;
        }
    }
    /**
     * Définit la date affichée dans le label "Date du jour".
     *
     * @param parDate La date à afficher.
     */
    public void setLabelDateJour (DateCalendrier parDate){
        lblDateDuJour.setText("Date du jour : " + parDate);
        chDate = parDate;
    }
    /**
     * Retourne le niveau sélectionné dans le formulaire.
     *
     * @return Le niveau sélectionné.
     */
    private String getNiveauSelectionne() {
        if (rbDebutant.isSelected()) {
            return "Débutant";
        } else if (rbMoyen.isSelected()) {
            return "Moyen";
        } else if (rbAvance.isSelected()) {
            return "Avance";
        } else if (rbExpert.isSelected()) {
            return "Expert";
        } else {
            return "Non sélectionne";
        }
    }
}