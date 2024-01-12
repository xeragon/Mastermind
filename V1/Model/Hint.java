package V1.Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Hint {
    private HintState [] hints;
    private ArrayList<Integer> index_color_wrong_place;
    private ArrayList<Integer> index_color_not_exist;

    public Hint(ArrayList<Integer> index_color_wrong_place, ArrayList<Integer> index_color_not_exist, Integer size, DisplayType display_type){
        this.index_color_wrong_place = index_color_wrong_place;
        this.index_color_not_exist = index_color_not_exist;
        this.hints = new HintState[size];
        Arrays.fill(hints, 0,size,HintState.CORRECT);
        
        switch(display_type) {
            case EASY:
                display_easy(size);
                return;
            case CLASSIC:
                display_normal(size);
                return;
            case NUMERIC:
                display_hard(size);
                return;
        }
        return;
    }

    private void display_easy(int size){
        for (Integer x : index_color_not_exist) {
            this.hints[x] = HintState.COLOR_NOT_EXIST;
        }
        for (Integer x : index_color_wrong_place) {
            this.hints[x] = HintState.COLOR_WRONG_PLACE;
        }
    }

    private void display_normal(int size){
        int i = size - (index_color_not_exist.size() + index_color_wrong_place.size());
        for(int k = 0; k < index_color_wrong_place.size(); k++){
            hints[i + k] = HintState.COLOR_WRONG_PLACE;
        }
        i += index_color_wrong_place.size();
        for(int k = 0; k < index_color_not_exist.size(); k++){
            hints[i + k] = HintState.COLOR_NOT_EXIST;
        }
    }

    private void display_hard(int size){
        int i = size - (index_color_not_exist.size() + index_color_wrong_place.size());
        for(int k = 0; k < index_color_wrong_place.size(); k++){
            hints[i + k] = HintState.COLOR_NOT_EXIST;
        }
        i += index_color_wrong_place.size();
        for(int k = 0; k < index_color_not_exist.size(); k++){
            hints[i + k] = HintState.COLOR_NOT_EXIST;
        }
    }



    public String get_hint(int index){
        return this.hints[index].toString();
    }
}
