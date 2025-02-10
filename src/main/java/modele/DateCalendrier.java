package modele;

import java.util.Calendar;

public class DateCalendrier extends Date implements ConstanteCalendrier, Comparable<Date> {

	private int jourSemaine;
	private int weekOfYear;

	/**
	 * Constructeur par défaut de la classe DateCalendrier.
	 * Initialise la date à la date actuelle.
	 */
	public DateCalendrier() {
		Calendar dateAuj = Calendar.getInstance();
		chAnnee = dateAuj.get(Calendar.YEAR);
		chMois = dateAuj.get(Calendar.MONTH) + 1;
		chJour = dateAuj.get(Calendar.DAY_OF_MONTH);
		jourSemaine = dateAuj.get(Calendar.DAY_OF_WEEK);
		if (jourSemaine == Calendar.SUNDAY) {
			jourSemaine = 7;
		} else {
			jourSemaine -= 1;
		}
		weekOfYear = dateAuj.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Constructeur de la classe DateCalendrier.
	 * Initialise une date avec le jour, le mois et l'année spécifiés.
	 *
	 * @param parJour  le jour du mois.
	 * @param parMois  le mois de l'année.
	 * @param parAnnee l'année.
	 */
	public DateCalendrier(int parJour, int parMois, int parAnnee) {
		super(parJour, parMois, parAnnee);
		Calendar date = Calendar.getInstance();
		date.set(chAnnee, chMois - 1, chJour);
		jourSemaine = date.get(Calendar.DAY_OF_WEEK);
		if (jourSemaine == Calendar.SUNDAY) {
			jourSemaine = 7;
		} else {
			jourSemaine -= 1;
		}
		weekOfYear = date.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * Retourne une représentation sous forme de chaîne de la date.
	 *
	 * @return une chaîne de caractères représentant la date avec le jour de la semaine.
	 */
	public String toString() {
		return JOURS_SEMAINE[jourSemaine - 1] + " " + chJour + " " + MOIS[chMois - 1];
	}

	/**
	 * Retourne le jour de la semaine pour cette date.
	 *
	 * @return le jour de la semaine (1 = lundi, ..., 7 = dimanche).
	 */
	public int getJourSemaine() {
		return jourSemaine;
	}

	/**
	 * Vérifie si cette date est la date d'aujourd'hui.
	 *
	 * @return true si cette date est aujourd'hui, false sinon.
	 */
	public boolean isToday() {
		return this.compareTo(new DateCalendrier()) == 0;
	}

	/**
	 * Retourne le numéro de la semaine dans l'année pour cette date.
	 *
	 * @return le numéro de la semaine.
	 */
	public int getWeekOfYear() {
		return weekOfYear;
	}

	/**
	 * Retourne la date du lendemain.
	 *
	 * @return une nouvelle instance de DateCalendrier représentant le jour suivant.
	 */
	public DateCalendrier dateDuLendemain() {
		Date dateLendemain = super.dateDuLendemain();
		return new DateCalendrier(dateLendemain.chJour, dateLendemain.chMois, dateLendemain.chAnnee);
	}

	/**
	 * Retourne la date de la veille.
	 *
	 * @return une nouvelle instance de DateCalendrier représentant le jour précédent.
	 */
	public DateCalendrier dateDeLaVeille() {
		Date dateVeille = super.dateDeLaVeille();
		return new DateCalendrier(dateVeille.chJour, dateVeille.chMois, dateVeille.chAnnee);
	}

	/**
	 * Compare cette date avec une autre date.
	 *
	 * @param other la date à comparer.
	 * @return un entier négatif, zéro ou un entier positif si cette date est antérieure, égale ou postérieure à other.
	 */
	@Override
	public int compareTo(Date other) {
		return super.compareTo(other);
	}
}