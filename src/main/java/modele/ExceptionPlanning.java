package modele;

/**
 * ExceptionPlanning est une classe d'exception personnalisée pour gérer les erreurs de planning.
 */
public class ExceptionPlanning extends Exception {

    private int chCodeErreur;

    /**
     * Constructeur de la classe ExceptionPlanning.
     * Initialise l'exception avec un code d'erreur spécifique.
     *
     * @param parCodeErreur le code d'erreur associé à l'exception.
     */
    public ExceptionPlanning(int parCodeErreur) {
        chCodeErreur = parCodeErreur;
    }

    /**
     * Retourne le code d'erreur associé à cette exception.
     *
     * @return le code d'erreur.
     */
    public int getChCodeErreur() {
        return chCodeErreur;
    }
}
