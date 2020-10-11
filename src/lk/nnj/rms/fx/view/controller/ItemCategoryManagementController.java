package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import jdk.nashorn.internal.scripts.JO;
import lk.nnj.rms.fx.model.Category;
import lk.nnj.rms.fx.model.Customer;
import lk.nnj.rms.fx.model.ItemCategory;
import lk.nnj.rms.fx.service.ICategoryService;
import lk.nnj.rms.fx.service.ICustomerService;
import lk.nnj.rms.fx.service.IItemCategoryService;
import lk.nnj.rms.fx.service.Impl.CategoryServiceImpl;
import lk.nnj.rms.fx.service.Impl.CustomerServiceImpl;
import lk.nnj.rms.fx.service.Impl.ItemCategoryServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemCategoryManagementController implements Initializable {

    @FXML
    private JFXComboBox<String> cmb_SelectCat;

    @FXML
    private JFXTextField txt_selectItem;

    @FXML
    private TableView<ItemCategory> tbl_itemCategory;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_delete;

    @FXML
    void add(ActionEvent event) throws Exception {
        String itemiId = txt_selectItem.getText();
        String cName = cmb_SelectCat.getValue();

        ICategoryService iCategoryService =  new CategoryServiceImpl();
        Category category = null;
        category = iCategoryService.findId(cName);


        IItemCategoryService iItemCategoryService = new ItemCategoryServiceImpl();

        if(cName == null)
        {
            JOptionPane.showMessageDialog(null,"Select Category to add");
        }else {
            boolean result = false;
                result = iItemCategoryService.add(new ItemCategory(itemiId, category.getCategory_id(), category.getCategory_name()));
                iCategoryService.updateNoOfItems(category.getCategory_id(),category.getNo_of_items()+1);

            if (result) {
                JOptionPane.showMessageDialog(null, "Item successfully added to the selected category");
                viewTable(itemiId);
                cmb_SelectCat.setValue(null);
            } else {
                JOptionPane.showMessageDialog(null, "Item has already added to the selected category");
            }
        }
    }

    private void viewTable(String itemId) {
        tbl_itemCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_id"));
        tbl_itemCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("category_id"));
        tbl_itemCategory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        IItemCategoryService iItemCategoryService = new ItemCategoryServiceImpl();
        try {
            List<ItemCategory> allUsers = iItemCategoryService.findAll(itemId);
            tbl_itemCategory.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click(MouseEvent event)
    {
        ArrayList<ItemCategory> itemCategoriesList =  new ArrayList<>(tbl_itemCategory.getSelectionModel().getSelectedItems());
        for(ItemCategory itemCategory:itemCategoriesList)
        {
            txt_selectItem.setText(itemCategory.getItem_id());
            cmb_SelectCat.setValue(itemCategory.getCategoryName());
        }
    }

    public  void setItemId(String itemId)
    {
        txt_selectItem.setText(itemId);
        tbl_itemCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_id"));
        tbl_itemCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("category_id"));
        tbl_itemCategory.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        IItemCategoryService iItemCategoryService = new ItemCategoryServiceImpl();
        try {
            List<ItemCategory> allUsers = iItemCategoryService.findAll(itemId);
            tbl_itemCategory.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) throws Exception {
        String itemiId = txt_selectItem.getText();
        String cName = cmb_SelectCat.getValue();
        IItemCategoryService iItemCategoryService = new ItemCategoryServiceImpl();
        ICategoryService iCategoryService =  new CategoryServiceImpl();
        Category category = iCategoryService.findId(cName);

        if(cName == null)
        {
            JOptionPane.showMessageDialog(null,"Select from table to remove");
        }else {
            boolean result = iItemCategoryService.delete(itemiId, category.getCategory_id());
            iCategoryService.updateNoOfItems(category.getCategory_id(),category.getNo_of_items() -1);
            if (result) {
                JOptionPane.showMessageDialog(null, "Item has successfully removed from selected category");
                cmb_SelectCat.setValue(null);
                viewTable(itemiId);
            } else {
                JOptionPane.showMessageDialog(null, "Error, Item cannot remove");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ICategoryService iCategoryService = new CategoryServiceImpl();
        try {
            List<String> allCategory = iCategoryService.findAllCategory();
            String [] nameList = new String[allCategory.size()];
            nameList = allCategory.toArray(nameList);

            ObservableList<String> options =FXCollections.observableArrayList(nameList);
            cmb_SelectCat.setItems(options);
            viewTable(txt_selectItem.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
