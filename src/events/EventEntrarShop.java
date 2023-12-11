package src.events;

import src.gui.GUI;

public class EventEntrarShop extends Event {

    private final int terminal;

    public EventEntrarShop(int id, int terminal) {
        super(id);
        this.terminal = terminal;
    }

    @Override
    public void execute(GUI gui) 
    {
        gui.entrarShop(id, terminal);
        System.out.printf("Pasajero %d entro al Shop %d.\n", id, terminal);
    }
    
}
