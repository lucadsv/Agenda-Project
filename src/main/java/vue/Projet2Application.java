package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;

import java.io.File;

/**
 * La classe Projet2Application est la classe principale de l'application JavaFX.
 */
public class Projet2Application extends Application {

    /**
     * Méthode principale de l'application JavaFX.
     * Crée la scène principale et affiche l'interface graphique.
     *
     * @param stage le stage principal de l'application.
     */
    public void start(Stage stage) {
        HBoxRoot root = new HBoxRoot();
        Scene scene = new Scene(root, 1100, 350);
        root.setStyle(
                "-fx-background-image: url('https://img.freepik.com/photos-premium/fond-gris-clair-fond-gris-clair_670382-39342.jpg'); " +
                        "-fx-background-repeat: no-repeat; " +
                        "-fx-background-size: 1100 350; " +
                        "-fx-background-position: center center;");
        stage.setScene(scene);
        stage.setTitle("Calendrier du mois");
        stage.centerOnScreen();
        stage.show();
        File[] fichiersCss = new File("css").listFiles();
        if (fichiersCss != null) {
            for (File fichier : fichiersCss) {
                scene.getStylesheets().add(fichier.toURI().toString());
            }
        }
    }

    /**
     * Méthode principale de l'application.
     * Lance l'application JavaFX.
     *
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        launch(args);
    }
}