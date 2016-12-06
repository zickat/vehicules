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
    public boolean ajouterALaListe(GestionnaireClients gestionnaireClients) {
        GestionnaireClientsWithContraintes g = (GestionnaireClientsWithContraintes)gestionnaireClients;
        return g.verifierContraintes() && super.ajouterALaListe(gestionnaireClients);
    }

    @Override
    public GestionnaireClients creerGestionnaire(GestionnaireClients g) {
        return new GestionnaireClientsWithContraintes(g);
    }
}
