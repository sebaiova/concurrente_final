package src.events;
import src.gui.GUI;

public abstract class Event {

    protected final int data;

    public Event(int data)
    {
        this.data = data;
    }

    public abstract void execute(GUI gui);
}
