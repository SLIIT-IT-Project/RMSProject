package lk.nnj.rms.fx.view.controller;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Category;
import lk.nnj.rms.fx.model.Customer;
import lk.nnj.rms.fx.service.ICategoryService;
import lk.nnj.rms.fx.service.ICustomerService;
import lk.nnj.rms.fx.service.Impl.CategoryServiceImpl;
import lk.nnj.rms.fx.service.Impl.CustomerServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CategoryManagementController implements Initializable{

    @FXML
    private AnchorPane root;

        @FXML
        private JFXTextField txt_category_id;

        @FXML
        private JFXTextField txt_category_name;

        @FXML
        private JFXTextField txt_description;

        @FXML
        private TableView<Category> tbl_category;

        @FXML
        private JFXButton btn_category_add;

        @FXML
        private JFXButton btn_category_update;

        @FXML
        private JFXButton btn_category_delete;

        @FXML
        private JFXTextField txt_searchCategoryID;

        @FXML
        private ImageView lbl_search_category;

        @FXML
        private ImageView lbl_back;

        @FXML
        void add(ActionEvent event) {


            String category_id,category_name,description,no_of_items;
            category_id=txt_category_id.getText();
            category_name=txt_category_name.getText();
            description=txt_description.getText();
            no_of_items="0";


            Category category = new Category(category_id,category_name,description,no_of_items);
            ICategoryService iCategoryService = new CategoryServiceImpl();

            try{
                iCategoryService.add(category);
                reset();
                JOptionPane.showMessageDialog(null,"Added successfully");
                viewTable();


            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error cannot add");
                e.printStackTrace();
            }

        }

        @FXML
        void delete(ActionEvent event) {
            String id = txt_category_id.getText();
            ICategoryService iCategoryService = new CategoryServiceImpl();
            try {
                iCategoryService.delete(id);
                viewTable();
                reset();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @FXML
        void update(ActionEvent event) {

            String id,name,description,no_of_items;
            id=txt_category_id.getText();
            name=txt_category_name.getText();
            description=txt_description.getText();
            no_of_items="0";


            Category category = new Category(id,name,description,no_of_items);
            ICategoryService iCategoryService = new CategoryServiceImpl();

            try{
                if(iCategoryService.update(category)){
                    reset();
                    JOptionPane.showMessageDialog(null,"Updated successfully");
                    viewTable();
                }
                else
                {

                }


            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"Error cannot be updated");
                e.printStackTrace();
            }

        }

        public void viewTable()
        {
            tbl_category.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("category_id"));
            tbl_category.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("category_name"));
            tbl_category.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
            tbl_category.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("no_of_items"));


            ICategoryService iCategoryService = new CategoryServiceImpl();
            try {
                List<Category> allcategories = iCategoryService.findAll();
                tbl_category.setItems(FXCollections.observableArrayList(allcategories));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    @FXML
    void click(MouseEvent event) {

        ArrayList<Category> categoriesList =  new ArrayList<>(tbl_category.getSelectionModel().getSelectedItems());
        for(Category category:categoriesList)
        {
            txt_category_id.setText(category.getCategory_id());
            txt_category_name.setText(category.getCategory_name());
            txt_description.setText(category.getDescription());

        }

    }

    @FXML
    void searchCategory(MouseEvent event) {

        String id=txt_searchCategoryID.getText();

        ICategoryService iCategoryService=new CategoryServiceImpl();
        try{
            Category category=iCategoryService.find(id);

            txt_category_id.setText(category.getCategory_id());
            txt_category_name.setText(category.getCategory_name());
            txt_description.setText(category.getDescription());

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/AdminPanel.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(true);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
    }

    public void reset()
    {
        txt_category_name.setText("");
        txt_description.setText("");
        txt_category_id.setText("");
        txt_searchCategoryID.setText("");
    }
}
