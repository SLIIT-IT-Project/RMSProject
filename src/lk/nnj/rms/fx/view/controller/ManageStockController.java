package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Stock;
import lk.nnj.rms.fx.service.Impl.IStockService;
import lk.nnj.rms.fx.service.Impl.Impl.StockDetailsImpl;

import java.io.IOException;
import java.sql.*;
import javax.swing.*;
import java.net.URL;
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
    void add(ActionEvent event) {

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
        IStockService istock = new StockDetailsImpl();
        try {
            istock.add(stock);
            JOptionPane.showMessageDialog(null,"Add Success");
            reset ();
            viewTable();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! can't Add");
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {

        String STId=txtSTId.getText();
        int id =Integer.parseInt(STId);

        IStockService istock = new StockDetailsImpl();

        try {
            istock.delete(id);
            JOptionPane.showMessageDialog(null,"Delete Success");
            reset ();
            viewTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! can't Delete");
            e.printStackTrace();
        }

    }

    @FXML
    void print(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

        String STId=txtSTId.getText();

        int sid =Integer.parseInt(STId);

        IStockService istock = new StockDetailsImpl();
        try {
            Stock stock = istock.find(sid);

            txtStName.setText(stock.getSTName());
            txtQut.setText(Integer.toString(stock.getQuantity()));
            txtType.setText(stock.getType());
            date.setValue(LocalDate.parse((String.valueOf(date.getValue()))));
            txtTC.setText(Integer.toString(stock.getTotalCost()));
            viewTable(sid);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! No Information");
            e.printStackTrace();
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
        IStockService istock = new StockDetailsImpl();
        try {
            if(istock.update(stock)) {

                JOptionPane.showMessageDialog(null, "Add Success");
                reset();
                viewTable();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update Faild");
            }


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! can't Add");
            e.printStackTrace();
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
            date.setValue(LocalDate.parse(String.valueOf(stock.getDate_Time())));
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewTable();
    }

    public void viewTable() {
        tableDetailsStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("STId"));
        tableDetailsStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("STName"));
        tableDetailsStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        tableDetailsStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Type"));
        tableDetailsStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Date_Time"));
        tableDetailsStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("TotalCost"));



        IStockService istock = new StockDetailsImpl();
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
        tableDetailsStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Date_Time"));
        tableDetailsStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("TotalCost"));

        IStockService istock = new StockDetailsImpl();
        try {
            List<Stock> allStock = istock.findAll();
            tableDetailsStock.setItems(FXCollections.observableArrayList(allStock));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void back(MouseEvent mouseEvent) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/Inventory.fxml"));
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
}
