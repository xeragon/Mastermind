package V1.View;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class TiltLabel extends JLabel {
    public TiltLabel(String text) {
        super(text);
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
