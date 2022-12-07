package by.radzionau.imdb.model.dao;

import by.radzionau.imdb.exception.DaoException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;

import java.util.List;
import java.util.Optional;

/**
 * Interface describes the behavior of {@link Movie} entity
 *
 * @author Anton Radzionau
 */
public interface MovieDao {
    /**
     * Add movie. Throws DaoException if writing to data source throws exception.
     *
     * @param movie the movie to add
     * @return the count of updated rows
     * @throws DaoException if writing to data source throws exception
     */
    int add(Movie movie) throws DaoException;

    /**
     * Update movie. Throws DaoException if updating of data source throws exception.
     *
     * @param movie the updated movie
     * @return the count of updated rows
     * @throws DaoException if updating of data source throws exception
     */
    int update(Movie movie) throws DaoException;

    /**
     * Delete movie. Throws DaoException if updating of data source throws exception.
     *
     * @param movie the movie to delete
     * @throws DaoException if updating of data source throws exception
     * @return
     */
    int delete(Movie movie) throws DaoException;

    /**
     * Find movie by id. Throws DaoException if reading of data source throws exception.
     *
     * @param movieId the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Movie> findMovieById(Long movieId) throws DaoException;

    /**
     * Find movies by title. Throws DaoException if reading of data source throws exception.
     *
     * @param title the title
     * @return the list of movies
     * @throws DaoException if reading of data source throws exception
     */
    List<Movie> findMoviesByTitle(String title) throws DaoException;

    /**
     * Find movies by movie type. Throws DaoException if reading of data source throws exception.
     *
     * @param movieType the movie type
     * @return the list of movies
     * @throws DaoException if reading of data source throws exception
     */
    List<Movie> findMoviesByMovieType(MovieType movieType) throws DaoException;

    /**
     * Find movie score by movie id. Throws DaoException if reading of data source throws exception.
     *
     * @param movieId the movie id
     * @return the optional of movie
     * @throws DaoException if reading of data source throws exception
     */
    Optional<Double> findMovieScoreByMovieId(Long movieId) throws DaoException;
}
