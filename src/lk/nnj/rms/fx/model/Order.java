package lk.nnj.rms.fx.model;

import java.time.LocalDateTime;

public class Order {
    private int oid;
    private LocalDateTime date_tme;
    private String description;
    private String order_type;
    private double order_amount;
    private double service_charge;
    private double tot_amount;
    private int cid;

    public Order(int oid, LocalDateTime date_tme, String description, String order_type, double order_amount, double service_charge, double tot_amount, int cid) {
        this.oid = oid;
        this.date_tme = date_tme;
        this.description = description;
        this.order_type = order_type;
        this.order_amount = order_amount;
        this.service_charge = service_charge;
        this.tot_amount = tot_amount;
        this.cid = cid;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public LocalDateTime getDate_tme() {
        return date_tme;
    }

    public void setDate_tme(LocalDateTime date_tme) {
        this.date_tme = date_tme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String order_type) {
        this.order_type = order_type;
    }

    public double getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(double order_amount) {
        this.order_amount = order_amount;
    }

    public double getService_charge() {
        return service_charge;
    }

    public void setService_charge(double service_charge) {
        this.service_charge = service_charge;
    }

    public double getTot_amount() {
        return tot_amount;
    }

    public void setTot_amount(double tot_amount) {
        this.tot_amount = tot_amount;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid=" + oid +
                ", date_tme=" + date_tme +
                ", description='" + description + '\'' +
                ", order_type='" + order_type + '\'' +
                ", order_amount=" + order_amount +
                ", service_charge=" + service_charge +
                ", tot_amount=" + tot_amount +
                ", cid=" + cid +
                '}';
    }
}