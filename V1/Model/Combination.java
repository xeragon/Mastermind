package V1.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Combination {
    public Color [] colors;
    private Set<Color> used_colors;
    public Combination(Integer combination_size){
        this.colors = new Color[combination_size];
        this.used_colors = new HashSet<>(Arrays.asList(this.colors));
    }

    public Color get_color(int index){
        return colors[index];
    }
    public void set_color(Integer index, Color color){
        this.colors[index] = color;
        
    }

    public Hint compare_to(Combination combination){
        Integer s = this.colors.length;
        ArrayList<Integer> index_color_not_exist = new ArrayList<Integer>();
        ArrayList<Integer> index_color_wrong_place = new ArrayList<Integer>();
        for (int i = 0; i < s; i++) {
            if (this.colors[i] != combination.colors[i]){
                if (!Arrays.asList(combination.colors).contains(this.colors[i])) {
                    index_color_not_exist.add(i);
                }
                else{
                    index_color_wrong_place.add(i);
                }
            }
        }
        return new Hint(index_color_wrong_place,index_color_not_exist,s);
    }   
        
    public void random(){
        int n;
        Random rand = new Random();
        for (int i = 0; i < this.colors.length; i++) {
            n = rand.nextInt(Color.values().length-1);
            this.colors[i] = Color.values()[n];
        }
    }

    public String get_content(){
        String r = "";
        for (Color color : this.colors) {
            r += color.toString() + " "; 
        }

        return r;
    }
}
