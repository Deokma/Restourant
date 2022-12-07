package by.popolamov.restourant.model.entity;

public class MenuOrder {
    private int orderid;
    private int dishid;

    private String dishname;
    private int quantity;
    private int userid;

    private int price;

    public MenuOrder() {

    }

    public MenuOrder(int orderid, int dishid, String dishname, int quantity, int userid, int price) {
        this.orderid = orderid;
        this.dishid = dishid;
        this.dishname = dishname;
        this.quantity = quantity;
        this.userid = userid;
        this.price = price;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Gets builder.
     *
     * @return the builder instance
     */
    public static MenuOrder.Builder builder() {
        return new MenuOrder.Builder();
    }


    /**
     * The Builder for Movie.
     */
    public static class Builder {
        private final MenuOrder menuOrder;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            menuOrder = new MenuOrder();
        }

        /**
         * Sets dish id.
         *
         * @param dishId the movie id
         * @return the movie id
         */
        public MenuOrder.Builder setDishId(int dishId) {
            menuOrder.setDishid(dishId);
            return this;
        }

        /**
         * Sets dish id.
         *
         * @param dishName the movie id
         * @return the movie id
         */
        public MenuOrder.Builder setDishName(String dishName) {
            menuOrder.setDishname(dishName);
            return this;
        }

        /**
         * Sets dish id.
         *
         * @param quantity the movie id
         * @return the movie id
         */
        public MenuOrder.Builder setQuantity(int quantity) {
            menuOrder.setQuantity(quantity);
            return this;
        }

        public MenuOrder.Builder setPrice(int price) {
            menuOrder.setPrice(price);
            return this;
        }
        public MenuOrder.Builder setUserid(int userid) {
            menuOrder.setUserid(userid);
            return this;
        }
//        /**
//         * Sets title.
//         *
//         * @param price the title
//         * @return the title
//         */
//        public MenuOrder.Builder setPrice(int price) {
//            menu.setPrice(price);
//            return this;
//        }


        /**
         * Build movie.
         *
         * @return the movie
         */
        public MenuOrder build() {
            return menuOrder;
        }
    }
}
