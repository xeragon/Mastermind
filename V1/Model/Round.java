package V1.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Round {

    private Combination [] combinations;
    private Hint [] hints;
    private Combination secret_combination;
    private Integer nb_guess;
    private Integer index_current_guess = 0;
    private Integer combination_size ;
    public Round(int nb_guess, int combination_size){
        this.combination_size = combination_size;
        this.hints = new Hint[nb_guess];
        this.combinations = new Combination[nb_guess];
        Arrays.fill(combinations, new Combination(combination_size));
        this.secret_combination = new Combination(combination_size);
        this.secret_combination.random();

    }

    public void display_game(){
        System.out.println("the secret combination is " + secret_combination.get_content());
    }
    public void listen_for_combi(){
        Scanner s = new Scanner(System.in);
        System.out.println("submit a choice");
        for (int i = 0; i < combination_size; i++) {
            Color toSet = Color.valueOf(s.next().toUpperCase());
            combinations[index_current_guess].setColor(i,toSet);
        }
        hints[index_current_guess] = combinations[index_current_guess].compareTo(secret_combination);
        System.out.println("your hint is : " + hints[index_current_guess].get_display_hint());
        index_current_guess++;
        // s.close();
    }

    
}