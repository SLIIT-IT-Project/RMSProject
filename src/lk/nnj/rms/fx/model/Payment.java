package lk.nnj.rms.fx.model;

import java.time.LocalDateTime;

public class Payment {

    private int PID;
    private LocalDateTime DateTime;
    private float Amount;
    private String Status;
    private String Type;
    private String Description;
    private int OID;

    public Payment(int PID,float amount,LocalDateTime dateTime, String status, String type, String description, int OID) {
        this.PID = PID;
        DateTime = dateTime;
        Amount = amount;
        Status = status;
        Type = type;
        Description = description;
        this.OID = OID;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }

    public float getAmount() {
        return Amount;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getOID() {
        return OID;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "PID='" + PID + '\'' +
                ", Amount=" + Amount +
                ", DateTime=" + DateTime +
                ", Status='" + Status + '\'' +
                ", Type='" + Type + '\'' +
                ", Description='" + Description + '\'' +
                ", OID='" + OID + '\'' +
                '}';
    }
}
