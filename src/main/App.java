package src.main;

import src.main.model.Movie;
import src.main.service.RecommendationService;
import src.main.util.DataLoader;
import src.main.util.MovieHelper;
import src.main.util.RecommendationEngine;

public class App {
    public static void main(String[] args) {
        // Load data
        DataLoader.loadAllData();

        // Calculate average ratings for all movies
        MovieHelper.calculateAverageRatings();

        // Recommend movies for a specific user
        System.out.println("Recommended Movies for User 1:");
        RecommendationEngine.recommendMoviesForUser(1, 5).forEach(movie ->
                System.out.println(movie.getTitle() + " (Average Rating: " + movie.getAverageRating() + ")")
        );

        // Get the most watched movie
        Movie mostWatched = MovieHelper.getMostWatchedMovie();
        System.out.println("Most Watched Movie: " + mostWatched.getTitle());
    }
}