package vviv;

/**
 * Created by valen on 01/12/2016.
 */
public class ListeVoisinWithContraintes extends ListeVoisin {

    public ListeVoisinWithContraintes(GestionnaireClients gestionnaireClients) {
        super(gestionnaireClients);
        nbVoisins = gestionnaireClients.listeClients.size();
    }

    @Override
    public void genererVoisins() {
        int i = 0;
        lastForFusion = null;
        int nbTentatives = 0;
        i = 0;
        nbTentatives = 0;
        while(i < nbVoisins && nbTentatives < nbTentativesMax){
            if(genererDivision()){
                i++;
            }
            nbTentatives++;
        }
        int nbCamions = gestionnaireClients.getGestionnaireCamion().getListeCamion().size();
        i = 1;
        nbTentatives = 0;
        while(i < nbVoisins && nbCamions - i > 0 && nbTentatives < nbTentativesMax){
            if(genererFusion()){
                i++;
            }
            nbTentatives++;
        }
    }

    @Override
    public boolean ajouterALaListe(GestionnaireClients gestionnaireClients) {
        GestionnaireClientsWithContraintes g = (GestionnaireClientsWithContraintes)gestionnaireClients;
        return g.verifierContraintes() && super.ajouterALaListe(gestionnaireClients);
    }

    @Override
    public GestionnaireClients creerGestionnaire(GestionnaireClients g) {
        return new GestionnaireClientsWithContraintes(g);
    }
}
