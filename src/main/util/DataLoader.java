package src.main.util;

import src.main.model.Genre;
import src.main.model.Movie;
import src.main.model.Rating;
import src.main.model.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class DataLoader {
    public static final String GENRE_FILE = "data/genre.data";
    public static final String MOVIE_FILE = "data/movie.data";
    public static final String RATING_FILE = "data/ratings.data";
    public static final String USER_FILE = "data/user.data";

    public static Map<Integer, Genre> genres = new HashMap<>();
    public static Map<Integer, Movie> movies = new HashMap<>();
    public static Map<Integer, User> users = new HashMap<>();
    public static List<Rating> ratings = new ArrayList<>();

    public static void loadAllData() {
        loadGenres();
        loadMovies();
        loadUsers();
        loadRatings();
    }

    private static void loadGenres() {
        try (Stream<String> lines = Files.lines(Paths.get(GENRE_FILE))) {
            lines.map(line -> line.split("\\|"))
                    .forEach(data -> genres.put(Integer.parseInt(data[1]), new Genre(Integer.parseInt(data[1]), data[0])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    static void loadGenresOld() throws FileNotFoundException {
//        String path_with_file_name=GENRE_FILE;
//        File file1=new File(path_with_file_name);//open file
//        Scanner scan1=new Scanner (file1);//scan file
//
//        while(scan1.hasNextLine()){
//            String arr1[]=scan1.nextLine().split("\\|");//split at every |
//
//            if(arr1.length>1){
//                int val=Integer.parseInt(arr1[1]);
//                String str=arr1[0];
//                genres.put(val,str);
//            }
//        }
//    }







    private static void loadMovies() {
        try (Stream<String> lines = Files.lines(Paths.get(MOVIE_FILE))) {
            lines.map(line -> line.split("\\|"))
                    .forEach(data -> {
                        int id = Integer.parseInt(data[0]);
                        Movie movie = new Movie(id, data[1], data[2], data[3], data[4]);
                        for (int i = 5; i < data.length; i++) {
                            if (data[i].equals("1")) {
                                movie.addGenre(genres.get(i - 5));
                            }
                        }
                        movies.put(id, movie);
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadUsers() {
        try (Stream<String> lines = Files.lines(Paths.get(USER_FILE))) {
            lines.map(line -> line.split("\\|"))
                    .forEach(data -> users.put(Integer.parseInt(data[0]),
                            new User(Integer.parseInt(data[0]), Integer.parseInt(data[1]), data[2].charAt(0), data[3], data[4])));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void loadRatings() {
        try (Stream<String> lines = Files.lines(Paths.get(RATING_FILE))) {
            lines.map(line -> line.split("\\t"))
                    .forEach(data -> ratings.add(new Rating(
                            Integer.parseInt(data[0]),
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]))
                    ));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
