package V1.Model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

import V1.Controller.GameController;

public class Game {
    private Color selected_color = Color.BLUE;

    private GameController game_controller;

    private int nb_round;
    private int combination_size;
    private int nb_guess;
    private int nb_color_availaible;
    private int difficulty;             // pour le choix de la difficulté des Hints : 0 facile, 1 normal, 2 difficile.


    private Round [] rounds; 
    private int index_current_round = 0 ;

    public Game(GameController game_controller,int nb_round, int combination_size,int nb_guess,int nb_color_availaible){
        this.game_controller = game_controller;
        this.nb_round = nb_round;
        this.combination_size = combination_size;
        this.nb_guess = nb_guess;
        this.nb_color_availaible = nb_color_availaible;
        this.rounds = new Round[nb_round];
        for (int i = 0; i < nb_round; i++) {
            rounds[i] = new Round(game_controller, nb_guess, combination_size, nb_color_availaible);
        }
        this.difficulty = 0;
    }

    // public void start(){
    //     this.rounds[this.index_current_round].display_game();
    //     this.rounds[this.index_current_round].listen_for_combi();
    // }
    public Color get_current_color(){
        return selected_color;
    }
    public Round get_current_round(){
        return this.rounds[this.index_current_round];
    }
    public int get_current_round_index(){
        return this.index_current_round;
    }
    public void increment_index_current_round(){
        this.index_current_round++;
    }
    public int get_combination_size() {
        return combination_size;
    }
    public int get_nb_color_availaible() {
        return nb_color_availaible;
    }
    public int get_nb_guess() {
        return nb_guess;
    }

    public int get_nb_round() {
        return nb_round;
    }
    public int get_difficulty(){return difficulty;}

    public void set_combination_size(int combination_size) {
        this.combination_size = combination_size;
    }
    public void set_nb_color_availaible(int nb_color_availaible) {
        this.nb_color_availaible = nb_color_availaible;
    }
    public void set_nb_guess(int nb_guess) {
        this.nb_guess = nb_guess;
    }
    public void set_nb_round(int nb_round) {
        this.nb_round = nb_round;
    }
    public void set_difficulty(int difficulty){this.difficulty = difficulty;}

    
    


}


