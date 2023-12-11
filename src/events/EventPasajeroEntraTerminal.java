package src.events;

import src.gui.GUI;

public class EventPasajeroEntraTerminal extends Event {

    private final int terminal;

    public EventPasajeroEntraTerminal(int id, int terminal) {
        super(id);
        this.terminal = terminal;
    }

    @Override
    public void execute(GUI gui) {
        gui.entraTerminal(id, terminal);
        System.out.printf("Pasajero %d entra a la Terminal %d.\n", id, terminal);
    }
    
}
