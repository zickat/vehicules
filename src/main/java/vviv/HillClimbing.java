package vviv;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

/**
 * Created by valen on 11/11/2016.
 */
public class HillClimbing {

    private  int nbMaxIterMauvaise;

    private GestionnaireClients gestionnaireClientsInitial;

    private ListeTabou listeTabou;

    private long dureeMax;

    private float bestForAll;

    private long debut;

    public HillClimbing(GestionnaireClients gestionnaireClients, int tailleListeTabou, int dureeMax) {
        this.gestionnaireClientsInitial = gestionnaireClients;
        this.nbMaxIterMauvaise = gestionnaireClients.getListeClients().size();
        this.dureeMax = dureeMax;
        debut = System.currentTimeMillis();
        listeTabou = new ListeTabou(tailleListeTabou);
    }

    private long duree(){
        long d = System.currentTimeMillis() - debut;
        //System.out.println("Duree : "+ (d/1000) + "s -- meilleur : "+ bestForAll);
        return d;
    }

    public float findBestCout(){
        bestForAll = Float.MAX_VALUE;
        float courant;
        int nbr = 0;
        GestionnaireClients meilleur = null;
        GestionnaireClients gCourant = null;
        while (duree() < dureeMax){
            GestionnaireClients g = creerGestionnaire(gestionnaireClientsInitial);
            g.genererSituationInitiale();
            gCourant = findBestCoutForInitial(g);
            courant = gCourant.cout();
            nbr++;
            if(courant < bestForAll){
                bestForAll = courant;
                meilleur = gCourant;
            }
        }
        //System.out.println("cout : " + bestForAll);
        try {
            assert meilleur != null;
            (new ExportDot(meilleur.getGestionnaireCamion())).export();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bestForAll;
    }

    public GestionnaireClients findBestCoutForInitial(GestionnaireClients g){
        GestionnaireClients gCourant = creerGestionnaire(g);
        float courant = g.cout();
        float best = courant;
        GestionnaireClients meilleur = g;
        int nbIter = 0;
        while(nbIter < nbMaxIterMauvaise && duree() < dureeMax){
            gCourant = gCourant.meilleurVoisin(listeTabou);
            courant = gCourant.cout();
            //System.out.println("test : "+meilleur.cout() + "   " + best + "   " +gCourant.cout());
            //System.out.println(temp);
            //System.out.println("Cout de base : "+ best);
            if (courant < best){
                meilleur = creerGestionnaire(gCourant);
                best = meilleur.cout();
                nbIter = 0;
                listeTabou.add(courant);
            }else{
                //listeTabou.add(courant);
                nbIter++;
            }
        }
        return meilleur;
    }

    protected GestionnaireClients creerGestionnaire(GestionnaireClients gestionnaireClients){
        if(gestionnaireClients instanceof GestionnaireClientsWithContraintes)
            return new GestionnaireClientsWithContraintes(gestionnaireClients);
        else
            return new GestionnaireClients(gestionnaireClients);
    }

}
