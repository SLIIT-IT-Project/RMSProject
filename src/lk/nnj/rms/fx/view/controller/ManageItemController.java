package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.embed.swing.JFXPanel;
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
import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.service.IItemService;
import lk.nnj.rms.fx.service.Impl.ItemServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageItemController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView lbl_back;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXTextField txt_sItemID;

    @FXML
    private ImageView lbl_search;

    @FXML
    private ImageView lbl_reset;

    @FXML
    private JFXTextField txt_itemID;

    @FXML
    private JFXTextField txt_description;

    @FXML
    private JFXTextField txt_unitPrice;

    @FXML
    private TableView<Item> tbl_item;

    @FXML
    private JFXTextField txt_itemName;

    @FXML
    void Add(ActionEvent event) {
        String itemID,itemName,description;
        double unitPrice;
        itemID=txt_itemID.getText();
        itemName=txt_itemName.getText();
        description=txt_description.getText();

        //Check whether required fields are empty or not
        if(itemID.equals("") || itemName.equals(""))
        {
            JOptionPane.showMessageDialog(null,"One or more required field is empty");
        }else
            {
                try
                {
                    unitPrice = Double.parseDouble(txt_unitPrice.getText());
                    IItemService iItemService = new ItemServiceImpl();

                    // Add Item to the Item Table
                    boolean result =iItemService.add(new Item(itemID,itemName,description,unitPrice));

                    if(result)
                    {
                        JOptionPane.showMessageDialog(null,"Item added successfully");
                        viewTable();
                        reset();
                    }else
                        {
                            JOptionPane.showMessageDialog(null,"Item cannot add");
                        }
                }catch (NumberFormatException e)
                {
                    JOptionPane.showMessageDialog(null,"Entered unit price is invalid");
                }catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null,"Error! Item id already exists");
                }
            }
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
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

    @FXML
    void Click(MouseEvent event) {
        ArrayList<Item> itemList =  new ArrayList<>(tbl_item.getSelectionModel().getSelectedItems());
        for(Item item:itemList)
        {
            txt_itemID.setText(item.getItem_id());
            txt_itemName.setText(item.getItem_name());
            txt_description.setText(item.getDescription());
            txt_unitPrice.setText(Double.toString(item.getUnit_price()));
        }
    }

    @FXML
    void Delete(ActionEvent event) {
        String itemID = txt_itemID.getText();
        IItemService iItemService = new ItemServiceImpl();
        if (JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this item ?", "WARNING",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                boolean result  = iItemService.delete(itemID);
                if(result)
                {
                    JOptionPane.showMessageDialog(null,"Deleted successfully");
                    viewTable();
                    reset();
                }else
                {
                    JOptionPane.showMessageDialog(null,"Delete failed");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error! cannot delete");
            }
        }


    }

    @FXML
    void Reset(MouseEvent event) {
        viewTable();
    }

    @FXML
    void Search(MouseEvent event) {
        String id =txt_sItemID.getText();
        viewTable(id);
    }

    @FXML
    void Update(ActionEvent event) {
        String itemID,itemName,description,Category;
        double unitPrice;
        itemID=txt_itemID.getText();
        itemName=txt_itemName.getText();
        description=txt_description.getText();

        //Check whether required fields are empty or not
        if(itemID.equals("") || itemName.equals("") || description.equals(""))
        {
            JOptionPane.showMessageDialog(null,"One or more required field is empty");
        }else
        {
            try
            {
                unitPrice = Double.parseDouble(txt_unitPrice.getText());
                IItemService iItemService = new ItemServiceImpl();

                // Update Item in the Item Table
                boolean result =iItemService.update(new Item(itemID,itemName,description,unitPrice));

                if(result)
                {
                    JOptionPane.showMessageDialog(null,"Item updated successfully");
                    viewTable();
                    reset();
                }else
                {
                    JOptionPane.showMessageDialog(null,"Item cannot update");
                }
            }catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null,"Entered unit price is invalid");
            }catch (Exception e)
            {
                JOptionPane.showMessageDialog(null,"Error! Item id can't find");
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
    }
    public void viewTable()
    {
        tbl_item.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_item.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_item.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_item.getColumns().get(3).setStyle("-fx-alignment: center");


        tbl_item.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_id"));
        tbl_item.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("item_name"));
        tbl_item.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_item.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unit_price"));

        IItemService iItemService = new ItemServiceImpl();
        try {
            List<Item> allItems = iItemService.findAll();
            tbl_item.setItems(FXCollections.observableArrayList(allItems));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable(String id)
    {
        tbl_item.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_item.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_item.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_item.getColumns().get(3).setStyle("-fx-alignment: center");


        tbl_item.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("item_id"));
        tbl_item.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("item_name"));
        tbl_item.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_item.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unit_price"));

        IItemService iItemService = new ItemServiceImpl();
        try {
            Item item = iItemService.find(id);
            tbl_item.setItems(FXCollections.observableArrayList(item));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reset()
    {
        txt_itemID.setText("");
        txt_itemName.setText("");
        txt_description.setText("");
        txt_unitPrice.setText("");
    }
}
