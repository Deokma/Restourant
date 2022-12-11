package by.popolamov.restourant.model.entity;

/**
 * The Menu entity.
 */
public class Menu {
    private int dishid;
    private String dishname;
    private int price;
    private String image;
    private int categoryid;

    public Menu() {
    }

    public Menu(int dishid, String dishname, int price, String image, int categoryid) {
        this.dishid = dishid;
        this.dishname = dishname;
        this.price = price;
        this.image = image;
        this.categoryid = categoryid;
    }
    /**
     * Gets dish id.
     *
     * @return the dish id
     */
    public int getDishid() {
        return dishid;
    }
    /**
     * Sets dish id.
     *
     * @return the dish id
     */
    public void setDishid(int dishid) {
        this.dishid = dishid;
    }
    /**
     * Gets dish name.
     *
     * @return the dish name
     */
    public String getDishName() {
        return dishname;
    }
    /**
     * Sets dish name.
     *
     * @return the dish name
     */
    public void setDishName(String dishname) {
        this.dishname = dishname;
    }
    /**
     * Gets price.
     *
     * @return the price
     */
    public int getPrice() {
        return price;
    }
    /**
     * Sets price.
     *
     * @return the price
     */
    public void setPrice(int price) {
        this.price = price;
    }
    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }
    /**
     * Sets image.
     *
     * @return the image
     */
    public void setImage(String image) {
        this.image = image;
    }
    /**
     * Gets category id.
     *
     * @return the category id
     */
    public int getCategoryid() {
        return categoryid;
    }
    /**
     * Sets category id.
     *
     * @return the category id
     */
    public void setCategoryid(int categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "dishid=" + dishid +
                ", dishname='" + dishname + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", categoryid=" + categoryid +
                '}';
    }

    /**
     * Gets builder.
     *
     * @return the builder instance
     */
    public static Builder builder() {
        return new Builder();
    }


    /**
     * The Builder for Menu.
     */
    public static class Builder {
        private final Menu menu;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {
            menu = new Menu();
        }

        /**
         * Sets dish id.
         *
         * @param dishId the dish id
         * @return the dish id
         */
        public Builder setDishId(int dishId) {
            menu.setDishid(dishId);
            return this;
        }

        /**
         * Sets dishname.
         *
         * @param dishName the dishname
         * @return the dishname
         */
        public Builder setDishName(String dishName) {
            menu.setDishName(dishName);
            return this;
        }

        /**
         * Sets price.
         *
         * @param price the price
         * @return the price
         */
        public Builder setPrice(int price) {
            menu.setPrice(price);
            return this;
        }

        /**
         * Sets image.
         *
         * @param image the image
         * @return the image
         */
        public Builder setImage(String image) {
            menu.setImage(image);
            return this;
        }

        /**
         * Sets categoryid.
         *
         * @param categoryId the categoryid
         * @return the categoryid
         */
        public Builder setCategoryId(int categoryId) {
            menu.setCategoryid(categoryId);
            return this;
        }


        /**
         * Build menu.
         *
         * @return the menu
         */
        public Menu build() {
            return menu;
        }
    }
}
