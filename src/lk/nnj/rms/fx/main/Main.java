package lk.nnj.rms.fx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManagePayment.fxml"));
        primaryStage.setTitle("Restaurant Management System");
        primaryStage.setScene(new Scene(root, 457, 477));
        //primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
