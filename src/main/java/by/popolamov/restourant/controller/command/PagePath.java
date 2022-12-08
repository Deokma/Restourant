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
    CART_REDIRECT_PAGE("pages/redirect/cart_redirect.jsp"),

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

