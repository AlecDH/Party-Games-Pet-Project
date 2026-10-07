package persistence;

import entities.Game;
import exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameMapper {
	private ConnectionPool connectionPool;
	private static final Logger logger =
			LoggerFactory.getLogger(GameMapper.class);

	public GameMapper(ConnectionPool connectionPool){
		this.connectionPool = connectionPool;

	}

	public List<Game> getAllGames() throws DatabaseException {
		List<Game> games = new ArrayList<>();
		String query = "SELECT game_id, name, min_players, max_players, min_duration, max_duration, intro_text" +
				" FROM games";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query);
			 ResultSet rs = stm.executeQuery()){
			while (rs.next()) {
				int id = rs.getInt("game_id");
				String name = rs.getString("name");
				int minPlayers = rs.getInt("min_players");
				int maxPlayers = rs.getInt("max_players");
				int minDuration = rs.getInt("min_duration");
				int maxDuration = rs.getInt("max_duration");
				String intro = rs.getString("intro_text");
				Game game = new Game(id, name, minPlayers, maxPlayers, minDuration, maxDuration, intro);
				games.add(game);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter spil fejlede");
		}
		return games;
	}

	public Game getGameByID(int id) throws DatabaseException {
		Game game = null;
		String query = "SELECT game_id, name, min_players, max_players, min_duration, max_duration, intro_text" +
				" FROM games" +
				" WHERE game_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, String.valueOf(id));
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					String name = rs.getString("name");
					int minPlayers = rs.getInt("min_players");
					int maxPlayers = rs.getInt("max_players");
					int minDuration = rs.getInt("min_duration");
					int maxDuration = rs.getInt("max_duration");
					String intro = rs.getString("intro_text");
					game = new Game(id, name, minPlayers, maxPlayers, minDuration, maxDuration, intro);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter brugeren fejlede");
		}
		return game;
	}

	public void createGame(Game game) throws DatabaseException {
		String query = "INSERT INTO games (name, min_players, max_players, min_duration, max_duration, intro_text)" +
				" VALUES ?, ?, ?, ?, ?, ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stm.setString(1, game.getName());
			stm.setInt(2, game.getMinPlayers());
			stm.setInt(3, game.getMaxPlayers());
			stm.setInt(4, game.getMinDuration());
			stm.setInt(5, game.getMaxDuration());
			stm.setString(6, game.getIntroText());
			stm.executeUpdate();
			try (ResultSet rs = stm.getGeneratedKeys()) {
				if (rs.next()) {
					game.setID(rs.getInt("game_id"));
				} else throw new DatabaseException("Spillet kunne ikke oprettes");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Brugeren blev ikke gemt ");
		}
	}

	public void updateGame(Game game) throws DatabaseException {
		String query = "UPDATE games" +
				" SET (name, min_players, max_players, min_duration, max_duration, intro_text) " +
				" VALUES ?, ?, ?, ?, ?, ?" +
				" WHERE game_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, game.getName());
			stm.setString(2, String.valueOf(game.getMinPlayers()));
			stm.setString(3, String.valueOf(game.getMaxPlayers()));
			stm.setString(4, String.valueOf(game.getMinDuration()));
			stm.setString(5, String.valueOf(game.getMaxDuration()));
			stm.setString(6, game.getIntroText());
			stm.setString(7, String.valueOf(game.getID()));
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Spilændringerne blev ikke gemt ");
		}
	}

	public void deleteGame(Game game) throws DatabaseException {
		String query = "DELETE FROM games" +
				" WHERE game_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, String.valueOf(game.getID()));
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Spillet blev ikke slettet");
		}
	}

	/*
	- getRulesByGame
	 */


}
