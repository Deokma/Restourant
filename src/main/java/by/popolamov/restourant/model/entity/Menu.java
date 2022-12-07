package by.popolamov.restourant.model.entity;

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

    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }

    public String getDishName() {
        return dishname;
    }

    public void setDishName(String dishname) {
        this.dishname = dishname;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getCategoryid() {
        return categoryid;
    }

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
     * The Builder for Movie.
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
         * Sets movie id.
         *
         * @param dishId the movie id
         * @return the movie id
         */
        public Builder setDishId(int dishId) {
            menu.setDishid(dishId);
            return this;
        }

        /**
         * Sets title.
         *
         * @param dishName the title
         * @return the title
         */
        public Builder setDishName(String dishName) {
            menu.setDishName(dishName);
            return this;
        }
        /**
         * Sets title.
         *
         * @param price the title
         * @return the title
         */
        public Builder setPrice(int price) {
            menu.setPrice(price);
            return this;
        }

        /**
         * Sets logline.
         *
         * @param image the logline
         * @return the logline
         */
        public Builder setImage(String image) {
            menu.setImage(image);
            return this;
        }

        /**
         * Sets release year.
         *
         * @param categoryId the year
         * @return the release year
         */
        public Builder setCategoryId(int categoryId) {
            menu.setCategoryid(categoryId);
            return this;
        }



        /**
         * Build movie.
         *
         * @return the movie
         */
        public Menu build() {
            return menu;
        }
    }
}
