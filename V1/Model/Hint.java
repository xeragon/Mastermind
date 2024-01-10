package V1.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Hint {
    public HintState [] hints;

    public Hint(ArrayList<Integer> index_color_wrong_place, ArrayList<Integer> index_color_not_exist, Integer size){
        this.hints = new HintState[size];
        Arrays.fill(hints, 0,size,HintState.CORRECT);
        for (Integer x : index_color_not_exist) {
            this.hints[x] = HintState.COLOR_NOT_EXIST;
        }
        for (Integer x : index_color_wrong_place) {
            this.hints[x] = HintState.COLOR_WRONG_PLACE;
        }
    }

    public String get_display_hint(){
        String r = "" ;
        for (HintState hintState : this.hints) {
            r += hintState.toString() + " ";
        }

        return r; 

    }

    public String get_hint(int index){
        return this.hints[index].toString();
    }
}
