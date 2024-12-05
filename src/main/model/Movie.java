package src.main.model;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String title;
    private String releaseDate;
    private String videoReleaseDate;
    private String url;
    private List<Genre> genres = new ArrayList<>();
    private double totalRating = 0;
    private int ratingCount = 0;

    public Movie(int id, String title, String releaseDate, String videoReleaseDate, String url) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.videoReleaseDate = videoReleaseDate;
        this.url = url;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void addRating(int rating) {
        totalRating += rating;
        ratingCount++;
    }

    public double getAverageRating() {
        return ratingCount > 0 ? totalRating / ratingCount : 0;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public List<Genre> getGenres() {
        return genres;
    }
}
