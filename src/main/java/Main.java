import controllers.UserController;
import io.javalin.Javalin;
import io.javalin.http.*;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
	public static void main(String[] args){
		var app = Javalin.create(config -> {
			config.fileRenderer(new JavalinThymeleaf());
			UserController.setRoutes(config);
			config.staticFiles.add("/public");
		}).start(7070);
	}
}
