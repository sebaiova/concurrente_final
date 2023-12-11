package src.events;

import src.gui.GUI;

public class EventTrenLlegaA extends Event {

    public EventTrenLlegaA(int terminal) {
        super(terminal);
    }

    @Override
    public void execute(GUI gui) {
        gui.trenLlegaA(id);
        System.out.printf("Tren llega a estación %d.\n", id);
    }
    
}
