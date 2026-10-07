package persistence;

public class GameMapper {
	private ConnectionPool connectionPool;

	public GameMapper(ConnectionPool connectionPool){
		this.connectionPool = connectionPool;
	}
}
