package src.events;

import src.gui.GUI;

public class EventPasajeroEntraPuestoAtencion extends Event {

    private int puesto;

    public EventPasajeroEntraPuestoAtencion(int id, int puesto) {
        super(id);
        this.puesto = puesto;
    }

    @Override
    public void execute(GUI gui) {

        gui.entraPuestoAtencion(id, puesto);
        System.out.printf("Pasajero %d entra al puesto de Atención %d.\n", id, puesto);

    }
    
}
