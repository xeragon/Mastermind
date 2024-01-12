package V1.View;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelHead extends JPanel {
    public PanelHead(int round, int guess){

        this.setLayout(new BorderLayout());
        JLabel round_label = new JLabel("round : " + Integer.toString(round+1));
        JLabel guess_label = new JLabel("guesses left : " + Integer.toString(guess));
        round_label.setForeground(Color.WHITE);
        guess_label.setForeground(Color.WHITE);
        this.add(round_label, BorderLayout.WEST);
        this.add(guess_label, BorderLayout.EAST);
        this.setOpaque(false);

    }
}
