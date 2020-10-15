package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.nnj.rms.fx.model.EmployeeSalaryRate;
import lk.nnj.rms.fx.service.ISalaryrateService;
import lk.nnj.rms.fx.service.Impl.SalaryrateServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EmployeeSalaryRateController  implements Initializable {

    @FXML
    private JFXTextField txt_bsal;

    @FXML
    private JFXTextField txt_hourrate;

    @FXML
    private JFXTextField txt_otrate;

    @FXML
    private JFXComboBox<String> combo_emptype;

    @FXML
    private Label lb_id;


    @FXML
    private TableView<EmployeeSalaryRate> tbl_details;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_update;


    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_expences;

    @FXML
    private AnchorPane rootpane;


    @FXML
    void summary(ActionEvent event) throws IOException {
        LoadUi("FinanceManage.fxml");
    }

    @FXML
    void salary(ActionEvent event) throws IOException {
        LoadUi("CalculateSalary.fxml");
    }
    @FXML
    void expences(ActionEvent event) throws IOException {
        LoadUi("CalculateExpencess.fxml");
    }
    @FXML
    void rate(ActionEvent event) throws IOException {
        LoadUi("EmployeeSalaryRate.fxml");
    }

    @FXML
    void reports(ActionEvent event) throws IOException {
        LoadUi("FinanceReport.fxml");
    }

    private void LoadUi(String ui) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/"+ui));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    void add(ActionEvent event) {
        String id,empType,basicSal,otRate,hourRate;
        addNewExpences();
        id = lb_id.getText();
        empType = combo_emptype.getSelectionModel().getSelectedItem().toString();
        basicSal = txt_bsal.getText();
        otRate = txt_hourrate.getText();
        hourRate = txt_otrate.getText();

    /*    if(empType.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Select a Employee Type");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
       else if(Integer.parseInt(basicSal)<=0){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Enter possible Value to Basic Salary");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
        else if(Double.parseDouble(otRate)<=0){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Enter possible Value to OT Rate");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
        else if(Double.parseDouble(hourRate)<=0){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Enter possible Value to Hour Rate!");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }*/


            int idnew = Integer.parseInt(id);
            EmployeeSalaryRate emprate = new EmployeeSalaryRate(idnew, empType, basicSal, otRate, hourRate);

            ISalaryrateService iFinance = new SalaryrateServiceImpl();

            try {
                replace(empType);
                iFinance.add(emprate);
                JOptionPane.showMessageDialog(null, "Success, added");
                viewTable();
                reset();
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error!!! can not add");
                e.printStackTrace();
            }

    }

    @FXML
    void delete(ActionEvent event) {
        String id = lb_id.getText();
        int idnew = Integer.parseInt(id);

        ISalaryrateService iFinance = new SalaryrateServiceImpl();

        try {
            iFinance.delete(idnew);
            JOptionPane.showMessageDialog(null,"Success, deleted");
            viewTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error!!! can not delete");
            e.printStackTrace();
        }
    }

    @FXML
    void search(ActionEvent event) {
        String id = lb_id.getText();
        int idnew = Integer.parseInt(id);

        ISalaryrateService iFinance = new SalaryrateServiceImpl();

        try {
            EmployeeSalaryRate emprate= iFinance.find(idnew);
            combo_emptype.setValue(emprate.getEmpType());
            txt_bsal.setText(emprate.getBasicSal());
            txt_otrate.setText(emprate.getOtRate());
            txt_hourrate.setText(emprate.getHourRate());
            viewTable(idnew);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!!! wrong");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String id,empType,basicSal,otRate,hourRate;
        id = lb_id.getText();
        empType = combo_emptype.getSelectionModel().getSelectedItem().toString();
        basicSal = txt_bsal.getText();
        otRate = txt_otrate.getText();
        hourRate = txt_hourrate.getText();

        int idnew = Integer.parseInt(id);
        EmployeeSalaryRate emprate = new EmployeeSalaryRate(idnew,empType,basicSal,otRate,hourRate);

        ISalaryrateService iFinance = new SalaryrateServiceImpl();

        try {
            if(iFinance.update(emprate)){
                JOptionPane.showMessageDialog(null,"Updated, added");
                reset();
                viewTable();
            }else{
                JOptionPane.showMessageDialog(null,"Error!!! can not update");
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }



    public void reset(){
        addNewExpences();
        combo_emptype.setValue("");
        txt_bsal.setText("");
        txt_otrate.setText("");
        txt_hourrate.setText("");
    }

    public void viewTable(){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("empType"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("basicSal"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("otRate"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("hourRate"));

        ISalaryrateService iFinance = new SalaryrateServiceImpl();

        try {
            List<EmployeeSalaryRate> allUsers = iFinance.findAll();
            tbl_details.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo_emptype.getItems().add("Receptionist");
        combo_emptype.getItems().add("Chef");
        combo_emptype.getItems().add("Cleaner");
        combo_emptype.getItems().add("Deliver");
        combo_emptype.getItems().add("Waiter");
        combo_emptype.getItems().add("Manager");
        addNewExpences();
        viewTable();
    }

    public void addNewExpences() {

        ISalaryrateService iService = new SalaryrateServiceImpl();
        try {
            int Id = iService.getInvoiceNo();
            lb_id.setText(String.valueOf(Id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void replace(String type) {

        ISalaryrateService iService = new SalaryrateServiceImpl();
        try {
            iService.replace(type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<EmployeeSalaryRate> EmpRateList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(EmployeeSalaryRate emprate:EmpRateList){
            lb_id.setText(Integer.toString(emprate.getId()));
            combo_emptype.setValue(emprate.getEmpType());
            txt_bsal.setText(emprate.getBasicSal());
            txt_otrate.setText(emprate.getOtRate());
            txt_hourrate.setText(emprate.getHourRate());
        }
    }

    public void viewTable(int id){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("empType"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("basicSal"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("otRate"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("hourRate"));

        ISalaryrateService iSalaryrateService = new SalaryrateServiceImpl();

        try {
            EmployeeSalaryRate emprate = iSalaryrateService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(emprate));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String[] columns = {"ID","EmpType","BasicSalary","OTRate","HourlyRate"};

    @FXML
    void report(MouseEvent event) throws Exception{

        ISalaryrateService iSalaryrateService = new SalaryrateServiceImpl();
        List<EmployeeSalaryRate> allItems = iSalaryrateService.findAll();

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Item Details");

        Font headerFont = workbook.createFont();

        ((Font)headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);
        Row headerRow = sheet.createRow(0);

        for(int i=0;i< columns.length;i++){
            Cell cell=headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum=1;
        for(EmployeeSalaryRate rate:allItems){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rate.getId());
            row.createCell(1).setCellValue(rate.getEmpType());
            row.createCell(2).setCellValue(rate.getBasicSal());
            row.createCell(3).setCellValue(rate.getOtRate());
            row.createCell(4).setCellValue(rate.getHourRate());
        }

        for(int i = 0; i<columns.length; i++){
            sheet.autoSizeColumn(i);
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export to Excel");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Excel workbook (*.xlsx)","*.xlsx");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(rootpane.getScene().getWindow());

        if(file!=null){
            String path =file.getAbsolutePath();
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }
    }



}

