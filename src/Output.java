package src;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

import src.events.Event;
import src.gui.GUI;

public class Output {
    
    static private Queue<Event> eventQueue;
    static private Semaphore sem;
    static private Thread thread;
    static private GUI gui;

    static synchronized public void start(GUI aGUI)
    {
        eventQueue = new LinkedList<>();
        sem = new Semaphore(0);
        gui = aGUI;

        thread = new Thread(()->{
            while(true)
            {
                try { sem.acquire(); } catch (InterruptedException e) {}
                pollEvent();
            }
        });

        thread.start();
    }

    static synchronized public void pushEvent(Event event)
    {
        eventQueue.add(event);
        sem.release();
    }

    static synchronized private void pollEvent()
    {
        eventQueue.poll().execute(gui);
    }
}
