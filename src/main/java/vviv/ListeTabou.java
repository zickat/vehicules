package vviv;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by valen on 23/11/2016.
 */
public class ListeTabou {

    private Queue<GestionnaireClients> queue = new ArrayDeque<>();

    private int nbElementMax;

    public ListeTabou(int nbElementMax) {
        this.nbElementMax = nbElementMax;
    }

    public boolean contains(GestionnaireClients gestionnaireClients){
        return queue.contains(gestionnaireClients);
    }

    public boolean add(GestionnaireClients gestionnaireClients){
        if(queue.size() >= nbElementMax){
            queue.poll();
        }
        return queue.offer(gestionnaireClients);
    }

}
