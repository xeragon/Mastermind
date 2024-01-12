package V1.View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;

public class CombinationPanel extends PanelRound {
    
    public CombinationPanel(){
        super();
        setLayout(new FlowLayout());
        setBackground(new Color(0, 0, 0, 0));
        setRoundBottomLeft(30);
        setRoundTopLeft(30);
        setRoundBottomRight(30);
        setRoundTopRight(30);
        setOpaque(false);
        
    }

    public CombinationPanel(GridLayout gridLayout) {
        super(gridLayout);
        setBackground(new Color(0, 0, 0, 0));
        setRoundBottomLeft(30);
        setRoundTopLeft(30);
        setRoundBottomRight(30);
        setRoundTopRight(30);
        setOpaque(false);

    }


}
