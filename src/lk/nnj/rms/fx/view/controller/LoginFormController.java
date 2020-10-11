package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.User;
import lk.nnj.rms.fx.service.IUser;
import lk.nnj.rms.fx.service.Impl.UserServiceImpl;

import javax.swing.*;
import java.io.IOException;

public class LoginFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private ImageView lbl_back111;

    @FXML
    private JFXTextField txt_user;

    @FXML
    private JFXPasswordField txt_pass;

    @FXML
    private JFXButton btn_sign;

    @FXML
    private ImageView lbl_back11;

    @FXML
    void log(ActionEvent event) throws Exception {
        IUser iUser = new UserServiceImpl();
        User user = iUser.find(txt_user.getText());
        if(user != null && user.getPwd().equals(txt_pass.getText()) && "Manager".equals(user.getType()))
        {
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
        }else if(user != null && user.getPwd().equals(txt_pass.getText()) && "Reciption".equals(user.getType()))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/nnj/rms/fx/view/style/PlaceOrderForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                PlaceOrderFormController pController = fxmlLoader.getController();
                pController.setAssistant(txt_user.getText());
                primaryStage.centerOnScreen();
                if(primaryStage.isMaximized())
                {
                    primaryStage.setMaximized(false);
                }
                primaryStage.setMaximized(true);
                primaryStage.setResizable(true);
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }else
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, login failed");
                alert1.setContentText("Invalid Credentials");
                alert1.showAndWait();
            }
    }

    @FXML
    void logc(ActionEvent event) throws Exception {
        IUser iUser = new UserServiceImpl();
        User user = iUser.find(txt_user.getText());

        if(user != null && user.getPwd().equals(txt_pass.getText()) && "Manager".equals(user.getType()))
        {
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
        }else if(user != null && user.getPwd().equals(txt_pass.getText()) && "Reciption".equals(user.getType()))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lk/nnj/rms/fx/view/style/PlaceOrderForm.fxml"));
            Parent root = (Parent) fxmlLoader.load();

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                PlaceOrderFormController pController = fxmlLoader.getController();
                pController.setAssistant(txt_user.getText());
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                if(primaryStage.isMaximized())
                {
                    primaryStage.setMaximized(false);
                }
                primaryStage.setOnCloseRequest(event1 -> {
                    try {
                        pController.appendLogFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                primaryStage.setMaximized(true);
                primaryStage.setResizable(true);
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }else
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Error, login failed");
            alert1.setContentText("Invalid Credentials");
            alert1.showAndWait();
        }
    }
}
