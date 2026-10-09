package services;

import entities.Game;
import persistence.ConnectionPool;
import persistence.GameMapper;
import persistence.StatisticMapper;

import java.util.List;

public class GameService {

    private ConnectionPool connectionPool;
    private GameMapper gameMapper;
    private StatisticMapper statisticMapper;
    private List<Game> gameList;

    public GameService(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
        gameMapper = new GameMapper(connectionPool);
        statisticMapper = new StatisticMapper(connectionPool);
        //gameList = gameFactory.createGames(9);
    }

    public static List<Game> getGameList(){
        return /*gamelist*/ null;
    }

    public static Game getGame(String name) {
        for (Game game : getGameList()) {
            System.out.println(game.getName());
            if (game.getName().equals(name)) {
                return game;
            }
        }
        System.out.println("Returning null");
        return null;
    }
}
