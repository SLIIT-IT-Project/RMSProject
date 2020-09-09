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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Supplier;
import lk.nnj.rms.fx.service.ISupplierService;
import lk.nnj.rms.fx.service.Impl.SupplierDetailsImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageSupplierController implements Initializable {


    @FXML
    private JFXTextField txtSName;

    @FXML
    private JFXTextField txtCompny;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPNo;

    @FXML
    private JFXTextField txtDis;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnadd;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private JFXButton btnprint;

    @FXML
    private TableView<Supplier> TableDetailsSupplier;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXTextField txtSId;

    @FXML
    private AnchorPane root;

    @FXML
    void add(ActionEvent event) {

        String SId,Name,Email,Company,PhoneNo,Discreption,Address;

        SId =txtSId.getText();
        Name=txtSName.getText();
        Email=txtEmail.getText();
        Company=txtCompny.getText();
        PhoneNo=txtPNo.getText();
        Discreption=txtDis.getText();
        Address=txtAddress.getText();

        int id =Integer.parseInt(SId);

        Supplier supplier=new Supplier(id,Name,Email,Company,PhoneNo,Discreption,Address);
        ISupplierService isupplier = new SupplierDetailsImpl();
        try {
            isupplier.add(supplier);
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

        String SId=txtSId.getText();
        int id =Integer.parseInt(SId);

        ISupplierService isupplier = new SupplierDetailsImpl();

        try {
            isupplier.delete(id);
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

        String SId=txtSId.getText();
        int id = Integer.parseInt(SId);

        ISupplierService isupplier = new SupplierDetailsImpl();
        try {
            Supplier supplier = isupplier.find(id);
            txtSName.setText(supplier.getSName());
            txtEmail.setText(supplier.getEmail());
            txtCompny.setText(supplier.getCompany());
            txtPNo.setText(supplier.getPhoneNo());
            txtDis.setText(supplier.getDiscreption());
            txtAddress.setText(supplier.getAddress());
            viewTable(id);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! No Information");
            e.printStackTrace();
        }

    }

    @FXML
    void update(ActionEvent event) {

        String SId,SName,Email,Company,PhoneNo,Discreption,Address;

        SId =txtSId.getText();
        SName=txtSName.getText();
        Email=txtEmail.getText();
        Company=txtCompny.getText();
        PhoneNo=txtPNo.getText();
        Discreption=txtDis.getText();
        Address=txtAddress.getText();

        int id =Integer.parseInt(SId);

        Supplier supplier=new Supplier(id,SName,Company,PhoneNo,Email,Address,Discreption);
        ISupplierService isupplier = new SupplierDetailsImpl();
        try {
            if(isupplier.update(supplier))
            {
                JOptionPane.showMessageDialog(null, "Update Success");
                reset ();
                viewTable();
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Update Faild");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! can't Update");
            e.printStackTrace();
        }

    }

    public void reset ()
    {

        txtSId.setText(" ");
        txtSName.setText(" ");
        txtEmail.setText(" ");
        txtCompny.setText(" ");
        txtPNo.setText(" ");
        txtDis.setText(" ");
        txtAddress.setText(" ");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        viewTable();
    }



    public void viewTable() {
        TableDetailsSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SId"));
        TableDetailsSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("SName"));
        TableDetailsSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Email"));
        TableDetailsSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        TableDetailsSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("company"));
        TableDetailsSupplier.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));
        TableDetailsSupplier.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("discreption"));


        ISupplierService isupplier = new SupplierDetailsImpl();
        try {
            List<Supplier> allSupplier = isupplier.findAll();
            TableDetailsSupplier.setItems(FXCollections.observableArrayList(allSupplier));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        public void viewTable(int SId)
        {
            TableDetailsSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SId"));
            TableDetailsSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("SName"));
            TableDetailsSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Email"));
            TableDetailsSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
            TableDetailsSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("company"));
            TableDetailsSupplier.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));
            TableDetailsSupplier.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("discreption"));


            ISupplierService isupplier = new SupplierDetailsImpl();
            try {
                List<Supplier> allSupplier = isupplier.findAll();
                TableDetailsSupplier.setItems(FXCollections.observableArrayList(allSupplier));
            } catch (Exception e) {
                e.printStackTrace();
            }

    }


    @FXML
    void viewselectsupplier(MouseEvent event) {

        ArrayList<Supplier> userList = new ArrayList<>(TableDetailsSupplier.getSelectionModel().getSelectedItems());
        for (Supplier supplier:userList) {
            txtSId.setText(Integer.toString(supplier.getSId()));
            txtSName.setText(supplier.getSName());
            txtEmail.setText(supplier.getEmail());
            txtCompny.setText(supplier.getCompany());
            txtPNo.setText(supplier.getPhoneNo());
            txtDis.setText(supplier.getDiscreption());
            txtAddress.setText(supplier.getAddress());


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

