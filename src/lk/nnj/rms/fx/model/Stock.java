package lk.nnj.rms.fx.model;


import java.util.Date;

public class Stock {

    private int STId;
    private String STName;
    private int Quantity;
    private String Type;
    private Date Date_Time;
    private int TotalCost;

    public Stock(int STId, String STName, int quantity, String type, Date date_Time, int totalCost) {
        this.STId = STId;
        this.STName = STName;
        Quantity = quantity;
        Type = type;
        Date_Time = date_Time;
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

    public Date getDate_Time() {
        return Date_Time;
    }

    public void setDate_Time(Date date_Time) {
        Date_Time = date_Time;
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
                ", Date_Time=" + Date_Time +
                ", TotalCost=" + TotalCost +
                '}';
    }
}
