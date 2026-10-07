package controllers;

import entities.Game;
import entities.User;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import services.GameService;
import services.UserService;

public class GameController {
    private GameService gameService;
    private ConnectionPool connectionPool;

    public GameController(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
        gameService = new GameService(connectionPool);
    }

    public void setRoutes(JavalinConfig config){
        config.routes.get("/spilPopup", ctx -> renderPopup(ctx));
        config.routes.get("/spilInfo", ctx -> renderInfo(ctx));
    }

    private void renderPopup(Context ctx) {
        String name = ctx.queryParam("game");
        Game game = GameService.getGame(name);
        ctx.attribute("game", game);
        ctx.render("/spilPopup");
    }

    private void renderInfo(Context ctx) {
        String name = ctx.queryParam("game");
        Game game = GameService.getGame(name);
        System.out.println(game.getName());
        game = GameService.getGameList().getFirst();
        ctx.attribute("game", game);
        ctx.render("/spilLæsMere");
    }
}
