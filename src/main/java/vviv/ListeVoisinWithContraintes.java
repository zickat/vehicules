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
        //System.out.println(listeVoisins.size());
        int nbCamions = gestionnaireClients.getGestionnaireCamion().getListeCamion().size();
        nbTentatives = 1;
        while(nbCamions - nbTentatives > 1){
            if(genererFusion()){
                i++;
                //System.out.println("Camions : " + (nbCamions - nbTentatives));
            }
            nbTentatives++;
        }
        while(i < 2 * nbVoisins) {
            if (genererPermutation()) {
                i++;
            }
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
