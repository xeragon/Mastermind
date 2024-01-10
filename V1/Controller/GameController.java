package V1.Controller;

import V1.View.*;

import java.util.Arrays;

import V1.Model.*;

public class GameController {
    private MainWindow window;
    private Game game = new Game(this,3, 2, 3, 5);
    
    
    public GameController(){}

    public void start_game(){
        window = new MainWindow(game, this);
        window.display_game();
    }

    public boolean submit_guess(Combination combination, Hint hint){
        Round current_round = game.get_current_round();
        int current_guess_index = game.get_current_round().get_nb_guess_taken();
        current_round.set_combination(combination,current_guess_index);
        current_round.set_hints(hint, current_guess_index);
        if(Arrays.compare(current_round.get_secret_combination().colors,current_round.get_combinations()[current_round.get_nb_guess_taken()].colors) == 0){
            window.display_win();
            return true; // if win return true so display knows he won 
            
        }   
        current_round.increment_nb_guess_taken();
        return false;
    }

    public void end_round(){
        if(game.get_current_round_index()+1 == game.get_nb_round()){
            System.out.println("end of game");
        }
        else{
            game.increment_index_current_round();
            display_round();
        }
    }
    public void display_round(){
        window.display_game();
    }   

}