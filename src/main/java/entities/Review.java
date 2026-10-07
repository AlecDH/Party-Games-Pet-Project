package entities;

public class Review {
	private int rating;
	private String text;
	private User author;

	public Review(int rating, String text, User author) {
		this.rating = rating;
		this.text = text;
		this.author = author;
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
