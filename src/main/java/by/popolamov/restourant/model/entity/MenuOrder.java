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
     * The Builder for MenuOrder.
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
         * @param dishId the dish id
         * @return the dish id
         */
        public MenuOrder.Builder setDishId(int dishId) {
            menuOrder.setDishid(dishId);
            return this;
        }

        /**
         * Sets dishname.
         *
         * @param dishName the dishname
         * @return the dishname
         */
        public MenuOrder.Builder setDishName(String dishName) {
            menuOrder.setDishname(dishName);
            return this;
        }

        /**
         * Sets Quantity.
         *
         * @param quantity the quantity
         * @return the quantity
         */
        public MenuOrder.Builder setQuantity(int quantity) {
            menuOrder.setQuantity(quantity);
            return this;
        }

        /**
         * Sets price.
         *
         * @param price the price
         * @return the price
         */
        public MenuOrder.Builder setPrice(int price) {
            menuOrder.setPrice(price);
            return this;
        }

        /**
         * Sets Userid.
         *
         * @param userid the Userid
         * @return the Userid
         */
        public MenuOrder.Builder setUserid(int userid) {
            menuOrder.setUserid(userid);
            return this;
        }

        /**
         * Sets orderid.
         *
         * @param orderId the orderid
         * @return the orderid
         */
        public MenuOrder.Builder setOrderId(int orderId) {
            menuOrder.setOrderid(orderId);
            return this;
        }

        /**
         * Build MenuOrder.
         *
         * @return the menuorder
         */
        public MenuOrder build() {
            return menuOrder;
        }
    }
}
