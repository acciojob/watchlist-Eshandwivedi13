package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService serviceObj;
    @PostMapping("/add-movie")
    public ResponseEntity<String> addMovie(@RequestBody Movie movie){
        String res = serviceObj.addMovie(movie);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/add-director")
    public ResponseEntity<String> addDirector(@RequestBody Director director){
        String res = serviceObj.addDirector(director);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
    @PutMapping("/add-movie-director-pair")
    public ResponseEntity<String> addMovieDirectorPair(@RequestBody String movieName, @RequestBody String directorName){
        String res = serviceObj.addMovieDirectorPair(movieName, directorName);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/get-movie-by-name/{name}")
    public ResponseEntity<Movie> getMovieByName(@PathVariable String movieName){
        Movie res = serviceObj.getMovieByName(movieName);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/get-director-by-name/{name}")
    public ResponseEntity<Director> getDirectorByName(@PathVariable String directorName){
        Director res = serviceObj.getDirectorByName(directorName);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }


    @GetMapping("/get-movies-by-director-name/{director}")
    public  ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable String directorName){
        List<String> movies = serviceObj.getMoviesByDirectorName(directorName);
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-movies")
    public ResponseEntity<List<String>> findAllMovies(){
        List<String> movies = serviceObj.findAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity<String> deleteDirectorByName(@RequestParam String directorName){
        String res = serviceObj.deleteDirectorByName(directorName);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @DeleteMapping("/movies/delete-all-directors")
    public ResponseEntity<String> deleteAllDirectors(){
        String res = serviceObj.deleteAllDirectors();
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
