package factories;

import entities.Game;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class GameFactory {

    public GameFactory(){

    }

    public List<Game> createGames(int amount){
        List<Game> gameList = new ArrayList<>();

        for (int i = 0; i<amount; i++){
            gameList.add(new Game(""+i,i));
        }

        return gameList;
    }
}
