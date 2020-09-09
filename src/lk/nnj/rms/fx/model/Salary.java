package lk.nnj.rms.fx.model;

import java.util.*;

public class Salary {

    private String EmpID;
    private Date SalDate;
    private String BasicSal;
    private String OtRate;
    private String HourRate;
    private String TotSal;

    public Salary(String id,Date salDate, String basicSal, String otRate, String hourRate, String totSal) {
        this.EmpID=id;
        this.SalDate=salDate;
        this.BasicSal=basicSal;
        this.OtRate=otRate;
        this.HourRate=hourRate;
        this.TotSal=totSal;
    }



    public String getEmpID() {
        return EmpID;
    }

    public void setEmpID(String empID) {
        EmpID = empID;
    }

    public Date getSalDate() {
        return SalDate;
    }

    public void setSalDate(Date salDate) {
        SalDate = salDate;
    }

    public String getBasicSal() {
        return BasicSal;
    }

    public void setBasicSal(String basicSal) {
        BasicSal = basicSal;
    }

    public String getOtRate() {
        return OtRate;
    }

    public void setOtRate(String otRate) {
        OtRate = otRate;
    }

    public String getHourRate() {
        return HourRate;
    }

    public void setHourRate(String hourRate) {
        HourRate = hourRate;
    }

    public String getTotSal() {
        return TotSal;
    }

    public void setTotSal(String totSal) {
        TotSal = totSal;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + getEmpID() +
                ", salDate='" + getSalDate() + '\'' +
                ", BasicSal='" + getBasicSal() + '\'' +
                ", OtRate='" + getOtRate() + '\'' +
                ", HourRate='" + getHourRate() + '\'' +
                ", totSal='" + getTotSal() + '\'' +
                '}';
    }
}
