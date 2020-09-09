package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class AdminPanelController {
    @FXML
    private AnchorPane root;

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
    private AreaChart<?, ?> Sales_Chart;

    @FXML
    private Label lbl_tSales;

    @FXML
    private Label lbl_tSold;

    @FXML
    private Label lbl_tCustomer;

    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/LoginForm.fxml"));
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
    void viewAbout(ActionEvent event) {

    }

    @FXML
    void viewCategory(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/CategoryManagement.fxml"));
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
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/CustomerManagement.fxml"));
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
    void viewDel(ActionEvent event) {

    }

    @FXML
    void viewEmp(ActionEvent event) {

    }

    @FXML
    void viewEvent(ActionEvent event) {

    }

    @FXML
    void viewFinance(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/FinanceManage.fxml"));
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
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/Inventory.fxml"));
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
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/ManageItem.fxml"));
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
    void viewOrder(ActionEvent event) {

    }

    @FXML
    void viewPlay(ActionEvent event) {

    }

    @FXML
    void viewProp(ActionEvent event) {

    }

}
