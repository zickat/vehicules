package vviv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by valen on 09/11/2016.
 */
public class LectureFichier {

    private String filename;

    public LectureFichier(String filename) {
        this.filename = filename;
    }

    public GestionnaireClientsWithContraintes lireClientsWC() throws IOException {
        String line;
        Float[] splitInt;
        BufferedReader buff = new BufferedReader(new FileReader(filename));
        line = buff.readLine();
        int nbCamions = (splitInt(line, 1)[0]).intValue();
        Camion.setCapaciteMax(nbCamions);
        buff.readLine();
        line = buff.readLine();
        splitInt = splitInt(line);
        Depot d = new Depot(splitInt[0].intValue(),
                new Coordonnees(splitInt[1], splitInt[2])
                , splitInt[3], splitInt[4], splitInt[5], splitInt[6]);
        GestionnaireCamion gestionnaireCamion = new GestionnaireCamion(d);
        GestionnaireClientsWithContraintes g = new GestionnaireClientsWithContraintes(gestionnaireCamion, d);
        while ((line = buff.readLine()) != null){
            splitInt = splitInt(line);
            g.addClient(new Client(splitInt[0].intValue(),
                    new Coordonnees(splitInt[1], splitInt[2])
                    , splitInt[3], splitInt[4], splitInt[5], splitInt[6]));
        }
        return g;
    }

    public GestionnaireClients lireClients() throws IOException {
        String line;
        Float[] splitInt;
        BufferedReader buff = new BufferedReader(new FileReader(filename));
        line = buff.readLine();
        int nbCamions = (splitInt(line, 1)[0]).intValue();
        Camion.setCapaciteMax(nbCamions);
        buff.readLine();
        line = buff.readLine();
        splitInt = splitInt(line);
        Depot d = new Depot(splitInt[0].intValue(),
                new Coordonnees(splitInt[1], splitInt[2])
                , splitInt[3], splitInt[4], splitInt[5], splitInt[6]);
        GestionnaireCamion gestionnaireCamion = new GestionnaireCamion(d);
        GestionnaireClients g = new GestionnaireClients(gestionnaireCamion, d);
        while ((line = buff.readLine()) != null){
            splitInt = splitInt(line);
            g.addClient(new Client(splitInt[0].intValue(),
                    new Coordonnees(splitInt[1], splitInt[2])
                    , splitInt[3], splitInt[4], splitInt[5], splitInt[6]));
        }
        return g;
    }

    private Float[] splitInt(String line){
        return splitInt(line, 0);
    }

    private Float[] splitInt(String line, int debut){
        String[] s = line.split("[\\t\\s]");
        Float[] floats = new Float[s.length];
        for(int i = debut; i < s.length; i++){
            floats[i - debut] = Float.parseFloat(s[i]);
        }
        //System.out.println(floats[0]);
        return floats;
    }

}
