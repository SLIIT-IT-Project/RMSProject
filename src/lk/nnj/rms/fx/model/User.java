package lk.nnj.rms.fx.model;

import java.util.Date;

public class User {
    private String emp_id;
    private String fullname;
    private String type;
    private Date dob;
    private String address;
    private int mobile;
    private Date jsd;
    private String pwd;



    public User(String emp_id, String fullname, Date dob, String address, int mobile, Date jsd, String pwd, String type) {
        this.emp_id = emp_id;
        this.fullname = fullname;
        this.dob = dob;
        this.address = address;
        this.mobile = mobile;
        this.jsd = jsd;
        this.pwd = pwd;
        this.type = type;
    }

    public User(String emp_id, String fullname, String dob, String address, String mobile, String jsd, String pwd, String type) {
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public Date getJsd() {
        return jsd;
    }

    public void setJsd(Date jsd) {
        this.jsd = jsd;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" +
                "emp_id='" + emp_id + '\'' +
                ", fullname='" + fullname + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                ", mobile=" + mobile +
                ", jsd=" + jsd +
                ", pwd='" + pwd + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
