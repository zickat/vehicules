package vviv;

import java.util.Collections;

/**
 * Created by valen on 01/12/2016.
 */
public class GestionnaireClientsWithContraintes extends GestionnaireClients{

    public GestionnaireClientsWithContraintes(GestionnaireCamion gestionnaireCamion, Depot depot) {
        super(gestionnaireCamion, depot);
    }

    public GestionnaireClientsWithContraintes(GestionnaireClients gestionnaireClients) {
        super(gestionnaireClients);
    }

    @Override
    public GestionnaireClients meilleurVoisin(ListeTabou listeTabou) {
        return super.meilleurVoisin(listeTabou);
    }

    @Override
    public void genererSituationInitiale() {
        Collections.shuffle(listeClients);
        int nb = getListeClients().size();
        GestionnaireCamion gestionnaireCamion = getGestionnaireCamion();
        while(gestionnaireCamion.size() < nb){
            gestionnaireCamion.addCamion(new Camion(getDepot()));
        }
        dispatchClients();
        //System.out.println(gestionnaireCamion.verifierContraintes());
    }

    public boolean verifierContraintes(){
        return getGestionnaireCamion().verifierContraintes();
    }

    @Override
    public ListeVoisin genererListeVoisin() {
        return new ListeVoisinWithContraintes(this);
    }
}
