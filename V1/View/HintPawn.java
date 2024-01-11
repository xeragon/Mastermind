package V1.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;

public class HintPawn extends PanelRound {
    public HintPawn(){
        setRoundBottomLeft(100);
        setRoundTopLeft(100);
        setRoundBottomRight(100);
        setRoundTopRight(100);
        setBackground(Color.LIGHT_GRAY);
        // setBorder(null);
        setOpaque(false);
    }

    public void change_color(Color color){
        setBackground(color);
    }

    
}
