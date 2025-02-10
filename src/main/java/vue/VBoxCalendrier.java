package vue;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import modele.CalendrierDuMois;
import modele.DateCalendrier;

import java.util.Collection;

/**
 * La classe VBoxCalendrier représente une boîte verticale contenant le calendrier du mois.
 */
public class VBoxCalendrier extends VBox {
    private Label labAujourd;

    /**
     * Constructeur de la classe VBoxCalendrier.
     * Initialise la boîte verticale contenant le calendrier du mois en cours.
     */
    public VBoxCalendrier() {
        DateCalendrier aujourd = new DateCalendrier();
        int mois = aujourd.getMois();
        int an = aujourd.getAnnee();
        labAujourd = new Label(mois + "/" + an);
        labAujourd.setId("titre");
        VBox.setMargin(labAujourd, new Insets(15));

        VBox vboxDates = new VBox(3);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(vboxDates);
        CalendrierDuMois calendrierDuMois = new CalendrierDuMois(mois, an);
        Collection<DateCalendrier> dates = calendrierDuMois.getDates();
        for (DateCalendrier d : dates) {
            Label lab = new Label(d.toString());
            if (d.compareTo(aujourd) == 0)
                lab.setId("today");
            if (d.getMois() != mois)
                lab.setId("horsmois");
            vboxDates.getChildren().add(lab);
        }
        getChildren().addAll(labAujourd, scrollPane);
    }
}