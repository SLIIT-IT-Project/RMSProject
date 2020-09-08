package lk.nnj.rms.fx.model;

public class Item {
    private String item_id;
    private String item_name;
    private String description;
    private double unit_price;

    public Item(String item_id, String item_name, String description, double unit_price) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.description = description;
        this.unit_price = unit_price;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "item_id='" + item_id + '\'' +
                ", item_name='" + item_name + '\'' +
                ", description='" + description + '\'' +
                ", unit_price=" + unit_price +
                '}';
    }
}
