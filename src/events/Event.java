package src.events;
import src.gui.GUI;

public abstract class Event {

    protected final int id;

    public Event(int id)
    {
        this.id = id;
    }

    public abstract void execute(GUI gui);
}
