package controllers;

import entities.Game;
import entities.User;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import services.GameService;
import services.UserService;

import java.util.List;

public class UserController {

    static UserService userService = new UserService();
    static GameService gameService = new GameService();

    public static void setRoutes(JavalinConfig config){
        config.routes.get("/", ctx -> renderFrontPage(ctx));
        config.routes.post("/login", ctx -> login(ctx));
    }

    private static void renderFrontPage(Context ctx) {
        List<Game> gameList = gameService.getGameList();

        ctx.attribute("gameList", gameList);
        ctx.render("templates/spilOversigt.html");
    }

    private static void login(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        User user = userService.login(username, password);

        if (user != null){
            ctx.attribute("loggedInUser", user);
        }
        renderFrontPage(ctx);
    }
}
