package by.radzionau.imdb.controller.command.impl.moveto.general;

import by.radzionau.imdb.controller.command.Command;
import by.radzionau.imdb.controller.command.PagePath;
import by.radzionau.imdb.controller.command.RequestAttribute;
import by.radzionau.imdb.controller.command.Router;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.Movie;
import by.radzionau.imdb.model.entity.MovieType;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class MoveToMainPageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(MoveToMainPageCommand.class);
    private static final MovieService movieService = MovieServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        try {
            List<Movie> movieList = new ArrayList<>();
            movieList.addAll(movieService.findMoviesByMovieType(MovieType.FILM));
            movieList.addAll(movieService.findMoviesByMovieType(MovieType.SERIAL));
            List<String> movieCoversList = new ArrayList<>();
            List<Double> movieRatingList = new ArrayList<>();
            for (Movie movie : movieList) {
                movieCoversList.add(ImageInputStreamUtil.getInstance().addDescriptionToCoverImage(movie.getCover()));
                movieRatingList.add(movieService.findMovieScore(movie));
            }
            request.setAttribute(RequestAttribute.MOVIES_LIST, movieList);
            request.setAttribute(RequestAttribute.MOVIE_COVERS_LIST, movieCoversList);
            request.setAttribute(RequestAttribute.MOVIE_RATING_LIST, movieRatingList);
        } catch (ServiceException e) {
            logger.error("Error at MoveToMainPageCommand", e);
        }
        return new Router(PagePath.MAIN_PAGE.getAddress(), Router.RouterType.FORWARD);
    }
}
