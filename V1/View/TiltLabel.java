package V1.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


import V1.Model.*;

public class TiltLabel extends JLabel {
    public TiltLabel(String text) {
        super(text);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setOpaque(true);
    }

    @Override
    public void paintComponent(Graphics grphcs) {

        Graphics2D g2d = (Graphics2D) grphcs.create();
        
        // g2d.drawString("vide", 1, 1);
        // g2d.rotate(Math.PI/2);

        super.paintComponent(g2d);
    }
}
