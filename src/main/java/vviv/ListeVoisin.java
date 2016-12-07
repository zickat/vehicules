package vviv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by valen on 23/11/2016.
 */
public class ListeVoisin {

    protected GestionnaireClients gestionnaireClients;

    protected List<GestionnaireClients> listeVoisins = new ArrayList<>();

    protected GestionnaireClients lastForFusion = null;

    protected GestionnaireClients temp;

    //private Permute permute;

    protected int nbVoisins = 1000;

    protected int nbTentativesMax = 10;

    public ListeVoisin(GestionnaireClients gestionnaireClients) {
        this.gestionnaireClients = gestionnaireClients;
        //nbVoisins = gestionnaireClients.listeClients.size();
    }

    public void genererVoisins(){
        int i = 0;
        //permute = new Permute(gestionnaireClients.getListeClients().toArray());
        while(i < nbVoisins) {
            if (genererPermutation()) {
                i++;
            }
        }
    }

    protected boolean genererPermutation(){
        /*if(permute.hasNext()){
            GestionnaireClients temp = creerGestionnaire(gestionnaireClients);
            List<Client> listeTemp = new ArrayList<>();
            Object[] p = (Object[]) permute.next();
            for(Object o : p){
                listeTemp.add((Client) o);
            }
            temp.setListeClients(listeTemp);
            temp.dispatchClients();
            return ajouterALaListe(temp);
        }
        return false;*/
        GestionnaireClients temp = creerGestionnaire(gestionnaireClients);
        temp.shuffle();
        temp.dispatchClients();
        return ajouterALaListe(temp);
    }

    protected boolean genererDivision() {
        GestionnaireClients temp = creerGestionnaire(gestionnaireClients);
        Camion nouveau = new Camion(temp.getDepot());
        temp.getGestionnaireCamion().addCamion(nouveau);
        temp.dispatchClients();
        return ajouterALaListe(temp);
    }

    protected boolean genererFusion() {
        if(lastForFusion != null){
            temp = creerGestionnaire(lastForFusion);
        }else {
            temp = creerGestionnaire(gestionnaireClients);
        }
        temp.getGestionnaireCamion().removeCamion();
        temp.dispatchClients();
        lastForFusion = temp;
        return ajouterALaListe(temp);
    }

    public GestionnaireClients meilleurVoisin(ListeTabou listeTabou){
        float cout = Float.MAX_VALUE;
        GestionnaireClients best = gestionnaireClients;
        for(GestionnaireClients g : listeVoisins){
            float c = g.cout();
            if(c < cout && !listeTabou.contains(cout)){
                cout = c;
                best = g;
                //System.out.println(c + "  " + cout);
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
