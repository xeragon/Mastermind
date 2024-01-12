package V1.View;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class NbGuessChoice extends JPanel {
    NumberComboBox ncbo = new NumberComboBox();
    public int get_nb_guess() {
        return ncbo.get_n();
    }
    public NbGuessChoice(){
        this.setLayout(new FlowLayout());
        this.add(new JLabel("nombre d'essais"));
        this.add(ncbo);
        setOpaque(false);

    }
    
}
