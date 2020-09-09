package lk.nnj.rms.fx.model;

public class Supplier {

    private int SId;
    private String SName;
    private String Email;
    private String phoneNo;
    private String company;
    private String address;
    private String discreption;

    public Supplier(int SId, String SName, String email, String phoneNo, String company, String address, String discreption) {
        this.SId = SId;
        this.SName = SName;
        Email = email;
        this.phoneNo = phoneNo;
        this.company = company;
        this.address = address;
        this.discreption = discreption;
    }

    public  int getSId()
    {
        return SId;
    }

    public void setSId(int SId)

    {
        this.SId = SId;
    }

    public  String getSName()

    {
        return SName;
    }

    public void setSName(String SName)

    {
        this.SName = SName;
    }

    public  String getEmail()

    {
        return Email;
    }

    public void setEmail(String email)

    {
        Email = email;
    }

    public  String getPhoneNo()

    {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo)

    {
        this.phoneNo = phoneNo;
    }

    public  String getCompany()

    {
        return company;
    }

    public void setCompany(String company)

    {
        this.company = company;
    }

    public  String getAddress()

    {
        return address;
    }

    public void setAddress(String address)

    {
        this.address = address;
    }

    public  String getDiscreption()

    {
        return discreption;
    }

    public void setDiscreption(String discreption)
    {
        this.discreption = discreption;
    }



    @Override
    public String toString() {
        return "Supplier1{"+
                "SId"+ SId +
                ", Name"+ SName + '\'' +
                ", Email='" + Email + '\'' +
                ",phoneNo =" + phoneNo +
                ",company = " + company + '\'' +
                ",address =" + address + '\'' +
                ",discreption =" + discreption + '\'' +
                '}';


    }
}
