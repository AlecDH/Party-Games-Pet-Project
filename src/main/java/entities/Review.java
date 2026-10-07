package entities;

public class Review {
	private int id;
	private int rating;
	private String text;
	private int userId;
	private int gameId;

	public Review(int id, int rating, String text, int userId, int gameId) {
		this.id = id;
		this.text = text;
		this.rating = rating;
		this.userId = userId;
		this.gameId = gameId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
}
