package persistence;

import entities.Statistic;
import exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class StatisticMapper {
	private ConnectionPool connectionPool;
	private static final Logger logger =
			LoggerFactory.getLogger(StatisticMapper.class);

	public StatisticMapper(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}


	public Statistic getStatisticsByGame(int id) throws DatabaseException {
		Statistic statistic = null;
		String query = """
		SELECT click_count, avg_rating, favorite_count, review_count
		FROM statistics
		WHERE game_id = ?""";

		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					int clickCount = rs.getInt("click_count");
					int avgRating = rs.getInt("avg_rating");
					int favoriteCount = rs.getInt("favorite_count");
					int reviewCount = rs.getInt("review_count");
					statistic = new Statistic(id, clickCount, avgRating, favoriteCount, reviewCount);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Spillets statistikker kunne ikke findes");
		}

		return statistic;
	}

	public void createStatistic(Statistic statistic) throws DatabaseException {
		String query = """
				INSERT INTO statistics (click_count, avg_rating, favorite_count, review_count)
				VALUES ?, ?, ?, ?""";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stm.setInt(1, statistic.getClickCount());
			stm.setInt(2, statistic.getAvgRating());
			stm.setInt(3, statistic.getFavoriteCount());
			stm.setInt(4, statistic.getReviewCount());
			stm.executeUpdate();
			try (ResultSet rs = stm.getGeneratedKeys()) {
				if (rs.next()) {
					statistic.setGameId(rs.getInt("game_id"));
				} else throw new DatabaseException("Statistikken blev ikke gemt");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Statistikken kunne ikke oprettes");
		}
	}

	public void updateStatistic(Statistic statistic) throws DatabaseException {
		String query = "UPDATE statstics" +
				" SET (click_count, avg_rating, favorite_count, review_count) " +
				" VALUES ?, ?, ?, ?" +
				" WHERE game_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, statistic.getClickCount());
			stm.setInt(2, statistic.getAvgRating());
			stm.setInt(3, statistic.getFavoriteCount());
			stm.setInt(4, statistic.getReviewCount());
			stm.setInt(5, statistic.getGameId());
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Ændringer i statistikken blev ikke gemt");
		}
	}

	public void deleteStatistic(Statistic statistic) throws DatabaseException {
		String query = "DELETE FROM statistics" +
				" WHERE game_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, statistic.getGameId());
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Statistikken kunne ikke slettes");
		}
	}
}
