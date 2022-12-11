package by.popolamov.restourant.model.entity;

public enum MenuCategory {

    FOOD(1),

    DRINKS(2);

    private final int category;

    @Override
    public String toString() {
        String menuCategory = "Food";
        switch (this) {
            case FOOD:
                menuCategory = "Food";
                break;
            case DRINKS:
                menuCategory = "Drink";
                break;
        }
        return menuCategory;
    }

    public int toInt() {
        int menuCategory = 1;
        switch (this) {
            case FOOD:
                menuCategory = 1;
                break;
            case DRINKS:
                menuCategory = 2;
                break;
        }
        return menuCategory;
    }

    MenuCategory(int category) {
        this.category = category;
    }
}
