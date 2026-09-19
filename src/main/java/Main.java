import io.javalin.Javalin;
import io.javalin.http.*;
import io.javalin.rendering.template.JavalinThymeleaf;

public class Main {
	public static void main(String[] args){
		var app = Javalin.create(config -> {
			config.routes.get("/login", ctx -> login(ctx));
			//.. anden konfiguration
			config.fileRenderer(new JavalinThymeleaf());
		}).start(7070);
	}

	public static void login(Context ctx) {
		ctx.redirect("templates/login.html");
	}
}
