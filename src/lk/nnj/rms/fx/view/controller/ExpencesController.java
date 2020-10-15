package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.nnj.rms.fx.model.Expences;
import lk.nnj.rms.fx.service.IExpencesService;
import lk.nnj.rms.fx.service.Impl.ExpencesServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

;

public class ExpencesController implements Initializable {

    @FXML
    private JFXTextField txt_search;

    @FXML
    private TableView<Expences> tbl_details;

    @FXML
    private JFXTextField txt_desc;

    @FXML
    private JFXTextField txt_val;

    @FXML
    private JFXComboBox<String> combo_expenType;


    @FXML
    private Label lb_id;


    @FXML
    private JFXDatePicker pick_date;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_add;

    @FXML
    private Label lbl_date;

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


    @FXML
    void refresh(MouseEvent event) throws IOException {
        LoadUi("CalculateExpencess.fxml");


    }


    private void LoadUi(String ui) throws IOException {
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/"+ui));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    void add(ActionEvent event) throws Exception {
        String id,expenType,desc,value;
        addNewExpences();
        id = lb_id.getText();
        expenType = combo_expenType.getSelectionModel().getSelectedItem().toString();
        desc = txt_desc.getText();
        value = txt_val.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(pick_date.getValue());

        if(expenType.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Select a ExpensesType");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
        else if(desc.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Description");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
        else if(value.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Value");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
        else if(Integer.parseInt(value)<=0){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Value Cannot be Negative!");
            alert1.setContentText("Expenses Details are required");
            alert1.showAndWait();
        }
        else {


            int idnew = Integer.parseInt(id);
            Expences expences = new Expences(idnew, expenType, desc, value, sqlDate);

            IExpencesService iExpencesService = new ExpencesServiceImpl();

            try {Integer.parseInt(txt_val.getText());
                if (iExpencesService.add(expences)) {
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Information Dialog");
                    alert1.setHeaderText("Successfuly Added");
                    alert1.setContentText("Data you entered has been added.");
                    alert1.showAndWait();
                    viewTable(year, month);
                    reset();
                }
               // iExpencesService.add(expences);
               // JOptionPane.showMessageDialog(null, "Success, added");
            }catch(NumberFormatException e){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Enter Valid Mobile Number");
                alert1.showAndWait();
            }
           /* } catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error!!! can not add");
                e.printStackTrace();
            }*/
        }
    }

    @FXML
    void delete(ActionEvent event) {
        String id = lb_id.getText();
        int idnew = Integer.parseInt(id);

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            iExpencesService.delete(idnew);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText("Successfuly Deleted");
            alert1.setContentText("Data you selected has been deleted.");
            alert1.showAndWait();
            viewTable(year,month);
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error!!! can not delete");
            e.printStackTrace();
        }
    }

    // @FXML
    //void search(ActionEvent event) {
    @FXML
    void search(MouseEvent event) {


        String id = txt_search.getText();
        int idnew = Integer.parseInt(id);

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            Expences expences = iExpencesService.find(idnew);
            combo_expenType.setValue(expences.getExpenType());
            txt_desc.setText(expences.getDesc());
            txt_val.setText(expences.getValue());
            //pick_date.setValue(expences.getDate());
            pick_date.setValue(LocalDate.parse(String.valueOf(expences.getDate())));


            viewTable(idnew);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!!! wrong");
        }
    }

    @FXML
    void update(ActionEvent event) throws ParseException {
        String iD,ExpenType,dEsc,vAlue;

        iD = lb_id.getText();
        ExpenType =  combo_expenType.getSelectionModel().getSelectedItem().toString();
        dEsc = txt_desc.getText();
        vAlue = txt_val.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(pick_date.getValue());


        int idnew = Integer.parseInt(iD);
        Expences expences = new Expences(idnew,ExpenType,dEsc,vAlue,sqlDate);

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            if(iExpencesService.update(expences)){
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Successfuly Updated");
                alert1.setContentText("Data you changed has been Updated.");
                alert1.showAndWait();
                viewTable(year,month);
                reset();
            }else{
                JOptionPane.showMessageDialog(null,"Error!!! can not update");
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }




    public void reset(){
        addNewExpences();
        combo_expenType.setValue("");
        txt_desc.setText("");
        txt_val.setText("");
        pick_date.setValue(LocalDate.parse(String.valueOf(pick_date.getValue())));
    }

    public void viewTable(int year, int month){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("expenType"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("desc"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("value"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("date"));

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {

            List<Expences> allUsers = iExpencesService.findAll(year,month);
            tbl_details.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    int c=0;
    int month=0;
    int year=0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        combo_expenType.getItems().add("Maintenance");
        combo_expenType.getItems().add("Stock");
        combo_expenType.getItems().add("Salary");
        combo_expenType.getItems().add("Event");
        combo_expenType.getItems().add("Other");
         year=getYear();
         month=getMonth();
        setMonth(month);
        viewTable(year,month);
        addNewExpences();


    }
    public void addNewExpences() {

        IExpencesService iExpencesService = new ExpencesServiceImpl();
        try {
            int Id = iExpencesService.getInvoiceNo();
            lb_id.setText(String.valueOf(Id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<Expences> ExpencesList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(Expences expences:ExpencesList){
            lb_id.setText(Integer.toString(expences.getId()));
            combo_expenType.setValue(expences.getExpenType());
            txt_desc.setText(expences.getDesc());
            txt_val.setText(expences.getValue());
            //pick_date.setValue(expences);
            pick_date.setValue(LocalDate.parse(String.valueOf(expences.getDate())));
        }
    }

    public void viewTable(int id){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("expenType"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("desc"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("value"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("date"));

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            Expences expences = iExpencesService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(expences));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


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
        reset();


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

    private int setMonth(int smonth){

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

        return month;
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
            reset();
        }


    }

    private static String[] columns = {"ID","ExpencesType","Description","Date","Value"};

    @FXML
    void report(MouseEvent event) throws Exception{

        IExpencesService iExpencesService = new ExpencesServiceImpl();
        List<Expences> allItems = iExpencesService.findAll(year,month);

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
        for(Expences rate:allItems){
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(rate.getId());
            row.createCell(1).setCellValue(rate.getExpenType());
            row.createCell(2).setCellValue(rate.getDesc());
            row.createCell(3).setCellValue(rate.getDate());
            row.createCell(4).setCellValue(rate.getValue());
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
