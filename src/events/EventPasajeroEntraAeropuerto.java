package src.events;

import src.gui.GUI;

public class EventPasajeroEntraAeropuerto extends Event {
        public EventPasajeroEntraAeropuerto(int data) {
        super(data);
    }

    @Override
    public void execute(GUI gui) {
        gui.entraPasajero(data);
        System.out.printf("Pasajero %d entra al aeropuerto.\n", data);
    }
    
}
