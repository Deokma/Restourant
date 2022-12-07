package by.popolamov.restourant.controller.command;

/**
 * The enum PagePath.
 */
public enum PagePath {
    INDEX_PAGE("index.jsp"),
    MAIN_PAGE("main.jsp"),
    LOGIN_PAGE("login.jsp"),
    SIGNUP_PAGE("signup.jsp"),
    ACCOUNT_PAGE("account.jsp"),
    CART_PAGE("cart.jsp"),
    ORDERS_PAGE("orders.jsp"),
    ORDER_REDIRECT_PAGE("pages/redirect/order_redirect.jsp"),

    ADD_MOVIE_PAGE("pages/admin/add_movie.jsp"),
    ADD_GENRE_PAGE("pages/admin/add_genre.jsp"),
    GET_USERS_PAGE("pages/admin/get_users.jsp"),
    EDIT_MOVIE_PAGE("pages/admin/edit_movie.jsp"),
    GET_CART_PAGE("cart.jsp"),
    ADD_FEEDBACK_PAGE("pages/user/add_feedback.jsp"),
    GET_FEEDBACKS_PAGE("pages/admin/get_feedbacks.jsp"),
    ADD_MOVIE_COVER_PAGE("pages/admin/add_movie_cover.jsp"),

    ERROR_500_PAGE("pages/error/error500.jsp"),
    ERROR_404_PAGE("pages/error/error404.jsp");

    private final String address;

    PagePath(String address) {
        this.address = address;
    }

    /**
     * Gets address.
     *
     * @return the address of page
     */
    public String getAddress() {
        return address;
    }
}

