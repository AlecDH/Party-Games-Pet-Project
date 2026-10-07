import configuration.ThymeleafConfig;
import controllers.GameController;
import controllers.UserController;
import factories.GameFactory;
import io.javalin.Javalin;
import io.javalin.http.*;
import io.javalin.rendering.template.JavalinThymeleaf;
import persistence.ConnectionPool;

public class Main {

	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String URL = "jdbc:postgresql://localhost:5432/%s?currentSchema=public";
	private static final String DB = "bodegaspil";

	private static final ConnectionPool connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);

	public static void main(String[] args){
		var app = Javalin.create(config -> {
			config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
			UserController userController = new UserController(connectionPool);
			GameController gameController = new GameController(connectionPool);
			userController.setRoutes(config);
			gameController.setRoutes(config);
			config.staticFiles.add("/public");
		}).start(7070);
	}
}
