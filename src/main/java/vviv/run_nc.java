package vviv;

import java.io.IOException;

/**
 * Created by valen on 09/11/2016.
 */
public class run_nc {

    public static void main(String[] args) throws IOException {
        if(args.length < 2){
            return;
        }
        int duree = Integer.parseInt(args[0]) * 1000;
        //System.out.println("Duree : "+duree);
        String file = args[1];
        LectureFichier lecture = new LectureFichier(file);
        GestionnaireClients gestionnaireClients = lecture.lireClients();
        // Duree en millisecondes : 1 min = 1000*60
        HillClimbing hillClimbing = new HillClimbing(gestionnaireClients, 100, duree);
        hillClimbing.findBestCout();
        //System.out.println(hillClimbing.findBestCout());
    }

}
