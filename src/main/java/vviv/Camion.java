package vviv;

import java.util.List;

/**
 * Created by valen on 09/11/2016.
 */
public class Camion {

    private static int capacite;

    private List<Client> listeClients;

    private Depot depot;

    public Camion(Depot depot) {
        this.depot = depot;
    }

    public static int getCapaciteMax() {
        return capacite;
    }

    public static void setCapaciteMax(int capaciteMax) {
        Camion.capacite = capaciteMax;
    }

    public boolean addClient(Client c){
        return listeClients.add(c);
    }

    public List<Client> getListeClients() {
        return listeClients;
    }

    public void setListeClients(List<Client> listeClients) {
        this.listeClients = listeClients;
    }

    public float cout(){
        float c = 0;
        Client precedant = depot;
        for(Client client : listeClients){
            c += client.getDuree() + client.getCoordonnees().distance(precedant.getCoordonnees());
            precedant = client;
        }
        c += precedant.getCoordonnees().distance(depot.getCoordonnees());
        return c;
    }

    public boolean verifierContraintes() {
        double debut = depot.getHeureDebut();
        Coordonnees coordonnees = depot.getCoordonnees();
        double capa = 0;
        for(Client client : listeClients){
            debut = debut + client.getCoordonnees().distance(coordonnees);
            debut = Math.max(debut, client.getTempsMin());
            if(debut < client.getTempsMax()
                    && debut + client.getDuree() < client.getTempsMax()
                    && capa + client.getQuantite() < capacite){
                debut = debut + client.getDuree();
                coordonnees = client.getCoordonnees();
                capa = capa + client.getQuantite();
            }else{
                return false;
            }
        }
        double fin = debut + coordonnees.distance(depot.getCoordonnees());
        if(fin > depot.getHeureFin()){
            return false;
        }
        return true;
    }
}