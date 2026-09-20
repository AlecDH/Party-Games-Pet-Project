package services;

import entities.Game;
import factories.GameFactory;

import java.util.List;

public class GameService {

    List<Game> gameList;

    public GameService(){
        GameFactory gameFactory = new GameFactory();
        gameList = gameFactory.createGames(9);
    }

    public List<Game> getGameList(){
        return this.gameList;
    }
}
