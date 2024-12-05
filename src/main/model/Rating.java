package src.main.model;

public class Rating {
    private int userId;
    private int movieId;
    private int rating;
    private int timestamp;

    public Rating(int userId, int movieId, int rating, int timestamp) {
        this.userId = userId;
        this.movieId = movieId;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public int getUserId() {
        return userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public int getRating() {
        return rating;
    }

    public int getTimestamp() {
        return timestamp;
    }
}