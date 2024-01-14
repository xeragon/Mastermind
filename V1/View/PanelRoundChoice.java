package V1.View;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelRoundChoice extends JPanel{
    private JTextField text ;
    public PanelRoundChoice(){
    setLayout(new FlowLayout());

    this.text = new JTextField("3");
    this.text.setSize(new Dimension(30,30));
    this.add(new JLabel("nombre de rounds : "));
    this.add(text);
    setOpaque(false);
    }

    public int get_nb_round(){
        int r;
        try {
            r = Integer.parseInt(text.getText());
            
        } catch (Exception e) {
            r = 3;
        }
        return r;
    }

}


