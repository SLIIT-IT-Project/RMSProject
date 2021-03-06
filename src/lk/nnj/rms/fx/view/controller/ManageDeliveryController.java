package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.nnj.rms.fx.model.Delivery;
import lk.nnj.rms.fx.service.IManageDeliveryService;
import lk.nnj.rms.fx.service.Impl.ManageDeliveryServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class ManageDeliveryController implements Initializable {
    @FXML
    private JFXButton btn_Delete;

    @FXML
    private JFXButton btn_Edit;

    @FXML
    private JFXButton btn_Add;

    @FXML
    private JFXTextField txt_Search;

    @FXML
    private JFXButton btn_Back;

    @FXML
    private JFXButton btn_Search;

    @FXML
    private JFXTextField txt_DeliverID;

    @FXML
    private JFXTextField txt_OrderId;

    @FXML
    private JFXTextField txt_DeliveredBy;

    @FXML
    private JFXTextField txt_Description;

    @FXML
    private TableView<Delivery> tbl_Details;

    @FXML
    private JFXTextField txt_Date;

    @FXML
    private JFXTextArea txtarea_des;

    @FXML
    private JFXTextField txt_Status;

    @FXML
    void Add(ActionEvent event) {
        String track_id, del_date_time, status, note, delivered_by,Orderid;
        track_id = txt_DeliverID.getText();
        del_date_time = txt_Date.getText();
        status = txt_Status.getText();
        note = txt_Description.getText();
        delivered_by = txt_DeliveredBy.getText();
        Orderid = txt_OrderId.getText();

        int TrackId = Integer.parseInt(track_id);
        int orderid = Integer.parseInt(Orderid);
        Timestamp ts = Timestamp.valueOf(del_date_time);

        Delivery delivery = new Delivery(TrackId, ts, status, note, delivered_by,orderid);
        IManageDeliveryService imanager = new ManageDeliveryServiceImpl();

        try {
            imanager.add(delivery);
            JOptionPane.showMessageDialog(null, "Added Succesfully");
            viewTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Cannot add");
            e.printStackTrace();


        }
    }

    @FXML
    void Delete(ActionEvent event) {
        String tid = txt_DeliverID.getText();
        int Trackid = Integer.parseInt(tid);

        IManageDeliveryService iManageDeliveryService = new ManageDeliveryServiceImpl();

        try {
            iManageDeliveryService.delete(Trackid);
            JOptionPane.showMessageDialog(null, "Deleted");
            viewTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error! Cannot Deleted");
            e.printStackTrace();
        }
    }

    @FXML
    void Edit(ActionEvent event) {
        String track_id, del_date_time, status, note, delivered_by,Orderid;
        track_id = txt_DeliverID.getText();
        del_date_time = txt_Date.getText();
        status = txt_Status.getText();
        note = txt_Description.getText();
        delivered_by = txt_DeliveredBy.getText();
        Orderid = txt_OrderId.getText();

        int TrackId = Integer.parseInt(track_id);
        int orderid = Integer.parseInt(Orderid);
        Timestamp ts = Timestamp.valueOf(del_date_time);

        Delivery delivery = new Delivery(TrackId, ts, status, note, delivered_by,orderid);
        IManageDeliveryService imanager = new ManageDeliveryServiceImpl();

        try {
            if(imanager.Edit(delivery))
            {
                JOptionPane.showMessageDialog(null,"Updated Success");
                viewTable();
                reset();
            }else
            {
                JOptionPane.showMessageDialog(null,"Updated failed");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }







    @FXML
    void Search(ActionEvent event) {

        String id = txt_DeliverID.getText();
        int idnew = Integer.parseInt(id);

        IManageDeliveryService iManageDeliveryService = new ManageDeliveryServiceImpl();
        try {
            Delivery delivery =iManageDeliveryService.find(idnew);
            String sid = String.valueOf(delivery.getTrack_id());
            txt_DeliverID.setText(sid);
            String date = String.valueOf(delivery.getTrack_id());

            txt_Date.setText(date);
            txt_Status.setText(delivery.getStatus());
            txt_Description.setText(delivery.getNote());
            txt_DeliveredBy.setText(delivery.getDelivered_by());
            String oid = String.valueOf(delivery.getOrder_id());
            txt_OrderId.setText(oid);

            viewTable(idnew);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error!, Cannot find");
        }

    }

    public void reset()
    {
        txt_DeliverID.setText("");
        txt_Date.setText("");
        txt_Status.setText("");
        txt_Description.setText("");
        txt_DeliveredBy.setText("");
        txt_OrderId.setText("");
    }

    public void viewTable()
    {
        tbl_Details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("track_id"));
        tbl_Details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("del_date_time"));
        tbl_Details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));
        tbl_Details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("note"));
        tbl_Details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("delivered_by"));
        tbl_Details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Order_id"));

        IManageDeliveryService iManageDeliveryService = new ManageDeliveryServiceImpl();
        try {
            List<Delivery> allDelivery = iManageDeliveryService.findAll();
            tbl_Details.setItems(FXCollections.observableArrayList(allDelivery));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewTable(int id)
    {
        tbl_Details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("track_id"));
        tbl_Details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("del_date_time"));
        tbl_Details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("status"));
        tbl_Details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("note"));
        tbl_Details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("delivered_by"));
        tbl_Details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("Order_id"));

        IManageDeliveryService iManageDeliveryService = new ManageDeliveryServiceImpl();
        try {
            Delivery delivery = iManageDeliveryService.find(id);
            tbl_Details.setItems(FXCollections.observableArrayList(delivery));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();

    }
    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<Delivery> ManageList =  new ArrayList<>(tbl_Details.getSelectionModel().getSelectedItems());
        for(Delivery delivery :ManageList)
        {
            txt_DeliverID.setText(Integer.toString(delivery.getTrack_id()));
            txt_Date.setText(String.valueOf(delivery.getDel_date_time()));
            txt_Status.setText(delivery.getStatus());
            txt_Description.setText(delivery.getNote());
            txt_DeliveredBy.setText(delivery.getDelivered_by());
            txt_OrderId.setText(Integer.toString(delivery.getOrder_id()));

}
    }
}