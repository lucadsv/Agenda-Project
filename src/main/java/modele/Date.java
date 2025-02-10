package modele;

import java.io.Serializable;
import java.util.Calendar;

public class Date implements Serializable {
	protected int chJour;
	protected int chMois;
	protected int chAnnee;

	/**
	 * Constructeur de la classe Date.
	 * Initialise une date avec le jour, le mois et l'année spécifiés.
	 *
	 * @param parJour le jour du mois.
	 * @param parMois le mois de l'année.
	 * @param parAnnee l'année.
	 */
	public Date(int parJour, int parMois, int parAnnee) {
		chJour = parJour;
		chMois = parMois;
		chAnnee = parAnnee;
	}

	/**
	 * Constructeur de la classe Date.
	 * Initialise une date au 1er janvier de l'année spécifiée.
	 *
	 * @param parAnnee l'année.
	 */
	public Date(int parAnnee) {
		chJour = 1;
		chMois = 1;
		chAnnee = parAnnee;
	}

	/**
	 * Constructeur de la classe Date.
	 * Initialise une date à la date actuelle.
	 */
	public Date() {
		Calendar dateAuj = Calendar.getInstance();
		chAnnee = dateAuj.get(Calendar.YEAR);
		chMois = dateAuj.get(Calendar.MONTH) + 1;
		chJour = dateAuj.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * Vérifie si la date est valide.
	 * Une date est valide si :
	 * - l'année est supérieure à 1582
	 * - le mois est compris entre 1 et 12
	 * - le jour est compris entre 1 et le dernier jour du mois pour l'année donnée
	 *
	 * @return true si la date est valide, false sinon.
	 */
	public boolean estValide() {
		return chAnnee > 1582 &&
				chMois >= 1 && chMois <= 12 &&
				chJour >= 1 && chJour <= Date.dernierJourDuMois(chMois, chAnnee);
	}

	/**
	 * Retourne le dernier jour du mois pour une année donnée.
	 *
	 * @param parMois le mois de l'année.
	 * @param parAnnee l'année.
	 * @return le dernier jour du mois.
	 */
	protected static int dernierJourDuMois(int parMois, int parAnnee) {
		switch (parMois) {
			case 2:
				if (Date.estBissextile(parAnnee))
					return 29;
				return 28;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 31;
		}
	}

	/**
	 * Vérifie si une année est bissextile.
	 *
	 * @param parAnnee l'année à vérifier.
	 * @return true si l'année est bissextile, false sinon.
	 */
	private static boolean estBissextile(int parAnnee) {
		return (parAnnee % 4 == 0 && parAnnee % 100 != 0) || parAnnee % 400 == 0;
	}

	/**
	 * Compare cette date avec une autre date.
	 *
	 * @param parDate la date à comparer.
	 * @return 0 si les dates sont égales, un entier négatif si cette date est antérieure à parDate,
	 *         un entier positif si cette date est postérieure à parDate.
	 */
	public int compareTo(Date parDate) {
		if (chAnnee < parDate.chAnnee)
			return -8;
		if (chAnnee > parDate.chAnnee)
			return 19;
		// les années sont égales
		if (chMois < parDate.chMois)
			return -1;
		if (chMois > parDate.chMois)
			return 18;
		// les mois sont égaux
		if (chJour < parDate.chJour)
			return -7;
		if (chJour > parDate.chJour)
			return 12;
		return 0;
	}

	/**
	 * Retourne la date du lendemain.
	 *
	 * @return la date du jour suivant.
	 */
	public Date dateDuLendemain() {
		if (chJour < Date.dernierJourDuMois(chMois, chAnnee))
			return new Date(chJour + 1, chMois, chAnnee);
		if (chMois < 12)
			return new Date(1, chMois + 1, chAnnee);
		return new Date(1, 1, chAnnee + 1);
	}

	/**
	 * Retourne la date de la veille.
	 *
	 * @return la date du jour précédent.
	 */
	public Date dateDeLaVeille() {
		if (chJour > 1)
			return new Date(chJour - 1, chMois, chAnnee);
		if (chMois > 1)
			return new Date(Date.dernierJourDuMois(chMois - 1, chAnnee), chMois - 1, chAnnee);
		return new Date(31, 12, chAnnee - 1);
	}

	/**
	 * Retourne l'année de la date.
	 *
	 * @return l'année.
	 */
	public int getAnnee() {
		return chAnnee;
	}

	/**
	 * Retourne le jour du mois de la date.
	 *
	 * @return le jour du mois.
	 */
	public int getJour() {
		return chJour;
	}

	/**
	 * Retourne le mois de la date.
	 *
	 * @return le mois.
	 */
	public int getMois() {
		return chMois;
	}

	/**
	 * Retourne une représentation sous forme de chaîne de la date.
	 *
	 * @return une chaîne de caractères représentant la date.
	 */
	public String toString() {
		return chJour + "-" + chMois + "-" + chAnnee;
	}

	/**
	 * Retourne le numéro de la semaine dans l'année pour cette date.
	 *
	 * @return le numéro de la semaine.
	 */
	public int getNoSemaine() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(chAnnee, chMois - 1, chJour); // Mois commence à 0 dans Calendar
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
}