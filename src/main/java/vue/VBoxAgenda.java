package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.ConstanteCalendrier;
import modele.DateCalendrier;

import java.util.List;

/**
 * La classe VBoxAgenda représente une boîte verticale contenant l'agenda.
 * Elle affiche un calendrier mensuel sous forme de boutons pouvant être activés.
 */
public class VBoxAgenda extends VBox implements ConstanteCalendrier {
    /**
     * Constructeur de la classe VBoxAgenda.
     * Initialise une boîte verticale contenant l'agenda.
     * Affiche un calendrier mensuel sous forme de boutons pouvant être activés.
     */
    public VBoxAgenda() {
        super(40);
        DateCalendrier today = new DateCalendrier();

        Label labHaut = new Label(MOIS[today.getMois() - 1] + " " + today.getAnnee());

        Button boutonNext = new Button(">");
        Button boutonPrev = new Button("<");
        Button boutonLast = new Button(">>");
        Button boutonFirst = new Button("<<");

        HBox hboxBoutons = new HBox();
        hboxBoutons.getChildren().addAll(boutonFirst, boutonPrev, boutonNext, boutonLast);
        HBox hBoxHaut = new HBox(100);
        hBoxHaut.getChildren().addAll(labHaut, hboxBoutons);

        StackPane stackPaneMois = new StackPane();

        ToggleGroup buttonGroup = new ToggleGroup();
        for (int numMois = 1; numMois <= 12; numMois++) {
            CalendrierDuMois monthCalendar = new CalendrierDuMois(numMois, today.getAnnee());
            TilePane tilepane = new TilePane();
            tilepane.setPrefColumns(7);
            tilepane.setPrefRows(monthCalendar.getDates().size() % 7 + 1);
            tilepane.setId("opaque");

            for (String jourAb : JOURS_SEMAINE) {
                Label labelJour = new Label(jourAb);
                tilepane.getChildren().add(labelJour);
            }

            for (DateCalendrier date : monthCalendar.getDates()) {
                ToggleButton boutonDate = new ToggleButton(Integer.toString(date.getJour()));
                boutonDate.setToggleGroup(buttonGroup);
                tilepane.getChildren().add(boutonDate);
                boutonDate.setUserData(date);
                boutonDate.setOnAction(HBoxRoot.getControleur());
                if (date.getMois() != monthCalendar.getMois()) {
                    boutonDate.setId("dateHorsMois");
                }
                if (date.isToday()) {
                    boutonDate.setId("today");
                }
            }
            tilepane.setAccessibleText(MOIS[numMois - 1]);
            stackPaneMois.getChildren().add(tilepane);
        }
        this.getChildren().addAll(stackPaneMois, hBoxHaut);
        List<Node> liste = stackPaneMois.getChildren();
        int indexHaut = liste.size() - 1;
        while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[today.getMois() - 1])) {
            liste.get(0).toFront();
        }

        boutonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                liste.get(0).toFront();
                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + today.getAnnee());
            }
        });

        boutonPrev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                liste.get(liste.size() - 1).toBack();
                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + today.getAnnee());
            }
        });

        boutonFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[0])) {
                    liste.get(0).toFront();
                }

                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + today.getAnnee());
            }
        });

        boutonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[11])) {
                    liste.get(liste.size() - 1).toBack();
                }

                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + today.getAnnee());
            }
        });
    }
}