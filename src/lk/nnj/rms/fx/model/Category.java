package lk.nnj.rms.fx.model;

public class Category {
    private String category_id;
    private String category_name;
    private String description;
    private int no_of_items;


    public Category(String category_id, String category_name, String description, int no_of_items) {
        this.category_id = category_id;
        this.category_name = category_name;
        this.description = description;
        this.no_of_items = no_of_items;
    }


    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNo_of_items() {
        return no_of_items;
    }

    public void setNo_of_orders(int no_of_items) {
        this.no_of_items = no_of_items;
    }


}
