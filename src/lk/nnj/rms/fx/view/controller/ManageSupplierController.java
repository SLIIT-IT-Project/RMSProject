package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.Supplier;
import lk.nnj.rms.fx.service.ISupplierService;
import lk.nnj.rms.fx.service.Impl.ItemServiceImpl;
import lk.nnj.rms.fx.service.Impl.SupplierServiceImpl;
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

public class ManageSupplierController implements Initializable {


    @FXML
    private JFXTextField txtSName;

    @FXML
    private JFXTextField txtCompny;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPNo;

    @FXML
    private JFXTextField txtDis;

    @FXML
    private JFXTextField txtAddress;

    @FXML
    private JFXButton btnadd;

    @FXML
    private JFXButton btndelete;

    @FXML
    private JFXButton btnupdate;

    @FXML
    private JFXButton btnprint;

    @FXML
    private TableView<Supplier> TableDetailsSupplier;

    @FXML
    private JFXButton btnsearch;

    @FXML
    private JFXTextField txtSId;

    @FXML
    private AnchorPane root;

    @FXML
    private Label lblTot_supplier;

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDemo;

    //Add supplier Details
    @FXML
    void add(ActionEvent event) {

        String SId,SName,Email,Company,PhoneNo,Discreption,Address;
        SId =txtSId.getText();
        SName=txtSName.getText();
        Email=txtEmail.getText();
        Company=txtCompny.getText();
        PhoneNo=txtPNo.getText();
        Discreption=txtDis.getText();
        Address=txtAddress.getText();

        if(SId.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please Enter a Id");
            alert1.setContentText("ID are required");
            alert1.showAndWait();
        }
        else if(SName.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Name");
            alert1.setContentText("Supplier Details are required");
            alert1.showAndWait();
        }
        else if(Email.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a email");
            alert1.setContentText("Supplier Details are required");
            alert1.showAndWait();
        }
        else if(Company.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Value");
            alert1.setContentText("Supplier Details are required");
            alert1.showAndWait();
        }
        else if(Discreption.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Discreption");
            alert1.setContentText("Supplier Details are required");
            alert1.showAndWait();
        }
        else if(Address.equals("")){
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Please input a Address");
            alert1.setContentText("Supplier Details are required");
            alert1.showAndWait();
        }

        else {

            int id =Integer.parseInt(SId);

            Supplier supplier=new Supplier(id,SName,Email,Company,PhoneNo,Discreption,Address);
            ISupplierService isupplier = new SupplierServiceImpl();
            try {
                isupplier.add(supplier);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Add Successfully");
                alert1.setContentText("New Supplier Added");
                alert1.showAndWait();
                viewTable();
                reset();
            } catch (Exception e) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, Add failed");
                alert1.setContentText("Supplier cannot Add");
                alert1.showAndWait();
                e.printStackTrace();
            }
        }
        counter();
    }

    //Delete supplier Details
    @FXML
    void delete(ActionEvent event) {

        String SId=txtSId.getText();
        int id =Integer.parseInt(SId);

        ISupplierService isupplier = new SupplierServiceImpl();

        try {
            isupplier.delete(id);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Information Dialog");
            alert1.setHeaderText("Delete Successfully");
            alert1.setContentText("Supplier Deleted");
            alert1.showAndWait();
            reset ();
            viewTable();
        } catch (Exception e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Error, Delete failed");
            alert1.setContentText("Supplier cannot Delete");
            alert1.showAndWait();
        }
        counter();

    }

    //Search supplier Details
    @FXML
    void search(ActionEvent event) {

        String SId=txtSId.getText();
        int id = Integer.parseInt(SId);

        ISupplierService isupplier = new SupplierServiceImpl();
        try {
            Supplier supplier = isupplier.find(id);
            txtSName.setText(supplier.getSName());
            txtCompny.setText(supplier.getPhoneNo());
            txtEmail.setText(supplier.getEmail());
            txtPNo.setText(supplier.getCompany());
            txtAddress.setText(supplier.getDiscreption());
            txtDis.setText(supplier.getAddress());
            viewTable(id);
            }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,"Error! ");
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Error, No Information");
            alert1.setContentText("No Supplier Information");
            alert1.showAndWait();
        }
    }

    //Update supplier Details
    @FXML
    void update(ActionEvent event) {

        String SId,SName,Email,Company,PhoneNo,Discreption,Address;

        SId =txtSId.getText();
        SName=txtSName.getText();
        Email=txtEmail.getText();
        Company=txtCompny.getText();
        PhoneNo=txtPNo.getText();
        Discreption=txtDis.getText();
        Address=txtAddress.getText();

        int sid =Integer.parseInt(SId);

        Supplier supplier=new Supplier(sid,SName,Email,PhoneNo,Company,Discreption,Address);
        ISupplierService isupplier = new SupplierServiceImpl();
        try {
            if(isupplier.update(supplier))
            {
                Alert alert1= new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Information Dialog");
                alert1.setHeaderText("Updated Successfully");
                alert1.setContentText("Supplier updated successfully");
                alert1.showAndWait();
                reset ();
                viewTable();
            }
            else
            {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialog");
                alert1.setHeaderText("Error, update failed");
                alert1.setContentText("Supplier cannot update");
                alert1.showAndWait();
            }
        } catch (Exception e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialog");
            alert1.setHeaderText("Invalid input found");
            alert1.setContentText("Supplier id does not exist");
            alert1.showAndWait();

            JOptionPane.showMessageDialog(null,"Error!");
            e.printStackTrace();
        }
    }

    //reset text fields
    public void reset ()
    {
        txtSId.setText(" ");
        txtSName.setText(" ");
        txtEmail.setText(" ");
        txtCompny.setText(" ");
        txtPNo.setText(" ");
        txtDis.setText(" ");
        txtAddress.setText(" ");
    }

    @FXML
    void demo(ActionEvent event) {
        demo();
    }

    public void demo(){
        txtSId.setText("701050");
        txtSName.setText("Janith");
        txtEmail.setText("janith@gmail.com");
        txtCompny.setText("janith foods (pvt)ltd");
        txtPNo.setText("07123456789");
        txtDis.setText("Vegitable supplier");
        txtAddress.setText("Dambulla");
    }
    //counter
    public void counter(){

        SupplierServiceImpl SupplierService = new SupplierServiceImpl();
        try {
            int c = SupplierService.totalItems();
            lblTot_supplier.setText(Integer.toString(c));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
        SupplierServiceImpl SupplierService = new SupplierServiceImpl();
        try {
            int c = SupplierService.totalItems();
            lblTot_supplier.setText(Integer.toString(c));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // ViewTabel
    public void viewTable() {
        TableDetailsSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SId"));
        TableDetailsSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("SName"));
        TableDetailsSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Email"));
        TableDetailsSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("company"));
        TableDetailsSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
        TableDetailsSupplier.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));
        TableDetailsSupplier.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("discreption"));

        ISupplierService isupplier = new SupplierServiceImpl();
        try {
            List<Supplier> allSupplier = isupplier.findAll();
            TableDetailsSupplier.setItems(FXCollections.observableArrayList(allSupplier));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        public void viewTable(int SId)
        {
            TableDetailsSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("SId"));
            TableDetailsSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("SName"));
            TableDetailsSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Email"));
            TableDetailsSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("company"));
            TableDetailsSupplier.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("phoneNo"));
            TableDetailsSupplier.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("address"));
            TableDetailsSupplier.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("discreption"));

            ISupplierService isupplier = new SupplierServiceImpl();
            try {
                List<Supplier> allSupplier = isupplier.findAll();
                TableDetailsSupplier.setItems(FXCollections.observableArrayList(allSupplier));
            } catch (Exception e) {
                e.printStackTrace();
            }

    }
    @FXML
    void viewselectsupplier(MouseEvent event) {

        ArrayList<Supplier> userList = new ArrayList<>(TableDetailsSupplier.getSelectionModel().getSelectedItems());
        for (Supplier supplier:userList) {
            txtSId.setText(Integer.toString(supplier.getSId()));
            txtSName.setText(supplier.getSName());
            txtEmail.setText(supplier.getCompany());
            txtCompny.setText(supplier.getEmail());
            txtPNo.setText(supplier.getPhoneNo());
            txtDis.setText(supplier.getDiscreption());
            txtAddress.setText(supplier.getAddress());

        }
    }
    // Clear text Fields
    @FXML
    void clear(ActionEvent event) {
        reset ();
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/Inventory.fxml"));
        if (root != null) {
            Scene subScene = new Scene(root);
            Stage primaryStage = (Stage) this.root.getScene().getWindow();
            primaryStage.setScene(subScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(true);
            TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
            tt.setFromX(-subScene.getWidth());
            tt.setToX(0);
            tt.play();
        }
    }
    private static String[] columns = {"Supplier ID","Name","Email","Company","Phone No","Description","Address"};
    @FXML
    void Report(ActionEvent event) throws Exception{

        ISupplierService iSupplierService = new SupplierServiceImpl();
        List<Supplier> allItems = iSupplierService.findAll();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Supplier Details");
        Font headerFont = workbook.createFont();
        ((Font) headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
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
        for(Supplier supplier : allItems)
        {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(supplier.getSId());
            row.createCell(1).setCellValue(supplier.getSName());
            row.createCell(2).setCellValue(supplier.getEmail());
            row.createCell(3).setCellValue(supplier.getCompany());
            row.createCell(4).setCellValue(supplier.getPhoneNo());
            row.createCell(5).setCellValue(supplier.getDiscreption());
            row.createCell(6).setCellValue(supplier.getAddress());
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
}

