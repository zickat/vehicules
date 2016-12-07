package vviv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valen on 09/11/2016.
 */
public class GestionnaireCamion {

    private List<Camion> listeCamion = new ArrayList<>();

    private Depot depot;

    public GestionnaireCamion(Depot depot) {
        this.depot = depot;
        addCamion(new Camion(depot));
    }

    public GestionnaireCamion(GestionnaireCamion gestionnaireCamion, List<Client> listeClient){
        this.depot = gestionnaireCamion.getDepot();
        listeCamion = new ArrayList<>();
        for(Camion camion : gestionnaireCamion.getListeCamion()){
            listeCamion.add(new Camion(depot));
        }
        dispatchClients(listeClient);
    }

    public boolean addCamion(Camion c){
        return listeCamion.add(c);
    }

    public static GestionnaireCamion generateNewListe(Depot depot){
        GestionnaireCamion gc = new GestionnaireCamion(depot);
        int nb = gc.nbCamionAlea();
        for(int i = 0; i < nb; i++){
            gc.addCamion(new Camion(depot));
        }
        return gc;
    }

    private int nbCamionAlea(){
        //TODO alea !
        return 1;
    }

    public void dispatchClients(List<Client> listeClient){
        int nbCamions = listeCamion.size();
        int nbClients = listeClient.size();
        for (Camion camion : listeCamion){
            camion.setListeClients(new ArrayList<Client>());
        }
        int j = 0;
        for (Client client : listeClient){
            listeCamion.get(j).addClient(client);
            j = (j + 1)%nbCamions;
        }
        /*int i = 0;
        int nbCamions = listeCamion.size();
        int nbClients = listeClient.size();
        int nbByCamions = nbClients/nbCamions;
        for (Camion camion : listeCamion){
            camion.setListeClients(listeClient.subList(i, Math.min(nbClients, i + nbByCamions)));
            i += nbByCamions;
        }*/
    }

    public float cout(){
        float c = 0;
        for (Camion camion : listeCamion){
            c += camion.cout();
        }
        return c;
    }

    public List<Camion> getListeCamion() {
        return listeCamion;
    }

    public Depot getDepot() {
        return depot;
    }

    public void removeCamion() {
        listeCamion.remove(listeCamion.size() - 1);
    }

    public boolean verifierContraintes() {
        for (Camion c : listeCamion){
            if(!c.verifierContraintes()){
                return false;
            }
        }
        return true;
    }

    public int size() {
        return listeCamion.size();
    }
}
