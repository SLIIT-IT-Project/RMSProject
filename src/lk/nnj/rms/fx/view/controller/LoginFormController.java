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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    void log(ActionEvent event) throws IOException {
        if(txt_user.getText().equals("admin") && txt_pass.getText().equals("1234"))
        {
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
        }else if(txt_user.getText().equals("emp1") && txt_pass.getText().equals("emp1"))
        {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/PlaceOrderForm.fxml"));
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                primaryStage.setMaximized(true);
                primaryStage.setResizable(true);
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }
    }

    @FXML
    void logc(ActionEvent event) throws IOException {
        if(txt_user.getText().equals("admin") && txt_pass.getText().equals("1234"))
        {
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
        }else if(txt_user.getText().equals("emp1") && txt_pass.getText().equals("emp1"))
        {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/PlaceOrderForm.fxml"));
            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();
                primaryStage.setMaximized(true);
                primaryStage.setResizable(true);
                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();
            }
        }
    }
}
