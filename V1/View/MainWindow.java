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
    private JPanel pnlSecret;
    private Game game; 
    public MainWindow(Game game){
        super( "MasterMind" ); // ou setTitle("My app")
        this.game = game;
        setSize(400,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        System.out.println(game.get_nb_guess());
        pnlPrincipal = new JPanel(new GridLayout(game.get_nb_guess()+1,1));

        // JPanel pnlCombination = new JPanel(new GridLayout(5, 1));
        // JPanel pnlHint = new JPanel(new GridLayout(5, 1));
        pnlSecret = new JPanel(new GridLayout(1, game.get_combination_size()));

        
        setContentPane(pnlPrincipal);
        pnlPrincipal.add(pnlSecret, BorderLayout.PAGE_END);
        // pnlPrincipal.add(pnlCombination, BorderLayout.CENTER);
        // pnlPrincipal.add(pnlHint, BorderLayout.EAST);   
        
        
        
        setVisible( true );
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
        
        
        for (Combination c : current_round.get_combinations()) {
            pnlPrincipal.add(get_combination_panel(c, game.get_combination_size()));
        }

        repaint();
        revalidate();

    }
    
    public JPanel get_combination_panel(Combination combination, int combination_size){
        JPanel combination_panel = new JPanel(new FlowLayout());
        for (int i = 0; i < combination_size; i++){
            combination_panel.add(get_combination_button(combination.get_color(i)));
        }
        repaint();
        revalidate();
        return combination_panel;
    }

    public JButton get_combination_button(V1.Model.Color color){
        JButton combination_button = new JButton();
        combination_button.setBackground(convert_color(color));
        combination_button.addActionListener(actionEvent -> {
            // set color et faut gerer si c'est la current combi 
            combination_button.setBackground(convert_color(game.get_current_color()));
            System.out.println("button clicked");
        });
        combination_button.setEnabled(true);
    
        return combination_button;
    }

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
}
