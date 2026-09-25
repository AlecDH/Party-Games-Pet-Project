package controllers;

import entities.Game;
import entities.User;
import exceptions.UserAlreadyExistsException;
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
        config.routes.post("/register", ctx -> register(ctx));
        config.routes.post("/logout", ctx -> logout(ctx));
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
            ctx.sessionAttribute("loggedInUser", user);
        }
        ctx.redirect("/");
    }

    private static void logout(Context ctx){
        ctx.sessionAttribute("loggedInUser", null);
        ctx.redirect("/");
    }

    private static void register(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String repeatedPassword = ctx.formParam("password-repeat");

        try {
            // Tjek først om password og repeated password er ens og kast evt. en "passwords don't match exception"
            //--
            //--

            if (userService.findUser(username)){
                throw new UserAlreadyExistsException("Det valgte brugernavn er ikke tilgængeligt");
            }
            User user = userService.createUser(username, password);
            ctx.sessionAttribute("loggedInUser", user);
            ctx.redirect("/");
        } catch (UserAlreadyExistsException e){
            // Tror måske register.html bør være en template som opdateres med error-message her
            ctx.attribute("error-message", e.getMessage());
            // Her ville jeg så kunne redirecte tilbage til /register
            ctx.result(e.getMessage());
        }
    }
}
