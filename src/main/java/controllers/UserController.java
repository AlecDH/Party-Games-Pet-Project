package controllers;

import io.javalin.config.JavalinConfig;
import services.UserService;

public class UserController {

    static UserService userService = new UserService();

    public static void setRoutes(JavalinConfig config){
        config.routes.get("/", ctx -> ctx.redirect("spilOversigt.html"));
    }
}
