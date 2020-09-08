package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.nnj.rms.fx.model.EmployeeSalaryRate;
import lk.nnj.rms.fx.service.ISalaryrateService;
import lk.nnj.rms.fx.service.Impl.SalaryrateServiceImpl;


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
    private JFXTextField txt_id;

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
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/"+ui));
        rootpane.getChildren().setAll(pane);
    }

    @FXML
    void add(ActionEvent event) {
        String id,empType,basicSal,otRate,hourRate;
        id = txt_id.getText();
        empType = combo_emptype.getSelectionModel().getSelectedItem().toString();
        basicSal = txt_bsal.getText();
        otRate = txt_hourrate.getText();
        hourRate = txt_otrate.getText();

        int idnew = Integer.parseInt(id);
        EmployeeSalaryRate emprate = new EmployeeSalaryRate(idnew,empType,basicSal,otRate,hourRate);

        ISalaryrateService iFinance = new SalaryrateServiceImpl();

        try {
            iFinance.add(emprate);
            JOptionPane.showMessageDialog(null,"Success, added");
            viewTable();
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
        String id = txt_id.getText();
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
        id = txt_id.getText();
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
        txt_id.setText("");
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
        combo_emptype.getItems().add("sd 1");
        combo_emptype.getItems().add("sd 2");
        combo_emptype.getItems().add("sd 3");
        viewTable();
    }

    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<EmployeeSalaryRate> EmpRateList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(EmployeeSalaryRate emprate:EmpRateList){
            txt_id.setText(Integer.toString(emprate.getId()));
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
}

