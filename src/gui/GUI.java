package src.gui;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class GUI extends JFrame {
    
    private final HashMap<Integer, GUIPasajero> hashPasajeros;
    private final GUIClock guiClock;
    private final GUIQueue guiPuerta;
    private final GUIQueue guiPuestoInformes;
    private final GUIQueue[] guiPuestosAtencion;

    private final int PUESTOS_ATENCION = 4;

    public GUI(String caption)
    {
        super(caption);

        this.hashPasajeros = new HashMap<>();

        this.guiClock = new GUIClock();
        this.guiPuerta = new GUIQueue("Esperando en Sala de Informes");
        this.guiPuestoInformes = new GUIQueue("Hall");

        guiPuestosAtencion = new GUIQueue[PUESTOS_ATENCION];
        for(int i=0; i<PUESTOS_ATENCION; i++)
            guiPuestosAtencion[i] = new GUIQueue(String.format("Puesto de Atención %d", i+1));
    

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        GridLayout layout = new GridLayout(0, 1, 2, 2);
        setLayout(layout);
        
        add(guiClock);
        add(guiPuerta);
        add(guiPuestoInformes);
        
        for(int i=0; i<PUESTOS_ATENCION; i++)
            add(guiPuestosAtencion[i]);

        setSize(new Dimension(800,600));
        setVisible(true);
    }

    public void setHour(int hour)
    {
        guiClock.setHour(hour);
    }

    public void llegaPasajero(int id)
    {
        GUIPasajero pasajero = new GUIPasajero(id);
        hashPasajeros.put(id, pasajero);
        guiPuerta.push(pasajero);
    }

    public void entraPasajero(int id)
    {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiPuerta.pop(pasajero);
        guiPuestoInformes.push(pasajero);
    }
}
