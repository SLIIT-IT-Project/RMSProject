package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.EmployeeSalaryRate;
import lk.nnj.rms.fx.model.Salary;
import lk.nnj.rms.fx.service.ISalaryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalaryServiceImpl implements ISalaryService {
/*
    @Override
    public boolean add(Salary salary) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO salary VALUES(?,?,?,?,?,?)");
        pstm.setObject(1, salary.getEmpID());
        pstm.setObject(2, salary.getSalDate());
        pstm.setObject(3, salary.getBasicSal());
        pstm.setObject(4, salary.getOtRate());
        pstm.setObject(5, salary.getHourRate());
        pstm.setObject(6, salary.getTotSal());

        return pstm.executeUpdate() > 0;
    }



    @Override
    public boolean delete(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM salaryrate WHERE id=?");
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public Salary find(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salary WHERE id=?");
        pstm.setObject(1, id);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            return new Salary(
                    rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6)
            );
        }
        return null;
    }

    @Override
    public List<Salary> findAll() throws Exception {
        ArrayList<Salary> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salary");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            String id = rst.getString(1);
            Date EmpDate = rst.getDate(2);
            String BasicSal = rst.getString(3);
            String OtRate = rst.getString(4);
            String HourRate = rst.getString(5);
            String TotSal = rst.getString(6);

            Salary salary = new Salary(id, EmpDate, BasicSal, OtRate, HourRate,TotSal);
            allUser.add(salary);

        }
        return allUser;
    }

    @Override
    public List<Salary>GetRate() throws Exception {
        ArrayList<Salary> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT EmpType,ohT FROM salary");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            String id = rst.getString(1);
            Date EmpDate = rst.getDate(2);
            String BasicSal = rst.getString(3);
            String OtRate = rst.getString(4);
            String HourRate = rst.getString(5);
            String TotSal = rst.getString(6);

            Salary salary = new Salary(id, EmpDate, BasicSal, OtRate, HourRate,TotSal);
            allUser.add(salary);

        }
        return allUser;
    }*/
}
