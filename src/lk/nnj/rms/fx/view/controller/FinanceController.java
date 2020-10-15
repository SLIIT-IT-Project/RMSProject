package lk.nnj.rms.fx.view.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.nnj.rms.fx.service.IExpencesService;
import lk.nnj.rms.fx.service.IFinanceService;
import lk.nnj.rms.fx.service.Impl.ExpencesServiceImpl;
import lk.nnj.rms.fx.service.Impl.FinanceServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;


public class FinanceController implements Initializable {

    @FXML
    private AnchorPane rootpane;

    @FXML
    private JFXButton btn_expences;

    @FXML
    private PieChart pie;

    @FXML
    private Label lb_ex;

    @FXML
    private Label lb_in;

    @FXML
    private Label lb_profit;

    @FXML
    private JFXTextField tx;

    @FXML
    private LineChart<?, ?> lineChart;

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
        AnchorPane pane= FXMLLoader.load(getClass().getResource("/lk/nnj/rms/fx/view/style/"+ui));
        rootpane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int month=getMonth();
        int year=getYear();
         double ex=SumEx1(year,month);
        double in=SumIN1(year,month);
        String tot = String.valueOf(in-ex);
        lb_ex.setText(String.valueOf(ex));
        lb_in.setText(String.valueOf(in));
        lb_profit.setText(tot);

        ShowChart();
        showLine(year,month);


    }



    public void ShowChart()
    {
        IFinanceService iFinanceService = new FinanceServiceImpl();
        int year=getYear();
        int month=getMonth();
        try {

            double expenses = iFinanceService.SumEx(year,month);
            double income = iFinanceService.SumIN(year,month);


            ObservableList<PieChart.Data> pieChartData =
                    FXCollections.observableArrayList(
                            new PieChart.Data("Expenses", expenses),
                            new PieChart.Data("Income", income)
                    );

            pie.setData(pieChartData);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLine(int year,int month){
        LocalDate today = LocalDate.now();
        LocalDate date;
        LocalDate d1 = today.withDayOfMonth(1);
        LocalDate d2 = today.withDayOfMonth(today.lengthOfMonth());
        //txtFrom.setValue(d1);
        //txtTo.setValue(d2);
        IFinanceService iFinanceService = new FinanceServiceImpl();



        //lbl_salesDetails.setText("SALES DETAILS IN "+today.getMonth().toString());
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Expenses");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Income");




        for(int i=1;i<=month;i++)
        {

            try {
                double no = iFinanceService.SumEx(year,i);
                double tno = iFinanceService.SumIN(year,i);
                //  double dno = iFinanceService.findDineInSalesPerDay(today.withDayOfMonth(i));
                //   double deno = iFinanceService.findDeliverSalesPerDay(today.withDayOfMonth(i));
                date = today.withDayOfMonth(i);
                series1.getData().add(new XYChart.Data(date.getMonthValue() + "/" + date.getDayOfMonth(), no));
                series2.getData().add(new XYChart.Data(date.getMonthValue() + "/" + date.getDayOfMonth(), tno));
                // series3.getData().add(new XYChart.Data(date.getMonthValue() + "/" + date.getDayOfMonth(), dno));
                // series4.getData().add(new XYChart.Data(date.getMonthValue() + "/" + date.getDayOfMonth(), deno));
            } catch (Exception e) {
                e.printStackTrace();
            }

            lineChart.getData().addAll(series1, series2);
        }
    }




    public double SumEx1(int year,int month) {

        IFinanceService iFinanceService = new FinanceServiceImpl();
        double ex = 0;
        try {
            ex = iFinanceService.SumEx(year,month);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ex;
    }

    public double SumIN1(int year,int month) {

        IFinanceService iFinanceService = new FinanceServiceImpl();
        double in = 0;
        try {
            in = iFinanceService.SumIN(year,month);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return in;
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


}




