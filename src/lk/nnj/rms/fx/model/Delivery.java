package lk.nnj.rms.fx.model;

import java.sql.Timestamp;

public class Delivery {
    private int track_id;
    private Timestamp del_date_time;
    private String status;
    private String note;
    private String delivered_by;
    private int Order_id;

    public Delivery(int track_id, Timestamp del_date_time, String status, String note, String delivered_by, int order_id) {
        this.track_id = track_id;
        this.del_date_time = del_date_time;
        this.status = status;
        this.note = note;
        this.delivered_by = delivered_by;
        this.Order_id = order_id;
    }

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }

    public Timestamp getDel_date_time() {
        return del_date_time;
    }

    public void setDel_date_time(Timestamp del_date_time) {
        this.del_date_time = del_date_time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDelivered_by() {
        return delivered_by;
    }

    public void setDelivered_by(String delivered_by) {
        this.delivered_by = delivered_by;
    }

    public int getOrder_id() {
        return Order_id;
    }

    public void setOrder_id(int order_id) {
        Order_id = order_id;
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "track_id=" + track_id +
                ", del_date_time=" + del_date_time +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
                ", delivered_by='" + delivered_by + '\'' +
                ", Order_id=" + Order_id +
                '}';
    }
}






