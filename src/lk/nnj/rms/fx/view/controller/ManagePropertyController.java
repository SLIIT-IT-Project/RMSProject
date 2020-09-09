package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.nnj.rms.fx.model.Property;
import lk.nnj.rms.fx.model.User;
import lk.nnj.rms.fx.service.IPropertyService;
import lk.nnj.rms.fx.service.Impl.PropertyServiceImpl;

import javax.swing.*;
import java.net.URL;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ManagePropertyController implements Initializable {
    @FXML
    private JFXTextField txt_pid;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_vtype;

    @FXML
    private JFXTextField txt_no;

    @FXML
    private JFXTextField txt_quantity;

    @FXML
    private TableView<Property> tbl_property;

    @FXML
    private JFXComboBox<String> com_ptype;


    @FXML
    private JFXButton btn_submit;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private TextField txt_search;

    @FXML
    private TextField txt_searchAvaliable;

    @FXML
    private ImageView btn_searchAvaliable;

    @FXML
    private TableView<Property> tbl_avaProperty;


    @FXML
    void viewSelectProperty(MouseEvent event) {

    }
    @FXML
    void add(ActionEvent event) {
        String id,name,property_type,vehicle_type,vehicle_Number;
        int quantity;
        id =txt_pid.getText();
        name = txt_name.getText();
        property_type = com_ptype.getSelectionModel().getSelectedItem().toString();
        vehicle_type = txt_vtype.getText();
        vehicle_Number = txt_no.getText();
        quantity = parseInt(txt_quantity.getText());

        int idnew = parseInt(id);

        Property property = new Property(idnew,name,property_type,vehicle_type,vehicle_Number,quantity);
        IPropertyService iproperty = new PropertyServiceImpl();

        try {
            iproperty.add(property);
            JOptionPane.showMessageDialog(null,"Added Success");
            viewTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error! Cannot add");
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        String id = txt_pid.getText();
        int idnew = parseInt(id);

        IPropertyService iPropertyService = new PropertyServiceImpl();
        try {
            iPropertyService.delete(idnew);
            JOptionPane.showMessageDialog(null,"Deleted");
            viewTable();
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error!, Cannot Delete");
        }
    }

    @FXML
    void search(ActionEvent event) {
        String id = txt_search.getText();
        int idnew = Integer.parseInt(id);

        IPropertyService iPropertyService = new PropertyServiceImpl();
        try {
            Property property = iPropertyService.find(idnew);
            txt_name.setText(property.getPropertyname());
            com_ptype.setValue(property.getPropertytype());
            txt_no.setText(property.getVehiclenumber());
            String qu = String.valueOf(property.getQuantity());
            txt_quantity.setText(qu);

            viewTable(idnew);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error!, Cannot find");
        }
    }

    @FXML
    void update(ActionEvent event) {

        String id,name,property_type,vehicle_type,vehicle_Number;
        int quantity;
        id =txt_pid.getText();
        name = txt_name.getText();
        property_type = com_ptype.getSelectionModel().getSelectedItem().toString();
        vehicle_type = txt_vtype.getText();
        vehicle_Number = txt_no.getText();
        quantity = parseInt(txt_quantity.getText());

        int idnew = parseInt(id);

        Property property = new Property(idnew,name,property_type,vehicle_type,vehicle_Number,quantity);
        IPropertyService iPropertyService = new PropertyServiceImpl();

        try {
            if(iPropertyService.update(property))
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


    public void reset()
    {
        txt_pid.setText("");
        txt_name.setText("");
        com_ptype.setValue("");
        txt_vtype.setText("");
        txt_no.setText("");
        txt_quantity.setText("");
    }

    public void viewTable()
    {
        tbl_property.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("propertyID"));
        tbl_property.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("propertyname"));
        tbl_property.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("propertytype"));
        tbl_property.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("vehicletype"));
        tbl_property.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("vehiclenumber"));
        tbl_property.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("quantity"));

        IPropertyService iPropertyService = new PropertyServiceImpl();
        try {
            List<Property> allUsers = iPropertyService.findAll();
            tbl_property.setItems(FXCollections.observableArrayList(allUsers));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void viewTable(int id)
    {
        tbl_property.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("propertyID"));
        tbl_property.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("propertyname"));
        tbl_property.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("propertytype"));
        tbl_property.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("vehicletype"));
        tbl_property.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("vehiclenumber"));
        tbl_property.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("quantity"));

        IPropertyService IPropertyService = new PropertyServiceImpl();
        try {
            Property property = IPropertyService.find(id);
            tbl_property.setItems(FXCollections.observableArrayList(property));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        com_ptype.getItems().add("Vehicle");
        com_ptype.getItems().add("Kitchen");
        com_ptype.getItems().add("Other");

        viewTable();
    }
    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<Property> propertyList = new ArrayList<>(tbl_property.getSelectionModel().getSelectedItems());
        for (Property property : propertyList) {
            txt_pid.setText(Integer.toString(property.getPropertyID()));
            txt_name.setText(property.getPropertyname());
            com_ptype.setValue(property.getPropertytype());
            txt_vtype.setText(property.getVehicletype());
            txt_no.setText(property.getVehiclenumber());
            txt_quantity.setText(String.valueOf(property.getQuantity()));

        }


    }
    }
