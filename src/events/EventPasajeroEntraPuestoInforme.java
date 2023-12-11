package src.events;

import src.gui.GUI;

public class EventPasajeroEntraPuestoInforme extends Event {
        public EventPasajeroEntraPuestoInforme(int data) {
        super(data);
    }

    @Override
    public void execute(GUI gui) {
        gui.entraPuestoInformes(id);
        System.out.printf("Pasajero %d llega al aeropuerto.\n", id);
    }
    
}
