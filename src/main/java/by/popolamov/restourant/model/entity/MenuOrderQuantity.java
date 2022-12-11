package by.popolamov.restourant.model.entity;

public enum MenuOrderQuantity {
    ONE(1),
    TWO(2);

    private final int quantity;

    MenuOrderQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "MenuOrderQuantity{" +
                "quantity=" + quantity +
                '}';
    }

    public int toInt() {
        int cartQuentity = 1;
        switch (this) {
            case ONE:
                cartQuentity = 1;
                break;
            case TWO:
                cartQuentity = 2;
                break;
        }
        return cartQuentity;
    }
}
