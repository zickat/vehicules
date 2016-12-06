package vviv;

import java.io.IOException;

/**
 * Created by valen on 01/12/2016.
 */
public class run_wc {

    public static void main(String[] args) throws IOException {
        if(args.length < 2){
            return;
        }
        int duree = Integer.parseInt(args[0]) * 1000;
        //System.out.println("Duree : "+duree);
        String file = args[1];
        LectureFichier lecture = new LectureFichier(file);
        GestionnaireClientsWithContraintes gestionnaireClients = lecture.lireClientsWC();
        // Duree en millisecondes : 1 min = 1000*60
        HillClimbing hillClimbing = new HillClimbing(gestionnaireClients, 100, duree);
        hillClimbing.findBestCout();
        //System.out.println("best for all " +hillClimbing.findBestCout());
    }

}
