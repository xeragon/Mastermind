package V1.View;

import java.awt.GridBagConstraints;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import V1.Model.DisplayType;

public class PanelDisplayChoice extends JPanel {
    private DisplayType display_type = DisplayType.CLASSIC;

    public PanelDisplayChoice(){
        setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        c.weightx = 1;
        
        
        c.gridy = 0;
        c.weighty = 1;

        c.gridx = 0;
        
        this.add(new JLabel("Choix du type d'indices : "),c);
        // RadioButton pour le choix de la difficultée
        JRadioButton easy_button = new JRadioButton("Facile");
        easy_button.setOpaque(false);
        easy_button.setMnemonic(KeyEvent.VK_B);
        easy_button.setActionCommand("Facile");
        easy_button.addActionListener(e -> {
            this.display_type = DisplayType.EASY;
        });
        
        JRadioButton normal_button = new JRadioButton("Classique");
        normal_button.setOpaque(false);
        normal_button.setMnemonic(KeyEvent.VK_B);
        normal_button.setActionCommand("Classique");
        normal_button.addActionListener(e -> {
            this.display_type = DisplayType.CLASSIC;
        });
        normal_button.setSelected(true);
        
        JRadioButton hard_button = new JRadioButton("Numérique");
        hard_button.setOpaque(false);
        hard_button.setMnemonic(KeyEvent.VK_B);
        hard_button.setActionCommand("Numérique");
        hard_button.addActionListener(e -> {
            this.display_type = DisplayType.NUMERIC;
        });
        
        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(easy_button);
        group.add(normal_button);
        group.add(hard_button);

        c.gridx = 1;
        add(easy_button, c);
        c.gridx ++;
        add(normal_button, c);
        c.gridx ++;
        add(hard_button, c);

    }

    public DisplayType get_display_type(){
        return this.display_type;
    }
}
