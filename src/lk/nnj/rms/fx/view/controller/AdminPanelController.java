package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.animation.TranslateTransition;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.service.IAdminQueryService;
import lk.nnj.rms.fx.service.Impl.AdminQueryServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdminPanelController implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private Label lbl_salesDetails;

    @FXML
    private Label lbl_tOrder;

    @FXML
    private JFXButton btn_order;

    @FXML
    private JFXButton btn_category;

    @FXML
    private ImageView lbl_back;

    @FXML
    private JFXButton btn_finance;

    @FXML
    private JFXDatePicker txtFrom;

    @FXML
    private JFXDatePicker txtTo;

    @FXML
    private ImageView lbl_search;

    @FXML
    private JFXButton btn_inventory;

    @FXML
    private ImageView lbl_back1;

    @FXML
    private ImageView lbl_back11;

    @FXML
    private ImageView lbl_back111;

    @FXML
    private ImageView lbl_back1111;

    @FXML
    private JFXButton btn_about;

    @FXML
    private ImageView lbl_aboutimg;

    @FXML
    private JFXButton btn_item;

    @FXML
    private ImageView lbl_back12;

    @FXML
    private JFXButton btn_del;

    @FXML
    private ImageView lbl_back13;

    @FXML
    private JFXButton btn_cus;

    @FXML
    private ImageView lbl_back11112;

    @FXML
    private JFXButton btn_emp;

    @FXML
    private ImageView lbl_back11113;

    @FXML
    private JFXButton btn_prop;

    @FXML
    private ImageView lbl_back11114;

    @FXML
    private JFXButton btn_event;

    @FXML
    private ImageView lbl_back11115;

    @FXML
    private JFXButton btn_play;

    @FXML
    private ImageView lbl_back111151;

    @FXML
    private AreaChart<XYChart.Series,XYChart.Series> Sales_Chart;

    @FXML
    private Label lbl_tSales;

    @FXML
    private Label lbl_tSold;

    @FXML
    private Label lbl_tCustomer;

    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/LoginForm.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }
    private Stage stage = null;
    @FXML
    void viewAbout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/nnj/rms/fx/view/style/AboutForm.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        if (root != null) {
            if (stage == null) {
                stage = new Stage();
                stage.setTitle("About");
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
    }

    @FXML
    void viewCategory(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/CategoryManagement.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void viewCustomer(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/CustomerManagement.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void viewDel(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/DeliveryDetails.fxml"));
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
    void viewEmp(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManageEmployee.fxml"));
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
    void viewEvent(ActionEvent event) {

    }

    @FXML
    void viewFinance(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/FinanceManage.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(true);
            primaryStage.setMaximized(true);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void viewInventory(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/Inventory.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void viewItem(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManageItem.fxml"));
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
    void viewOrder(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/OrderDetailForm.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            if(primaryStage.isMaximized())
            {
                primaryStage.setMaximized(false);
            }
            primaryStage.setMaximized(true);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    @FXML
    void viewPlay(ActionEvent event) {

    }

    @FXML
    void viewProp(ActionEvent event) {

    }

    @FXML
    void search(MouseEvent event) {
        LocalDate d1,d2;
        d1 = txtFrom.getValue();
        d2 = txtTo.getValue();
        IAdminQueryService iAdminQueryService = new AdminQueryServiceImpl();
        int totOrders=0,totCustomers=0,totItems=0;
        double totSales=0;
        try {
            totOrders = iAdminQueryService.findTotalOrders(d1,d2);
            totCustomers =iAdminQueryService.findTotalCustomers(d1,d2);
            totItems = iAdminQueryService.findTotalItems(d1,d2);
            totSales = iAdminQueryService.findTotalSales(d1,d2);
            lbl_tOrder.setText(Integer.toString(totOrders));
            lbl_tCustomer.setText(Integer.toString(totCustomers));
            lbl_tSold.setText(Integer.toString(totItems));
            lbl_tSales.setText("Rs."+Double.toString(totSales));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        LocalDate today = LocalDate.now();
        LocalDate d1,d2,date;
        d1 = today.withDayOfMonth(1);
        d2 = today.withDayOfMonth(today.lengthOfMonth());
        txtFrom.setValue(d1);
        txtTo.setValue(d2);
        IAdminQueryService iAdminQueryService = new AdminQueryServiceImpl();
        int totOrders=0,totCustomers=0,totItems=0;
        double totSales=0;
        try {
            totOrders = iAdminQueryService.findTotalOrders(d1,d2);
            totCustomers =iAdminQueryService.findTotalCustomers(d1,d2);
            totItems = iAdminQueryService.findTotalItems(d1,d2);
            totSales = iAdminQueryService.findTotalSales(d1,d2);
            lbl_tOrder.setText(Integer.toString(totOrders));
            lbl_tCustomer.setText(Integer.toString(totCustomers));
            lbl_tSold.setText(Integer.toString(totItems));
            lbl_tSales.setText("Rs."+Double.toString(totSales));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lbl_salesDetails.setText("SALES DETAILS IN "+today.getMonth().toString());
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Total Sales");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Take Away");
        XYChart.Series series3 = new XYChart.Series();
        series3.setName("Dine Inn");
        XYChart.Series series4 = new XYChart.Series();
        series4.setName("Delivery");
        for(int i=1; i<=today.lengthOfMonth(); i++)
        {
            try {
                double no= iAdminQueryService.findTotalSalesPerDay(today.withDayOfMonth(i));
                double tno = iAdminQueryService.findTakeAwaySalesPerDay(today.withDayOfMonth(i));
                double dno = iAdminQueryService.findDineInSalesPerDay(today.withDayOfMonth(i));
                double deno= iAdminQueryService.findDeliverSalesPerDay(today.withDayOfMonth(i));
                date = today.withDayOfMonth(i);
                series1.getData().add(new XYChart.Data(date.getMonthValue()+"/"+date.getDayOfMonth(),no));
                series2.getData().add(new XYChart.Data(date.getMonthValue()+"/"+date.getDayOfMonth(),tno));
                series3.getData().add(new XYChart.Data(date.getMonthValue()+"/"+date.getDayOfMonth(),dno));
                series4.getData().add(new XYChart.Data(date.getMonthValue()+"/"+date.getDayOfMonth(),deno));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Sales_Chart.getData().addAll(series1,series2,series3,series4);

    }
}
