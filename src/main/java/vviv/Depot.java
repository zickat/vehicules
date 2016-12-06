package vviv;

/**
 * Created by valen on 09/11/2016.
 */
public class Depot extends Client {

    public Depot(int id, Coordonnees coordonnees, float quantite, float tempsMin, float tempsMax, float duree) {
        super(id, coordonnees, quantite, tempsMin, tempsMax, duree);
    }

    public float getHeureDebut(){
        return getTempsMin();
    }

    public float getHeureFin(){
        return getTempsMax();
    }

}
