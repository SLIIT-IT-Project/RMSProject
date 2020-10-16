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
import lk.nnj.rms.fx.model.Machine;
import lk.nnj.rms.fx.service.IMachineService;
import lk.nnj.rms.fx.service.Impl.MachineServiceImpl;
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

public class ManageMachineController implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_report;

    @FXML
    private JFXTextField txt_id;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private JFXTextField txt_score;

    @FXML
    private TableView<Machine> tbl_details;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXButton btn_machineplayer;

    @FXML
    private JFXButton btn_player;

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
    void goMachinePlayer(ActionEvent event) {
        try {
            Parent root = null;
            root = FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/ManagePlayerMachine.fxml"));
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
        String id,name,score;
        id = txt_id.getText();
        name = txt_name.getText();
        score = txt_score.getText();

        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Machine ID !!!");
            return;
        }else if(!(String.valueOf(id.charAt(0)).equals("M"))){
            JOptionPane.showMessageDialog(null, "Invalid Machine ID !!!");
            return;
        }else if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Name !!!");
            return;
        }else if(score.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Score !!!");
            return;
        }

        try{
            int scorenew = Integer.parseInt(score);
            Machine machine = new Machine(id,name,scorenew);

            if(scorenew < 0){
                JOptionPane.showMessageDialog(null, "Can not enter minus values !!!");
                return;
            }

            IMachineService imachine = new MachineServiceImpl();

            try {
                imachine.add(machine);
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
        String id = txt_id.getText();

        IMachineService iMachineService = new MachineServiceImpl();

        try {
            iMachineService.delete(id);
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

        IMachineService iMachineService = new MachineServiceImpl();

        try {
            Machine machine = iMachineService.find(id);
            txt_id.setText(machine.getMachineID());
            txt_name.setText(machine.getGameName());
            int scorenew = machine.getScoreLimit();
            txt_score.setText(String.valueOf(scorenew));/////////////////////////////////////
            viewTable(id);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Error!!! wrong");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String id,name,score;
        id = txt_id.getText();
        name = txt_name.getText();
        score = txt_score.getText();

        if(id.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Machine ID !!!");
            return;
        }else if(!(String.valueOf(id.charAt(0)).equals("M"))){
            JOptionPane.showMessageDialog(null, "Invalid Machine ID !!!");
            return;
        }else if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Name !!!");
            return;
        }else if(score.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Score !!!");
            return;
        }

        try{
            int scorenew = Integer.parseInt(score);
            Machine machine = new Machine(id,name,scorenew);

            if(scorenew < 0){
                JOptionPane.showMessageDialog(null, "Can not enter minus values !!!");
                return;
            }

            IMachineService iMachineService = new MachineServiceImpl();

            try {
                if(iMachineService.update(machine)){
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



    public void reset(){
        txt_id.setText("");
        txt_score.setText("");
        txt_name.setText("");
        txt_search.setText("");
    }

    public void viewTable(){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("machineID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("GameName"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("scoreLimit"));


        IMachineService iMachineService = new MachineServiceImpl();

        try {
            List<Machine> allMachines = iMachineService.findAll();
            tbl_details.setItems(FXCollections.observableArrayList(allMachines));
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
        ArrayList<Machine> machineList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for(Machine machine:machineList){
            txt_id.setText(machine.getMachineID());
            txt_name.setText(machine.getGameName());
            txt_score.setText(Integer.toString(machine.getScoreLimit()));

        }
    }

    public void viewTable(String id){
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("machineID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("GameName"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("scoreLimit"));


        IMachineService iMachineService = new MachineServiceImpl();

        try {
            Machine machine = iMachineService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(machine));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String[] columns = {"machineID", "GameName", "scoreLimit"};

    @FXML
    void generateReport(MouseEvent event) throws Exception {

        IMachineService iMachineService = new MachineServiceImpl();
        List<Machine> allMachines = iMachineService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Machine Details");

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

        for (Machine machine : allMachines) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(machine.getMachineID());
            row.createCell(1).setCellValue(machine.getGameName());
            row.createCell(2).setCellValue(machine.getScoreLimit());

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
        IMachineService iMachineService = new MachineServiceImpl();
        try {
            List<Machine> machineList = iMachineService.findAll();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


            int count0 = 0;
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;
            int count5 = 0;




            for (Machine machine : machineList) {
                if (machine.getScoreLimit() <= 100) {
                    count0 += 1;
                } else if(machine.getScoreLimit() <= 200){
                    count1 += 1;
                }else if(machine.getScoreLimit() <= 300){
                    count2 += 1;
                }else if(machine.getScoreLimit() <= 400){
                    count3 += 1;
                }else if(machine.getScoreLimit() <= 500){
                    count4 += 1;
                } else{
                    count5 += 1;
                }

            }

            pieChartData.add(new PieChart.Data("<= 100",count0));
            pieChartData.add(new PieChart.Data("<= 200",count1));
            pieChartData.add(new PieChart.Data("<= 300",count2));
            pieChartData.add(new PieChart.Data("<= 400",count3));
            pieChartData.add(new PieChart.Data("<= 500",count4));
            pieChartData.add(new PieChart.Data("> 500",count5));

            chart.setData(pieChartData);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setDemo() {
        txt_id.setText("M03");
        txt_name.setText("Game3");
        txt_score.setText("300");

    }
}
