package src.events;

import src.gui.GUI;

public class EventPasajeroEmbarca extends Event {

    private final int terminal;

    public EventPasajeroEmbarca(int id, int terminal) {
        super(id);
        this.terminal = terminal;
    }

    @Override
    public void execute(GUI gui) {
        gui.embarcar(id, terminal);
        System.out.printf("Pasajero %d embarcó.\n", id);
    }
    
}
