package persistence;

import entities.Review;
import exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewMapper {
	private ConnectionPool connectionPool;
	private static final Logger logger =
			LoggerFactory.getLogger(ReviewMapper.class);

	public ReviewMapper(ConnectionPool connectionPool) {
		this.connectionPool = connectionPool;
	}

	public List<Review> getReviewsByGame(int id) throws DatabaseException{
		List<Review> reviews = new ArrayList<>();
		String query = """
		SELECT review_id, rating, review_text, user_id
		FROM reviews
		WHERE game_id = ?""";


		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					int reviewId = rs.getInt("review_id");
					int rating = rs.getInt("rating");
					String reviewText = rs.getString("review_text");
					int userId = rs.getInt("user_id");
					reviews.add(new Review(reviewId, rating, reviewText, userId, id));
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter reviews fejlede");
		}

		return reviews;
	}

	public List<Review> getReviewsByUser(int id) throws DatabaseException{
		List<Review> reviews = new ArrayList<>();
		String query = """
		SELECT review_id, rating, review_text, game_id
		FROM reviews
		WHERE user_id = ?""";


		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, id);
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					int reviewId = rs.getInt("review_id");
					int rating = rs.getInt("rating");
					String reviewText = rs.getString("review_text");
					int gameId = rs.getInt("game_id");
					reviews.add(new Review(reviewId, rating, reviewText, id, gameId));
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter reviews fejlede");
		}

		return reviews;
	}

	public void createReview(Review review) throws DatabaseException {
		String query = """
				INSERT INTO reviews (rating, review_text, user_id, game_id)
				VALUES ?, ?, ?, ?""";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stm.setInt(1, review.getRating());
			stm.setString(2, review.getText());
			stm.setInt(3, review.getUserId());
			stm.setInt(4, review.getGameId());
			stm.executeUpdate();
			try (ResultSet rs = stm.getGeneratedKeys()) {
				if (rs.next()) {
					review.setId(rs.getInt("review_id"));
				} else throw new DatabaseException("Reviewet blev ikke gemt");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Reviewet kunne ikke oprettes");
		}
	}

	public void updateReview(Review review) throws DatabaseException {
		String query = "UPDATE reviews" +
				" SET (rating, review_text) " +
				" VALUES ?, ?" +
				" WHERE review_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, review.getRating());
			stm.setString(2, review.getText());
			stm.setInt(3, review.getId());
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Ændringer i reviewet blev ikke gemt");
		}
	}

	public void deleteReview(Review review) throws DatabaseException {
		String query = "DELETE FROM reviews" +
				" WHERE review_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setInt(1, review.getId());
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Reviewet kunne ikke slettes");
		}
	}

}
