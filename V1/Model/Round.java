package V1.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import V1.Controller.GameController;

public class Round {
    
    private GameController game_controller;
    private Combination [] combinations;
    private Hint [] hints;
    private Combination secret_combination;
    private int nb_guess;
    private Integer combination_size ;
    private int nb_guess_taken;

    public Round(GameController game_controller,int nb_guess, int combination_size,int nb_colors_availaible){
        this.game_controller = game_controller;
        this.combination_size = combination_size;
        this.nb_guess_taken = 0;
        this.nb_guess = nb_guess;
        this.hints = new Hint[nb_guess];
        this.combinations = new Combination[nb_guess];
        Arrays.fill(combinations, new Combination(combination_size));
        this.secret_combination = new Combination(combination_size);
        this.secret_combination.random(nb_colors_availaible);
        }


    public String get_display_secret_combi(){
        String r = "" ;
        for (Color c : this.secret_combination.colors) {
            r += c.toString() + " ";
        }

        return r; 

    }
    public boolean can_continue_guess(){

        boolean r = false;
        if(nb_guess_taken < nb_guess){
            r = true;
        }
        return r;
    }

    public int get_nb_guess_taken() {
        return nb_guess_taken;
    }
    public void increment_nb_guess_taken(){
        nb_guess_taken++;
    }
    public Combination get_secret_combination(){
        return secret_combination;
    }
    public Combination [] get_combinations(){
        return combinations;
    }
    public void set_combination(Combination combination, int index){
        this.combinations[index] = combination;
    }
    public void set_hints(Hint hint, int index){this.hints[index] = hint;}


    

    
}