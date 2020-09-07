package lk.nnj.rms.fx.view.controller;


import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    @FXML
    private JFXButton btn_dessert;

    @FXML
    private JFXButton btn_beverage;

    @FXML
    private TextArea txt_address;

    @FXML
    private JFXRadioButton rb_dine;

    @FXML
    private ToggleGroup orderType;

    @FXML
    private JFXRadioButton rb_tAway;

    @FXML
    private JFXRadioButton rb_del;

    @FXML
    private JFXButton btn_del;

    @FXML
    private ImageView lbl_del;

    @FXML
    private JFXButton btn_orderRemove;

    @FXML
    private JFXButton btn_new;

    @FXML
    private JFXButton btn_checkOut;

    @FXML
    private ImageView lbl_checkOut;

    @FXML
    private ImageView lbl_new;

    @FXML
    private ImageView lbl_remove;

    @FXML
    private Label lbl_serviceCharge;

    @FXML
    private JFXComboBox<?> cmb_pType;

    @FXML
    private TextField txt_invoiceNo;

    @FXML
    private TextField txt_dateTime;

    @FXML
    private TableView<?> lbl_itemOrder;

    @FXML
    private JFXListView<?> lst_viewCategory;

    @FXML
    private JFXComboBox<?> cmb_sCategory;

    @FXML
    private JFXButton btn_smallP;

    @FXML
    private JFXButton btn_largeP;

    @FXML
    private JFXTextField txt_itemName;

    @FXML
    private TextField txt_qty;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_remove;

    @FXML
    private TextField txt_itemID;

    @FXML
    private TextField txt_cname;

    @FXML
    private TextField txt_cmobile;

    @FXML
    private JFXListView<?> lst_log;

    @FXML
    private JFXButton btn_mediumP;

    @FXML
    private JFXButton btn_elargeP;

    @FXML
    private JFXButton btn_add1;

    @FXML
    private JFXButton btn_remove1;

    @FXML
    private TextField txt_qty1;

    @FXML
    private Label lbl_subTotal;

    @FXML
    private TextField txt_cashPaid;

    @FXML
    private Label lbl_balance;

    @FXML
    private ImageView lbl_plus;

    @FXML
    private ImageView lbl_minus;

    @FXML
    private ImageView btn_plus1;

    @FXML
    private ImageView btn_minus1;

    @FXML
    private ImageView lbl_mediumP;

    @FXML
    private ImageView lbl_largeP;

    @FXML
    private ImageView lbl_smallP;

    @FXML
    private ImageView lbl_elargeP;

    @FXML
    void AddItem1(ActionEvent event) {

    }

    @FXML
    void addItem(ActionEvent event) {

    }

    @FXML
    void checkOut(MouseEvent event) {

    }

    //Check whether deliver radio button is selected if enable address text area
    @FXML
    void deliver(MouseEvent event) {
        if(rb_del.isSelected())
        {
            txt_address.setDisable(false);
        }
    }
    //Check whether takeAway radio button is selected
    @FXML
    void takeAway(MouseEvent event) {
        if(rb_tAway.isSelected())
        {
            txt_address.setDisable(true);
        }
    }
    //Check whether dineInn radio button is selected
    @FXML
    void dineInn(MouseEvent event) {
        if(rb_dine.isSelected())
        {
            txt_address.setDisable(true);
        }
    }
    //Decrement Quantity
    @FXML
    void minus(MouseEvent event) {
        int qty = Integer.parseInt(txt_qty.getText());
        if(qty!=0)
        {
            qty--;
            txt_qty.setText(Integer.toString(qty));
        }
    }

    @FXML
    void minus1(MouseEvent event) {

    }

    @FXML
    void newOrder(MouseEvent event) {

    }

    //Increment Quantity
    @FXML
    void plus(MouseEvent event) {
        int qty = Integer.parseInt(txt_qty.getText());
        qty++;
        txt_qty.setText(Integer.toString(qty));
    }

    @FXML
    void plus1(MouseEvent event) {

    }

    @FXML
    void removeItem(ActionEvent event) {

    }

    @FXML
    void removeItem1(ActionEvent event) {

    }

    @FXML
    void removeOrder(MouseEvent event) {

    }

    @FXML
    void viewDel(MouseEvent event) {

    }

    @FXML
    void viewDessert(ActionEvent event) {

    }

    @FXML
    void viewElarge(MouseEvent event) {

    }

    @FXML
    void viewLarge(MouseEvent event) {

    }

    @FXML
    void viewMedium(MouseEvent event) {

    }

    @FXML
    void viewSmall(MouseEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
