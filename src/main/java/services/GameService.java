package services;

import entities.Game;
import persistence.ConnectionPool;
import persistence.GameMapper;
import persistence.StatisticMapper;

import java.util.ArrayList;
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

    public static List<Game> filterByCategory(List<Game> games, String category) {
        List<Game> filtered = new ArrayList<>();
        for (Game game : games) {
            if (game.getCategories().contains(category)) {
                filtered.add(game);
            }
        }
        return filtered;
    }

    public static List<Game> filterByPlayerCapacity(List<Game> games, int players) {
        List<Game> filtered = new ArrayList<>();
        for (Game game : games) {
            if (game.getMinPlayers() <= players && game.getMaxPlayers() >= players) {
                filtered.add(game);
            }
        }
        return filtered;
    }

    public static List<Game> filterByDuration(List<Game> games, int duration) {
        List<Game> filtered = new ArrayList<>();
        for (Game game : games) {
            if (game.getMinPlayers() <= duration && game.getMaxPlayers() >= duration) {
                filtered.add(game);
            }
        }
        return filtered;
    }
}
