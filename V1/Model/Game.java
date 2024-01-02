package V1.Model;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Game {
    private int nb_round;
    private int combination_size;
    private int nb_guess;
    private int nb_color_availaible;

    private Round [] rounds; 
    private int index_current_round = 0 ;

    public Game(int nb_round, int combination_size,int nb_guess,int nb_color_availaible){
        this.nb_round = nb_round;
        this.combination_size = combination_size;
        this.nb_guess = nb_guess;
        this.nb_color_availaible = nb_color_availaible;
        this.rounds = new Round[nb_round];
        Arrays.fill(this.rounds, new Round(nb_guess,combination_size));
        
    }

    public void start(){
        this.rounds[this.index_current_round].display_game();
        this.rounds[this.index_current_round].listen_for_combi();
    }

    public int getCombination_size() {
        return combination_size;
    }
    public int getNb_color_availaible() {
        return nb_color_availaible;
    }
    public int getNb_guess() {
        return nb_guess;
    }
    public int getNb_round() {
        return nb_round;
    }

    public void setCombination_size(int combination_size) {
        this.combination_size = combination_size;
    }
    public void setNb_color_availaible(int nb_color_availaible) {
        this.nb_color_availaible = nb_color_availaible;
    }
    public void setNb_guess(int nb_guess) {
        this.nb_guess = nb_guess;
    }
    public void setNb_round(int nb_round) {
        this.nb_round = nb_round;
    }

    
    


}


