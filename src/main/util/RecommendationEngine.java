package src.main.util;

import src.main.model.Movie;
import src.main.model.Rating;


import java.util.*;
import java.util.stream.Collectors;


public class RecommendationEngine {
    // Recommend movies for a user
    public static List<Movie> recommendMoviesForUser(int userId, int count) {
        Set<Integer> watchedMovies = getWatchedMoviesForUser(userId);
        Map<String, Double> genrePreferences = getUserGenrePreferences(userId);

        // Recommend movies the user hasn't seen, sorted by their genre preferences
        return DataLoader.movies.values().stream()
                .filter(movie -> !watchedMovies.contains(movie.getId()))
                .sorted(Comparator.comparingDouble(movie -> calculateMovieScore((Movie) movie, genrePreferences)).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    // Get movies the user has already watched
    private static Set<Integer> getWatchedMoviesForUser(int userId) {
        return DataLoader.ratings.stream()
                .filter(rating -> rating.getUserId() == userId)
                .map(Rating::getMovieId)
                .collect(Collectors.toSet());
    }

    // Calculate user's genre preferences based on their ratings
    private static Map<String, Double> getUserGenrePreferences(int userId) {
        Map<String, List<Integer>> genreRatings = new HashMap<>();

        // Collect ratings for each genre
        DataLoader.ratings.stream()
                .filter(rating -> rating.getUserId() == userId)
                .forEach(rating -> {
                    Movie movie = DataLoader.movies.get(rating.getMovieId());
                    if (movie != null) {
                        movie.getGenres().forEach(genre -> {
                            genreRatings.putIfAbsent(genre.getName(), new ArrayList<>());
                            genreRatings.get(genre.getName()).add(rating.getRating());
                        });
                    }
                });

        // Calculate average rating per genre
        return genreRatings.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0)));
    }

    // Calculate the score of a movie based on genre preferences
    private static double calculateMovieScore(Movie movie, Map<String, Double> genrePreferences) {
        return movie.getGenres().stream()
                .mapToDouble(genre -> genrePreferences.getOrDefault(genre.getName(), 0.0))
                .average()
                .orElse(0.0);
    }
}
