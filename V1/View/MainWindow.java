package V1.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.Color;
import java.lang.reflect.Array;
import java.util.Arrays;

import V1.Controller.GameController;
import V1.Model.*;


public class MainWindow extends JFrame {
    private HintDisplayMode hint_display_mode;
    private JPanel pnlPrincipal;
    private JPanel pnlSecret; // combinaison secrete
    private JPanel pnlTries; // Regrouppe tout les panels de combinaison

    private V1.Model.Color last_color = V1.Model.Color.RED;
    private Game game;
    private GameController game_controller;

    public MainWindow(Game game, GameController game_controller) {
        super("MasterMind"); // ou setTitle("My app")
        this.game = game;
        this.game_controller = game_controller;
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private boolean is_combinaison_panel_set(JPanel combinaison_panel) {
        boolean r = true;
        for (Component c : combinaison_panel.getComponents()) {
            if (c.getBackground() == Color.BLACK) {
                r = false;
            }
        }
        return r;
    }

    private void submit_guess() {
        // set color et faut gerer si c'est la current combi
        boolean is_win = false;
        Round current_round = game.get_current_round();
        System.out.println("current round nb_guess_taken : " +  Integer.toString(current_round.get_nb_guess_taken()));
        
        JPanel combi = (JPanel) pnlTries.getComponent(current_round.get_nb_guess_taken());
        if (is_combinaison_panel_set(combi)) {
            // le code des hints ici
            Combination combination = convert_to_combination((JPanel)pnlTries.getComponent(current_round.get_nb_guess_taken()));
            Hint hint = combination.compare_to(current_round.get_secret_combination());
            set_panel_hints((JPanel)combi.getComponent(game.get_combination_size()),hint);

            is_win = game_controller.submit_guess(get_combination_colors_from_panel(combi));
            
            for (Component c : combi.getComponents()) {
                c.setEnabled(false);
            }
            
            if (current_round.can_continue_guess() && !is_win) {
                pnlTries.add(get_combination_panel(current_round.get_combinations()[current_round.get_nb_guess_taken()],game.get_combination_size()));
                repaint();
                revalidate();
            }
            else{
                if(!is_win){
                    JOptionPane.showInternalMessageDialog(null,"you are a looser !");
                }
                game_controller.end_round();
                
            }
        } else {
            JOptionPane.showInternalMessageDialog(null,"you must set the all the colors of the combination before submiting");
        }
    }

    private void display_menu() {

    }
    public void display_win(){
        JOptionPane.showInternalMessageDialog(null,"you won !!!");
    }

    public void display_game() {
        Round current_round = game.get_current_round();
        System.out.println("current round index : " +  Integer.toString(game.get_current_round_index()));
        
        pnlPrincipal = new JPanel(new BorderLayout());
        pnlTries = new JPanel(new GridLayout(game.get_nb_guess(), 1));
        
        // JPanel pnlCombination = new JPanel(new GridLayout(5, 1));
        JPanel pnl_available_colors = available_colors(); // Regrouppe tout les boutons de couleur
        
        pnlSecret = new JPanel(new GridLayout(1, game.get_combination_size()));
        
        setContentPane(pnlPrincipal);
        pnlPrincipal.add(pnlSecret, BorderLayout.NORTH);
        pnlPrincipal.add(pnlTries, BorderLayout.CENTER);
        
        pnlPrincipal.add(pnl_available_colors, BorderLayout.SOUTH);
        
        JButton submit_button = new JButton();
        submit_button.setBackground(Color.LIGHT_GRAY);
        
        submit_button.setText("submit");
        submit_button.addActionListener(actionEvent -> {
            submit_guess();
        });
        submit_button.setEnabled(true);
        pnl_available_colors.add(submit_button);
        
        // afficher combi secrete :

        Component[] components = get_combination_panel(current_round.get_secret_combination(), game.get_combination_size()).getComponents();
        for (Component component : components) {
            pnlSecret.add(component);
        }
        
        pnlTries.add(get_combination_panel(current_round.get_combinations()[0], game.get_combination_size()));

        repaint();
        revalidate();

    }

    // Transforme une combination en JPanel
    public JPanel get_combination_panel(Combination combination, int combination_size) {
        JPanel combination_panel = new JPanel(new GridLayout(1, game.get_combination_size() + 1));
        for (int i = 0; i < combination_size; i++) {
            combination_panel.add(get_combination_button(combination.get_color(i)));
        }
        JPanel pnlHint = new JPanel(new GridLayout(game.get_combination_size(), 1)); // 1 panel de Hint par combinaison
        for (int i = 0; i < game.get_combination_size(); i++) {
            pnlHint.add(new JLabel("hint " + Integer.toString(i)));
        }
        combination_panel.add(pnlHint);
        repaint();
        revalidate();
        return combination_panel;
    }

    // transforme l'élément de la combinaison en bouton
    public JButton get_combination_button(V1.Model.Color color) {
        JButton combination_button = new JButton();
        combination_button.setBackground(convert_color(color));
        combination_button.addActionListener(actionEvent -> {
            // set color et faut gerer si c'est la current combi
            combination_button.setBackground(convert_color(game.get_current_color()));
            combination_button.setBackground(convert_color(this.last_color));           // <- big erreur ici et c'est vlad qui l'a faite
        });
        combination_button.setEnabled(true);
        return combination_button;
    }

    // transforme la couleur selectionnable en bouton
    public JButton get_color_button(V1.Model.Color color) {
        JButton combination_button = new JButton();
        combination_button.setBackground(convert_color(color));
        combination_button.addActionListener(actionEvent -> {
            this.last_color = color;
        });
        combination_button.setEnabled(true);

        return combination_button;
    }

    // transforme la couleur du modele en couleur de la vue
    public Color convert_color(V1.Model.Color color) {

        if (color == null) {
            return Color.BLACK;
        }
        switch (color) {
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case YELLOW:
                return Color.YELLOW;
            case PURPLE:
                return Color.MAGENTA; // y'a pas purple :(
            case PINK:
                return Color.PINK;
        }
        return null;
    }

    private V1.Model.Color[] get_combination_colors_from_panel(JPanel panel){
        V1.Model.Color[] colors = new V1.Model.Color[game.get_combination_size()];
        Component[] components = panel.getComponents();
        for(int i = 0 ; i < game.get_combination_size();i++){
            colors[i] = convert_to_color(components[i].getBackground());
        }
        return colors;
    }

    private JPanel available_colors() {
        JPanel res = new JPanel(new GridLayout(game.get_nb_color_availaible() % game.get_combination_size(),
                game.get_combination_size()));

        for (int i = 0; i < game.get_nb_color_availaible(); i++) {
            res.add(get_color_button(V1.Model.Color.values()[i]));
        }
        
        return res;
    }

    private Combination convert_to_combination(JPanel combination_panel) {
        Combination res = new Combination(game.get_combination_size());


        for (int i = 0; i < game.get_combination_size(); i++) {
            JButton button = (JButton) combination_panel.getComponent(i);
            res.set_color(i, convert_to_color(button.getBackground()));
        }
        return res;
    }

    private V1.Model.Color convert_to_color(Color color) {
        if (color == Color.RED) {
            return V1.Model.Color.RED;
        }

        else if (color == Color.RED) {
            return V1.Model.Color.RED;
        } else if (color == Color.BLUE) {
            return V1.Model.Color.BLUE;
        } else if (color == Color.GREEN) {
            return V1.Model.Color.GREEN;
        } else if (color == Color.YELLOW) {
            return V1.Model.Color.YELLOW;
        } else if (color == Color.MAGENTA) {
            return V1.Model.Color.PURPLE;
        } else if (color == Color.PINK) {
            return V1.Model.Color.PINK;
        }

        else {
            return null;
        }

    }

    private void set_panel_hints(JPanel panel, Hint hint) {
        Component[] components = panel.getComponents();
        for (int i = 0; i < game.get_combination_size(); i++) {
            JLabel label = (JLabel) components[i];
            label.setText(hint.get_hint(i));
            switch(hint.get_hint(i)){
                case "CORRECT" :
                    label.setBackground(Color.GREEN);
                    break;
                case "COLOR_WRONG_PLACE" :
                    label.setBackground(Color.YELLOW);
                    break;
                case "COLOR_NOT_EXIST" :
                    label.setBackground(Color.RED);
                    break;
            }
            label.setOpaque(true);
        }
    }
}