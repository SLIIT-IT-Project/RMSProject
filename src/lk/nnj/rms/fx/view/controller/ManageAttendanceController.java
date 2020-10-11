package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.nnj.rms.fx.model.Attendance;
import lk.nnj.rms.fx.service.IAttendance;
import lk.nnj.rms.fx.service.IUser;
import lk.nnj.rms.fx.service.Impl.AttendanceServiceImpl;
import lk.nnj.rms.fx.service.Impl.UserServiceImpl;

import javax.swing.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageAttendanceController implements Initializable {

    @FXML
    private ImageView lbl_back;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_empid;

    @FXML
    private JFXTextField txt_workingh;

    @FXML
    private JFXTextField txt_oth;

    @FXML
    private JFXButton btn_add;

    @FXML
    private TableView<Attendance> tbl_attendance;

    @FXML
    private JFXButton btn_print;

    @FXML
    private JFXComboBox<?> cmb_type;

    @FXML
    private JFXDatePicker txt_date;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    void add(ActionEvent event) {
        String emp_id, fullname, date;
        int working_h, ot_h;
        emp_id = txt_empid.getText();
        fullname = txt_name.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(txt_date.getValue());
        working_h = Integer.parseInt(txt_workingh.getText());
        ot_h = Integer.parseInt(txt_oth.getText());

        Attendance attendance = new Attendance(emp_id, fullname, sqlDate, working_h, ot_h);
        IAttendance iAttendance = new AttendanceServiceImpl();

        try {
            if (iAttendance.add(attendance)) {
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

        IAttendance iAttendance = new AttendanceServiceImpl();
        try {
            iAttendance.delete(id);
            JOptionPane.showMessageDialog(null, "Deleted");

            reset();
            viewTable();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error! Cannot Delete.");

        }

    }

    @FXML
    void print(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {
        String id = txt_empid.getText();

        IAttendance iAttendance = new AttendanceServiceImpl();
        try {
            Attendance attendance = iAttendance.find(id);
            txt_name.setText(attendance.getFullname());
            txt_date.setValue(LocalDate.parse(String.valueOf(attendance.getDate())));
            txt_workingh.setText(String.valueOf(attendance.getWorking_h()));
            txt_oth.setText(String.valueOf(attendance.getOt_h()));

            viewTable();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Cannot Find.");
            e.printStackTrace();
        }

    }


    @FXML
    void update(ActionEvent event) {
        String emp_id, fullname, date;
        int working_h, ot_h;
        emp_id = txt_empid.getText();
        fullname = txt_name.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(txt_date.getValue());
        working_h = Integer.parseInt(txt_workingh.getText());
        ot_h = Integer.parseInt(txt_oth.getText());

        Attendance attendance = new Attendance(emp_id, fullname, sqlDate, working_h, ot_h);
        IAttendance iAttendance = new AttendanceServiceImpl();

        try {
            if (iAttendance.update(attendance)) {
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

    private void viewTable() {
        tbl_attendance.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tbl_attendance.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullname"));
        tbl_attendance.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_attendance.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("working_h"));
        tbl_attendance.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("ot_h"));

        IAttendance iAttendance = new AttendanceServiceImpl();
        try {
            List<Attendance> allUsers = iAttendance.findAll();
            tbl_attendance.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewTable(String id) {
        tbl_attendance.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("emp_id"));
        tbl_attendance.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("fullname"));
        tbl_attendance.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("date"));
        tbl_attendance.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("working_h"));
        tbl_attendance.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("ot_h"));
        IUser iUserService = new UserServiceImpl();
        IAttendance iAttendance = new AttendanceServiceImpl();
        try {
            List<Attendance> allUsers = iAttendance.findAll();
            tbl_attendance.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        txt_empid.setText("");
        txt_name.setText("");
        txt_date.setValue(LocalDate.parse(String.valueOf(txt_date.getValue())));
        txt_workingh.setText("");
        txt_oth.setText("");
    }


    @FXML
    void tbl_clck(MouseEvent event) {
        ArrayList<Attendance> attendancesList = new ArrayList<>(tbl_attendance.getSelectionModel().getSelectedItems());

        for (Attendance attendance : attendancesList) {
            txt_empid.setText(attendance.getEmp_id());
            txt_name.setText(attendance.getFullname());
            txt_date.setValue(LocalDate.parse(String.valueOf(txt_date.getValue())));
            txt_workingh.setText(String.valueOf(attendance.getWorking_h()));
            txt_oth.setText(String.valueOf(attendance.getOt_h()));
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        cmb_type.getItems().add("Reciption");
//        cmb_type.getItems().add("Manager");
//        cmb_type.getItems().add("Deliver");


        viewTable();

    }
    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManageEmployee.fxml"));
        if(root != null)
        {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(true);
            TranslateTransition tt = new TranslateTransition(Duration.millis(450),subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }
}




