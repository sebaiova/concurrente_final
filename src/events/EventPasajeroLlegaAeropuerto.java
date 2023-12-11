package src.events;

import src.gui.GUI;

public class EventPasajeroLlegaAeropuerto extends Event {
        public EventPasajeroLlegaAeropuerto(int data) {
        super(data);
    }

    @Override
    public void execute(GUI gui) {
        gui.llegaPasajero(data);
        System.out.printf("Pasajero %d llega al aeropuerto.\n", data);
    }
    
}
