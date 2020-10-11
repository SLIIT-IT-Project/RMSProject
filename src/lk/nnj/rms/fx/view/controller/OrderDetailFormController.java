package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.Order;
import lk.nnj.rms.fx.service.IOrderService;
import lk.nnj.rms.fx.service.Impl.OrderServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class OrderDetailFormController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView lbl_back;

    @FXML
    private JFXTextField txt_inNo;

    @FXML
    private ImageView lbl_search;

    @FXML
    private ImageView lbl_reset;

    @FXML
    private JFXDatePicker txt_from;

    @FXML
    private JFXDatePicker txt_to;

    @FXML
    private ImageView lbl_search1;

    @FXML
    private ImageView lbl_excel;

    @FXML
    private TableView<Order> tbl_Order;

    @FXML
    void Back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/AdminPanel.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void Reset(MouseEvent event) {
        viewTable();
    }

    @FXML
    void Search(MouseEvent event) {
        try
        {
            int oid = Integer.parseInt(txt_inNo.getText());
            viewTable(oid);
        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Order id is incorrect.");
        }
    }
    @FXML
    void DSearch(MouseEvent event) {
        LocalDate d1,d2;
        if(txt_from.getValue() !=null && txt_to.getValue() != null)
        {
            d1 = txt_from.getValue();
            d2 = txt_to.getValue();
            viewTable(d1,d2);
        }else
            {
                JOptionPane.showMessageDialog(null,"Required fields are empty");
            }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
    }

    public void viewTable(LocalDate d1, LocalDate d2)
    {
        tbl_Order.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(3).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(4).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(5).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(6).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(7).setStyle("-fx-alignment: center");

        tbl_Order.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("oid"));
        tbl_Order.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date_tme"));
        tbl_Order.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_Order.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("order_type"));
        tbl_Order.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("order_amount"));
        tbl_Order.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("service_charge"));
        tbl_Order.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("tot_amount"));
        tbl_Order.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("cid"));

        IOrderService iOrderService = new OrderServiceImpl();
        try {
            List<Order> allOrders = iOrderService.findAll(d1,d2);
            tbl_Order.setItems(FXCollections.observableArrayList(allOrders));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable()
    {
        tbl_Order.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(3).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(4).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(5).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(6).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(7).setStyle("-fx-alignment: center");

        tbl_Order.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("oid"));
        tbl_Order.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date_tme"));
        tbl_Order.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_Order.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("order_type"));
        tbl_Order.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("order_amount"));
        tbl_Order.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("service_charge"));
        tbl_Order.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("tot_amount"));
        tbl_Order.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("cid"));

        IOrderService iOrderService = new OrderServiceImpl();
        try {
            List<Order> allOrders = iOrderService.findAll();
            tbl_Order.setItems(FXCollections.observableArrayList(allOrders));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable(int oid)
    {
        tbl_Order.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(3).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(4).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(5).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(6).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(7).setStyle("-fx-alignment: center");

        tbl_Order.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("oid"));
        tbl_Order.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date_tme"));
        tbl_Order.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_Order.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("order_type"));
        tbl_Order.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("order_amount"));
        tbl_Order.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("service_charge"));
        tbl_Order.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("tot_amount"));
        tbl_Order.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("cid"));

        IOrderService iOrderService = new OrderServiceImpl();
        try {
            Order allOrders = iOrderService.find(oid);
            tbl_Order.setItems(FXCollections.observableArrayList(allOrders));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static String[] columns = {"Invoice No","Date & Time","Description","Order Type",
            "Order Amount","Service Charge","Total Amount","Customer ID"};

    @FXML
    void generateReport(MouseEvent event) throws Exception{
        ArrayList<Order> orderList =  new ArrayList<>(tbl_Order.getItems());

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Item Details");

        Font headerFont = workbook.createFont();
        ((Font) headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
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

        for(Order order : orderList)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(order.getOid());
            row.createCell(1).setCellValue(order.getDate_tme().toString());
            row.createCell(2).setCellValue(order.getDescription());
            row.createCell(3).setCellValue(order.getOrder_type());
            row.createCell(4).setCellValue(order.getOrder_amount());
            row.createCell(5).setCellValue(order.getService_charge());
            row.createCell(6).setCellValue(order.getTot_amount());
            row.createCell(7).setCellValue(order.getCid());
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
