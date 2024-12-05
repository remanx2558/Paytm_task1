package src.main.service;

import src.main.model.Movie;
import src.main.util.DataLoader;

import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    public List<Movie> getTopRatedMovies(int count) {
        return DataLoader.movies.values().stream()
                .sorted((m1, m2) -> Double.compare(m2.getAverageRating(), m1.getAverageRating()))
                .limit(count)
                .collect(Collectors.toList());
    }

    public Movie getMovieById(int id) {
        return DataLoader.movies.get(id);
    }
}

