package lk.nnj.rms.fx.model;

public class EmployeeSalaryRate {
    private int id;
    private String EmpType;
    private String BasicSal;
    private String OtRate;
    private String HourRate;

    public EmployeeSalaryRate(int id, String empType, String basicSal, String otRate, String hourRate) {
        this.id=id;
        this.EmpType=empType;
        this.BasicSal=basicSal;
        this.OtRate=otRate;
        this.HourRate=hourRate;

    }




    public int getId() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getEmpType() {
        return EmpType;
    }

    public void setEmpType(String empType) {
        EmpType = empType;
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
    @Override
    public String toString() {
        return "EmployeeSalaryRate{" +
                "id=" + getId() +
                ", EmpType='" + getEmpType() + '\'' +
                ", BasicSal='" + getBasicSal() + '\'' +
                ", OtRate='" + getOtRate() + '\'' +
                ", HourRate='" + getHourRate() + '\'' +
                '}';
    }
}
