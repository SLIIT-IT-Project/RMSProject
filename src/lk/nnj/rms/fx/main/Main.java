package lk.nnj.rms.fx.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.nnj.rms.fx.model.Order;
import lk.nnj.rms.fx.service.IOrderService;
import lk.nnj.rms.fx.service.Impl.OrderServiceImpl;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/PlaceOrderForm.fxml"));
        primaryStage.setTitle("Restaurant Management System");
        primaryStage.setScene(new Scene(root, 1189, 701));
        primaryStage.show();

//        IOrderService iOrderService = new OrderServiceImpl();
//        iOrderService.add(new Order(1000,java.time.LocalDateTime.now(),"ahjkdhajd","ordertype",1,100.0,100.0,100.0,"C001"));

    }

    public static void main(String[] args) {
        launch(args);
    }
}
