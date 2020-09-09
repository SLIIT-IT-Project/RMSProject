package lk.nnj.rms.fx.model;

public class ItemOrder {
    private int oid;
    private String itemID;
    private String itemName;
    private int qty;
    private double unitPrice;

    public ItemOrder(int oid, String itemID, String itemName, int qty, double unitPrice) {
        this.oid = oid;
        this.itemID = itemID;
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "ItemOrder{" +
                "oid=" + oid +
                ", itemID='" + itemID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
