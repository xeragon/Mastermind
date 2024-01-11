package V1.View;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Arrays;

import V1.Controller.GameController;
import V1.Model.*;


public class MainWindow extends JFrame {
    private HintDisplayMode hint_display_mode;
    private BackgroundPanel backgroundPanel;
    private JPanel pnlPrincipal;
    private JPanel pnlSecret; // combinaison secrete
    private JPanel pnlTries; // Regrouppe tout les panels de combinaison
    private JPanel pnl_head; // Regrouppe tout les panels de combinaison

    final Color EMPTY_COLOR = new Color(70,70,70);

    private V1.Model.Color last_color = V1.Model.Color.RED;
    private Game game;
    private GameController game_controller;

    public MainWindow(Game game, GameController game_controller) {
        super("MasterMind"); // ou setTitle("My app")
        this.game = game;
        this.game_controller = game_controller;
        setSize(500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new BackgroundPanel();
        setContentPane(backgroundPanel);
        setVisible(true);
    }

    private boolean is_combinaison_panel_set(JPanel combinaison_panel) {
        boolean r = true;
        for (Component c : combinaison_panel.getComponents()) {
            if (c.getBackground() == EMPTY_COLOR) {
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
            Hint hint = combination.compare_to(current_round.get_secret_combination(), game.get_difficulty());
            set_panel_hints((JPanel)combi.getComponent(game.get_combination_size()),hint);
            // enregistrer la combi et les hints
            is_win = game_controller.submit_guess(combination, hint);


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
    public void increment_nb_guesses_left(){
  
        JLabel l =  (JLabel) pnl_head.getComponent(1);
        l.setText("guesses left : " + Integer.toString(game.get_nb_guess()-game.get_current_round().get_nb_guess_taken()));

    }
    public void display_menu() {
        backgroundPanel.removeAll(); // pour reset le panel
        JPanel pnl_menu = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;

        c.weighty = 0.1;
        c.gridy = 0;
        pnl_menu.add(new JLabel("Choix de la difficulté : "),c);

        // RadioButton pour le choix de la difficultée
        JRadioButton easy_button = new JRadioButton("Facile");
        easy_button.setMnemonic(KeyEvent.VK_B);
        easy_button.setActionCommand("Facile");
        easy_button.addActionListener(e -> {
            game.set_difficulty(0);
        });
        easy_button.setSelected(true);

        JRadioButton normal_button = new JRadioButton("Normal");
        normal_button.setMnemonic(KeyEvent.VK_B);
        normal_button.setActionCommand("Normal");
        normal_button.addActionListener(e -> {
            game.set_difficulty(1);
        });

        JRadioButton hard_button = new JRadioButton("Difficile");
        hard_button.setMnemonic(KeyEvent.VK_B);
        hard_button.setActionCommand("Difficile");
        hard_button.addActionListener(e -> {game.set_difficulty(2);
        });

        //Group the radio buttons.
        ButtonGroup group = new ButtonGroup();
        group.add(easy_button);
        group.add(normal_button);
        group.add(hard_button);

        c.gridx = 1;
        pnl_menu.add(easy_button, c);
        c.gridx ++;
        pnl_menu.add(normal_button, c);
        c.gridx ++;
        pnl_menu.add(hard_button, c);



        c.gridy = 1;
        c.gridx = 0;
        JButton play_button = new JButton();
        play_button.setBackground(Color.LIGHT_GRAY);

        play_button.setText("Jouer");
        play_button.addActionListener(actionEvent -> {
            game_controller.start_game();
        });
        play_button.setEnabled(true);
        pnl_menu.add(play_button, c);

        backgroundPanel.add(pnl_menu);


        revalidate();
        repaint();

    }
    public void display_win(){
        JOptionPane.showInternalMessageDialog(null,"you won !!!");
    }
    
    
    public void display_game() {
        backgroundPanel.removeAll(); // pour reset le panel 

        Round current_round = game.get_current_round();

        pnlPrincipal = new JPanel();
        pnlPrincipal.setLayout(new GridBagLayout());
        pnlPrincipal.setOpaque(false);
        backgroundPanel.add(pnlPrincipal,BorderLayout.CENTER);


        
        pnl_head = new PanelHead(game.get_current_round_index(),game.get_nb_guess());
        
        // pnlTries = new JPanel(new GridLayout(game.get_nb_guess(), 1));
        pnlTries = new JPanel(new GridLayout(12,1));
        pnlTries.setOpaque(false);
        pnlSecret = new JPanel(new GridLayout(1, game.get_combination_size()));
        JPanel pnl_available_colors = available_colors(); // Regrouppe tout les boutons de couleur
        
        
        // JPanel pnlCombination = new JPanel(new GridLayout(5, 1));
        
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.BOTH;
        
        c.weightx = 1; 
        
        c.weighty = 0.1;
        c.gridy = 0;
        
        pnlPrincipal.add(pnl_head,c);
        
        
        c.weighty = 0.6;
        c.gridy = 1;
        
        pnlPrincipal.add(pnlTries,c);
        
        
        c.weighty = 0.05;
        c.gridy = 2;
        
        pnlPrincipal.add(pnl_available_colors,c);
        
        // pnlPrincipal.add(pnlSecret,c);
        
        JButton submit_button = new JButton();
        submit_button.setBackground(Color.LIGHT_GRAY);
        
        submit_button.setText("submit");
        submit_button.addActionListener(actionEvent -> {
            submit_guess();
        });
        submit_button.setEnabled(true);
        pnl_available_colors.add(submit_button);
        
        // afficher combi secrete :
        
        // Component[] components = get_combination_panel(current_round.get_secret_combination(), game.get_combination_size()).getComponents();
        // for (Component component : components) {
            //     pnlSecret.add(component);
            // }
            
        pnlTries.add(get_combination_panel(current_round.get_combinations()[0], game.get_combination_size()));

        // surtout ne regardez pas ça
        // non non je vous jure y'a rien a voir
        setSize(499, 800);
        setSize(500, 800);
        // on avait pas le choix pour forcer l'actualisation de la backgroundImage...

        repaint();
        revalidate();
    }

    public void display_stats(){
        System.out.println("display stats");
        backgroundPanel.removeAll(); // pour reset le panel
        JPanel pnl_menu = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 0.1;

        // afficher le resumé

        // boutons
        c.gridx = 0;
        c.gridy ++;
        JButton play_button = new JButton();
        play_button.setBackground(Color.LIGHT_GRAY);

        play_button.setText("Rejouer");
        play_button.addActionListener(actionEvent -> {
            game_controller.start_game();
        });
        play_button.setEnabled(true);
        pnl_menu.add(play_button, c);

        c.gridx ++;
        JButton menu_button = new JButton();
        menu_button.setBackground(Color.LIGHT_GRAY);

        menu_button.setText("Menu");
        menu_button.addActionListener(actionEvent -> {
            game_controller.show_menu();
        });
        menu_button.setEnabled(true);
        pnl_menu.add(menu_button, c);

        backgroundPanel.add(pnl_menu);
        revalidate();
        repaint();
    }
        
        
    // Transforme une combination en JPanel
    public JPanel get_combination_panel(Combination combination, int combination_size) {
        CombinationPanel combination_panel = new CombinationPanel(new GridLayout(1, game.get_combination_size() + 1));
        for (int i = 0; i < combination_size; i++) {
            combination_panel.add(get_combination_button(combination.get_color(i)));
        }

        JPanel pnl_hint;
        if(game.get_difficulty() < 2) {
            pnl_hint = new JPanel(new GridLayout(1, game.get_combination_size())); // 1 panel de Hint par combinaison
            for (int i = 0; i < game.get_combination_size(); i++) {

                RoundColorButton lbl_hint = new RoundColorButton();
                lbl_hint.setEnabled(false);
                lbl_hint.setBackground(Color.LIGHT_GRAY);
                pnl_hint.add(lbl_hint);
            }
        }else{
            pnl_hint = new JPanel(new GridLayout(2, 1)); // 1 panel de Hint par combinaison
            pnl_hint.add(new JLabel("Correct : "));
            pnl_hint.add(new JLabel("faux : "));
        }
        pnl_hint.setOpaque(false);
        combination_panel.add(pnl_hint);
        repaint();
        revalidate();
        return combination_panel;
    }

    // transforme l'élément de la combinaison en bouton
    public JButton get_combination_button(V1.Model.Color color) {
        JButton combination_button = new RoundColorButton();
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
        RoundColorButton combination_button = new RoundColorButton();
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
            return EMPTY_COLOR;
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

    private JPanel available_colors() {
        JPanel res = new JPanel(new GridLayout(1,game.get_nb_color_availaible()+1));
        res.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < game.get_nb_color_availaible(); i++) {
            res.add(get_color_button(V1.Model.Color.values()[i]));
        }
        res.setOpaque(false);
        
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
        // pour un affichage numerique
        if(game.get_difficulty() == 2){
            int correct = 0;

            for (int i = 0; i < game.get_combination_size(); i++) {
                if(hint.get_hint(i) == "CORRECT")
                    correct ++;
                else
                    break;
            }
            int wrong = game.get_combination_size() - correct;
            Component[] components = panel.getComponents();
            JLabel nb_correct = (JLabel)components[0];
            JLabel nb_wrong = (JLabel)components[1];
            nb_correct.setText("Correct : " + correct);
            nb_wrong.setText("Faux : " + wrong);

        }else {
            Component[] components = panel.getComponents();
            for (int i = 0; i < game.get_combination_size(); i++) {
                RoundColorButton pawn = (RoundColorButton) components[i];
                switch (hint.get_hint(i)) {
                    case "CORRECT":
                        pawn.setBackground(Color.BLACK);
                        break;
                    case "COLOR_WRONG_PLACE":
                        pawn.setBackground(Color.WHITE);
                        break;
                    case "COLOR_NOT_EXIST":
                        pawn.setBackground(Color.LIGHT_GRAY);
                        break;
                }
            }
        }
    }
}