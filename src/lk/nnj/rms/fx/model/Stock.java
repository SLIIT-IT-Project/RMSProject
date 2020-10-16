package lk.nnj.rms.fx.model;


import java.util.Date;

public class Stock {

    private int STId;
    private String STName;
    private int Quantity;
    private String Type;
    private Date Date;
    private int TotalCost;

    public Stock(int STId, String STName, int quantity, String type, java.util.Date date, int totalCost) {
        this.STId = STId;
        this.STName = STName;
        Quantity = quantity;
        Type = type;
        Date = date;
        TotalCost = totalCost;
    }

    public int getSTId() {
        return STId;
    }

    public void setSTId(int STId) {
        this.STId = STId;
    }

    public String getSTName() {
        return STName;
    }

    public void setSTName(String STName) {
        this.STName = STName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public java.util.Date getDate() {
        return Date;
    }

    public void setDate(java.util.Date date) {
        Date = date;
    }

    public int getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(int totalCost) {
        TotalCost = totalCost;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "STId=" + STId +
                ", STName='" + STName + '\'' +
                ", Quantity=" + Quantity +
                ", Type='" + Type + '\'' +
                ", Date=" + Date +
                ", TotalCost=" + TotalCost +
                '}';
    }
}
