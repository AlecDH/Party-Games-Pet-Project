package persistence;

import entities.Game;
import exceptions.DatabaseException;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameMapperTest {

	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String URL =
			"jdbc:postgresql://localhost:5432/%s?currentSchema=test";
	private static final String DB = "bodegaspil";

	private static ConnectionPool connectionPool;
	private static GameMapper gameMapper;

	@BeforeAll
	static void setUpClass() {
		connectionPool = ConnectionPool.getInstance(USER, PASSWORD, URL, DB);
		gameMapper = new GameMapper(connectionPool);

		try (Connection connection = connectionPool.getConnection();
			Statement stmt = connection.createStatement()) {

			stmt.execute("CREATE SCHEMA IF NOT EXISTS test");

			// Drop tables
			stmt.execute("DROP TABLE IF EXISTS test.categories");
			stmt.execute("DROP TABLE IF EXISTS test.games");
			stmt.execute("DROP TABLE IF EXISTS test.games_categories");
			stmt.execute("DROP TABLE IF EXISTS test.games_materials");
			stmt.execute("DROP TABLE IF EXISTS test.games_users");
			stmt.execute("DROP TABLE IF EXISTS test.materials");
			stmt.execute("DROP TABLE IF EXISTS test.reviews");
			stmt.execute("DROP TABLE IF EXISTS test.rules");
			stmt.execute("DROP TABLE IF EXISTS test.statistics");
			stmt.execute("DROP TABLE IF EXISTS test.users");

			// Drop sequences
			stmt.execute("DROP SEQUENCE IF EXISTS test.categories_category_id_seq");
			stmt.execute("DROP SEQUENCE IF EXISTS test.games_game_id_seq");
			stmt.execute("DROP SEQUENCE IF EXISTS test.materials_material_id_seq");
			stmt.execute("DROP SEQUENCE IF EXISTS test.reviews_review_id_seq");
			stmt.execute("DROP SEQUENCE IF EXISTS test.rules_rule_id_seq");
			stmt.execute("DROP SEQUENCE IF EXISTS test.users_user_id_seq");

			// Create tables
			stmt.execute("""
            CREATE TABLE test.categories AS
            SELECT * FROM public.categories
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.games AS
            SELECT * FROM public.games
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.games_categories AS
            SELECT * FROM public.games_categories
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.games_materials AS
            SELECT * FROM public.games_materials
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.games_users AS
            SELECT * FROM public.games_users
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.materials AS
            SELECT * FROM public.materials
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.reviews AS
            SELECT * FROM public.reviews
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.rules AS
            SELECT * FROM public.rules
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.statistics AS
            SELECT * FROM public.statistics
            WITH NO DATA
            """);

			stmt.execute("""
            CREATE TABLE test.users AS
            SELECT * FROM public.users
            WITH NO DATA
            """);

			// Create sequences


			stmt.execute("CREATE SEQUENCE test.categories_category_id_seq");
			stmt.execute("""
            ALTER TABLE test.categories
            ALTER COLUMN category_id
            SET DEFAULT nextval('test.categories_category_id_seq')
            """);

			stmt.execute("CREATE SEQUENCE test.games_game_id_seq");
			stmt.execute("""
            ALTER TABLE test.games
            ALTER COLUMN game_id
            SET DEFAULT nextval('test.games_game_id_seq')
            """);

			stmt.execute("CREATE SEQUENCE test.materials_material_id_seq");
			stmt.execute("""
            ALTER TABLE test.materials
            ALTER COLUMN material_id
            SET DEFAULT nextval('test.materials_material_id_seq')
            """);

			stmt.execute("CREATE SEQUENCE test.reviews_review_id_seq");
			stmt.execute("""
            ALTER TABLE test.reviews
            ALTER COLUMN review_id
            SET DEFAULT nextval('test.reviews_review_id_seq')
            """);

			stmt.execute("CREATE SEQUENCE test.rules_rule_id_seq");
			stmt.execute("""
            ALTER TABLE test.rules
            ALTER COLUMN rule_id
            SET DEFAULT nextval('test.rules_rule_id_seq')
            """);

			stmt.execute("CREATE SEQUENCE test.users_user_id_seq");
			stmt.execute("""
            ALTER TABLE test.users
            ALTER COLUMN user_id
            SET DEFAULT nextval('test.users_user_id_seq')
            """);



		} catch (SQLException e) {
			fail("Database setup failed: " + e.getMessage());
		}
	}

	@BeforeEach
	void setUp() {
		try (Connection connection = connectionPool.getConnection();
			 Statement stmt = connection.createStatement()) {

			// Tøm tabellerne
			stmt.execute("DELETE FROM test.categories");
			stmt.execute("DELETE FROM test.games");
			stmt.execute("DELETE FROM test.games_categories");
			stmt.execute("DELETE FROM test.games_materials");
			stmt.execute("DELETE FROM test.games_users");
			stmt.execute("DELETE FROM test.materials");
			stmt.execute("DELETE FROM test.reviews");
			stmt.execute("DELETE FROM test.rules");
			stmt.execute("DELETE FROM test.statistics");
			stmt.execute("DELETE FROM test.users");


			// Nulstil sequences
			stmt.execute("ALTER SEQUENCE test.categories_category_id_seq RESTART WITH 1");
			stmt.execute("ALTER SEQUENCE test.games_game_id_seq RESTART WITH 1");
			stmt.execute("ALTER SEQUENCE test.materials_material_id_seq RESTART WITH 1");
			stmt.execute("ALTER SEQUENCE test.reviews_review_id_seq RESTART WITH 1");
			stmt.execute("ALTER SEQUENCE test.rules_rule_id_seq RESTART WITH 1");
			stmt.execute("ALTER SEQUENCE test.users_user_id_seq RESTART WITH 1");

			// Kategorier
			stmt.execute("""
            INSERT INTO test.categories (name) VALUES
            ('Strategi'),
            ('Held')
            """);

			// Spil
			stmt.execute("""
            INSERT INTO test.games (name, min_players, max_players, min_duration, max_duration, intro_text) VALUES
            ('Spil1', 2, 8, '10 minutes', '20 minutes', 'Spil1 introtekst'),
            ('Spil2', 1, 5, '20 minutes', '30 minutes', 'Spil2 introtekst'),
            ('Spil3', 3, 4, '30 minutes', '40 minutes', 'Spil3 introtekst')
            """);

			// Materialer
			stmt.execute("""
            INSERT INTO test.materials (name) VALUES
            ('Kort'),
            ('Terninger'),
            ('Papir og blyant')
            """);

			// Anmeldelser
			stmt.execute("""
            INSERT INTO test.reviews (rating, review_text, user_id, game_id) VALUES
            (3, 'review1 tekst', 1, 1),
            (4, 'review2 tekst', 2, 2)
            """);

			// Regler
			stmt.execute("""
            INSERT INTO test.rules (game_id, rules_text, is_drinking) VALUES
            (1, 'Spil1 drikkeregler', true),
            (1, 'Spil1 regler', false),
            (2, 'Spil2 regler', false),
            (3, 'Spil3 drikkeregler', true)
            """);

			// Brugere
			stmt.execute("""
			INSERT INTO test.users (username, password) VALUES 
			('user1', 'password1'),
			('user2', 'password2'),
			('user3', 'password3')
			""");

		} catch (SQLException e) {
			fail("Test data setup failed: " + e.getMessage());
		}
	}

	@Test
	void testConnection() throws SQLException {
		assertNotNull(ConnectionPool.getConnection());
	}

	@Test
	void getAllGames() throws DatabaseException {
		List<Game> games = gameMapper.getAllGames();
		assertEquals(3, games.size());
		assertEquals("Spil1", games.get(0).getName());
		assertEquals(1, games.get(1).getMinPlayers());
		assertEquals("00:10:00", games.get(0).getMinDuration());
	}

}