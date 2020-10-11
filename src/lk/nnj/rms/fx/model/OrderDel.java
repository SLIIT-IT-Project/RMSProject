package lk.nnj.rms.fx.model;

import java.time.LocalDateTime;

public class OrderDel {
    private int invoiceNo;
    private LocalDateTime date_time;
    private String description;
    private int tid;
    private String status;
    private String cname;
    private String mobile;
    private String address;

    public OrderDel(int invoiceNo, LocalDateTime date_time, String description, int tid, String status, String cname, String mobile, String address) {
        this.invoiceNo = invoiceNo;
        this.date_time = date_time;
        this.description = description;
        this.tid = tid;
        this.status = status;
        this.cname = cname;
        this.mobile = mobile;
        this.address = address;
    }

    public int getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(int invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public LocalDateTime getDate_time() {
        return date_time;
    }

    public void setDate_time(LocalDateTime date_time) {
        this.date_time = date_time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "OrderDel{" +
                "invoiceNo=" + invoiceNo +
                ", date_time=" + date_time +
                ", description='" + description + '\'' +
                ", tid=" + tid +
                ", status='" + status + '\'' +
                ", cname='" + cname + '\'' +
                ", mobile='" + mobile + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
