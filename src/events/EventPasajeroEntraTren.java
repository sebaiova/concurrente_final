package src.events;

import src.gui.GUI;

public class EventPasajeroEntraTren extends Event {

    public EventPasajeroEntraTren(int id) {
        super(id);
    }

    @Override
    public void execute(GUI gui) {
        gui.entraTren(id);
        System.out.printf("Pasajero %d entra al Tren.\n", id);
    }
    
}
