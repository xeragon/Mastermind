package V1.View;

import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import V1.Model.DisplayType;

public class PanelColorAvailableChoice extends JPanel {
    private int nb_color_availaible = 6;

    public PanelColorAvailableChoice(){

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        c.weightx = 1;
        
        
        c.gridy = 0;
        c.weighty = 1;

        c.gridx = 0;
        
        this.add(new JLabel("Nombre de couleurs disponible : "),c);
        // RadioButton pour le choix de la difficultée
        JRadioButton four = new JRadioButton("4");
        four.setOpaque(false);
        four.setMnemonic(KeyEvent.VK_B);
        four.setActionCommand("4");
        four.addActionListener(e -> {
            this.nb_color_availaible = 4;
        });
        
        JRadioButton five = new JRadioButton("5");
        five.setOpaque(false);
        five.setMnemonic(KeyEvent.VK_B);
        five.setActionCommand("5");
        five.addActionListener(e -> {
            this.nb_color_availaible = 5;
        });
        five.setSelected(true);
        
        JRadioButton six = new JRadioButton("6");
        six.setOpaque(false);
        six.setMnemonic(KeyEvent.VK_B);
        six.setActionCommand("6");
        six.addActionListener(e -> {
            this.nb_color_availaible = 6;
        });

        JRadioButton seven = new JRadioButton("7");
        seven.setOpaque(false);
        seven.setMnemonic(KeyEvent.VK_B);
        seven.setActionCommand("7");
        seven.addActionListener(e -> {
            this.nb_color_availaible = 7;
        });

        JRadioButton eight = new JRadioButton("8");
        eight.setOpaque(false);
        eight.setMnemonic(KeyEvent.VK_B);
        eight.setActionCommand("8");
        eight.addActionListener(e -> {
            this.nb_color_availaible = 8;
        });
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(four);
        group.add(five);
        group.add(six);
        group.add(seven);
        group.add(eight);

        c.gridx = 1;
        add(four, c);
        c.gridx ++;
        add(five, c);
        c.gridx ++;
        add(six, c);
        c.gridx ++;
        add(seven, c);
        c.gridx ++;
        add(eight, c);
        setOpaque(false);
    }

    public int get_nb_color(){
        return this.nb_color_availaible;
    }
}
