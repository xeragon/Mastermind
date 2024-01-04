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
    public MainWindow(){
        super( "MasterMind" ); // ou setTitle("My app")
        setSize( 400, 500 );
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pnlPrincipal = new JPanel(new BorderLayout());
        // JPanel pnlCombination = new JPanel(new GridLayout(5, 1));
        // JPanel pnlHint = new JPanel(new GridLayout(5, 1));
        pnlSecret = new JPanel(new GridLayout(1, 5));

        
        setContentPane(pnlPrincipal);
        pnlPrincipal.add(pnlSecret, BorderLayout.PAGE_END);
        // pnlPrincipal.add(pnlCombination, BorderLayout.CENTER);
        // pnlPrincipal.add(pnlHint, BorderLayout.EAST);
        pnlPrincipal.add(new JPanel(), BorderLayout.CENTER);    
        
        
        
        setVisible( true );
    }
    
    private void display_menu(){
        
    }
    
    public void display_game(Game game){
        Round current_round = game.get_current_round();
        // afficher combi secrete :
        Component[] components = get_combination_panel(current_round.get_secret_combination(), game.get_combination_size()).getComponents();
        for (Component component : components) {
            pnlSecret.add(component);
        }
        repaint();



        /*pnlSecret.add(get_combination_panel(current_round.get_secret_combination(), game.get_combination_size()));
        for (Combination c : current_round.get_combinations()) {
            pnlSecret.add(get_combination_panel(c, game.get_combination_size()));
        }*/
    }
    
    public JPanel get_combination_panel(Combination combination, int combination_size){
        JPanel combination_panel = new JPanel(new FlowLayout());
        for (int i = 0; i < combination_size; i++){
            combination_panel.add(get_combination_button(combination.get_color(i)));
        }
        repaint();
        return combination_panel;
    }

    public JButton get_combination_button(V1.Model.Color color){
        JButton combination_button = new JButton();
        combination_button.setBackground(convert_color(color));
        combination_button.addActionListener(actionEvent -> {
            // set color et faut gerer si c'est la current combi 
            System.out.println("button clicked");
        });
        combination_button.setEnabled(false);
    
        return combination_button;
    }

    public Color convert_color(V1.Model.Color color){
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
                return Color.pink;
        }
        return Color.BLACK;
    }



}
