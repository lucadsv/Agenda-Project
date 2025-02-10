package modele;
/**
 * Classe représentant un plage horaire.
 */
public class PlageHoraire implements Comparable<PlageHoraire>{
    private Horaire chHoraireDebut;
    private Horaire chHoraireFin;
    private final static int chDureeMinimum=60;
    /**
     * Constructeur de la classe PlageHoraire.
     * Initialise une plage horaire avec une heure de début et une heure de fin.
     *
     * @param parHoraireDebut l'heure.
     * @param parHoraireFin le quart d'heure.
     */
    public PlageHoraire (Horaire parHoraireDebut, Horaire parHoraireFin){
        chHoraireDebut=parHoraireDebut;
        chHoraireFin=parHoraireFin;
    }
    /**
     * Retourne une représentation sous forme de chaîne de la plage horaire.
     *
     * @return une chaîne de caractères représentant la plage horaire.
     */
    public String toString() {
        int duree= this.duree();
        int dureeHeure=duree/60;
        int dureeMinutes=duree%60;
        return chHoraireDebut.toString() + " - " + chHoraireFin.toString() + ", duree : " + dureeHeure + "h" + dureeMinutes + "mn";
    }
    /**
     * Retourne un booléen représentant la validité de la plage horaire.
     *
     * @return un booléen selon la validité de la plage horaire.
     */
    public boolean estValide(){
        if (chHoraireDebut.toMinute()<chHoraireFin.toMinute()){
            return true;
        }
        return false;
    }
    /**
     * Retourne la durée de la plage horaire sous la forme d'un entier.
     *
     * @return un entier correspondant à la durée de la plage horaire.
     */
    public int duree(){
        int hd= chHoraireDebut.toMinute(), hf= chHoraireFin.toMinute();
        int duree=hf-hd;
        return duree;
    }
    /**
     * Compare cette plage horaire avec une autre plage horaire.
     *
     * @param parPlageHoraire la plage horaire à comparer.
     * @return un entier négatif, zéro ou un entier positif.
     */
    public int compareTo(PlageHoraire parPlageHoraire){
        if (this.chHoraireFin.toMinute()<=parPlageHoraire.chHoraireDebut.toMinute()){
            return this.chHoraireFin.toMinute()-parPlageHoraire.chHoraireDebut.toMinute()-this.duree();
        } else if (this.chHoraireDebut.toMinute()>=parPlageHoraire.chHoraireFin.toMinute()) {
            return this.chHoraireDebut.toMinute()-parPlageHoraire.chHoraireFin.toMinute()+this.duree();
        }
        return 0;
    }
}
