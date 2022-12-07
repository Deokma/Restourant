package by.popolamov.restourant.model.service.impl;

import by.popolamov.restourant.exception.DaoException;
import by.popolamov.restourant.exception.ServiceException;
//import by.popolamov.restourant.model.dao.MovieDao;
//import by.popolamov.restourant.model.dao.impl.GenreDaoImpl;
//import by.popolamov.restourant.model.dao.impl.MovieDaoImpl;
import by.popolamov.restourant.model.entity.Menu;
import by.popolamov.restourant.model.entity.MenuCategory;
import by.popolamov.restourant.model.service.MenuService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The implementation of MovieService interface.
 */
public class MovieServiceImpl implements MenuCategory {
    private static final Logger logger = LogManager.getLogger(MovieServiceImpl.class);
    private final MovieDao movieDao = MovieDaoImpl.getInstance();
    private final GenreDao genreDao = GenreDaoImpl.getInstance();

    private MovieServiceImpl() {

    }

    private static final class MovieServiceInstanceHolder {
        private static final MovieServiceImpl INSTANCE = new MovieServiceImpl();
    }

    /**
     * Gets instance of movie service.
     *
     * @return the instance of movie service
     */
    public static MovieService getInstance() {
        return MovieServiceInstanceHolder.INSTANCE;
    }

    @Override
    public void addMovie(Movie movie) throws ServiceException {
        if (!MovieValidator.getInstance().isValid(movie)) {
            logger.error("Invalid movie");
            throw new ServiceException("Invalid movie");
        }
        try {
            movieDao.add(movie);
        } catch (DaoException e) {
            logger.error("Can't handle addMovie request at MovieService", e);
            throw new ServiceException("Can't handle addMovie request at MovieService", e);
        }
    }

    @Override
    public void update(Movie movie) throws ServiceException {
        if (!MovieValidator.getInstance().isValid(movie)) {
            logger.error("Invalid movie");
            throw new ServiceException("Invalid movie");
        }
        try {
            movieDao.update(movie);
        } catch (DaoException e) {
            logger.error("Can't handle update request at MovieService", e);
            throw new ServiceException("Can't handle update request at MovieService", e);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws ServiceException {
        if (!MovieValidator.getInstance().isValid(movie)) {
            logger.error("Invalid movie");
            throw new ServiceException("Invalid movie");
        }
        try {
            movieDao.delete(movie);
        } catch (DaoException e) {
            logger.error("Can't handle deleteMovie request at MovieService", e);
            throw new ServiceException("Can't handle deleteMovie request at MovieService", e);
        }
    }

    @Override
    public Movie findMovieById(Long movieId) throws ServiceException {
        if (movieId == null) {
            logger.error("MovieId doesn't present");
            throw new ServiceException("MovieId doesn't present");
        }
        try {
            Optional<Movie> optionalMovie = movieDao.findMovieById(movieId);
            if (optionalMovie.isPresent()) {
                return optionalMovie.get();
            } else {
                logger.error("Movie with id={} not found", movieId);
                throw new ServiceException("Movie with id=" + movieId + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle getMovieById request at MovieService", e);
            throw new ServiceException("Can't handle getMovieById request at MovieService", e);
        }
    }

    @Override
    public List<Movie> findMoviesByTitle(String title) throws ServiceException {
        if (title == null || title.isEmpty()) {
            logger.error("Title doesn't present");
            throw new ServiceException("Title doesn't present");
        }
        List<Movie> movies;
        try {
            movies = movieDao.findMoviesByTitle(title);
        } catch (DaoException e) {
            logger.error("Can't handle findMoviesByTitle request at MovieService", e);
            throw new ServiceException("Can't handle findMoviesByTitle request at MovieService", e);
        }
        return movies;
    }

    @Override
    public List<Movie> findMoviesByMovieType(MovieType movieType) throws ServiceException {
        if (movieType == null) {
            logger.error("MovieType doesn't present");
            throw new ServiceException("MovieType doesn't present");
        }
        List<Movie> movies;
        try {
            movies = movieDao.findMoviesByMovieType(movieType);
        } catch (DaoException e) {
            logger.error("Can't handle findMoviesByMovieType request at MovieService", e);
            throw new ServiceException("Can't handle findMoviesByMovieType request at MovieService", e);
        }
        return movies;
    }

    @Override
    public Double findMovieScoreByMovieId(Long movieId) throws ServiceException {
        if (movieId == null) {
            logger.error("MovieId doesn't present");
            throw new ServiceException("MovieId doesn't present");
        }
        try {
            Optional<Double> optionalScore = movieDao.findMovieScoreByMovieId(movieId);
            if (optionalScore.isPresent()) {
                return optionalScore.get();
            } else {
                logger.error("Movie with id={} not found", movieId);
                throw new ServiceException("Movie with id=" + movieId + " not found");
            }
        } catch (DaoException e) {
            logger.error("Can't handle findMovieScoreByMovieId request at MovieService", e);
            throw new ServiceException("Can't handle findMovieScoreByMovieId request at MovieService", e);
        }
    }

    @Override
    public Double findMovieScore(Movie movie) throws ServiceException {
        if (!MovieValidator.getInstance().isValid(movie)) {
            logger.error("Invalid movie");
            throw new ServiceException("Invalid movie");
        }
        return findMovieScoreByMovieId(movie.getMovieId());
    }
}
