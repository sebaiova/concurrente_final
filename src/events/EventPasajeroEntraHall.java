package src.events;

import src.gui.GUI;

public class EventPasajeroEntraHall extends Event {
        public EventPasajeroEntraHall(int data) {
        super(data);
    }

    @Override
    public void execute(GUI gui) {
        gui.entraHall(id);
        System.out.printf("Pasajero %d entra al Hall.\n", id);
    }
    
}
