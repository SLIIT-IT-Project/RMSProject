package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import lk.nnj.rms.fx.model.Customer;
import lk.nnj.rms.fx.service.ICustomerService;
import lk.nnj.rms.fx.service.Impl.CustomerServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private ImageView lbl_back;
    @FXML
    private JFXTextField txt_searchCustomerID;

    @FXML
    private ImageView lbl_search_customer;

    @FXML
    private JFXTextField txt_customer_id;

    @FXML
    private JFXTextField txt_cname;

    @FXML
    private JFXTextField txt_mobile;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_no_of_orders;

    @FXML
    private JFXButton btn_customeradd;

    @FXML
    private JFXButton btn_customerupdate;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private TableView<Customer> tbl_customer;


    @FXML
    void add(ActionEvent event) {

        int id,no_of_orders;
        String cname,mobile,address;
        id=Integer.parseInt(txt_customer_id.getText());
        cname=txt_cname.getText();
        mobile=txt_mobile.getText();
        address=txt_address.getText();
        no_of_orders=Integer.parseInt(txt_no_of_orders.getText());

        Customer customer = new Customer(id,cname,mobile,address,no_of_orders);
        ICustomerService icustomerService= new CustomerServiceImpl();

        try{
            icustomerService.add(customer);
            reset();
            JOptionPane.showMessageDialog(null,"Added successfully");
            viewTable();


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error cannot add");
            e.printStackTrace();
        }

    }

    @FXML
    void delete(ActionEvent event) {
        int id = Integer.parseInt(txt_customer_id.getText());
        ICustomerService iCustomerService = new CustomerServiceImpl();
        try {
            iCustomerService.delete(id);
            viewTable();
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchCustomer(MouseEvent event) {

        int id=Integer.parseInt(txt_searchCustomerID.getText());

        ICustomerService iCustomerService=new CustomerServiceImpl();
        try{
            Customer customer=iCustomerService.find(id);
            txt_customer_id.setText(Integer.toString(customer.getCustomer_id()));
            txt_cname.setText(customer.getCname());
            txt_mobile.setText(customer.getMobile());
            txt_address.setText(customer.getAddress());
            txt_no_of_orders.setText(Integer.toString(customer.getNo_of_orders()));
            
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void update(ActionEvent event) {

        int id,no_of_orders;
        String cname,mobile,address;
        id=Integer.parseInt(txt_customer_id.getText());
        cname=txt_cname.getText();
        mobile=txt_mobile.getText();
        address=txt_address.getText();
        no_of_orders=Integer.parseInt(txt_no_of_orders.getText());


        Customer customer = new Customer(id,cname,mobile,address,no_of_orders);
        ICustomerService iCustomerService = new CustomerServiceImpl();

        try{
            if(iCustomerService.update(customer)){
                reset();
                JOptionPane.showMessageDialog(null,"Updated successfully");
                viewTable();
            }
            else
                {

                }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error cannot be updated");
            e.printStackTrace();
        }
    }
    public void viewTable()
    {
        tbl_customer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        tbl_customer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("cname"));
        tbl_customer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_customer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbl_customer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("no_of_orders"));

        ICustomerService iCustomerService = new CustomerServiceImpl();
        try {
            List<Customer> allUsers = iCustomerService.findAll();
            tbl_customer.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void click(MouseEvent event) {

        ArrayList<Customer> customersList =  new ArrayList<>(tbl_customer.getSelectionModel().getSelectedItems());
        for(Customer customer:customersList)
        {
            txt_customer_id.setText(Integer.toString(customer.getCustomer_id()));
            txt_cname.setText(customer.getCname());
            txt_mobile.setText(customer.getMobile());
            txt_address.setText(customer.getAddress());
            txt_no_of_orders.setText(Integer.toString(customer.getNo_of_orders()));

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
    }

    public void reset()
    {
        txt_customer_id.setText("");
        txt_cname.setText("");
        txt_mobile.setText("");
        txt_address.setText("");
        txt_no_of_orders.setText("");
        txt_searchCustomerID.setText("");

    }
    @FXML
    void back(MouseEvent event) throws IOException {
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
    }
}
