package by.popolamov.restourant.model.entity;

public enum OrderStatus {
    COMPLETE(1),

    INPROGRESS(2);

    private final int status;

    OrderStatus(int status){this.status = status;}

//    public int getStatus() {
//        OrderStatus orderStatus
//        return status;
//    }
    public int toInt() {
        int status = 1;
        switch (this) {
            case COMPLETE:
                status = 1;
                break;
            case INPROGRESS:
                status = 2;
                break;
        }
        return status;
    }
}
