package vviv;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by valen on 09/11/2016.
 */
public class GestionnaireClients {

    protected List<Client> listeClients = new ArrayList<>();

    private GestionnaireCamion gestionnaireCamion;

    private Depot depot;

    public GestionnaireClients(GestionnaireClients gestionnaireClients){
        depot = gestionnaireClients.getDepot();
        listeClients = new ArrayList<>(gestionnaireClients.getListeClients());
        gestionnaireCamion = new GestionnaireCamion(gestionnaireClients.getGestionnaireCamion(), listeClients);
    }

    public GestionnaireClients(GestionnaireCamion gestionnaireCamion, Depot depot) {
        this.gestionnaireCamion = gestionnaireCamion;
        this.depot = depot;
    }

    public boolean addClient(Client c){
        return listeClients.add(c);
    }

    @Override
    public String toString() {
        String s = "";
        s += depot.toString()+'\n';
        for(Client c : listeClients){
            s += c.toString()+'\n';
        }
        return s;
    }

    public void dispatchClients(){
        gestionnaireCamion.dispatchClients(this.listeClients);
    }

    public GestionnaireClients meilleurVoisin(ListeTabou listeTabou){
        ListeVoisin listeVoisin = genererListeVoisin();
        listeVoisin.genererVoisins();
        return listeVoisin.meilleurVoisin(listeTabou);
    }

    public float cout(){
        return gestionnaireCamion.cout();
    }

    public GestionnaireCamion getGestionnaireCamion() {
        return gestionnaireCamion;
    }

    public void setListeClients(List<Client> listeClients) {
        this.listeClients = listeClients;
    }

    public Depot getDepot() {
        return depot;
    }

    public void genererSituationInitiale(){
        Collections.shuffle(listeClients);
        dispatchClients();
    }

    public List<Client> getListeClients() {
        return listeClients;
    }

    public void shuffle(){
        Collections.shuffle(listeClients);
    }

    public ListeVoisin genererListeVoisin(){
        return new ListeVoisin(this);
    }

}
