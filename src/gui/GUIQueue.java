package src.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;

public class GUIQueue extends JLayeredPane {
    
    public GUIQueue(String caption)
    {
        setBorder(BorderFactory.createTitledBorder(caption));

        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 2, 2);
        setLayout(layout);

        setVisible(true);
    }

    public void push(GUIPasajero pasajero)
    {
        add(pasajero);
        repaint();
        revalidate();
    }

    public void pop(GUIPasajero pasajero)
    {
        remove(pasajero);
        repaint();
        revalidate();
    }
}
