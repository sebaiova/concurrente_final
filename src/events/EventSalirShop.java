package src.events;

import src.gui.GUI;

public class EventSalirShop extends Event {

    private final int terminal;

    public EventSalirShop(int id, int terminal) {
        super(id);
        this.terminal = terminal;
    }

    @Override
    public void execute(GUI gui) 
    {
        gui.salirShop(id, terminal);
        System.out.printf("Pasajero %d salió del Shop %d.\n", id, terminal);
    }
    
}
