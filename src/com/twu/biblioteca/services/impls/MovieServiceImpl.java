package com.twu.biblioteca.services.impls;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.Movie;
import com.twu.biblioteca.services.MovieService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services.impls
 * @date: 8/1/20
 * @version: 1.0
 */
public class MovieServiceImpl implements MovieService {
    private Library library;
    public MovieServiceImpl(Library library) {
        this.library = library;
    }
    @Override
    public List<Movie> getAllMovies() {
        return library.getMovies();
    }

    @Override
    public List<Movie> getAllAvailableMovies() {
        return this.getAllMovies().stream().filter(Movie::getState).collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllUnavailableMovies() {
        return null;
    }

    @Override
    public Boolean checkOut(int id) {
        try {
            Movie movie = this.getAllAvailableMovies().stream().filter(item -> item.getId().equals(id)).findFirst().get();
            movie.setState(false);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean returnMovie(int id) {
        return null;
    }
}
