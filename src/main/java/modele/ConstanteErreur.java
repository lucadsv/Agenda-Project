package modele;

/**
 * Interface ConstanteErreur.
 * Définit des constantes pour les messages d'erreur liés à la gestion du planning.
 */
public interface ConstanteErreur {

    /**
     * Les messages d'erreur possibles pour le planning.
     */
    public final String[] ERREURS_PLANNING = {
            "Reservation invalide",
            "Planning complet",
            "Reservation incompatible"
    };
}