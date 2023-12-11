package src.events;
import src.gui.GUI;

public class EventSetHour extends Event{

    public EventSetHour(int data) {
        super(data);
    }

    @Override
    public void execute(GUI gui) {
        gui.setHour(data);
        System.out.printf("Hora actual: %d:00.\n", data);
    }
    
}
