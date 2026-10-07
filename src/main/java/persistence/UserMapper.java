package persistence;

import entities.User;
import exceptions.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {
	ConnectionPool connectionPool;
	private static final Logger logger =
			LoggerFactory.getLogger(UserMapper.class);

	public UserMapper(ConnectionPool connectionPool){
		this.connectionPool = connectionPool;
	}

	public List<User> getAllUsers() throws DatabaseException {
		List<User> users = new ArrayList<>();
		String query = "SELECT user_id, username, password FROM users";

		try (Connection connection = connectionPool.getConnection();
			PreparedStatement stm = connection.prepareStatement(query);
			ResultSet rs = stm.executeQuery()){
			while (rs.next()) {
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				User user = new User(id, username, password);
				users.add(user);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter brugeren fejlede");
		}
		return users;
	}

	public User getUserByUsername(String username) throws DatabaseException {
		User user = null;
		String query = "SELECT user_id, username, password " +
				"FROM users" +
				"WHERE username = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, username);
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					int id = rs.getInt("user_id");
					String password = rs.getString("password");
					user = new User(id, username, password);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter brugeren fejlede");
		}
		return user;
	}

	public User getUserByID(int id) throws DatabaseException {
		User user = null;
		String query = "SELECT user_id, username, password " +
				"FROM users" +
				"WHERE user_id = ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, String.valueOf(id));
			try (ResultSet rs = stm.executeQuery()) {
				if (rs.next()) {
					String username = rs.getString("username");
					String password = rs.getString("password");
					user = new User(id, username, password);
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Søgning efter brugeren fejlede");
		}
		return user;
	}

	public void createUser(User user) throws DatabaseException {
		String query = "INSERT INTO users (username, password) " +
				" VALUES ?, ?";
		try (Connection connection = connectionPool.getConnection();
			 PreparedStatement stm = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.executeUpdate();
			try (ResultSet rs = stm.getGeneratedKeys()) {
				if (rs.next()) {
					user.setId(rs.getInt("user_id"));
				} else throw new DatabaseException("Brugeren kunne ikke oprettes");
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Brugeren blev ikke gemt ");
		}
	}

	public void updateUser(User user) throws DatabaseException {
		String query = "UPDATE users" +
				" SET (username, password) " +
				" VALUES ?, ?" +
				" WHERE user_id = ?";
		try (Connection connection = connectionPool.getConnection();
			PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, user.getUsername());
			stm.setString(2, user.getPassword());
			stm.setString(3, String.valueOf(user.getId()));
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Brugerændringerne blev ikke gemt ");
		}
	}

	public void deleteUser(User user) throws DatabaseException {
		String query = "DELETE FROM users" +
				" WHERE user_id = ?";
		try (Connection connection = connectionPool.getConnection();
			PreparedStatement stm = connection.prepareStatement(query)) {
			stm.setString(1, String.valueOf(user.getId()));
			stm.executeQuery();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			throw new DatabaseException("Brugeren blev ikke slettet");
		}
	}
}
