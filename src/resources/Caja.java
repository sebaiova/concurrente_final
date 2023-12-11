package src.resources;

import java.util.LinkedList;
import java.util.Queue;

public class Caja {
    
    private final Queue<Integer> queue;

    public Caja()
    {
        queue = new LinkedList<>(); 
    }

    public int size()
    {
        return queue.size();
    }

    synchronized public void hacerFila(int id)
    {
        queue.add(id);
    }

    synchronized public void comprar(int id) throws InterruptedException
    {
        while(queue.peek()!=id)
            wait();
    }

    synchronized public void pagar()
    {
        queue.poll();
        notify();
    }
}
