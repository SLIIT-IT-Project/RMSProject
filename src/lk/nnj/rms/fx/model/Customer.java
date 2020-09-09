package lk.nnj.rms.fx.model;

public class Customer {


    private int customer_id;
    private String cname;
    private String mobile;
    private String address;
    private int no_of_orders;


    public Customer(int customer_id, String cname, String mobile, String address, int no_of_orders) {
        this.customer_id = customer_id;
        this.cname = cname;
        this.mobile = mobile;
        this.address = address;
        this.no_of_orders = no_of_orders;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNo_of_orders() {
        return no_of_orders;
    }

    public void setNo_of_orders(int no_of_orders) {
        this.no_of_orders = no_of_orders;
    }




}
