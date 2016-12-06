package vviv;

import java.io.*;
import java.util.List;

/**
 * Created by valen on 13/11/2016.
 */
public class ExportDot {

    private GestionnaireCamion gestionnaireCamion;

    private String filaname = "best.dot";

    public ExportDot(GestionnaireCamion gestionnaireCamion) {
        this.gestionnaireCamion = gestionnaireCamion;
    }

    public ExportDot(GestionnaireCamion gestionnaireCamion, String filaname) {
        this.gestionnaireCamion = gestionnaireCamion;
        this.filaname = filaname;
    }

    public void export() throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter writer = new PrintWriter(System.out);
        writer.println("digraph G {");
        List<Camion> listeCamion = gestionnaireCamion.getListeCamion();
        for (Camion camion : listeCamion){
            List<Client> listeClients = camion.getListeClients();
            Client precedant = gestionnaireCamion.getDepot();
            for (Client client : listeClients){
                writeLine(writer, precedant, client);
                precedant = client;
            }
            writeLine(writer, precedant, gestionnaireCamion.getDepot());
        }
        writer.println("}");
        writer.close();
    }

    private void writeLine(PrintWriter writer, Client first, Client second){
        writer.println("    " + first + " -> " + second + ";");
    }

}
