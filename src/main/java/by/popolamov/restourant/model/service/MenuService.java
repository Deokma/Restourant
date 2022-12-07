package by.radzionau.imdb.model.service;

import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;

import java.util.List;

/**
 * The interface MovieService provides methods to manage movies.
 */
public interface MovieService {
    /**
     * Add movie. Throws ServiceException if movie is null or if writing to data source throws the exception.
     *
     * @param movie the movie to add
     * @throws ServiceException if movie is null or if writing to data source throws the exception
     */
    void addMovie(Movie movie) throws ServiceException;

    /**
     * Update movie. Throws ServiceException if movie is null or if writing to data source throws the exception.
     *
     * @param movie the updated movie
     * @throws ServiceException if movie is null or if writing to data source throws the exception
     */
    void update(Movie movie) throws ServiceException;

    /**
     * Delete movie. Throws ServiceException if movie is null or if updating of data source throws the exception.
     *
     * @param movie the movie to delete
     * @throws ServiceException if movie is null or if updating of data source throws the exception
     */
    void deleteMovie(Movie movie) throws ServiceException;

    /**
     * Find movie by movie id. Throws ServiceException if movie id is null or if reading from data source throws the exception.
     *
     * @param movieId the movie id
     * @return the movie
     * @throws ServiceException if movie id is null or if reading from data source throws the exception
     */
    Movie findMovieById(Long movieId) throws ServiceException;

    /**
     * Find movies by title (search by part of title). Throws ServiceException if title is null or empty or if reading from data source throws the exception.
     *
     * @param title the title
     * @return the list of movies
     * @throws ServiceException if title is null or empty or if reading from data source throws the exception
     */
    List<Movie> findMoviesByTitle(String title) throws ServiceException;

    /**
     * Find movies by movie type. Throws ServiceException if movie type is null or if reading from data source throws the exception.
     *
     * @param movieType the movie type
     * @return the list of movies
     * @throws ServiceException if movie type is null or if reading from data source throws the exception
     */
    List<Movie> findMoviesByMovieType(MovieType movieType) throws ServiceException;

    /**
     * Find movie score by movie id. Throws ServiceException if movie id is null or if movie does not exist or if reading from data source throws the exception.
     *
     * @param movieId the movie id
     * @return the movie score
     * @throws ServiceException if movie id is null or if movie does nor exist or if reading from data source throws the exception
     */
    Double findMovieScoreByMovieId(Long movieId) throws ServiceException;

    /**
     * Find movie score of movie. Throws ServiceException if movie is null or if movie does not exist or if reading from data source throws the exception.
     *
     * @param movie the movie
     * @return the score of movie
     * @throws ServiceException if movie is null or if movie does not exist or if reading from data source throws the exception
     */
    Double findMovieScore(Movie movie) throws ServiceException;
}
