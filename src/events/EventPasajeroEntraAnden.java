package src.events;

import src.gui.GUI;

public class EventPasajeroEntraAnden extends Event {

    private final int puesto;

    public EventPasajeroEntraAnden(int data, int puesto) {
        super(data);
        this.puesto = puesto;
    }

    @Override
    public void execute(GUI gui) {
        gui.entraAnden(id, puesto);
        System.out.printf("Pasajero %d llega al Andén.\n", id);
    }
    
}
