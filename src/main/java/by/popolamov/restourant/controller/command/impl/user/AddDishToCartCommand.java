package by.radzionau.imdb.controller.command.impl.user;

import by.radzionau.imdb.controller.command.*;
import by.radzionau.imdb.controller.command.RequestUtil;
import by.radzionau.imdb.exception.ServiceException;
import by.radzionau.imdb.model.entity.*;
import by.radzionau.imdb.model.service.FeedbackService;
import by.radzionau.imdb.model.service.GenreService;
import by.radzionau.imdb.model.service.MovieService;
import by.radzionau.imdb.model.service.impl.FeedbackServiceImpl;
import by.radzionau.imdb.model.service.impl.GenreServiceImpl;
import by.radzionau.imdb.model.service.impl.MovieServiceImpl;
import by.radzionau.imdb.util.ImageInputStreamUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The class AddFeedbackCommand.
 */
public class AddFeedbackCommand implements Command {
    private static final Logger logger = LogManager.getLogger(AddFeedbackCommand.class);
    private static final FeedbackService feedbackService = FeedbackServiceImpl.getInstance();
    private static final MovieService movieService = MovieServiceImpl.getInstance();
    private static final GenreService genreService = GenreServiceImpl.getInstance();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        RequestUtil requestUtil = RequestUtil.getInstance();
        try {
            Long movieId = requestUtil.getParameterAsLong(request, RequestParameter.MOVIE_ID);
            int score = requestUtil.getParameterAsInt(request, RequestParameter.FEEDBACK_SCORE);
            String content = requestUtil.getParameterAsString(request, RequestParameter.FEEDBACK_CONTENT);
            User user = (User) request.getSession().getAttribute(SessionAttribute.USER);
            Feedback feedback = buildFeedback(score, content, movieId, user);
            feedbackService.addFeedback(feedback);
            Movie movie = movieService.findMovieById(movieId);
            List<Genre> genresList = genreService.findGenresOfMovieByMovieId(movieId);
            Double movieScore = movieService.findMovieScore(movie);
            request.setAttribute(RequestAttribute.MOVIE_SCORE, movieScore);
            request.setAttribute(RequestAttribute.MOVIE, movie);
            request.setAttribute(RequestAttribute.MOVIE_COVER, ImageInputStreamUtil.getInstance().addDescriptionToCoverImage(movie.getCover()));
            request.setAttribute(RequestAttribute.GENRES_LIST, genresList);
            router = new Router(PagePath.GET_MOVIE_PAGE.getAddress(), Router.RouterType.FORWARD);
        } catch (ServiceException e) {
            logger.error("Error at AddFeedbackCommand", e);
            String pageTo = getPageFrom(request);
            router = new Router(pageTo, Router.RouterType.REDIRECT);
        }
        return router;
    }

    private Feedback buildFeedback(int score, String content, Long movieId, User user) {
        return Feedback.builder()
                .setFeedbackDate(LocalDateTime.now())
                .setScore(score)
                .setContent(content)
                .setMovieId(movieId)
                .setUserId(user.getUserId())
                .setFeedbackStatus(content.isEmpty() ? FeedbackStatus.APPROVED : FeedbackStatus.UNDER_CONSIDERATION)
                .build();
    }
}
