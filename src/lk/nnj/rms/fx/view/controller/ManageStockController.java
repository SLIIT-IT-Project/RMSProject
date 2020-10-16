package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Stock;
import lk.nnj.rms.fx.model.Supplier;
import lk.nnj.rms.fx.service.IStockService;
import lk.nnj.rms.fx.service.ISupplierService;
import lk.nnj.rms.fx.service.Impl.StockServiceImpl;
import lk.nnj.rms.fx.service.Impl.SupplierServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ManageStockController implements Initializable {

    @FXML
    private JFXTextField txtSTId;

    @FXML
    private JFXTextField txtStName;

    @FXML
    private JFXTextField txtQut;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtTC;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private JFXButton btnPrint;

    @FXML
    private TableView<Stock> tableDetailsStock;

    @FXML
    private PieChart piechart;

    @FXML
    private ImageView imgback;

    @FXML
    private JFXButton btnSearch;

    @FXML
    private JFXDatePicker date;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblTot_stock;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXTextField txtEDQT;
    private Stock stock;



    @FXML
    void add(ActionEvent event) {

        String STId,STName,Quantity,Type,TotalCost;

        STId =txtSTId.getText();
        STName=txtStName.getText();
        Quantity=txtQut.getText();
        Type=txtType.getText();

        TotalCost=txtTC.getText();


        if(STId.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Enter a Id");
            alert1.setContentText("ID are required");
            alert1.showAndWait();
        }
        else if(STName.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Name");
            alert1.setContentText("Stock Details are required");
            alert1.showAndWait();
        }
        else if(Quantity.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Quantity");
            alert1.setContentText("Stock Details are required");
            alert1.showAndWait();
        }
        else if(Type.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Type");
            alert1.setContentText("Stock Details are required");
            alert1.showAndWait();
        }
        else if(date.getValue() == null){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a DateTime");
            alert1.setContentText("Stock Details are required");
            alert1.showAndWait();
        }
        else if(TotalCost.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a TotalCost");
            alert1.setContentText("Stock Details are required");
            alert1.showAndWait();
        }
        else{

            Date DateTime= Date.valueOf(date.getValue());
            int id =Integer.parseInt(STId);
            int Qut =Integer.parseInt(Quantity);
            int TC =Integer.parseInt(TotalCost);

            Stock stock=new Stock(id,STName,Qut,Type,DateTime,TC);
            IStockService istock = new StockServiceImpl();
            try{
                istock.add(stock);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Add Successfully");
                alert1.setContentText("New Stock Added");
                alert1.showAndWait();
                viewTable();
                reset();
            }
            catch (Exception e)
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, Add failed");
                alert1.setContentText("Stock cannot Add");
                alert1.showAndWait();
                e.printStackTrace();
            }
        }
            counter();

    }

    @FXML
    void delete(ActionEvent event) {

        String STId=txtSTId.getText();
        int id =Integer.parseInt(STId);

        IStockService istock = new StockServiceImpl();

        try {
            istock.delete(id);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText("Delete Successfully");
            alert1.setContentText("Stock Item Deleted");
            alert1.showAndWait();
            reset ();
            viewTable();
        } catch (Exception e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Error, Delete failed");
            alert1.setContentText("Stock Item cannot Delete");
            alert1.showAndWait();
        }
        counter();
    }

    @FXML
    void search(ActionEvent event) {

        String STId=txtSTId.getText();

        int sid =Integer.parseInt(STId);

        IStockService istock = new StockServiceImpl();
        try {
            Stock stock = istock.find(sid);

            txtStName.setText(stock.getSTName());
            txtQut.setText(Integer.toString(stock.getQuantity()));
            txtType.setText(stock.getType());
            date.setValue(LocalDate.parse((String.valueOf(date.getValue()))));
            txtTC.setText(Integer.toString(stock.getTotalCost()));

            viewTable(sid);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! ");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Error, No Information");
            alert1.setContentText("No Stock Item Information");
            alert1.showAndWait();
        }

    }

    @FXML
    void update(ActionEvent event) {

        String STId,STName,Quantity,Type,TotalCost;

        STId =txtSTId.getText();
        STName=txtStName.getText();
        Quantity=txtQut.getText();
        Type=txtType.getText();
        Date DateTime= Date.valueOf(date.getValue());
        TotalCost=txtTC.getText();

        int sid =Integer.parseInt(STId);
        int Qut =Integer.parseInt(Quantity);
        int TC =Integer.parseInt(TotalCost);

        Stock stock=new Stock(sid,STName,Qut,Type,DateTime,TC);
        IStockService istock = new StockServiceImpl();
        try {
            if(istock.update(stock)) {

                Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Updated Successfully");
                alert1.setContentText("Stock Item updated successfully");
                alert1.showAndWait();
                reset ();
                viewTable();
            }
            else
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, update failed");
                alert1.setContentText("Stock Item cannot update");
                alert1.showAndWait();

            }
        } catch (Exception e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Invalid input found");
            alert1.setContentText("Stock Item id does not exist");
            alert1.showAndWait();

        }

    }

    @FXML
    void viewSelectStock(MouseEvent event) {

        ArrayList<Stock> userList = new ArrayList<>(tableDetailsStock.getSelectionModel().getSelectedItems());
        for (Stock stock:userList) {

            txtSTId.setText(Integer.toString(stock.getSTId()));
            txtStName.setText(stock.getSTName());
            txtQut.setText(Integer.toString(stock.getQuantity()));
            txtType.setText(stock.getType());
            date.setValue(LocalDate.parse(String.valueOf(stock.getDate())));
            txtTC.setText(Integer.toString(stock.getTotalCost()));


        }

    }

    public void reset ()
    {
        txtSTId.setText(" ");
        txtStName.setText(" ");
        txtQut.setText(" ");
        txtType.setText(" ");
        txtTC.setText(" ");
        date.setValue(LocalDate.parse(String.valueOf(date.getValue())));
    }

    @FXML
    void demo(ActionEvent event) {
        demo();
    }

    public void demo(){
        txtSTId.setText("800909");
        txtStName.setText("Tomato Sauce (5L)");
        txtQut.setText("50");
        txtType.setText("Bottle");
        txtTC.setText("25000");
        date.setValue(LocalDate.of(2020,07,15));
    }
    //counter
    public void counter(){

        StockServiceImpl stockService = new StockServiceImpl();
        try {
            int c = stockService.totalItems();
            lblTot_stock.setText(Integer.toString(c));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();

        StockServiceImpl stockService = new StockServiceImpl();
        try {
            int c = stockService.totalItems();
            lblTot_stock.setText(Integer.toString(c));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewTable() {
        tableDetailsStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("STId"));
        tableDetailsStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("STName"));
        tableDetailsStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tableDetailsStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableDetailsStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableDetailsStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("TotalCost"));




        IStockService istock = new StockServiceImpl();
        try {
            List<Stock> allStock = istock.findAll();
            tableDetailsStock.setItems(FXCollections.observableArrayList(allStock));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable(int STId)
    {
        tableDetailsStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("STId"));
        tableDetailsStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("STName"));
        tableDetailsStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tableDetailsStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableDetailsStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Date"));
        tableDetailsStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("TotalCost"));



        IStockService istock = new StockServiceImpl();
        try {
            List<Stock> allStock = istock.findAll();
            tableDetailsStock.setItems(FXCollections.observableArrayList(allStock));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void clear(ActionEvent actionEvent) {
        reset ();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/Inventory.fxml"));
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
    private static String[] columns = {"Stock ID","Stock Item Name","Quantity","Type","Date","Total Cost"};
    @FXML
    void Report(ActionEvent event) throws Exception{

        IStockService iStockService = new StockServiceImpl();
        List<Stock> allItems = iStockService.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Stock Details");
        Font headerFont = workbook.createFont();
        ((Font) headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
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
        for(Stock stock : allItems)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(stock.getSTId());
            row.createCell(1).setCellValue(stock.getSTName());
            row.createCell(2).setCellValue(stock.getQuantity());
            row.createCell(3).setCellValue(stock.getType());
            row.createCell(4).setCellValue(stock.getDate().toString());
            row.createCell(5).setCellValue(stock.getTotalCost());
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