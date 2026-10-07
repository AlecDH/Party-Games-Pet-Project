package controllers;

import entities.Game;
import entities.User;
import exceptions.IncorrectLoginInfoException;
import exceptions.PasswordsDontMatchException;
import exceptions.UserAlreadyExistsException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import services.GameService;
import services.UserService;

import java.util.List;

public class UserController {

    private ConnectionPool connectionPool;
    private UserService userService;
    private GameService gameService;

    public UserController(ConnectionPool connectionPool){
        this.connectionPool = connectionPool;
        this.userService = new UserService(connectionPool);
        this.gameService = new GameService(connectionPool);
    }

    public void setRoutes(JavalinConfig config){
        config.routes.get("/", ctx -> renderFrontPage(ctx));
        config.routes.post("/login", ctx -> login(ctx));
        config.routes.get("/registerButton", ctx -> renderRegisterPage(ctx));
        config.routes.post("/register", ctx -> register(ctx));
        config.routes.post("/logout", ctx -> logout(ctx));
    }

    private void renderFrontPage(Context ctx) {
       List<Game> gameList = gameService.getGameList();

        ctx.attribute("gameList", gameList);
        ctx.render("spiloversigt");
    }

    private void login(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");

        User user = userService.login(username, password);
        try {
            if (user == null){
                throw new IncorrectLoginInfoException("Forkert brugernavn/password");
            }
            ctx.sessionAttribute("errorMessage", null);
            ctx.sessionAttribute("loggedInUser", user);
        } catch (IncorrectLoginInfoException e){
            ctx.sessionAttribute("errorMessage", e.getMessage());
        }
        ctx.redirect("/");
    }

    private void logout(Context ctx){
        ctx.sessionAttribute("loggedInUser", null);
        ctx.redirect("/");
    }

    private void renderRegisterPage(Context ctx){
        ctx.attribute("errorMessage", null);
        ctx.render("register");
    }

    private void register(Context ctx){
        String username = ctx.formParam("username");
        String password = ctx.formParam("password");
        String repeatedPassword = ctx.formParam("password-repeat");

        try {
            if (!password.equals(repeatedPassword)){
                throw new PasswordsDontMatchException("Gentagne password matcher ikke");
            }
            if (userService.findUser(username)){
                throw new UserAlreadyExistsException("Det valgte brugernavn er ikke tilgængeligt");
            }
            ctx.sessionAttribute("errorMessage", null);
            User user = userService.createUser(username, password);
            ctx.sessionAttribute("loggedInUser", user);
            ctx.redirect("/");
        } catch (UserAlreadyExistsException | PasswordsDontMatchException e){
            ctx.sessionAttribute("errorMessage", e.getMessage());
            ctx.redirect("/registerButton");
        }
	}
}
