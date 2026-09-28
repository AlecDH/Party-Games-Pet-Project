import configuration.ThymeleafConfig;
import controllers.GameController;
import controllers.UserController;
import factories.GameFactory;
import io.javalin.Javalin;
import io.javalin.http.*;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
	public static void main(String[] args){
		var app = Javalin.create(config -> {
			config.fileRenderer(new JavalinThymeleaf(ThymeleafConfig.templateEngine()));
			UserController.setRoutes(config);
			GameController.setRoutes(config);
			config.staticFiles.add("/public");
		}).start(7070);
	}
}
