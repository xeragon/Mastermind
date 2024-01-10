package V1.Controller;

import V1.View.*;

import java.util.Arrays;

import V1.Model.*;

public class GameController {
    private MainWindow window;
    private Game game = new Game(3, 4, 3, 5);
    
    
    public GameController(){}

    public void start_game(){
        window = new MainWindow(game, this);
        window.display_game();
    }

    public void submit_guess(V1.Model.Color[] colors ){
        Round current_round = game.get_current_round();
        int current_guess_index = game.get_nb_guess_taken();
        current_round.set_combination(new Combination(colors),current_guess_index);
        if(Arrays.compare(current_round.get_secret_combination().colors,current_round.get_combinations()[game.get_nb_guess_taken()].colors) == 0){
            window.display_win();
        }   
    }
}