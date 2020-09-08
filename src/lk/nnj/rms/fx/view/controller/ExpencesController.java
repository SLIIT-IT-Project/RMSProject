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
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.nnj.rms.fx.model.Expences;
import lk.nnj.rms.fx.service.IExpencesService;;
import lk.nnj.rms.fx.service.Impl.ExpencesServiceImpl;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

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
    private JFXTextField txt_id;

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
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/"+ui));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    void add(ActionEvent event) throws ParseException {
        String id,expenType,desc,value;

        id = txt_id.getText();
        expenType = combo_expenType.getSelectionModel().getSelectedItem().toString();
        desc = txt_desc.getText();
        value = txt_val.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(pick_date.getValue());




        int idnew = Integer.parseInt(id);
        Expences expences = new Expences(idnew,expenType,desc,value,sqlDate);

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            iExpencesService.add(expences);
            JOptionPane.showMessageDialog(null,"Success, added");
            int year=getYear();
            int month=getMonth();
            viewTable(year,month);
            reset();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null,"Error!!! can not add");
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        String id = txt_id.getText();
        int idnew = Integer.parseInt(id);

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            iExpencesService.delete(idnew);
            JOptionPane.showMessageDialog(null,"Success, deleted");
            viewTable(getYear(),getMonth()+1);
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

        iD = txt_id.getText();
        ExpenType =  combo_expenType.getSelectionModel().getSelectedItem().toString();
        dEsc = txt_desc.getText();
        vAlue = txt_val.getText();
        java.sql.Date sqlDate = java.sql.Date.valueOf(pick_date.getValue());


        int idnew = Integer.parseInt(iD);
        Expences expences = new Expences(idnew,ExpenType,dEsc,vAlue,sqlDate);

        IExpencesService iExpencesService = new ExpencesServiceImpl();

        try {
            if(iExpencesService.update(expences)){
                JOptionPane.showMessageDialog(null,"Updated, added");
                reset();
                viewTable(getYear(),getMonth()+1);
            }else{
                JOptionPane.showMessageDialog(null,"Error!!! can not update");
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }




    public void reset(){
        txt_id.setText("");
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
        combo_expenType.getItems().add("sd 1");
        combo_expenType.getItems().add("sd 2");
        combo_expenType.getItems().add("sd 3");
        int year=getYear();
        int month=getMonth();
        setMonth(month);
        viewTable(year,month);

    }

    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<Expences> ExpencesList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(Expences expences:ExpencesList){
            txt_id.setText(Integer.toString(expences.getId()));
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
}
