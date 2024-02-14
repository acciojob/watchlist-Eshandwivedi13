package com.driver;

import com.driver.model.Director;
import com.driver.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository repoObj;
    public String addMovie(Movie movie){
        HashMap<String, Movie> movieDb = repoObj.getMovieDb();
        movieDb.put(movie.getName(), movie);
        repoObj.setMovieDb(movieDb);
        return "success";
    }
    public String addDirector(@RequestBody Director director){
        HashMap<String, Director> directorDb = repoObj.getDirectorDb();
        directorDb.put(director.getName(), director);
        repoObj.setDirectorDb(directorDb);
        return "success";
    }
    public String addMovieDirectorPair(String movieName, String directorName){
        HashMap<String, List<String>> movieDirectorPairDb = repoObj.getMovieDirectorPairDb();
        HashMap<String, Director> directorDb = repoObj.getDirectorDb();
        List<String> temp = movieDirectorPairDb.getOrDefault(directorName, new ArrayList<>());
        temp.add(movieName);
        Director director = directorDb.get(directorName);
        director.setNumberOfMovies(director.getNumberOfMovies() + 1);
        directorDb.put(directorName, director);
        movieDirectorPairDb.put(directorName, temp);
        repoObj.setMovieDirectorPairDb(movieDirectorPairDb);
        repoObj.setDirectorDb(directorDb);
        return "success";
    }


    public Movie getMovieByName(String movieName){
        HashMap<String, Movie> movieDb = repoObj.getMovieDb();
        return movieDb.getOrDefault(movieName, null);
    }

    public Director getDirectorByName(String directorName){
        HashMap<String, Director> directorDb = repoObj.getDirectorDb();
        return directorDb.getOrDefault(directorName, null);
    }

    public List<String> getMoviesByDirectorName(String directorName){
       HashMap<String, List<String>> movieDirectorPairDb = repoObj.getMovieDirectorPairDb();
        return movieDirectorPairDb.getOrDefault(directorName, new ArrayList<>());
    }

    public List<String> findAllMovies(){
        List<String> movies = new ArrayList<>();
        HashMap<String, Movie> movieDb = repoObj.getMovieDb();
        for(String movieName : movieDb.keySet()){
            movies.add(movieName);
        }
        return  movies;
    }
    public String deleteDirectorByName(String directorName){
        HashMap<String, Movie> movieDb = repoObj.getMovieDb();
        HashMap<String, Director> directorDb = repoObj.getDirectorDb();
        HashMap<String, List<String>> movieDirectorPairDb = repoObj.getMovieDirectorPairDb();

        if(!directorDb.containsKey(directorName)){
            return "success";
        }
        List<String> moviesToDelete = movieDirectorPairDb.get(directorName);
        directorDb.remove(directorName);
        movieDirectorPairDb.remove(directorName);
        for(String movie : moviesToDelete){
            if(movieDb.containsKey(movie)) movieDb.remove(movie);
        }
        repoObj.setMovieDb(movieDb);
        repoObj.setDirectorDb(directorDb);
        repoObj.setMovieDirectorPairDb(movieDirectorPairDb);
        return "success";
    }


    public String deleteAllDirectors(){
        HashMap<String, Movie> movieDb = repoObj.getMovieDb();
        HashMap<String, Director> directorDb = repoObj.getDirectorDb();
        HashMap<String, List<String>> movieDirectorPairDb = repoObj.getMovieDirectorPairDb();

        List<String> moviesToDelete = new ArrayList<>();
        for(String director : movieDirectorPairDb.keySet()){
            moviesToDelete.addAll(movieDirectorPairDb.get(director));
        }
        directorDb.clear();
        movieDirectorPairDb.clear();
        for(String movie : moviesToDelete){
            if(movieDb.containsKey(movie)) movieDb.remove(movie);
        }
        repoObj.setMovieDb(movieDb);
        repoObj.setDirectorDb(directorDb);
        repoObj.setMovieDirectorPairDb(movieDirectorPairDb);

        return "success";
    }



}
