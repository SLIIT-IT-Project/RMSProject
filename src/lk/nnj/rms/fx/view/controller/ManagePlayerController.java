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
import lk.nnj.rms.fx.model.Player;
import lk.nnj.rms.fx.service.IPlayerService;
import lk.nnj.rms.fx.service.Impl.PlayerServiceImpl;
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

public class ManagePlayerController implements Initializable {

    @FXML
    private PieChart chart;

    @FXML
    private JFXButton btn_chart;

    @FXML
    private AnchorPane root;

    @FXML
    private JFXButton btn_report;

    @FXML
    private JFXTextField txt_search;

    @FXML
    private JFXButton btn_search;

    @FXML
    private JFXTextField txt_playerID;

    @FXML
    private JFXButton btn_add;

    @FXML
    private JFXTextField txt_name;

    @FXML
    private TableView<Player> tbl_details;

    @FXML
    private JFXButton btn_update;

    @FXML
    private JFXButton btn_delete;

    @FXML
    private JFXButton btn_machineplayer;

    @FXML
    private JFXButton btn_machine;

    @FXML
    private JFXButton btn_back;

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
    void resetButton(ActionEvent event) {
        reset();
        viewTable();
    }


    @FXML
    void add(ActionEvent event) {
        String pid, name, rank;
        pid = txt_playerID.getText();
        name = txt_name.getText();

        if(pid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Player ID !!!");
            return;
        }else if(!(String.valueOf(pid.charAt(0)).equals("P"))){
            JOptionPane.showMessageDialog(null, "Invalid Player ID !!!");
            return;
        }else if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Name !!!");
            return;
        }

        Player player = new Player(pid, name);

        IPlayerService iplayer = new PlayerServiceImpl();

        try {
            iplayer.add(player);
            JOptionPane.showMessageDialog(null, "Success, added");
            viewTable();
            reset();
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Error!!! can not add");
            e.printStackTrace();
        }
    }

    @FXML
    void delete(ActionEvent event) {
        String pid = txt_playerID.getText();

        IPlayerService iPlayerService = new PlayerServiceImpl();

        try {
            iPlayerService.delete(pid);
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
        String id = txt_search.getText();

        IPlayerService iPlayerService = new PlayerServiceImpl();

        try {
            Player palyer = iPlayerService.find(id);
            txt_playerID.setText(palyer.getPlayerID());
            txt_name.setText(palyer.getPlayerName());

            viewTable(id);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error!!! wrong");
        }
    }

    @FXML
    void update(ActionEvent event) {
        String pid, name, rank;
        pid = txt_playerID.getText();
        name = txt_name.getText();

        if(pid.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Player ID !!!");
            return;
        }else if(!(String.valueOf(pid.charAt(0)).equals("P"))){
            JOptionPane.showMessageDialog(null, "Invalid Player ID !!!");
            return;
        }else if(name.equals("")){
            JOptionPane.showMessageDialog(null, "Empty Name !!!");
            return;
        }


        Player player = new Player(pid, name);

        IPlayerService iPlayerService = new PlayerServiceImpl();

        try {
            if (iPlayerService.update(player)) {
                JOptionPane.showMessageDialog(null, "Updated, added");
                reset();
                viewTable();
            } else {
                JOptionPane.showMessageDialog(null, "Error!!! can not update");
            }
        } catch (Exception e) {


            e.printStackTrace();
        }
    }

    @FXML
    void viewSelect(MouseEvent event) {
        ArrayList<Player> playerList = new ArrayList<>(tbl_details.getSelectionModel().getSelectedItems());

        for (Player player : playerList) {
            txt_playerID.setText(player.getPlayerID());
            txt_name.setText(player.getPlayerName());

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        viewTable();
    }

    public void reset() {
        txt_playerID.setText("");
        txt_name.setText("");
        txt_search.setText("");
    }

    public void setDemo() {
        txt_playerID.setText("P03");
        txt_name.setText("Kamal");
    }

    public void viewTable() {
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Rank"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PlayerID"));


        IPlayerService iPlayerService = new PlayerServiceImpl();

        try {
            List<Player> allPlayer = iPlayerService.findAll();
            tbl_details.setItems(FXCollections.observableArrayList(allPlayer));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void viewTable(String id) {
        tbl_details.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("PlayerID"));
        tbl_details.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("PlayerName"));
        tbl_details.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("Rank"));

        IPlayerService iPlayerService = new PlayerServiceImpl();

        try {
            Player player = iPlayerService.find(id);
            tbl_details.setItems(FXCollections.observableArrayList(player));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static String[] columns = {"PlayerID", "PlayerName", "Rank"};

    @FXML
    void generateReport(MouseEvent event) throws Exception {

        IPlayerService iPlayerService = new PlayerServiceImpl();
        List<Player> allPlayers = iPlayerService.findAll();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Player Details");

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

        for (Player player : allPlayers) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(player.getPlayerID());
            row.createCell(1).setCellValue(player.getPlayerName());
            row.createCell(2).setCellValue(player.getRank());

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
        IPlayerService iPlayerService = new PlayerServiceImpl();
        try {
            List<Player> playList = iPlayerService.findAll();
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();


            int count0 = 0;
            int count1 = 0;
            int count2 = 0;
            int count3 = 0;
            int count4 = 0;
            int count5 = 0;




            for (Player play : playList) {
                if (play.getRank() == 0.0) {
                    count0 += 1;
                } else if(play.getRank() == 1.0){
                    count1 += 1;
                }else if(play.getRank() == 2.0){
                    count2 += 1;
                }else if(play.getRank() == 3.0){
                    count3 += 1;
                }else if(play.getRank() == 4.0){
                    count4 += 1;
                } else{
                    count5 += 1;
                }

            }

            pieChartData.add(new PieChart.Data("rank 0",count0));
            pieChartData.add(new PieChart.Data("rank 1",count1));
            pieChartData.add(new PieChart.Data("rank 2",count2));
            pieChartData.add(new PieChart.Data("rank 3",count3));
            pieChartData.add(new PieChart.Data("rank 4",count4));
            pieChartData.add(new PieChart.Data("rank 5",count5));

            chart.setData(pieChartData);



        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
