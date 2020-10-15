package lk.nnj.rms.fx.view.controller;

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
import lk.nnj.rms.fx.model.Salary_cal;
import lk.nnj.rms.fx.service.ISalaryService;
import lk.nnj.rms.fx.service.Impl.SalaryCalcImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class SalaryController implements Initializable {

    @FXML
    private TableView<Salary_cal> tbl_details;

    @FXML
    private AnchorPane rootpane;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private Label lbl_date;

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


    @FXML
    void refresh(MouseEvent event) throws IOException {
        LoadUi("CalculateSalary.fxml");
    }


    private void LoadUi(String ui) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/" + ui));
        rootpane.getChildren().setAll(pane);
    }


    @FXML
    void search(MouseEvent event) {

        String id = txt_search.getText();
        int idnew = Integer.parseInt(id);

        System.out.println(idnew);
        try {
            SearchTable(idnew,year,month);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error!!! wrong");
        }
    }

    public void SearchTable (int id,int year,int month) {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("EmpType"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("BasicSal"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("OtRate"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("HourRate"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("WorkHours"));
        tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("OtHours"));
        tbl_details.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("TotalSal"));
        System.out.println();

        ISalaryService isalary = new SalaryCalcImpl();

        try {
            Salary_cal salary = isalary.find(id,year,month);
            tbl_details.setItems(FXCollections.observableArrayList(salary));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



  /*      public void viewTable () {
            tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ID"));
            tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("EmpType"));
            tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("BasicSal"));
            tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("OtRate"));
            tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("HourRate"));
            tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("WorkHours"));
            tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("OtHours"));
            tbl_details.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("TotalSal"));
            System.out.println();

            ISalaryService isalary = new SalaryCalcImpl();

            try {
                List<Salary_cal> allUsers = isalary.findSalary();
                tbl_details.setItems(FXCollections.observableArrayList(allUsers));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }*/

    public void viewTable (int year,int month) {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("ID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("EmpType"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("BasicSal"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("OtRate"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("HourRate"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("WorkHours"));
        tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("OtHours"));
        tbl_details.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("TotalSal"));
        System.out.println();

        ISalaryService isalary = new SalaryCalcImpl();

        try {
            List<Salary_cal> allUsers = isalary.findSalary(year,month);
            tbl_details.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

        @Override
        public void initialize (URL location, ResourceBundle resources){
            //viewTable();
             year=getYear();
             month=getMonth();
            setMonth(month);
            viewTable(year,month);
        }

    int c=0;
    int month=0;
    int year=0;
    @FXML
    void backward(MouseEvent event) {

        if (c==0) {
            year = getYear();
            month = getMonth();

        }
        if(month==1)
        {
            year=year-1;
            month=12;
        }
        else{
            month=month-1;
        };
        c++;

        viewTable(year,month);
        setMonth(month);


    }

    private int getYear(){

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year  = localDate.getYear();
        return year;
    };

    private int getMonth(){

        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return month;
    };

    private int setMonth(int month){

        System.out.println(month);

        if(month==0)
        {    month=getMonth();
            setMonth(month);
        }
        else if(month==1){
            lbl_date.setText("January");
        }
        else if(month==2){
            lbl_date.setText("February");
        }
        else if(month==3){
            lbl_date.setText("March");
        }
        else if(month==4){
            lbl_date.setText("April");
        }
        else if(month==5){
            lbl_date.setText("May");
        }
        else if(month==6){
            lbl_date.setText("June");
        }
        else if(month==7){
            lbl_date.setText("July");
        }
        else if(month==8){
            lbl_date.setText("August");
        }
        else if(month==9){
            lbl_date.setText("September");
        }
        else if(month==10){
            lbl_date.setText("October");
        }
        else if(month==11){
            lbl_date.setText("November");
        }
        else if(month==12){
            lbl_date.setText("December");
        }

        return 0;
    };

    @FXML
    void forward(MouseEvent event) {

        if(c!=0){

            if(month==12)
            {
                year=year+1;
                month=1;
            }
            else{
                month=month+1;
            };
            c=c-1;
            viewTable(year,month);
            setMonth(month);
        }


    }

    private static String[] columns = {"ID","Employee Type","Basic Salary","OTRate","HourlyRate","Working Hours","Ot Hours","Salary"};

    @FXML
    void report(MouseEvent event) throws Exception{

        ISalaryService iSalaryService = new SalaryCalcImpl();
        List<Salary_cal> allItems = iSalaryService.findSalary(year,month);

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
        for(Salary_cal rate:allItems){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rate.getID());
            row.createCell(1).setCellValue(rate.getEmpType());
            row.createCell(2).setCellValue(rate.getBasicSal());
            row.createCell(3).setCellValue(rate.getOtRate());
            row.createCell(4).setCellValue(rate.getHourRate());
            row.createCell(5).setCellValue(rate.getWorkHours());
            row.createCell(6).setCellValue(rate.getOtHours());
            row.createCell(7).setCellValue(rate.getTotalSal());
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