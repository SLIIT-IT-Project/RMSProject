package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
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
import lk.nnj.rms.fx.model.Order;
import lk.nnj.rms.fx.model.OrderDel;
import lk.nnj.rms.fx.service.IOrderService;
import lk.nnj.rms.fx.service.Impl.OrderServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

public class OrderDelDetailController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txt_inNo;

    @FXML
    private ImageView lbl_search;

    @FXML
    private ImageView lbl_reset;

    @FXML
    private JFXDatePicker txt_to;

    @FXML
    private ImageView lbl_search1;

    @FXML
    private TableView<OrderDel> tbl_Order;


    @FXML
    void DSearch(MouseEvent event) {
        LocalDate date =txt_to.getValue();
        viewTable(date);
    }

    @FXML
    void Reset(MouseEvent event) {
        viewTable(LocalDate.now());
    }

    @FXML
    void Search(MouseEvent event) {
        int oid = Integer.parseInt(txt_inNo.getText());
        viewTable(oid);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable(LocalDate.now());
    }
    public void viewTable(LocalDate d1)
    {
        tbl_Order.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(3).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(4).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(5).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(6).setStyle("-fx-alignment: center");
        tbl_Order.getColumns().get(7).setStyle("-fx-alignment: center");

        tbl_Order.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        tbl_Order.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date_time"));
        tbl_Order.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_Order.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tid"));
        tbl_Order.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tbl_Order.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("cname"));
        tbl_Order.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_Order.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("address"));

        IOrderService iOrderService = new OrderServiceImpl();
        try {
            List<OrderDel> allOrders = iOrderService.findAllOrderDel(d1);
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

        tbl_Order.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("invoiceNo"));
        tbl_Order.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date_time"));
        tbl_Order.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_Order.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("tid"));
        tbl_Order.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("status"));
        tbl_Order.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("cname"));
        tbl_Order.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_Order.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("address"));

        IOrderService iOrderService = new OrderServiceImpl();
        try {
            OrderDel allOrders = iOrderService.findOrderDel(oid);
            tbl_Order.setItems(FXCollections.observableArrayList(allOrders));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
