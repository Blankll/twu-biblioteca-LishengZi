package com.twu.biblioteca;

import com.twu.biblioteca.entities.Library;
import com.twu.biblioteca.entities.Movie;
import com.twu.biblioteca.services.MovieService;
import com.twu.biblioteca.services.impls.MovieServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author: Blank
 * @description: com.twu.biblioteca
 * @date: 8/1/20
 * @version: 1.0
 */
@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {
    @Mock
    private Library library;
    private MovieService movieService;
    List<Movie> movies;
    @Before
    public void prepareInjection() {
        movies = new ArrayList<>();
        movies.add(new Movie(1, "MOVIE A"));
        movies.add(new Movie(2, "MOVIE B"));
        when(library.getMovies()).thenReturn(movies);
        movieService = new MovieServiceImpl(library);
    }

    @Test
    public void shouldReturnAllMoviesWhenGetAllMovies() {
        List<Movie> res = movieService.getAllMovies();
        assertThat(res, equalTo(movies));
    }
    @Test
    public void shouldReturnAllAvailableMoviesWhenGetAllAvailableMovies() {
        List<Movie> res = movieService.getAllAvailableMovies();
        assertThat(res, equalTo(movies));
    }
    @Test
    public void givenIdWhenCustomerCheckoutMovieThenReturnTrue() {
        Boolean res = movieService.checkOut(1);
        assertThat(res, equalTo(true));
    }
}
