package entities;

public class Statistic {

    private int gameId;
    private int clickCount;
    private int avgRating;
    private int favoriteCount;
    private int reviewCount;

    public Statistic(int gameId, int clickCount, int avgRating, int favoriteCount, int reviewCount) {
        this.gameId = gameId;
        this.clickCount = clickCount;
        this.avgRating = avgRating;
        this.favoriteCount = favoriteCount;
        this.reviewCount = reviewCount;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }
}
