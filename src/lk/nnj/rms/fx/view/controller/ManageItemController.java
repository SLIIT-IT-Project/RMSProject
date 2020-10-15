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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.service.IItemService;
import lk.nnj.rms.fx.service.Impl.ItemServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageItemController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView lbl_back;

    @FXML
    private JFXButton btn_category;

    @FXML
    private JFXButton btn_report;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_demo;

    @FXML
    private JFXTextField txt_sItemID;

    @FXML
    private ImageView lbl_search;

    @FXML
    private Label lbl_tot;

    @FXML
    private ImageView lbl_excel;

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
    void Demo(ActionEvent event) {
        txt_itemID.setText("P0013");
        txt_itemName.setText("Sausage Delight Medium Pizza");
        txt_description.setText("Chicken sausages & onions with a double layer of mozzarella");
        txt_unitPrice.setText("990");
    }
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
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Error, cannot add data");
            alert1.setContentText("One or more required field is empty.");
            alert1.showAndWait();

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
                        Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information Dialog");
                        alert1.setHeaderText("Item Added");
                        alert1.setContentText("Item added successfully");
                        alert1.showAndWait();
                        viewTable();
                        totItems();
                        reset();
                    }else
                        {
                            Alert alert1 = new Alert(Alert.AlertType.ERROR);
                            alert1.setTitle("Error Dialog");
                            alert1.setHeaderText("Error, Database connection failed");
                            alert1.setContentText("Item cannot add");
                            alert1.showAndWait();
                        }
                }catch (NumberFormatException e)
                {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("Invalid input found");
                    alert1.setContentText("Entered unit price is invalid.");
                    alert1.showAndWait();
                }catch (Exception e)
                {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("Invalid input found");
                    alert1.setContentText("Error! Item id already exists");
                    alert1.showAndWait();
                }
            }
    }

    @FXML
    void Back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/AdminPanel.fxml"));
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Confirm your decision. ");
        alert.setContentText("Are you sure you want to delete ?");

        Optional<ButtonType> result1 = alert.showAndWait();
        if (result1.get() == ButtonType.OK){
            // ... user chose OK
            try {
                boolean result  = iItemService.delete(itemID);
                if(result)
                {
                    Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Information Dialog");
                    alert1.setHeaderText("Deleted Successfully");
                    alert1.setContentText("Item deleted successfully");
                    alert1.showAndWait();
                    viewTable();
                    reset();
                    totItems();
                }else
                {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("Error, delete failed");
                    alert1.setContentText("Item cannot delete");
                    alert1.showAndWait();
                }
            } catch (Exception e) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, delete failed");
                alert1.setContentText("Item cannot delete");
                alert1.showAndWait();
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
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Invalid input found");
            alert1.setContentText("One or more required field is empty.");
            alert1.showAndWait();
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
                    Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Information Dialog");
                    alert1.setHeaderText("Updated Successfully");
                    alert1.setContentText("Item updated successfully");
                    alert1.showAndWait();
                    viewTable();
                    reset();
                }else
                {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("Error, update failed");
                    alert1.setContentText("Item cannot update");
                    alert1.showAndWait();
                }
            }catch (NumberFormatException e)
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Invalid input found");
                alert1.setContentText("Entered unit price is invalid");
                alert1.showAndWait();
            }catch (Exception e)
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Invalid input found");
                alert1.setContentText("Item id does not exist");
                alert1.showAndWait();
            }
        }
    }
    public void totItems()
    {
        ItemServiceImpl itemService = new ItemServiceImpl();
        try {
            int c = itemService.totalItems();
            lbl_tot.setText(Integer.toString(c));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
        totItems();
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
    private static Stage stage =null;
    @FXML
    void viewCategory(ActionEvent event) throws IOException {

        if(!txt_itemID.getText().equals("")) {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/nnj/rms/fx/view/style/ItemCategoryManagement.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            if (root != null) {
                if (stage == null) {
                    stage = new Stage();
                    stage.setTitle("Add to Category");
                    ItemCategoryManagementController setController = fxmlLoader.getController();
                    setController.setItemId(txt_itemID.getText());
                    stage.setResizable(false);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setOnCloseRequest(event1 -> {
                                stage = null;
                            }
                    );
                    stage.setScene(new Scene(root));
                    stage.show();
                }

            }
        }else
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, Item not selected");
                alert1.setContentText("select item before added to the category.");
                alert1.showAndWait();
            }
    }

    private static String[] columns = {"Item ID","Item Name","Description","Unit Price"};

    @FXML
    void generateReport(MouseEvent event) throws Exception {

        IItemService iItemService = new ItemServiceImpl();
        List<Item> allItems = iItemService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Item Details");

        Font headerFont = workbook.createFont();
        ((Font) headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for(int i=0 ; i<columns.length; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum=1;

        for(Item item : allItems)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getItem_id());
            row.createCell(1).setCellValue(item.getItem_name());
            row.createCell(2).setCellValue(item.getDescription());
            row.createCell(3).setCellValue(item.getUnit_price());
        }

        for(int i =0; i<columns.length; i++)
        {
            sheet.autoSizeColumn(i);
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export to Excel");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Excel Workbook (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(root.getScene().getWindow());
        if (file != null)
        {
            String path = file.getAbsolutePath();
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }
    }
}
