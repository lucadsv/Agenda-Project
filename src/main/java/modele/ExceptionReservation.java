package modele;

/**
 * ExceptionReservation est une classe d'exception personnalisée pour gérer les erreurs de réservation.
 */
public class ExceptionReservation extends Exception {

    /**
     * Constructeur de la classe ExceptionReservation.
     * Initialise l'exception avec un message d'erreur spécifique.
     *
     * @param parMessageErreur le message d'erreur associé à l'exception.
     */
    public ExceptionReservation(String parMessageErreur) {
        super(parMessageErreur);
    }
}