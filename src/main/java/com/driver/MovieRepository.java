package com.driver;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    private HashMap<String, Movie> movieDb;
    private HashMap<String, Director> directorDb;
    private  HashMap<String, List<String>> movieDirectorPairDb;
    public MovieRepository() {
        this.movieDb = new HashMap<>();
        this.directorDb = new HashMap<>();
        this.movieDirectorPairDb = new HashMap<>();
    }

    public HashMap<String, Director> getDirectorDb() {
        return directorDb;
    }

    public HashMap<String, List<String>> getMovieDirectorPairDb() {
        return movieDirectorPairDb;
    }

    public void setMovieDirectorPairDb(HashMap<String, List<String>> movieDirectorPairDb) {
        this.movieDirectorPairDb = movieDirectorPairDb;
    }

    public void setDirectorDb(HashMap<String, Director> directorDb) {
        this.directorDb = directorDb;
    }

    public HashMap<String, Movie> getMovieDb() {
        return movieDb;
    }

    public void setMovieDb(HashMap<String, Movie> movieDb) {
        this.movieDb = movieDb;
    }
}
