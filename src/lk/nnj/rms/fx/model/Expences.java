package lk.nnj.rms.fx.model;

import java.util.Date;

public class Expences {

    private int id;
    private String ExpenType;
    private String Desc;
    private String Value;
    private Date Date;

    public Expences(int id, String expenType, String desc, String value, Date date) {
        this.id = id;
        this.ExpenType = expenType;
        this.Desc = desc;
        this.Value = value;
        this.Date = date;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpenType() {
        return ExpenType;
    }

    public void setExpenType(String expenType) {
        ExpenType = expenType;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date date) {
        Date = date;
    }
    @Override
    public String toString() {
        return "Expences{" +
                "id=" + getId() +
                ", ExpenType='" + getExpenType() + '\'' +
                ", Description='" + getDesc() + '\'' +
                ", Value='" + getValue() + '\'' +
                ", Date='" + getDate() + '\'' +
                '}';
    }
}
