package vviv;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 * Created by valen on 23/11/2016.
 */
public class ListeTabou {

    private Queue<Float> queue = new ArrayDeque<>();

    private int nbElementMax;

    public ListeTabou(int nbElementMax) {
        this.nbElementMax = nbElementMax;
    }

    public boolean contains(Float cout){
        return queue.contains(cout);
    }

    public boolean add(Float cout){
        if(queue.size() >= nbElementMax){
            queue.poll();
        }
        return queue.offer(cout);
    }

}
