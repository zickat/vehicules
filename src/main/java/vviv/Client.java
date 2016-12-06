package vviv;

/**
 * Created by valen on 09/11/2016.
 */
public class Client {

    private int id;

    private Coordonnees coordonnees;

    private float quantite;

    private float tempsMin;

    private float tempsMax;

    private float duree;

    public Client(int id, Coordonnees coordonnees, float quantite, float tempsMin, float tempsMax, float duree) {
        this.id = id;
        this.coordonnees = coordonnees;
        this.quantite = quantite;
        this.tempsMin = tempsMin;
        this.tempsMax = tempsMax;
        this.duree = duree;
    }

    @Override
    public String toString() {
        return ""+id;
    }

    public int getId() {
        return id;
    }

    public Coordonnees getCoordonnees() {
        return coordonnees;
    }

    public float getQuantite() {
        return quantite;
    }

    public float getTempsMin() {
        return tempsMin;
    }

    public float getTempsMax() {
        return tempsMax;
    }

    public float getDuree() {
        return duree;
    }
}
