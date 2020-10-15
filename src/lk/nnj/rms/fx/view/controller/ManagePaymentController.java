package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import lk.nnj.rms.fx.model.Payment;
import lk.nnj.rms.fx.service.IPaymentService;
import lk.nnj.rms.fx.service.Impl.PaymentServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagePaymentController implements Initializable {

    private static String[] columns = {"PID","Amount","DateTime","Status","Type","Description","OID"};



    @FXML
    private JFXDatePicker txt_date;

    @FXML
    private JFXTimePicker txt_time;

    @FXML
    private PieChart chart;

    @FXML
    private PieChart chart2;

    @FXML
    private JFXButton btn_chart;

    @FXML
    public JFXComboBox<String> cb_status;

    @FXML
    public JFXComboBox<String> cb_type;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_reset;

    @FXML
    private JFXTextField txt_pid;

    @FXML
    private TableView<Payment> tbl_details;


    @FXML
    private JFXTextField txt_amount;

    @FXML
    private JFXTextField txt_description;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXButton btn_report;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXTextField txt_oid;

    @FXML
    private JFXButton btn_back;

    @FXML
    private JFXButton btn_demo;

    @FXML
    void back(ActionEvent event) {
        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.close();
    }

    @FXML
    void resetButton(ActionEvent event) {
        reset();
        viewTable();
    }

    @FXML
    void add(ActionEvent event) {
        String pid, amount, datetime, status, type, description, oid;

        pid = txt_pid.getText();
        amount = txt_amount.getText();
        //datetime = txt_datetime.getText();
        status = cb_status.getValue();
        type = cb_type.getValue();
        description = txt_description.getText();
        oid = txt_oid.getText();

        ///////////////////////

        LocalDate d1 = txt_date.getValue();
        LocalTime t1 = txt_time.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = d1.toString() +" "+ t1.toString();
        LocalDateTime ld = LocalDateTime.parse(date,formatter);





        ///////////////////////////

        if(pid.equals("")) {
            JOptionPane.showMessageDialog(null, "Empty PID !!!");
            return;
        }else if(amount.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Amount !!!");
            return;
        }else if(d1.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Date !!!");
            return;
        }else if(t1.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Time !!!");
            return;
        }else if(status.equals("-") || status.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Status !!!");
            return;
        }else if(type.equals("-") || type.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Type !!!");
            return;
        }else if(oid.equals("")) {
            JOptionPane.showMessageDialog(null, "Empty OID !!!");
            return;
        }

        try {
            int pidnew = Integer.parseInt(pid);
            int oidnew = Integer.parseInt(oid);
            float amountnew = Float.parseFloat(amount);
            //Timestamp datetimenew = Timestamp.valueOf(datetime);

            if(pidnew < 0 || oidnew < 0 || amountnew <0.0){
                JOptionPane.showMessageDialog(null, "Can not enter minus values !!!");
                return;
            }


            Payment payment = new Payment(pidnew, amountnew, ld, status, type, description, oidnew);

            IPaymentService ipayment = new PaymentServiceImpl();

            try {
                ipayment.add(payment);
                JOptionPane.showMessageDialog(null, "Successfully Added");
                viewTable();
                reset();
            }catch (Exception e) {

                JOptionPane.showMessageDialog(null, "Error !!! can not add");
                e.printStackTrace();
            }
        }catch (NumberFormatException ne){
            JOptionPane.showMessageDialog(null, "Error !!! Invalid values");
            ne.printStackTrace();
        }catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(null, "Error !!! Invalid values");
            ie.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        int pid = Integer.parseInt(txt_pid.getText());

        IPaymentService iPaymentService = new PaymentServiceImpl();

        try {
            iPaymentService.delete(pid);
            JOptionPane.showMessageDialog(null, "Success, deleted");
            viewTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error!!! can not delete");
            e.printStackTrace();
        }
    }

    @FXML
    void search(ActionEvent event) {
        String pid = txt_search.getText();
        int pidnew = Integer.parseInt(pid);

        IPaymentService iPaymentService = new PaymentServiceImpl();

        try {
            Payment payment = iPaymentService.find(Integer.parseInt(pid));
            txt_pid.setText(String.valueOf(payment.getPID()));
            txt_amount.setText(String.valueOf(payment.getAmount()));
            //txt_datetime.setText(String.valueOf(payment.getDateTime()));
            ////////////////////////////
            String dateTime = String.valueOf(payment.getDateTime());
            String date = dateTime.substring(0,10);
            String time = dateTime.substring(11,16);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
            txt_date.setValue(LocalDate.parse(date,formatter));
            txt_time.setValue(LocalTime.parse(time,formatter1));
            /////////////////////////////

            cb_status.setValue(payment.getStatus());
            cb_type.setValue(payment.getType());
            txt_description.setText(payment.getDescription());
            txt_oid.setText(String.valueOf(payment.getOID()));
            viewTable(pidnew);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error!!! wrong");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String pid, amount, datetime, status, type, description, oid;
        pid = txt_pid.getText();

        amount = txt_amount.getText();
        //datetime = txt_datetime.getText();
        status = cb_status.getValue();
        type = cb_type.getValue();
        description = txt_description.getText();
        oid = txt_oid.getText();

        ///////////////////////

        LocalDate d1 = txt_date.getValue();
        LocalTime t1 = txt_time.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String date = d1.toString() +" "+ t1.toString();
        LocalDateTime ld = LocalDateTime.parse(date,formatter);





        ///////////////////////////

        if(pid.equals("")) {
            JOptionPane.showMessageDialog(null, "Empty PID !!!");
            return;
        }else if(amount.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Amount !!!");
            return;
        }else if(d1.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Date !!!");
            return;
        }else if(t1.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Time !!!");
            return;
        }else if(status.equals("-") || status.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Status !!!");
            return;
        }else if(type.equals("-") || type.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Type !!!");
            return;
        }else if(oid.equals("")) {
            JOptionPane.showMessageDialog(null, "Empty OID !!!");
            return;
        }

        try{
            int pidnew = Integer.parseInt(pid);
            int oidnew = Integer.parseInt(oid);
            float amountnew = Float.parseFloat(amount);
            //Timestamp datetimenew = Timestamp.valueOf(datetime);

            if(pidnew < 0 || oidnew < 0 || amountnew <0.0){
                JOptionPane.showMessageDialog(null, "Can not enter minus values !!!");
                return;
            }

            Payment payment = new Payment(pidnew, amountnew, ld, status, type, description, oidnew);

            IPaymentService iPaymentService = new PaymentServiceImpl();

            try {
                if (iPaymentService.update(payment)) {
                    JOptionPane.showMessageDialog(null, "Updated, added");
                    reset();
                    viewTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Error!!! can not update");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error!!! can not update");

                e.printStackTrace();
            }
        }catch (NumberFormatException ne){
            JOptionPane.showMessageDialog(null, "Error !!! Invalid values");
            ne.printStackTrace();
        }catch (IllegalArgumentException ie) {
            JOptionPane.showMessageDialog(null, "Error !!! Invalid values");
            ie.printStackTrace();
        }


    }


    public void reset() {
        txt_pid.setText("");
        txt_amount.setText("");
        //txt_datetime.setText("");
        txt_date.setValue(null);
        txt_time.setValue(null);
        txt_search.setText("");
        cb_type.setValue("-");
        cb_status.setValue("-");
        txt_description.setText("");
        txt_oid.setText("");
    }

    public void viewTable() {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Amount"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Status"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Type"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Description"));
        tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("OID"));


        IPaymentService iPaymentService = new PaymentServiceImpl();

        try {
            List<Payment> allPayments = iPaymentService.findAll();
            tbl_details.setItems(FXCollections.observableArrayList(allPayments));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();

        cb_status.getItems().add("Pending");
        cb_status.getItems().add("Paid");

        cb_type.getItems().add("Credit");
        cb_type.getItems().add("Debit");
        cb_type.getItems().add("Cash");
        cb_type.getItems().add("Other");
    }

    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<Payment> paymentList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for (Payment payment : paymentList) {
            txt_pid.setText(String.valueOf(payment.getPID()));
            txt_amount.setText(Float.toString(payment.getAmount()));
            //txt_datetime.setText(String.valueOf(payment.getDateTime()));
            /////////////////////

            String dateTime = String.valueOf(payment.getDateTime());
            String date = dateTime.substring(0,10);
            String time = dateTime.substring(11,16);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("HH:mm");
            txt_date.setValue(LocalDate.parse(date,formatter));
            txt_time.setValue(LocalTime.parse(time,formatter1));


            ///////////////////




            cb_status.setValue(payment.getStatus());
            cb_type.setValue(payment.getType());
            txt_description.setText(payment.getDescription());
            txt_oid.setText(String.valueOf(payment.getOID()));

        }
    }

    public void viewTable(int id) {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("Amount"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("Status"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Type"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Description"));
        tbl_details.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("OID"));


        IPaymentService iPaymentService = new PaymentServiceImpl();

        try {
            Payment payment = iPaymentService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(payment));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    @FXML
    void generateReport(MouseEvent event) throws Exception {

        IPaymentService iPaymentService = new PaymentServiceImpl();
        List<Payment> allPayments = iPaymentService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Payment Details");

        Font headerFont =  workbook.createFont();
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

        for(Payment payment : allPayments)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(payment.getPID());
            row.createCell(1).setCellValue(payment.getAmount());
            row.createCell(2).setCellValue(payment.getDateTime().toString());
            row.createCell(3).setCellValue(payment.getStatus());
            row.createCell(4).setCellValue(payment.getType());
            row.createCell(5).setCellValue(payment.getDescription());
            row.createCell(6).setCellValue(payment.getOID());

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

    public void ShowChart()
    {
        IPaymentService iPaymentService = new PaymentServiceImpl();
        try {
            List<Payment> payList = iPaymentService.findAll();
            List<Payment> payList2 = iPaymentService.findAll();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList();

            int countPending = 0;
            int countPaid = 0;

            int countCredit = 0;
            int countDebit = 0;
            int countCash = 0;
            int countOther = 0;

            for(Payment pay:payList)
            {
                if(pay.getStatus().equals("Pending")){
                    countPending +=1;
                }else{
                    countPaid +=1;
                }

            }

            for (Payment pay : payList2) {
                if (pay.getType().equals("Credit")) {
                    countCredit += 1;
                } else if(pay.getType().equals("Debit")){
                    countDebit += 1;
                }else if(pay.getType().equals("Cash")){
                    countCash += 1;
                }else{
                    countOther += 1;
                }

            }

            pieChartData.add(new PieChart.Data("Pending",countPending));
            pieChartData.add(new PieChart.Data("Paid",countPaid));
            chart.setData(pieChartData);

            pieChartData2.add(new PieChart.Data("Credit", countCredit));
            pieChartData2.add(new PieChart.Data("Debit", countDebit));
            pieChartData2.add(new PieChart.Data("Cash", countCash));
            pieChartData2.add(new PieChart.Data("Other", countOther));
            chart2.setData(pieChartData2);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDemo() {
        txt_pid.setText("1000001");
        txt_amount.setText("3000.0");
        txt_date.setValue(LocalDate.of(2020,10,10));
        txt_time.setValue(LocalTime.of(6,20));
        cb_status.setValue("Paid");
        cb_type.setValue("Credit");
        txt_description.setText("Successfully Done");
        txt_oid.setText("2222222");

    }

}