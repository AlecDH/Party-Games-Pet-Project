package controllers;

import entities.Game;
import exceptions.DatabaseException;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import persistence.ConnectionPool;
import persistence.GameMapper;
import services.GameService;

import java.util.Map;

public class GameController {
	private GameService gameService;
	private GameMapper gameMapper;
	private ConnectionPool connectionPool;

	public GameController(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
		gameService = new GameService(connectionPool);
		gameMapper = new GameMapper(connectionPool);
	}

	public void setRoutes(JavalinConfig config) {
		config.routes.get("/spilPopup", ctx -> renderPopup(ctx));
		config.routes.get("/games/{id}/info", ctx -> renderInfo(ctx));
		config.routes.get("/games/{id}", ctx -> gameToJson(ctx));
	}

	private void renderPopup(Context ctx) {
		String name = ctx.queryParam("game");
		try {
			Game game = gameService.getGameByName(name);
			ctx.attribute("game", game);
		} catch (DatabaseException e) {
			ctx.sessionAttribute("errorMessage", e.getMessage());
		}
		ctx.render("/spilPopup");
	}

	private void renderInfo(Context ctx) {
		int id = Integer.parseInt(ctx.pathParam("id"));
		try {
			Game game = gameMapper.getGameByID(id);
			ctx.attribute("game", game);
			ctx.render("/spilLæsMere", Map.of("game", game));
		} catch (DatabaseException e) {
			ctx.sessionAttribute("errorMessage", e.getMessage());
		}
	}

	private void gameToJson(Context ctx) {
		try {
			int id = Integer.parseInt(ctx.pathParam("id"));
			Game game = gameMapper.getGameByID(id);
            if (game == null) {
                ctx.status(404);
                return;
            }
            ctx.json(game);
		} catch (DatabaseException e) {
			ctx.sessionAttribute("errorMessage", e.getMessage());

            // Temporary
            ctx.result(e.getMessage());
		}
	}
}
