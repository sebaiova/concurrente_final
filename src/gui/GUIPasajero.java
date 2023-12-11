package src.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIPasajero extends JPanel {

    public GUIPasajero(int id)
    {    
        setBackground(new Color(255, 100, 255));
        setOpaque(true);
        setForeground(Color.black);
        setBorder(BorderFactory.createLineBorder(Color.black));
        setPreferredSize(new Dimension(32, 32));

        add(new JLabel(String.valueOf(id)));
    }
}