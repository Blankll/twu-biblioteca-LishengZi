package com.twu.biblioteca.services.impls;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.Movie;
import com.twu.biblioteca.services.MovieService;

import java.util.List;

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
        return this.getAllMovies();
    }

    @Override
    public List<Movie> getAllUnavailableMovies() {
        return null;
    }

    @Override
    public Boolean checkOut(int id) {
        return null;
    }

    @Override
    public Boolean returnMovie(int id) {
        return null;
    }
}
