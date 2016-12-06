package vviv;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by valen on 23/11/2016.
 */
public class ListeVoisin {

    private GestionnaireClients gestionnaireClients;

    private List<GestionnaireClients> listeVoisins = new ArrayList<>();

    private GestionnaireClients lastForFusion = null;

    protected int nbVoisins = 25;

    private int nbTentativesMax = 1000;

    public ListeVoisin(GestionnaireClients gestionnaireClients) {
        this.gestionnaireClients = gestionnaireClients;
        //nbVoisins = gestionnaireClients.listeClients.size();
    }

    public void genererVoisins(){
        int i = 0;
        lastForFusion = null;
        int nbTentatives = 0;
        while(i < nbVoisins){
            if(genererPermutation()){
                i++;
            }
        }
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

    private boolean genererPermutation(){
        GestionnaireClients temp = creerGestionnaire(gestionnaireClients);
        temp.shuffle();
        temp.dispatchClients();
        return ajouterALaListe(temp);
    }

    private boolean genererDivision() {
        GestionnaireClients temp = creerGestionnaire(gestionnaireClients);
        Camion nouveau = new Camion(temp.getDepot());
        temp.getGestionnaireCamion().addCamion(nouveau);
        temp.dispatchClients();
        return ajouterALaListe(temp);
    }

    private boolean genererFusion() {
        GestionnaireClients temp;
        if(lastForFusion != null){
            temp = creerGestionnaire(lastForFusion);
        }else {
            temp = creerGestionnaire(gestionnaireClients);
        }
        temp.getGestionnaireCamion().removeCamion();
        temp.dispatchClients();
        return ajouterALaListe(temp);
    }

    GestionnaireClients meilleurVoisin(ListeTabou listeTabou){
        float cout = Float.MAX_VALUE;
        GestionnaireClients best = null;
        for(GestionnaireClients g : listeVoisins){
            float c = g.cout();
            if(c < cout && !listeTabou.contains(g)){
                cout = c;
                best = g;
            }
        }
        return best;
    }

    public boolean ajouterALaListe(GestionnaireClients gestionnaireClients){
        return listeVoisins.add(gestionnaireClients);
    }

    public GestionnaireClients creerGestionnaire(GestionnaireClients g){
        return new GestionnaireClients(g);
    }

}
