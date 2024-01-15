package V1.View;

import javax.swing.*;

import java.awt.*;
import java.awt.Color;

import V1.Controller.GameController;
import V1.Model.*;


public class MainWindow extends JFrame {
    private BackgroundPanel backgroundPanel;
    private JPanel pnlPrincipal;
    private JPanel pnlSecret; // combinaison secrete
    private JPanel pnlTries; // Regrouppe tout les panels de combinaison
    private JPanel pnl_head; // Regrouppe tout les panels de combinaison

    final Color EMPTY_COLOR = new Color(70,70,70);

    private V1.Model.Color last_color = V1.Model.Color.RED;
    private Game game;
    private GameController game_controller;
    
    private DisplayType display_type = DisplayType.CLASSIC;

    public MainWindow(Game game, GameController game_controller) {
        super("MasterMind"); // ou setTitle("My app")
        this.game = game;
        this.game_controller = game_controller;
        setSize(500, 850);
        setResizable(false);
        setMinimumSize(new Dimension(500,850));
        setMaximumSize(new Dimension(500,850));
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
                // calculé les points
                for(int i = 0; i < game.get_combination_size(); i++){
                    if(hint.get_hint(i) == "CORRECT"){
                        game.add_score(4);
                    }else if(hint.get_hint(i) == "COLOR_WRONG_PLACE"){
                        game.add_score(1);
                    }
                    if(game.get_difficulty() == DisplayType.CLASSIC || game.get_difficulty() == DisplayType.NUMERIC){
                        game.add_score(4);
                    }
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

        BackgroundPanel background = new BackgroundPanel();

        JPanel pnl_menu = new JPanel(new GridBagLayout());
        PanelDisplayChoice display_choice = new PanelDisplayChoice();
        PanelRoundChoice round_choice = new PanelRoundChoice();
        PanelColorAvailableChoice color_available_choice = new PanelColorAvailableChoice();
        CombinationSizeChoice combination_size_choice  = new CombinationSizeChoice();
        NbGuessChoice nb_guess_choice  = new NbGuessChoice();
        background.add(pnl_menu,BorderLayout.CENTER);
        
        
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        
        c.weightx = 1;
        
        
        c.gridy = 0;
        
        
        
        c.gridx = 0;
        
        c.insets = new Insets(50, 10, 0,10);
        c.weighty = 0.1;
        JPanel title = new JPanel();
        title.setOpaque(false);
        JLabel label = new JLabel("Mastermind");
        label.setFont(new Font("Serif", Font.BOLD, 32));
        title.add(label);
        pnl_menu.add(title,c);
        c.insets = new Insets(20, 10, 0,10);
        
        c.weightx = 1;
        c.weighty = 0.2;
        c.gridy++;
        pnl_menu.add(round_choice,c);
        c.weighty = 0.2;
        c.gridy++;
        pnl_menu.add(display_choice,c);
        c.weighty = 0.2;
        c.gridy++;
        pnl_menu.add(color_available_choice,c);
        c.weighty = 0.2;
        c.gridy++;
        pnl_menu.add(combination_size_choice,c);
        c.weighty = 0.2;
        c.gridy++;
        pnl_menu.add(nb_guess_choice,c);
        
        
        
        JButton play_button = new JButton();
        play_button.setBackground(Color.LIGHT_GRAY);
        
        play_button.setText("Jouer");
        play_button.addActionListener(actionEvent -> {
            game_controller.start_game(round_choice.get_nb_round(),combination_size_choice.get_combination_size(),nb_guess_choice.get_nb_guess(),color_available_choice.get_nb_color(),display_choice.get_display_type());
        });
        play_button.setEnabled(true);
        
        c.insets = new Insets(10, 10, 10,10);
        c.weighty = 0.1;
        c.gridy ++;
        pnl_menu.add(play_button, c);
        pnl_menu.setOpaque(false);
        
        backgroundPanel.add(pnl_menu);
        setContentPane(backgroundPanel);
        
        
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
        c.ipady = 10;
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
        

        pnlTries.add(get_combination_panel(current_round.get_combinations()[0], game.get_combination_size()));

        // surtout ne regardez pas ça
        // non non je vous jure y'a rien a voir
        setSize(551, 800);
        setSize(550, 800);
        // on avait pas le choix pour forcer l'actualisation de la backgroundImage...

        repaint();
        revalidate();
    }

    public void display_stats(){
        backgroundPanel.removeAll(); // pour reset le panel

        BackgroundPanel background = new BackgroundPanel();

        JPanel pnl_menu = new JPanel(new GridBagLayout());
        JPanel pnl_score = new JPanel(new FlowLayout());
        background.add(pnl_menu,BorderLayout.CENTER);



        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;

        c.weightx = 1;


        c.gridy = 0;



        c.gridx = 0;

        c.insets = new Insets(50, 10, 0,10);
        c.weighty = 0.1;
        JPanel title = new JPanel();
        title.setOpaque(false);
        JLabel label = new JLabel("Mastermind");
        label.setFont(new Font("Serif", Font.BOLD, 64));
        title.add(label);
        pnl_menu.add(title,c);
        c.insets = new Insets(20, 10, 0,10);

        c.weightx = 1;
        c.weighty = 0.2;
        c.gridy++;
        JLabel lbl_score = new JLabel("Score final : " + game.get_score());
        lbl_score.setFont(new Font("Serif", Font.BOLD, 42));
        pnl_score.add(lbl_score);
        pnl_score.setOpaque(false);
        pnl_menu.add(pnl_score,c);
        c.weighty = 0.2;
        c.gridy++;
        //replay
        JButton play_button = new JButton();
        play_button.setBackground(Color.LIGHT_GRAY);

        play_button.setText("Rejouer");
        play_button.setFont(new Font("Serif", Font.BOLD, 42));
        play_button.addActionListener(actionEvent -> {
            game_controller.start_game(game.get_nb_round(), game.get_combination_size(), game.get_nb_guess(), game.get_nb_color_available(), game.get_difficulty());
        });
        play_button.setEnabled(true);

        c.insets = new Insets(10, 10, 10,10);
        c.weighty = 0.1;
        c.gridy ++;
        pnl_menu.add(play_button, c);

        c.weighty = 0.2;
        c.gridy++;
        //menu :
        JButton menu_button = new JButton();
        menu_button.setBackground(Color.LIGHT_GRAY);

        menu_button.setText("Menu");
        menu_button.setFont(new Font("Serif", Font.BOLD, 42));
        menu_button.addActionListener(actionEvent -> {
            game_controller.show_menu();
        });
        menu_button.setEnabled(true);

        c.insets = new Insets(10, 10, 10,10);
        c.weighty = 0.1;
        c.gridy ++;
        pnl_menu.add(menu_button,c);
        pnl_menu.setOpaque(false);

        backgroundPanel.add(pnl_menu);
        setContentPane(backgroundPanel);

        revalidate();
        repaint();
    }
        
        
    // Transforme une combination en JPanel
    public JPanel get_combination_panel(Combination combination, int combination_size) {
        CombinationPanel combination_panel = new CombinationPanel();
        for (int i = 0; i < combination_size; i++) {
            combination_panel.add(get_combination_button(combination.get_color(i)));
        }

        JPanel pnl_hint;
        if(game.get_difficulty() != DisplayType.NUMERIC) {
            pnl_hint = new JPanel(new GridLayout(2, game.get_combination_size())); // 1 panel de Hint par combinaison
            for (int i = 0; i < game.get_combination_size(); i++) {

                RoundColorButton lbl_hint = new RoundColorButton(new Dimension(20,20));
                lbl_hint.setEnabled(false);
                lbl_hint.setBackground(Color.GRAY);
                pnl_hint.add(lbl_hint);
            }
        }else{
            pnl_hint = new JPanel(new GridLayout(2, 1)); // 1 panel de Hint par combinaison
            pnl_hint.add(new JLabel("Correct : "));
            pnl_hint.add(new JLabel("Mauvais endroit : "));
        }
        pnl_hint.setOpaque(false);
        combination_panel.add(pnl_hint);
        repaint();
        revalidate();
        return combination_panel;
    }

    // transforme l'élément de la combinaison en bouton
    public JButton get_combination_button(V1.Model.Color color) {
        RoundColorButton combination_button = new RoundColorButton(new Dimension(45,45));
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
        RoundColorButton combination_button = new RoundColorButton(new Dimension(50,50));
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
            case CYAN:
                return Color.CYAN;
            case ORANGE:
                return Color.ORANGE;
        }
        return null;
    }

    private JPanel available_colors() {
        JPanel res = new JPanel(new GridLayout(1,game.get_nb_color_available()+1));
        res.setBackground(Color.DARK_GRAY);
        for (int i = 0; i < V1.Model.Color.values().length && i < game.get_nb_color_available() ; i++) {
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
        }else if (color == Color.CYAN){
            return V1.Model.Color.CYAN;
        }else if (color == Color.ORANGE){
            return V1.Model.Color.ORANGE;
        }

        else {
            return null;
        }

    }

    private void set_panel_hints(JPanel panel, Hint hint) {
        // pour un affichage numerique
        if(game.get_difficulty() == DisplayType.NUMERIC){
            int correct = 0;
            int wrong = 0;

            for (int i = 0; i < game.get_combination_size(); i++) {
                if(hint.get_hint(i) == "CORRECT")
                    correct ++;
                else if(hint.get_hint(i) == "COLOR_WRONG_PLACE")
                    wrong ++ ;
            }
            Component[] components = panel.getComponents();
            JLabel nb_correct = (JLabel)components[0];
            JLabel nb_wrong = (JLabel)components[1];
            nb_correct.setText("Correct : " + correct);
            nb_wrong.setText("Mauvais endroit  : " + wrong);

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