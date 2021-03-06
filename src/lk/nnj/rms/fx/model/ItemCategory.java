package lk.nnj.rms.fx.model;

public class ItemCategory {

    private String item_id;
    private String category_id;
    private String categoryName;

    public ItemCategory(String item_id, String category_id, String categoryName) {
        this.item_id = item_id;
        this.category_id = category_id;
        this.categoryName = categoryName;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
