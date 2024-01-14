package V1.Model;

import V1.Controller.GameController;

public class Game {
    private Color selected_color = Color.BLUE;

    private GameController game_controller;

    private int nb_round;
    private int combination_size;
    private int nb_guess;
    private int nb_color_availaible;
    private DisplayType display_type;
    private int score;


    private Round [] rounds; 
    private int index_current_round = 0 ;

    public Game(GameController game_controller,int nb_round, int combination_size,int nb_guess,int nb_color_availaible, DisplayType display_type){
        this.game_controller = game_controller;
        this.nb_round = nb_round;
        this.combination_size = combination_size;
        this.nb_guess = nb_guess;
        this.nb_color_availaible = nb_color_availaible;
        this.rounds = new Round[nb_round];
        for (int i = 0; i < nb_round; i++) {
            rounds[i] = new Round(game_controller, nb_guess, combination_size, nb_color_availaible);
        }
        this.display_type = display_type;
        this.score = 0;
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
    public int get_nb_color_available() {
        return nb_color_availaible;
    }
    public int get_nb_guess() {
        return nb_guess;
    }

    public int get_nb_round() {
        return nb_round;
    }
    public DisplayType get_difficulty(){return display_type;}
    public int get_score(){return this.score;}

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
    public void set_difficulty(DisplayType display_type){this.display_type = display_type;}
    public void set_score(int score){this.score = score;}
    public void add_score(int value){this.score += value;}

    
    


}


