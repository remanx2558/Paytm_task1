package src.main.util;

import src.main.model.Movie;
import src.main.model.Rating;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MovieHelper {
    // Get movies sorted by average rating
    public static List<Movie> getMoviesSortedByRating() {
        return DataLoader.movies.values().stream()
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .collect(Collectors.toList());
    }

    // Get movies by genre
    public static List<Movie> getMoviesByGenre(String genre) {
        return DataLoader.movies.values().stream()
                .filter(movie -> movie.getGenres().stream()
                        .anyMatch(g -> g.getName().equalsIgnoreCase(genre)))
                .collect(Collectors.toList());
    }

    // Calculate average rating for all movies
    public static void calculateAverageRatings() {
        DataLoader.ratings.forEach(rating -> {
            Movie movie = DataLoader.movies.get(rating.getMovieId());
            if (movie != null) {
                movie.addRating(rating.getRating());
            }
        });
    }

    // Get the most watched movie
    public static Movie getMostWatchedMovie() {
        return DataLoader.ratings.stream()
                .collect(Collectors.groupingBy(Rating::getMovieId, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(entry -> DataLoader.movies.get(entry.getKey()))
                .orElse(null);
    }

    // Get most popular genres by watch count
    public static Map<String, Long> getPopularGenres() {
        return DataLoader.movies.values().stream()
                .flatMap(movie -> movie.getGenres().stream())
                .collect(Collectors.groupingBy(genre -> genre.getName(), Collectors.counting()));
    }
}
