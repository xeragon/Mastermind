package V1.Controller;

import V1.View.*;
import V1.Model.*;

public class GameController {
    public MainWindow window = new MainWindow();
    
    
    public GameController(){}

    public void start_game(){
        Game game = new Game(3, 5, 5, 5);
        window.display_game(game);
    }
}