package vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.ConstanteCalendrier;
import modele.DateCalendrier;

import java.util.Collection;
import java.util.List;

import static modele.ConstanteCalendrier.MOIS;

/**
 * La classe VBoxStackPaneRoot représente une boîte verticale contenant un ensemble de StackPane pour afficher les calendriers de différents mois.
 */
public class VBoxStackPaneRoot extends VBox implements ConstanteCalendrier {
    /**
     * Constructeur de la classe VBoxStackPaneRoot.
     * Initialise une boîte verticale contenant un ensemble de StackPane pour afficher les calendriers de différents mois.
     */
    public VBoxStackPaneRoot() {
        super(20);
        DateCalendrier date = new DateCalendrier();
        Label labHaut = new Label(MOIS[date.getMois() - 1] + " " + date.getAnnee());
        Button boutonNext = new Button(">");
        Button boutonPrev = new Button("<");
        Button boutonLast = new Button(">>");
        Button boutonFirst = new Button("<<");
        HBox hboxBoutons = new HBox();
        hboxBoutons.getChildren().addAll(boutonFirst, boutonPrev, boutonNext, boutonLast);
        HBox hboxHaut = new HBox(100);
        hboxHaut.getChildren().addAll(labHaut, hboxBoutons);


        StackPane stackPane = new StackPane();
        for (int i = 0; i < MOIS.length; i++) {
            VBox vboxDates = new VBox (3);
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(vboxDates);
            scrollPane.setAccessibleText(MOIS[i]);
            CalendrierDuMois calendrierDuMois = new CalendrierDuMois (i+1, date.getAnnee());
            Collection<DateCalendrier> dates = calendrierDuMois.getDates();
            for (DateCalendrier d : dates) {
                Label lab = new Label(d.toString());
                if (d.compareTo(date) == 0)
                    lab.setId("today");
                if (d.getMois() != date.getMois())
                    lab.setId("horsmois");
                vboxDates.getChildren().add(lab);
            }
            stackPane.getChildren().add(scrollPane);
        }

        getChildren().addAll(hboxHaut, stackPane);
        List<Node> liste = stackPane.getChildren();
        int indexHaut = liste.size()-1;
        while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[date.getMois()-1]))
            liste.get(liste.size()-1).toBack();

        boutonFirst.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[0]))
                    liste.get(0).toFront();
                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + date.getAnnee());
            }
        });

        boutonLast.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                while (!liste.get(indexHaut).getAccessibleText().equals(MOIS[11]))
                    liste.get(0).toFront();
                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + date.getAnnee());
            }
        });

        boutonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                liste.get(0).toFront();
                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + date.getAnnee());
            }
        });

        boutonPrev.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                liste.get(11).toBack();
                String mois = liste.get(indexHaut).getAccessibleText();
                labHaut.setText(mois + " " + date.getAnnee());
            }

        });
    }
}