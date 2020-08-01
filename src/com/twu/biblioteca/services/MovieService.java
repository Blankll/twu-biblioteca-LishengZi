package com.twu.biblioteca.services;

import com.twu.biblioteca.entities.Movie;

import java.util.List;

/**
 * @author: Blank
 * @description: com.twu.biblioteca.services
 * @date: 8/1/20
 * @version: 1.0
 */
public interface MovieService {
    List<Movie> getAllMovies();
    List<Movie> getAllAvailableMovies();
    List<Movie> getAllUnavailableMovies();
    Boolean checkOut(int id);
    Boolean returnMovie(int id);
}
