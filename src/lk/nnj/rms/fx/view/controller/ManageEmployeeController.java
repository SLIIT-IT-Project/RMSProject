package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.User;
import lk.nnj.rms.fx.service.IUser;
import lk.nnj.rms.fx.service.Impl.UserServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXTextField txt_fullname;

    @FXML
    private JFXTextField txt_address;

    @FXML
    private JFXTextField txt_mobile;

    @FXML
    private ImageView img_search;

    @FXML
    private JFXDatePicker txt_jsd;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_attendance;

    @FXML
    private JFXTextField txt_empid;

    @FXML
    private JFXButton btn_print;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXComboBox<String> txt_type;

    @FXML
    private JFXDatePicker txt_dob;

    @FXML
    private JFXTextField txt_pwd;

    @FXML
    private TableView<User> tbl_details;
    private JFXPanel root;


    @FXML
    void add(ActionEvent event) {
        String emp_id, fullname, dob, address, jsd, pwd, type;
        int mobile;
        emp_id = txt_empid.getText();
        System.out.println("Emp-ID"+ emp_id);
        fullname = txt_fullname.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(txt_dob.getValue());
        address = txt_address.getText();
        mobile = Integer.parseInt(txt_mobile.getText());
       // jsd = txt_jsd.getText();
        java.sql.Date jsdDate= java.sql.Date.valueOf(txt_jsd.getValue());
        pwd = txt_pwd.getText();
        type = txt_type.getSelectionModel().getSelectedItem().toString();
        System.out.print(fullname);
        User user = new User(emp_id, fullname, sqlDate, address, mobile, jsdDate, pwd, type);
        IUser iuser = new UserServiceImpl();

        try {
            if (iuser.add(user)) {
                JOptionPane.showMessageDialog(null, "Success");
                reset();
                viewTable();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Cannot Add.");
            e.printStackTrace();
        }


    }

    @FXML
    void delete(ActionEvent event) {
        String id = txt_empid.getText();

        IUser iUserService = new UserServiceImpl();
        try {
            iUserService.delete(id);
            JOptionPane.showMessageDialog(null, "Deleted");

            reset();
            viewTable();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! Cannot Delete.");

        }

    }

    @FXML
    void search(MouseEvent event) {
        String id = txt_empid.getText();

        IUser iUserService = new UserServiceImpl();
        try {
            User user = iUserService.find(id);
            txt_fullname.setText(user.getFullname());
            txt_dob.setValue(LocalDate.parse(String.valueOf(user.getDob())));
            txt_address.setText(user.getAddress());
            txt_mobile.setText(String.valueOf(user.getMobile()));
            txt_jsd.setValue(LocalDate.parse(String.valueOf(user.getJsd())));
            txt_pwd.setText(user.getPwd());
            txt_type.setValue(user.getType());

            viewTable();



        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Cannot Find.");
            e.printStackTrace();
        }


    }

    @FXML
    void update(ActionEvent event) {
        String emp_id, fullname, dob, address, jsd, pwd, type;
        int mobile;
        emp_id = txt_empid.getText();
        fullname = txt_fullname.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(txt_dob.getValue());
        address = txt_address.getText();
        mobile = Integer.parseInt(txt_mobile.getText());
        java.sql.Date jsdDate= java.sql.Date.valueOf(txt_jsd.getValue());
        pwd = txt_pwd.getText();
        type = txt_type.getSelectionModel().getSelectedItem().toString();

        User user = new User(emp_id, fullname, sqlDate, address, mobile, jsdDate, pwd, type);
        IUser iuser = new UserServiceImpl();

        try {
            if (iuser.update(user)) {
                JOptionPane.showMessageDialog(null, "Updated Success");
                reset();
                viewTable();

            } else {
                JOptionPane.showMessageDialog(null, "Updated Faild.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Cannot Update.");
            e.printStackTrace();
        }

    }

    public void reset() {
        txt_empid.setText("");
        txt_fullname.setText("");
        txt_dob.setValue(LocalDate.parse(String.valueOf(txt_dob.getValue())));
        txt_address.setText("");
        txt_mobile.setText("");
        txt_jsd.setValue(LocalDate.parse(String.valueOf(txt_jsd.getValue())));
        txt_pwd.setText("");
        txt_type.setValue("");
    }

    @FXML
    void attendance(ActionEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/ManageAttendance.fxml"));
        if (root != null){
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(true);
            TranslateTransition tt = new TranslateTransition(Duration.millis(400), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
            
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txt_type.getItems().add("Reciption");
        txt_type.getItems().add("Manager");
        txt_type.getItems().add("Deliver");

        viewTable();


    }
    public void viewTable() {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullname"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("jsd"));
        tbl_details.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("pwd"));

        IUser iUser = new UserServiceImpl();
        try {
            List<User> allUsers = iUser.findAll();
            tbl_details.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewTable(String id) {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullname"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("type"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dob"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("address"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("mobile"));
        tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("jsd"));
        tbl_details.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("pwd"));
        IUser iUserService = new UserServiceImpl();
        try {
            User user = iUserService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(user));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    void tbl_click(MouseEvent event) {
        ArrayList<User> userList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(User user:userList)
        {
            txt_empid.setText(user.getEmp_id());
            txt_fullname.setText(user.getFullname());
            txt_dob.setValue(LocalDate.parse(String.valueOf(txt_dob.getValue())));
            txt_address.setText(user.getAddress());
            txt_mobile.setText(String.valueOf(user.getMobile()));
            txt_jsd.setValue(LocalDate.parse(String.valueOf(txt_jsd.getValue())));
            txt_pwd.setText(user.getPwd());
            txt_type.setValue(user.getType());
        }

    }

}
