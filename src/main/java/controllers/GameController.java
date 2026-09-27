package controllers;

import entities.Game;
import entities.User;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import services.GameService;
import services.UserService;

public class GameController {
    static GameService gameService = new GameService();

    public static void setRoutes(JavalinConfig config){
        config.routes.get("/spilPopup", ctx -> renderPopup(ctx));
        config.routes.get("/spilInfo", ctx -> renderInfo(ctx));
    }

    private static void renderPopup(Context ctx) {
        String name = ctx.queryParam("game");
        Game game = GameService.getGame(name);
        ctx.attribute("game", game);
        ctx.render("/spilPopup.html");
    }

    private static void renderInfo(Context ctx) {
        String name = ctx.queryParam("game");
        Game game = GameService.getGame(name);
        ctx.attribute("game", game);
        ctx.render("/spilLæsMere.html");
    }
}
