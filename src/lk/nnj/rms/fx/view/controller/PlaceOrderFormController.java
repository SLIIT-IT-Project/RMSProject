package lk.nnj.rms.fx.view.controller;


import com.jfoenix.controls.*;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Customer;
import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.ItemOrder;
import lk.nnj.rms.fx.model.Order;
import lk.nnj.rms.fx.service.*;
import lk.nnj.rms.fx.service.Impl.*;
import org.controlsfx.control.textfield.TextFields;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;

public class PlaceOrderFormController implements Initializable {

    @FXML
    private AnchorPane root;

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
    private ImageView lbl_logOut;


    @FXML
    private JFXComboBox<String> cmb_pType;

    @FXML
    private TextField txt_invoiceNo;


    @FXML
    private TableView<ItemOrder> tbl_itemOrder;

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
    private Label lbl_totAmount;

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
    private Label lbl_unitPrice;

    static double subTotal = 0, serviceCharge=0, totAmount = 0;

    @FXML
    void AddItem1(ActionEvent event) {

    }
    @FXML
    void Click(MouseEvent event) {
        ArrayList<ItemOrder> itemList =  new ArrayList<>(tbl_itemOrder.getSelectionModel().getSelectedItems());
        for(ItemOrder item:itemList)
        {
            txt_itemID.setText(item.getItemID());
            txt_itemName.setText(item.getItemName());
            txt_qty.setText(Integer.toString(item.getQty()));
            lbl_unitPrice.setText(Double.toString(item.getUnitPrice()));
        }
    }

    //Add items to the Item Order Table
    @FXML
    void addItem(ActionEvent event) {

        double unit_price;
        String itemID;
        int orderID;
        int qty;
        IQueryService iQueryService = new QueryServiceImpl();
        String itemName = txt_itemName.getText();
        if(itemName.equals(""))
        {

        }else
            {
                try {
                    orderID = Integer.parseInt(txt_invoiceNo.getText());
                    itemID=iQueryService.getItemID(itemName);
                    txt_itemID.setText(itemID);
                    qty =Integer.parseInt(txt_qty.getText());
                    unit_price = iQueryService.getItemPrice(itemName);

                    IOrderService iOrderService = new OrderServiceImpl();
                    try {
                        iOrderService.add(new Order(orderID, LocalDateTime.now(), "", "", 0, 0, 0, 100));
                    }catch (Exception e)
                    {

                    }
                    IItemOrderService iItemOrderService = new ItemOrderServiceImpl();
                    iItemOrderService.add(new ItemOrder(orderID,itemID,itemName,qty,unit_price));
                    subTotal = subTotal + unit_price * qty ;
                    serviceCharge = subTotal * 10.0/100;
                    totAmount = subTotal + serviceCharge;
                    lbl_subTotal.setText(Double.toString(subTotal));
                    lbl_serviceCharge.setText(Double.toString(serviceCharge));
                    lbl_totAmount.setText(Double.toString(totAmount));
                    viewTable(orderID);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

    }

    @FXML
    void checkOut(MouseEvent event){
        if(rb_dine.isSelected())
        {
            int oid = Integer.parseInt(txt_invoiceNo.getText());
            String oType = "Dine Inn";
            double orderAmount = Double.parseDouble(lbl_subTotal.getText());
            double sCharge = Double.parseDouble(lbl_serviceCharge.getText());
            double totAmount = Double.parseDouble(lbl_totAmount.getText());
            String desc="";
            int cid=0;
            try
            {
                IQueryService iQueryService = new QueryServiceImpl();
                cid = iQueryService.getCusNo();
                desc = iQueryService.findOrderDetails(oid);
            }catch (Exception e)
            {

            }


            String cname = txt_cname.getText();
            String mobile = txt_cmobile.getText();

            //add customer to the database
            try
            {
                ICustomerService iCustomerService = new CustomerServiceImpl();
                iCustomerService.add(new Customer(cid,cname,mobile,"",0));
            }catch (Exception e)
            {

            }
            //update order
            try
            {
                IOrderService iOrderService = new OrderServiceImpl();
                iOrderService.update(new Order(oid,LocalDateTime.now(),desc,oType,orderAmount,sCharge,totAmount,cid));
            }catch (Exception e)
            {

            }
            //Add payment Details
            try
            {
                IQueryService iQueryService = new QueryServiceImpl();
                int pid = iQueryService.getPaymentNo();

            }catch (Exception e)
            {

            }


        }
    }

    //Check whether deliver radio button is selected if enable address text area
    @FXML
    void deliver(MouseEvent event) {
        if(rb_del.isSelected())
        {
            txt_address.setDisable(false);
            lbl_serviceCharge.setText("0.0");
            totAmount = totAmount - serviceCharge;
            serviceCharge=0;
            lbl_totAmount.setText(Double.toString(totAmount));
        }
    }
    //Check whether takeAway radio button is selected
    @FXML
    void takeAway(MouseEvent event) {
        if(rb_tAway.isSelected())
        {
            txt_address.setDisable(true);
            lbl_serviceCharge.setText("0.0");
            totAmount = totAmount - serviceCharge;
            serviceCharge =0;
            lbl_totAmount.setText(Double.toString(totAmount));

        }
    }
    //Check whether dineInn radio button is selected
    @FXML
    void dineInn(MouseEvent event) {
        if(rb_dine.isSelected())
        {
            txt_address.setDisable(true);
            double totCharge = totAmount;
            double sCharge = totCharge* 10.0/100;
            totCharge = sCharge + totCharge;
            lbl_serviceCharge.setText(Double.toString(sCharge));
            lbl_totAmount.setText(Double.toString(totCharge));

        }
    }
    //Decrement Quantity
    @FXML
    void minus(MouseEvent event) {
        int qty = Integer.parseInt(txt_qty.getText());
        if(qty!=1)
        {
            qty--;
            txt_qty.setText(Integer.toString(qty));
        }
    }

    @FXML
    void minus1(MouseEvent event) {
        int qty = Integer.parseInt(txt_qty1.getText());
        if(qty!=1)
        {
            qty--;
            txt_qty1.setText(Integer.toString(qty));
        }
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
        int qty = Integer.parseInt(txt_qty1.getText());
        qty++;
        txt_qty1.setText(Integer.toString(qty));
    }

    @FXML
    void setBalance(ActionEvent event) {
        double totAmount = Double.parseDouble(lbl_totAmount.getText());
        double rec = Double.parseDouble(txt_cashPaid.getText());

        lbl_balance.setText(Double.toString(rec-totAmount));
    }

    @FXML
    void removeItem(ActionEvent event) {
        String itemID;
        double unit_price;
        int qty;
        int orderID = Integer.parseInt(txt_invoiceNo.getText());

        itemID = txt_itemID.getText();

        //Check whether item id is empty or not
        if(!txt_itemID.getText().equals(""))
        {
            IItemOrderService iItemOrderService = new ItemOrderServiceImpl();
            try {
                unit_price = Double.parseDouble(lbl_unitPrice.getText());
                qty = Integer.parseInt(txt_qty.getText());
                iItemOrderService.delete(itemID);
                subTotal = subTotal - unit_price * qty ;
                serviceCharge = subTotal * 10.0/100;

                if(subTotal ==0.0)
                {
                    IOrderService iOrderService = new OrderServiceImpl();
                    iOrderService.delete(orderID);
                }

                totAmount = subTotal + serviceCharge;
                lbl_subTotal.setText(Double.toString(subTotal));
                lbl_serviceCharge.setText(Double.toString(serviceCharge));
                lbl_totAmount.setText(Double.toString(totAmount));
                viewTable(orderID);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public void initialize(URL location, ResourceBundle resources){
        ObservableList<String> options =
                FXCollections.observableArrayList(
                        "Cash",
                        "Credit",
                        "Debit"
                );

        rb_dine.setSelected(true);
        cmb_pType.setItems(options);
        cmb_pType.setValue("Cash");
        IQueryService iQueryService = new QueryServiceImpl();
        try {
            txt_invoiceNo.setText(Integer.toString(iQueryService.getInvoiceNo()));
        } catch (Exception e) {
            e.printStackTrace();
        }


        List<String> itemList;
        try {
            itemList = iQueryService.getAllItemNames();
            String[] items = new String[itemList.size()];
            items = itemList.toArray(items);
            TextFields.bindAutoCompletion(txt_itemName, items);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void logOut(MouseEvent event) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/LoginForm.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }

    public void viewTable(int oid)
    {
        tbl_itemOrder.getColumns().get(0).setStyle("-fx-alignment: center");
        tbl_itemOrder.getColumns().get(1).setStyle("-fx-alignment: center");
        tbl_itemOrder.getColumns().get(2).setStyle("-fx-alignment: center");
        tbl_itemOrder.getColumns().get(3).setStyle("-fx-alignment: center");

        tbl_itemOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemID"));
        tbl_itemOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("itemName"));
        tbl_itemOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tbl_itemOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        IItemOrderService iItemOrderService = new ItemOrderServiceImpl();
        try {
            List<ItemOrder> allItems = iItemOrderService.findAll(oid);
            tbl_itemOrder.setItems(FXCollections.observableArrayList(allItems));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
