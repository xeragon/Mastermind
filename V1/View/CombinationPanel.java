package V1.View;

import java.awt.Color;
import java.awt.GridLayout;

public class CombinationPanel extends PanelRound {
    
    public CombinationPanel(){
        super();
        setBackground(Color.LIGHT_GRAY);
        setRoundBottomLeft(30);
        setRoundTopLeft(30);
        setRoundBottomRight(30);
        setRoundTopRight(30);
        
    }

    public CombinationPanel(GridLayout gridLayout) {
        super(gridLayout);
        setBackground(Color.LIGHT_GRAY);
        setRoundBottomLeft(30);
        setRoundTopLeft(30);
        setRoundBottomRight(30);
        setRoundTopRight(30);
    }
}
