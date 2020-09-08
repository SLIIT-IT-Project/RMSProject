package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.EmployeeSalaryRate;
import lk.nnj.rms.fx.service.ISalaryrateService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalaryrateServiceImpl implements ISalaryrateService {
    @Override
    public boolean add(EmployeeSalaryRate emprate) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO salaryrate VALUES(?,?,?,?,?)");
        pstm.setObject(1, emprate.getId());
        pstm.setObject(2, emprate.getEmpType());
        pstm.setObject(3, emprate.getBasicSal());
        pstm.setObject(4, emprate.getOtRate());
        pstm.setObject(5, emprate.getHourRate());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(EmployeeSalaryRate emprate) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE salaryrate SET empType=?,basicSal=?,otRate=?,hourrate=? WHERE id=?");
        pstm.setObject(5,emprate.getId());
        pstm.setObject(1, emprate.getEmpType());
        pstm.setObject(2, emprate.getBasicSal());
        pstm.setObject(3, emprate.getOtRate());
        pstm.setObject(4, emprate.getHourRate());

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
    public EmployeeSalaryRate find(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salaryrate WHERE id=?");
        pstm.setObject(1, id);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            return new EmployeeSalaryRate(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }

    @Override
    public List<EmployeeSalaryRate> findAll() throws Exception {
        ArrayList<EmployeeSalaryRate> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salaryrate");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            int id = rst.getInt(1);
            String EmpType = rst.getString(2);
            String BasicSal = rst.getString(3);
            String OtRate = rst.getString(4);
            String HourRate = rst.getString(5);

            EmployeeSalaryRate emprate = new EmployeeSalaryRate(id, EmpType, BasicSal, OtRate, HourRate);
            allUser.add(emprate);

        }
        return allUser;
    }
}
