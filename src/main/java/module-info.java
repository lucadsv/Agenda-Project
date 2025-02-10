module com.example.agenda {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.agenda to javafx.fxml;
    exports com.example.agenda;
    exports vue;
    exports modele;
}