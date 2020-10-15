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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Attendance;
import lk.nnj.rms.fx.model.User;
import lk.nnj.rms.fx.service.IAttendance;
import lk.nnj.rms.fx.service.IUser;
import lk.nnj.rms.fx.service.Impl.AttendanceServiceImpl;
import lk.nnj.rms.fx.service.Impl.UserServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
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
    private JFXComboBox<String> cmb_type;

    @FXML
    private JFXDatePicker txt_date;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private Label lbl_workingh;

    @FXML
    private Label lbl_oth;

    @FXML
    private JFXButton demo;

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
//                JOptionPane.showMessageDialog(null, "Success");
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Added");
                alert1.setContentText("Data you entered has been added.");
                alert1.showAndWait();
                reset();
                viewTable();
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error! Cannot Add.");
//            e.printStackTrace();
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Cannot Add.");
            alert1.setContentText("Invalid Credentials");
            alert1.showAndWait();
        }
    }


    @FXML
    void delete(ActionEvent event) {
        String id = txt_empid.getText();

        IAttendance iAttendance = new AttendanceServiceImpl();
        try {
//            iAttendance.delete(id);
//            JOptionPane.showMessageDialog(null, "Deleted");
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText("Deleted");
            alert1.setContentText("Data you selected has been deleted.");
            alert1.showAndWait();

            reset();
            viewTable();

        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Error! Cannot Delete.");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Cannot Delete.");
            alert1.setContentText("Invalid Credentials");
            alert1.showAndWait();

        }

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
//            JOptionPane.showMessageDialog(null, "Error! Cannot Find.");
//            e.printStackTrace();
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Cannot Find.");
            alert1.setContentText("Invalid Credentials");
            alert1.showAndWait();
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
//                JOptionPane.showMessageDialog(null, "Updated Success");
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Updated");
                alert1.setContentText("Data you selected has been updated.");
                alert1.showAndWait();
                reset();
                viewTable();

            } else {
//                JOptionPane.showMessageDialog(null, "Updated Faild.");
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Update Faild.");
                alert1.setContentText("Invalid Credentials");
                alert1.showAndWait();
            }
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error! Cannot Update.");
//            e.printStackTrace();
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Cannot Update.");
            alert1.setContentText("Invalid Credentials");
            alert1.showAndWait();
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
            txt_date.setValue(LocalDate.parse(String.valueOf(attendance.getDate())));
            txt_workingh.setText(String.valueOf(attendance.getWorking_h()));
            txt_oth.setText(String.valueOf(attendance.getOt_h()));
        }

        IAttendance iAttendance= new AttendanceServiceImpl();

        try{
            int year=getYear();
            int month=getMonth();

            int ot_h=iAttendance.findOt_h(txt_empid.getText(),year,month);
            int w_h=iAttendance.findWorking_h(txt_empid.getText(),year,month);

            lbl_oth.setText(String.valueOf(ot_h));
            lbl_workingh.setText(String.valueOf((w_h)));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private int getYear(){
        Date date=new Date();
        LocalDate localDate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        return year;
    };

    private int getMonth(){
        Date date=new Date();
        LocalDate localDate=date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return month;
    };


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        viewTable();

    }
    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource( "/lk/nnj/rms/fx/view/style/ManageEmployee.fxml"));
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

    private static String[] columns = {"Employee ID","Employee Name","Date","Working Hours","OT Hours"};

    @FXML
    void print(ActionEvent event) throws Exception {
        IAttendance iAttendance = new AttendanceServiceImpl();
        List<Attendance> allItems = iAttendance.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Item Details");
        Font headerFont = workbook.createFont();
        ((Font) headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);
        for(int i=0 ; i<columns.length; i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }
        int rowNum=1;
        for(Attendance attendance : allItems)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(attendance.getEmp_id());
            row.createCell(1).setCellValue(attendance.getFullname());
            row.createCell(2).setCellValue(attendance.getDate().toString());
            row.createCell(3).setCellValue(attendance.getWorking_h());
            row.createCell(4).setCellValue(attendance.getOt_h());

        }
        for(int i =0; i<columns.length; i++)
        {
            sheet.autoSizeColumn(i);
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export to Excel");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Excel Workbook (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(root.getScene().getWindow());
        if (file != null)
        {
            String path = file.getAbsolutePath();
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }

    }

    @FXML
    void demo(ActionEvent event) {
        txt_empid.setText("Emp01");
        txt_name.setText("Namal Rathnayake");
        txt_date.setValue(LocalDate.of(2020,10,12));
        txt_workingh.setText("5");
        txt_oth.setText("3");

    }

}




