package src.gui;
import javax.swing.JFrame;

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class GUI extends JFrame {
    
    private final HashMap<Integer, GUIPasajero> hashPasajeros;
    private final GUIClock guiClock;
    private final GUIQueue guiPuestoInformes;
    private final GUIQueue guiHall;
    private final GUIQueue guiAnden;
    private final GUITren  guiTren;
    private final GUIQueue[] guiPuestosAtencion;
    private final GUIQueue[] guiTerminal;
    private final GUIQueue[] guiShop;

    private final int PUESTOS_ATENCION = 4;
    private final int TERMINALES = 3;

    public GUI(String caption)
    {
        super(caption);

        this.hashPasajeros = new HashMap<>();
        this.guiClock = new GUIClock();
        this.guiPuestoInformes = new GUIQueue("Esperando en Sala de Informes");
        this.guiHall = new GUIQueue("Hall");
        this.guiAnden = new GUIQueue("Andén");
        this.guiTren = new GUITren("Tren");

        guiPuestosAtencion = new GUIQueue[PUESTOS_ATENCION];
        for(int i=0; i<PUESTOS_ATENCION; i++)
            guiPuestosAtencion[i] = new GUIQueue(String.format("Puesto de Atención %d", i+1));
    
        guiTerminal = new GUIQueue[TERMINALES];
        guiTerminal[0] = new GUIQueue("Terminal A");
        guiTerminal[1] = new GUIQueue("Terminal B");
        guiTerminal[2] = new GUIQueue("Terminal C");

        guiShop = new GUIQueue[TERMINALES];
        guiShop[0] = new GUIQueue("Shop A");
        guiShop[1] = new GUIQueue("Shop B");
        guiShop[2] = new GUIQueue("Shop C");

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {System.exit(0);}
        });

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();

        setLayout(layout);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = 0;
        constraints.gridx = 0;
        layout.addLayoutComponent(guiClock, constraints);
        add(guiClock);

        constraints.gridy++;
        layout.addLayoutComponent(guiPuestoInformes, constraints);
        add(guiPuestoInformes);

        constraints.gridy++;
        layout.addLayoutComponent(guiHall, constraints);
        add(guiHall);
        
        constraints.gridy++;
        constraints.gridwidth = 1;
        for(int i=0; i<PUESTOS_ATENCION; i++)
        {
            layout.addLayoutComponent(guiPuestosAtencion[i], constraints);
            add(guiPuestosAtencion[i]);
            constraints.gridx++;
        }

        constraints.gridx = 0;
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridy++;
        layout.addLayoutComponent(guiAnden, constraints);
        add(guiAnden);

        constraints.gridy++;
        constraints.gridwidth=1;
        layout.addLayoutComponent(guiTren, constraints);
        add(guiTren);

        constraints.gridy++;
        constraints.gridwidth = 1;
        constraints.gridheight = 3;
        for(int i=0; i<TERMINALES; i++)
        {
            constraints.gridx++;
            layout.addLayoutComponent(guiTerminal[i], constraints);
            add(guiTerminal[i]);
        }

        constraints.gridx = 0;
        constraints.gridheight = 1;
        for(int i=0; i<TERMINALES; i++)
        {
            layout.addLayoutComponent(guiShop[i], constraints);
            add(guiShop[i]);
            constraints.gridy++;
        }

        setSize(new Dimension(800,600));
        setVisible(true);
        pack();
    }

    public void setHour(int hour)
    {
        guiClock.setHour(hour);
    }

    public void entraPuestoInformes(int id)
    {
        GUIPasajero pasajero = new GUIPasajero(id);
        hashPasajeros.put(id, pasajero);
        guiPuestoInformes.push(pasajero);
    }

    public void entraHall(int id)
    {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiPuestoInformes.pop(pasajero);
        guiHall.push(pasajero);
    }

    public void entraPuestoAtencion(int id, int puesto)
    {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiHall.pop(pasajero);
        guiPuestosAtencion[puesto].push(pasajero);
    }

    public void entraAnden(int id, int puesto) {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiPuestosAtencion[puesto].pop(pasajero);
        guiAnden.push(pasajero);
    }

    public void entraTren(int id) {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiAnden.pop(pasajero);
        guiTren.push(pasajero);
    }

    public void entraTerminal(int id, int terminal)
    {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiTren.pop(pasajero);
        guiTerminal[terminal].push(pasajero);
    }

    public void trenLlegaA(int terminal)
    {
        guiTren.setTerminal(terminal);
        repaint();
        revalidate();
    }

    public void embarcar(int id, int terminal) 
    {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiTerminal[terminal].pop(pasajero);
    }

    public void entrarShop(int id, int terminal) {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiTerminal[terminal].pop(pasajero);
        guiShop[terminal].push(pasajero);
    }

    public void salirShop(int id, int terminal)
    {
        GUIPasajero pasajero = hashPasajeros.get(id);
        guiShop[terminal].pop(pasajero);
        guiTerminal[terminal].push(pasajero);
    }
}
