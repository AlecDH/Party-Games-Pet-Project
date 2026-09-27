package services;

import entities.Game;
import factories.GameFactory;

import java.util.List;

public class GameService {

    private static List<Game> gameList;

    public GameService(){
        GameFactory gameFactory = new GameFactory();
        gameList = gameFactory.createGames(9);
    }

    public static List<Game> getGameList(){
        return gameList;
    }

    public static Game getGame(String name) {
        for (Game game : getGameList()) {
            if (game.getName().equals(name)) {
                return game;
            }
        }
        return null;
    }
}
