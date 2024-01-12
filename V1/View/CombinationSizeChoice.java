package V1.View;

import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import V1.Model.DisplayType;

public class CombinationSizeChoice extends JPanel {
    private int combination_size = 4;

    public CombinationSizeChoice(){

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        c.weightx = 1;
        
        
        c.gridy = 0;
        c.weighty = 1;

        c.gridx = 0;
        
        this.add(new JLabel("Nombre de pions par combinaison : "),c);
        // RadioButton pour le choix de la difficultée
        JRadioButton two = new JRadioButton("2");
        two.setOpaque(false);
        two.setMnemonic(KeyEvent.VK_B);
        two.setActionCommand("2");
        two.addActionListener(e -> {
            this.combination_size = 2;
        });
        JRadioButton three = new JRadioButton("3");
        three.setOpaque(false);
        three.setMnemonic(KeyEvent.VK_B);
        three.setActionCommand("3");
        three.addActionListener(e -> {
            this.combination_size = 3;
        });
        JRadioButton four = new JRadioButton("4");
        four.setOpaque(false);
        four.setMnemonic(KeyEvent.VK_B);
        four.setActionCommand("4");
        four.addActionListener(e -> {
            this.combination_size = 4;
        });
        four.setSelected(true);
        
        JRadioButton five = new JRadioButton("5");
        five.setOpaque(false);
        five.setMnemonic(KeyEvent.VK_B);
        five.setActionCommand("5");
        five.addActionListener(e -> {
            this.combination_size = 5;
        });
        
        JRadioButton six = new JRadioButton("6");
        six.setOpaque(false);
        six.setMnemonic(KeyEvent.VK_B);
        six.setActionCommand("6");
        six.addActionListener(e -> {
            this.combination_size = 6;
        });
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(two);
        group.add(three);
        group.add(four);
        group.add(five);
        group.add(six);

        c.gridx = 1;

        add(two, c);
        c.gridx ++;
        add(three, c);
        c.gridx ++;
        add(four, c);
        c.gridx ++;
        add(five, c);
        c.gridx ++;
        add(six, c);
        setOpaque(false);
    }

    public int get_combination_size(){
        return this.combination_size;
    }
}
