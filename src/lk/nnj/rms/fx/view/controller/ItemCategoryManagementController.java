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
    private JFXComboBox<String> txt_selectCategory;

    @FXML
    private JFXTextField txt_selectItem;

    @FXML
    private TableView<ItemCategory> tbl_itemCategory;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_delete;

    @FXML
    void add(ActionEvent event) {


//        String itemid,categoryid;
//        itemid=txt_selectItem.getText();
//        categoryid=txt_selectCategory.getText();
//
//        ItemCategory itemCategory = new ItemCategory(itemid,categoryid);
//        IItemCategoryService iItemCategoryService= new ItemCategoryServiceImpl();
//
//        try{
//            iItemCategoryService.add(itemCategory);
//            JOptionPane.showMessageDialog(null,"Added successfully");
//            viewTable();
//
//
//        }catch (Exception e){
//            JOptionPane.showMessageDialog(null,"Error cannot add");
//            e.printStackTrace();
//        }


    }

    private void viewTable() {
    }

    @FXML
    void click(MouseEvent event) {

//        ArrayList<ItemCategory> itemCategoriesList =  new ArrayList<>(tbl_itemCategory.getSelectionModel().getSelectedItems());
//        for(ItemCategory itemCategory:itemCategoriesList)
//        {
//
//            txt_selectItem.setText(itemCategory.getItem_id());
//            txt_selectCategory.setText(itemCategory.getCategory_id());
//
//
//        }

        tbl_itemCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_id"));
        tbl_itemCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("category_id"));

        IItemCategoryService iItemCategoryService = new ItemCategoryServiceImpl();
        try {
            List<ItemCategory> allUsers = iItemCategoryService.findAll();
            tbl_itemCategory.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {

//        String itemid = txt_selectItem.getText();
//        String categoryid = txt_selectCategory.getText();
////        ICategoryService iItemCategoryService = new ItemCategoryServiceImpl();
//        try {
//            iItemCategoryService.delete(itemid);
//            viewTable();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ICategoryService iCategoryService = new CategoryServiceImpl();
        try {
            List<String> allCategory = iCategoryService.findAllCategory();
            String [] nameList = new String[allCategory.size()];
            nameList = allCategory.toArray(nameList);

            ObservableList<String> options =FXCollections.observableArrayList(nameList);
            txt_selectCategory.setItems(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
