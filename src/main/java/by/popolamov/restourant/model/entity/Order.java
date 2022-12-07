package by.popolamov.restourant.model.entity;

public class Order {
    public int orderid;
    public int userid;
    public int totalsum;
    public int status;

    public Order() {
    }

    public Order(int orderid, int userid, int totalsum, int status) {
        this.orderid = orderid;
        this.userid = userid;
        this.totalsum = totalsum;
        this.status = status;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getTotalsum() {
        return totalsum;
    }

    public void setTotalsum(int totalsum) {
        this.totalsum = totalsum;
    }

    public int getStatus() {
        return status;
    }
    public String getStatusString(){
        String status_string = null;
        switch (status) {
            case 1:
                status_string = "COMPLETE";
                break;

            case 2:
                status_string = "INPROGRESS";
                break;

        }
        return status_string;
    }
    public String getStatusCOMPLETE(){
        String status_string = null;
        switch (status) {
            case 1:
                status_string = "COMPLETE";
                break;
            default:
                status_string = null;
        }
        return status_string;
    }
    public String getStatusINPROGRESS(){
        String status_string = null;
        switch (status) {
            case 2:
                status_string = "INPROGRESS";
                break;
            default:
                status_string = null;
        }
        return status_string;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static Order.Builder builder() {
        return new Order.Builder();
    }

    public static class Builder {
        private final Order order;

        public Builder(){order = new Order();}

        public Builder setOrderid(int orderid){
            order.setOrderid(orderid);
            return this;
        }

        public Builder setUserid(int userid){
            order.setUserid(userid);
            return this;
        }

        public Builder setTotalsum(int totalsum){
            order.setTotalsum(totalsum);
            return this;
        }

        public Builder setStatus(int status){
            order.setStatus(status);
            return this;
        }

        public Order build() {
            return order;
        }
    }
}
