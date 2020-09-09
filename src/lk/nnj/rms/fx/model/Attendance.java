package lk.nnj.rms.fx.model;

import java.util.Date;

public class Attendance {
    private String emp_id;
    private String fullname;
    private Date date;
    private int working_h;
    private int ot_h;

    @Override
    public String toString() {
        return "Attendance{" +
                "emp_id='" + emp_id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", date=" + date +
                ", working_h=" + working_h +
                ", ot_h=" + ot_h +
                '}';
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getWorking_h() {
        return working_h;
    }

    public void setWorking_h(int working_h) {
        this.working_h = working_h;
    }

    public int getOt_h() {
        return ot_h;
    }

    public void setOt_h(int ot_h) {
        this.ot_h = ot_h;
    }

    public Attendance(String emp_id, String fullname, Date date, int working_h, int ot_h) {
        this.emp_id = emp_id;
        this.fullname = fullname;
        this.date = date;
        this.working_h = working_h;
        this.ot_h = ot_h;
    }
}
