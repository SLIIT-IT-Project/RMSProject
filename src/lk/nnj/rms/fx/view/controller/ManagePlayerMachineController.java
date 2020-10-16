package lk.nnj.rms.fx.view.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import lk.nnj.rms.fx.model.PlayerMachine;
import lk.nnj.rms.fx.service.IPlayerMachineService;
import lk.nnj.rms.fx.service.Impl.PlayerMachineServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ManagePlayerMachineController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXTextField txt_pmID;

    @FXML
    private JFXButton btn_report;

    @FXML
    private JFXTextField txt_playerID;

    @FXML
    private JFXTextField txt_machineID;

    @FXML
    private TableView<PlayerMachine> tbl_details;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXTextField txt_score;

    @FXML
    private JFXTextField txt_price;

    @FXML
    private JFXButton btn_player;

    @FXML
    private JFXButton btn_machine;

    @FXML
    private JFXButton btn_back;

    @FXML
    private PieChart chart;

    @FXML
    private JFXButton btn_chart;

    @FXML
    private JFXButton btn_demo;

    @FXML
    void back(ActionEvent event) throws IOException {


        Parent root = null;
        root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/AdminPanel.fxml"));
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

    @FXML
    void goMachine(ActionEvent event) {
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManageMachine.fxml"));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void goPlayer(ActionEvent event) {
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManagePlayer.fxml"));
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void resetButton(ActionEvent event) {
        reset();
        viewTable();
    }


    @FXML
    void add(ActionEvent event) {
        String pmid,pid,mid,datetime,score,price;

        pmid = txt_pmID.getText();
        pid = txt_playerID.getText();
        mid = txt_machineID.getText();
        score = txt_score.getText();
        //datetime =txt_datetime.getText();

        if(pmid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Player Machine ID !!!");
            return;
        }else if(pid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Player ID !!!");
            return;
        }else if(mid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Machine ID !!!");
            return;
        }else if(!(String.valueOf(mid.charAt(0)).equals("M"))){
            JOptionPane.showMessageDialog(null, "Invalid Machine ID !!!");
            return;
        }else if(!(String.valueOf(pid.charAt(0)).equals("P"))){
            JOptionPane.showMessageDialog(null, "Invalid Player ID !!!");
            return;
        }else if(!(String.valueOf(pmid.charAt(0)).equals("P"))){
            JOptionPane.showMessageDialog(null, "Invalid Player Machine ID !!!");
            return;
        }else if(!(String.valueOf(pmid.charAt(1)).equals("M"))){
            JOptionPane.showMessageDialog(null, "Invalid Player Machine ID !!!");
            return;
        }else if(score.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Score !!!");
            return;
        }

        try{
            int scorenew = Integer.parseInt(score);
            //Timestamp datetimenew = Timestamp.valueOf(datetime);
            Timestamp datetimenew = new Timestamp(System.currentTimeMillis());

            if(scorenew < 0){
                JOptionPane.showMessageDialog(null, "Can not enter minus values !!!");
                return;
            }

            PlayerMachine playerMachine = new PlayerMachine(pmid,pid,mid,datetimenew,scorenew);

            IPlayerMachineService iplayermachine = new PlayerMachineServiceImpl();

            try {
                iplayermachine.add(playerMachine);
                JOptionPane.showMessageDialog(null,"Success, added");
                viewTable();
                reset();
            } catch (Exception e) {

                JOptionPane.showMessageDialog(null,"Error!!! can not add");
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
        String pmid = txt_pmID.getText();
        //String pid = txt_playerID.getText();
        //String mid = txt_machineID.getText();

        IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();

        try {
            iPlayerMachineService.delete(pmid);
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
        String id = txt_search.getText();

        IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();

        try {
            PlayerMachine palyermachine = iPlayerMachineService.find(id);
            txt_pmID.setText(palyermachine.getPMID());
            txt_playerID.setText(palyermachine.getPlayerID());
            txt_machineID.setText(palyermachine.getMachineID());
            //txt_datetime.setText(String.valueOf(palyermachine.getDateTime()));
            txt_score.setText(String.valueOf(palyermachine.getScore()));


            viewTable(id);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!!! wrong");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String pmid,pid,mid,datetime,score,price;
        pmid = txt_pmID.getText();
        pid = txt_playerID.getText();
        mid = txt_machineID.getText();
        //datetime = txt_datetime.getText();
        score = txt_score.getText();

        if(pmid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Player Machine ID !!!");
            return;
        }else if(pid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Player ID !!!");
            return;
        }else if(mid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Machine ID !!!");
            return;
        }else if(!(String.valueOf(mid.charAt(0)).equals("M"))){
            JOptionPane.showMessageDialog(null, "Invalid Machine ID !!!");
            return;
        }else if(!(String.valueOf(pid.charAt(0)).equals("P"))){
            JOptionPane.showMessageDialog(null, "Invalid Player ID !!!");
            return;
        }else if(!(String.valueOf(pmid.charAt(0)).equals("P"))){
            JOptionPane.showMessageDialog(null, "Invalid Player Machine ID !!!");
            return;
        }else if(!(String.valueOf(pmid.charAt(1)).equals("M"))){
            JOptionPane.showMessageDialog(null, "Invalid Player Machine ID !!!");
            return;
        }else if(score.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Score !!!");
            return;
        }

        try{
            int scorenew = Integer.parseInt(score);
            //Timestamp datetimenew = Timestamp.valueOf(datetime);
            Timestamp datetimenew = new Timestamp(System.currentTimeMillis());

            if(scorenew < 0){
                JOptionPane.showMessageDialog(null, "Can not enter minus values !!!");
                return;
            }

            PlayerMachine playerMachine = new PlayerMachine(pmid,pid,mid,datetimenew,scorenew);

            IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();

            try {
                if(iPlayerMachineService.update(playerMachine)){
                    JOptionPane.showMessageDialog(null,"Updated, added");
                    reset();
                    viewTable();
                }else{
                    JOptionPane.showMessageDialog(null,"Error!!! can not update");
                }
            } catch (Exception e) {


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
    void viewSelect(MouseEvent event) {
        ArrayList<PlayerMachine> playermachineList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(PlayerMachine playerMachine:playermachineList){
            txt_pmID.setText(playerMachine.getPMID());
            txt_playerID.setText(playerMachine.getPlayerID());
            txt_machineID.setText(playerMachine.getMachineID());
            txt_score.setText(Integer.toString(playerMachine.getScore()));
            //txt_datetime.setText(String.valueOf(playerMachine.getDateTime()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
    }

    public void reset(){
        txt_pmID.setText("");
        txt_playerID.setText("");
        txt_machineID.setText("");
        txt_score.setText("");
        //txt_datetime.setText("");

        txt_search.setText("");
    }

    public void viewTable(){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PMID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PlayerID"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("MachineID"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Score"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("PriceEligibilty"));


        IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();

        try {
            List<PlayerMachine> allPlayerMachines = iPlayerMachineService.findAll();
            tbl_details.setItems(FXCollections.observableArrayList(allPlayerMachines));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewTable(String id){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PMID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PlayerID"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("MachineID"));
        tbl_details.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("DateTime"));
        tbl_details.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("Score"));
        tbl_details.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("PriceEligibilty"));


        IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();

        try {
            PlayerMachine playerMachine = iPlayerMachineService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(playerMachine));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String[] columns = {"PMID","PlayerID", "MachineID", "DateTime","Score","PriceEligibility"};

    @FXML
    void generateReport(MouseEvent event) throws Exception {

        IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();
        List<PlayerMachine> allPlayerMachines = iPlayerMachineService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Player Machine Details");

        Font headerFont = workbook.createFont();
        ((Font) headerFont).setBold(true);
        headerFont.setFontHeightInPoints((short) 17);
        headerFont.setColor(IndexedColors.RED.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;

        for (PlayerMachine playerMachine : allPlayerMachines) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(playerMachine.getPMID());
            row.createCell(1).setCellValue(playerMachine.getPlayerID());
            row.createCell(2).setCellValue(playerMachine.getMachineID());
            row.createCell(3).setCellValue(playerMachine.getDateTime().toString());
            row.createCell(4).setCellValue(playerMachine.getScore());
            row.createCell(5).setCellValue(playerMachine.getPriceEligibilty());

        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Export to Excel");
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("Excel Workbook (*.xlsx)", "*.xlsx");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(root.getScene().getWindow());
        if (file != null) {
            String path = file.getAbsolutePath();
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
        }
    }

    public void ShowChart()
    {
        IPlayerMachineService iPlayerMachineService = new PlayerMachineServiceImpl();
        try {
            List<PlayerMachine> playermachineList = iPlayerMachineService.findAll();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


            int count0 = 0;
            int count1 = 0;

            for (PlayerMachine playermachine : playermachineList) {
                if (playermachine.getPriceEligibilty().equals("Eligible")) {
                    count0 += 1;
                }else{
                    count1 += 1;
                }

            }

            pieChartData.add(new PieChart.Data("Eligible",count0));
            pieChartData.add(new PieChart.Data("Not Eligible",count1));


            chart.setData(pieChartData);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDemo() {
        txt_playerID.setText("P03");
        txt_pmID.setText("PM03");
        txt_machineID.setText("M03");
        txt_score.setText("400");
    }
}
