package modele;

/**
 * Interface ConstanteCalendrier.
 * Définit des constantes pour les noms des jours de la semaine et des mois de l'année.
 */
public interface ConstanteCalendrier {

    /**
     * Les noms des jours de la semaine en français.
     */
    final String[] JOURS_SEMAINE = {
            "lundi", "mardi", "mercredi", "jeudi", "vendredi", "samedi", "dimanche"
    };

    /**
     * Les noms des mois de l'année en français.
     */
    final String[] MOIS = {
            "janvier", "fevrier", "mars", "avril", "mai", "juin", "juillet", "aout", "septembre", "octobre", "novembre", "décembre"
    };
}