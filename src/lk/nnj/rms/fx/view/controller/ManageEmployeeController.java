package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.User;
import lk.nnj.rms.fx.service.IUser;
import lk.nnj.rms.fx.service.Impl.UserServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManageEmployeeController implements Initializable {

    @FXML
    private ImageView lbl_back;

    @FXML
    private AnchorPane root;

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
    private JFXPasswordField txt_pwd;

    @FXML
    private TableView<User> tbl_details;

    @FXML
    private JFXButton demo;


    @FXML
    void add(ActionEvent event) {
        String emp_id, fullname, dob, address, jsd, pwd, type, mobile;
//        int mobile;
        emp_id = txt_empid.getText();
        fullname = txt_fullname.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(txt_dob.getValue());
        address = txt_address.getText();
        mobile = txt_mobile.getText();
//        mobile = Integer.parseInt(txt_mobile.getText());
//        if (txt_mobile.getText().matches("\\\\d{10}|\\\\d{10}")) {
//
//            System.out.println("Its Valid Number");
//            //return true;
//        }else {
//
//            Alert alert1 = new Alert(Alert.AlertType.ERROR);
//            alert1.setTitle("Error Dialog");
//            alert1.setHeaderText("Enter Employee Mobile");
//            alert1.setContentText("Employee Mobile is required");
//            alert1.showAndWait();
//        }
       // jsd = txt_jsd.getText();
        java.sql.Date jsdDate= java.sql.Date.valueOf(txt_jsd.getValue());
        pwd = txt_pwd.getText();
        type = txt_type.getSelectionModel().getSelectedItem().toString();
//        System.out.print(fullname);

        if(emp_id.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee ID");
            alert1.setContentText("Employee ID is required");
            alert1.showAndWait();
        }else if(fullname.equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee Name");
            alert1.setContentText("Employee Name is required");
            alert1.showAndWait();
        }else if(sqlDate.equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee DOB");
            alert1.setContentText("Employee DOB is required");
            alert1.showAndWait();
        }else if(address.equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee Address");
            alert1.setContentText("Employee Address is required");
            alert1.showAndWait();
        }
        else if(txt_mobile.getText().trim().length() !=10)
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter valid Employee Mobile");
            alert1.setContentText("Employee Mobile is required");
            alert1.showAndWait();
        }
        else if(txt_jsd.getValue().equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee Job Started Date");
            alert1.setContentText("Employee JSD is required");
            alert1.showAndWait();
        }else if(pwd.equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee Password");
            alert1.setContentText("Employee Password is required");
            alert1.showAndWait();
        }else if(type.equals(""))
        {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Enter Employee Type");
            alert1.setContentText("Employee Type is required");
            alert1.showAndWait();
        }
        else
        {




            try {

                User user = new User(emp_id, fullname, sqlDate, address, mobile, jsdDate, pwd, type);
                IUser iuser = new UserServiceImpl();

                try {Integer.parseInt(txt_mobile.getText());
                    if (iuser.add(user)) {

                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setTitle("Information Dialog");
                        alert1.setHeaderText("Added");
                        alert1.setContentText("Data you entered has been added.");
                        alert1.showAndWait();
                        reset();
                        viewTable();
                        System.out.print(fullname);

                    }
                }catch(NumberFormatException e){
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialog");
                    alert1.setHeaderText("Enter Valid Mobile Number");
                    alert1.setContentText("Valid Mobile No. is Required.");
                    alert1.showAndWait();
                    System.out.print(fullname);

                }


            } catch (Exception e) {

                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Cannot Add.");
                alert1.setContentText("Invalid Credentials");
                alert1.showAndWait();
            }

        }



    }

    @FXML
    void delete(ActionEvent event) {
        String id = txt_empid.getText();

        IUser iUserService = new UserServiceImpl();
        try {
           iUserService.delete(id);
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
        String emp_id, fullname, dob, address, jsd, pwd, type,mobile;
//        int mobile;
        emp_id = txt_empid.getText();
        fullname = txt_fullname.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(txt_dob.getValue());
        address = txt_address.getText();
        mobile = txt_mobile.getText();
//        mobile = Integer.parseInt(txt_mobile.getText());
        java.sql.Date jsdDate= java.sql.Date.valueOf(txt_jsd.getValue());
        pwd = txt_pwd.getText();
        type = txt_type.getSelectionModel().getSelectedItem().toString();

        User user = new User(emp_id, fullname, sqlDate, address, mobile, jsdDate, pwd, type);
        IUser iuser = new UserServiceImpl();

        try {
            if (iuser.update(user)) {
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
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManageAttendance.fxml"));
        if (root != null){
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
//        tbl_details.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("pwd"));

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
            txt_dob.setValue(LocalDate.parse(String.valueOf(user.getDob())));
            txt_address.setText(user.getAddress());
            txt_mobile.setText(user.getMobile());
            txt_jsd.setValue(LocalDate.parse(String.valueOf(user.getJsd())));
            txt_pwd.setText(user.getPwd());
            txt_type.setValue(user.getType());
        }


    }

    @FXML
    void back(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/AdminPanel.fxml"));
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

    private static String[] columns = {"Employee ID","Employee Name","DOB","Address","Mobile","Job Started Date","Type"};
    @FXML
    void report(ActionEvent event) throws Exception {
        IUser iUser = new UserServiceImpl();
        List<User> allItems = iUser.findAll();
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
        for(User user : allItems)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getEmp_id());
            row.createCell(1).setCellValue(user.getFullname());
            row.createCell(2).setCellValue(user.getDob().toString());
            row.createCell(3).setCellValue(user.getAddress());
            row.createCell(4).setCellValue(user.getMobile());
            row.createCell(5).setCellValue(user.getJsd().toString());
            row.createCell(6).setCellValue(user.getType());
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
            txt_fullname.setText("Namal Rathnayake");
            txt_address.setText("No.23,Rahula Road, Matara");
            txt_mobile.setText("0777561256");
            txt_dob.setValue(LocalDate.of(1996,07,25));
            txt_jsd.setValue(LocalDate.of(2020,05,12));
            txt_type.setValue("Manager");
    }

}
