package V1.View;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import V1.Model.Combination;
import V1.Model.Game;
import V1.Model.Round;


public class MainWindow extends JFrame {
    private HintDisplayMode hint_display_mode;
    private JPanel pnlPrincipal;                
    private JPanel pnlSecret;                   //combinaison secrete
    private JPanel pnlTries;                    //Regrouppe tout les panels de combinaison

    private V1.Model.Color last_color = V1.Model.Color.RED;
    private Game game; 
    public MainWindow(Game game){
        super( "MasterMind" ); // ou setTitle("My app")
        this.game = game;
        setSize(500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        System.out.println(game.get_nb_guess());
        pnlPrincipal = new JPanel(new BorderLayout());
        pnlTries = new JPanel(new GridLayout(game.get_combination_size(),1));

        // JPanel pnlCombination = new JPanel(new GridLayout(5, 1));
        JPanel pnlHint = new JPanel(new GridLayout(1, game.get_nb_guess()));        //Regrouppe tout les panels de hint
        JPanel pnl_available_colors = available_colors();                               //Regrouppe tout les boutons de couleur
        
        pnlSecret = new JPanel(new GridLayout(1, game.get_combination_size()));


        setContentPane(pnlPrincipal);
        pnlPrincipal.add(pnlSecret, BorderLayout.NORTH);
        pnlPrincipal.add(pnlTries, BorderLayout.CENTER);
        pnlPrincipal.add(pnlHint, BorderLayout.EAST);

        pnlPrincipal.add(pnl_available_colors, BorderLayout.SOUTH);



        JButton submit_button = new JButton();
        submit_button.setBackground(Color.LIGHT_GRAY);

        submit_button.setText("submit");
        submit_button.addActionListener(actionEvent -> {
            // set color et faut gerer si c'est la current combi 
            JPanel combi = (JPanel) pnlTries.getComponent(game.get_nb_guess_taken()); 
            if (is_combinaison_panel_set(combi)){
                System.out.println("submited");
                for (Component c : combi.getComponents()) {
                    c.setEnabled(false);
                }
                if(game.can_continue_guess()){
                    pnlTries.add(get_combination_panel(game.get_current_round().get_combinations()[game.get_nb_guess_taken()], game.get_combination_size()));
                    repaint();
                    revalidate();
                }
            }
            
            
            //le code des hints ici
            // convert_to_combination((JPanel)pnlTries.getComponent(game.get_nb_guess_taken()));
        });
        submit_button.setEnabled(true);
        pnl_available_colors.add(submit_button);
        repaint();
        revalidate();
        
        setVisible(true);
    }
    
    private boolean is_combinaison_panel_set(JPanel combinaison_panel){
        boolean r = true;
        for (Component c : combinaison_panel.getComponents()) {
            if (c.getBackground() == Color.BLACK) {
                r = false;
            }
        }
        // System.out.println(r);
        return r;
    }
    
    private void display_menu(){
        
    }


    public void display_game(){
        Round current_round = game.get_current_round();
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
    public JPanel get_combination_panel(Combination combination, int combination_size){
        JPanel combination_panel = new JPanel(new GridLayout(1, game.get_combination_size()));
        for (int i = 0; i < combination_size; i++){
            combination_panel.add(get_combination_button(combination.get_color(i)));
        }
        repaint();
        revalidate();
        return combination_panel;
    }

    //transforme l'élément de la combinaison en bouton
    public JButton get_combination_button(V1.Model.Color color){
        JButton combination_button = new JButton();
        combination_button.setBackground(convert_color(color));
        combination_button.addActionListener(actionEvent -> {
            // set color et faut gerer si c'est la current combi 
            combination_button.setBackground(convert_color(game.get_current_color()));
            System.out.println("button clicked");
            combination_button.setBackground(convert_color(this.last_color));
        });
        combination_button.setEnabled(true);
        return combination_button;
    }

    //transforme la couleur selectionnable en bouton
    public JButton get_color_button(V1.Model.Color color){
        JButton combination_button = new JButton();
        combination_button.setBackground(convert_color(color));
        combination_button.addActionListener(actionEvent -> {
            // set color et faut gerer si c'est la current combi
            System.out.println("button clicked");
            this.last_color = color;
        });
        combination_button.setEnabled(true);

        return combination_button;
    }

    //transforme la couleur du modele en couleur de la vue
    public Color convert_color(V1.Model.Color color){
        
        if (color == null){
            return Color.BLACK;
        }
        switch(color){
            case RED:
                return Color.RED;
            case BLUE:
                return Color.BLUE;
            case GREEN:
                return Color.GREEN;
            case YELLOW:
                return Color.YELLOW;
            case PURPLE:
                return Color.MAGENTA;   //y'a pas purple :(
            case PINK:
                return Color.PINK;
        }
        return null;
    }

    private JPanel available_colors(){
        JPanel res = new JPanel(new GridLayout(game.get_nb_color_availaible()%game.get_combination_size(), game.get_combination_size()));
        res.add(get_color_button(V1.Model.Color.RED));
        res.add(get_color_button(V1.Model.Color.BLUE));
        res.add(get_color_button(V1.Model.Color.GREEN));
        res.add(get_color_button(V1.Model.Color.YELLOW));
        res.add(get_color_button(V1.Model.Color.PURPLE));
        res.add(get_color_button(V1.Model.Color.PINK));
        return res;
    }

    private Combination[] convert_to_combination(JPanel combination_panel){
        Combination[] res = new Combination[game.get_combination_size()];
        for (int i = 0; i < game.get_combination_size(); i++){
            JButton button = (JButton) combination_panel.getComponent(i);
            res[i].set_color(i, convert_to_color(button.getBackground()));
        }
        return res;
    }

    private V1.Model.Color convert_to_color(Color color){
        if(color == Color.RED){
            return V1.Model.Color.RED;
        }else if(color == Color.BLUE){
            return V1.Model.Color.BLUE;
        }else if(color == Color.GREEN){
            return V1.Model.Color.GREEN;
        }else if(color == Color.YELLOW){
            return V1.Model.Color.YELLOW;
        }else if(color == Color.MAGENTA){
            return V1.Model.Color.PURPLE;
        }else if(color == Color.PINK){
            return V1.Model.Color.PINK;
        }
        return null;
    }
}
