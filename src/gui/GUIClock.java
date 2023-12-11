package src.gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUIClock extends JPanel {

    private final JLabel labelHour;

    public GUIClock()
    {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        setLayout(layout);

        JLabel text = new JLabel("Hora");
        layout.setConstraints(text, constraints);
        add(text);


        labelHour = new JLabel();
        constraints.gridy = 1;
        layout.setConstraints(labelHour, constraints);
        add(labelHour);   
        
        setHour(1);
    }

    public void setHour(int hour)
    {
        labelHour.setText(String.format("%02d:00", hour));
    }
}
