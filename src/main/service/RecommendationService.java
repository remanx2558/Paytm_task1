package src.main.service;

import src.main.util.DataLoader;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import src.main.model.Rating;
import src.main.model.Movie;

public class RecommendationService {
    public List<String> recommendMoviesForUser(int userId) {
        Set<Integer> watchedMovies = DataLoader.ratings.stream()
                .filter(r -> r.getUserId() == userId)
                .map(Rating::getMovieId)
                .collect(Collectors.toSet());

        return DataLoader.movies.values().stream()
                .filter(movie -> !watchedMovies.contains(movie.getId()))
                .sorted(Comparator.comparingDouble(Movie::getAverageRating).reversed())
                .limit(5)
                .map(Movie::getTitle)
                .collect(Collectors.toList());
    }
}
