package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Salary_cal;
import lk.nnj.rms.fx.service.ISalaryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SalaryCalcImpl implements ISalaryService {



    @Override
    public Salary_cal find(int id, int year, int month) throws Exception {
        System.out.println(""+id);

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT distinct e.emp_id,s.empType,s.basicSal,s.otRate,s.hourRate FROM salaryrate s,attendance a,employee e WHERE YEAR(a.date)=? and month(a.date)=? and e.emp_id=? and e.emp_type=s.empType;");
        pstm.setObject(1, year);
        pstm.setObject(2, month);
        pstm.setObject(3, id);
        ResultSet rst = pstm.executeQuery();

        PreparedStatement pstm1 = connection.prepareStatement("SELECT sum(working_h),sum(ot_h) FROM attendance where YEAR(date)=? and month(date)=? and emp_id=?;");
        pstm1.setObject(1, year);
        pstm1.setObject(2, month);
        pstm1.setObject(3, id);
        ResultSet rst1 = pstm1.executeQuery();

        if (rst.next() && rst1.next()) {
            return new Salary_cal(
                    rst.getString(1),
                    rst.getString(2),
                    rst1.getDouble(2),
                    rst1.getDouble(1),
                    rst.getDouble(3),
                    rst.getDouble(4),
                    rst.getDouble(5),
                    rst.getDouble(3)+(rst1.getDouble(2)*  rst.getDouble(4))+(rst1.getDouble(1)*rst.getDouble(5))
            );
        }
        return null;
    }

    /*@Override
    public List<Salary> findAll() throws Exception {
        ArrayList<Salary> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salary");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            String EmpID = rst.getString(1);
            Date SalDate = rst.getDate(2);
            String OtHours = rst.getString(3);
            String WorkHours = rst.getString(4);
            String BasicSal = rst.getString(5);
            String TotSal = rst.getString(6);

            Salary salary = new Salary(EmpID,SalDate,OtHours,WorkHours,BasicSal,TotSal);
            allUser.add(salary);

        }
        return allUser;
    }*/


  /*  @Override
    public List<Salary> findAll() throws Exception {
        ArrayList<Salary> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM salary");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            String EmpID = rst.getString(1);
            Date SalDate = rst.getDate(2);
            String OtHours = rst.getString(3);
            String WorkHours = rst.getString(4);
            String BasicSal = rst.getString(5);
            String TotSal = rst.getString(6);

            Salary salary = new Salary(EmpID,SalDate,OtHours,WorkHours,BasicSal,TotSal);
            allUser.add(salary);

        }
        return allUser;
    }*/

 /*   @Override
    public List<Salary_cal> findSalary() throws Exception {
        ArrayList<Salary_cal> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement p = connection.prepareStatement("SELECT distinct emp_id FROM attendance");
        ResultSet r = p.executeQuery();
        System.out.println("ID");

        while (r.next()) {
            int id = r.getInt(1);


            PreparedStatement pstm = connection.prepareStatement("SELECT distinct s.id,s.empType,s.basicSal,s.otRate,s.hourRate FROM salaryrate s,attendance a,employee e WHERE e.emp_id=? and e.emp_type=s.empType;");
            pstm.setObject(1, id);
            ResultSet rst = pstm.executeQuery();
            String EmpType = null;
            double BasicSal = 0;
            double OtRate = 0;
            double HourRate = 0;
            String ID = null;
            if (rst.next()) {
                ID = rst.getString(1);
                System.out.println(ID);
                EmpType = rst.getString(2);
                BasicSal = rst.getDouble(3);
                OtRate = rst.getDouble(4);
                HourRate = rst.getDouble(5);


            }
            PreparedStatement pstm1 = connection.prepareStatement("SELECT sum(working_h),sum(ot_h) FROM restdb.attendance where emp_id=?;");
            pstm1.setObject(1, id);
            ResultSet rst1 = pstm1.executeQuery();
            double OtHours = 0;
            double WorkHours = 0;
            if (rst1.next()) {
                WorkHours = rst1.getDouble(1);
                OtHours = rst1.getDouble(2);
            }

            System.out.println(EmpType);
            double TotalSal=0.0;
            TotalSal=BasicSal+(OtHours*OtRate)+(WorkHours*HourRate);
            Salary_cal salary = new Salary_cal(ID, EmpType, OtHours, WorkHours, BasicSal, OtRate, HourRate,TotalSal);
            allUser.add(salary);
        }

        return allUser;
    }*/


    @Override
    public List<Salary_cal> findSalary(int year, int month) throws Exception {
        ArrayList<Salary_cal> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement p = connection.prepareStatement("SELECT distinct emp_id FROM attendance WHERE YEAR(date)=? and month(date)=?");
        p.setObject(1, year);
        p.setObject(2, month);
        ResultSet r = p.executeQuery();
        System.out.println("ID");

        while (r.next()) {
            int id = r.getInt(1);


            PreparedStatement pstm = connection.prepareStatement("SELECT distinct e.emp_id,s.empType,s.basicSal,s.otRate,s.hourRate FROM salaryrate s,attendance a,employee e WHERE YEAR(a.date)=? and month(a.date)=? and e.emp_id=? and e.emp_type=s.empType;");
            pstm.setObject(1, year);
            pstm.setObject(2, month);
            pstm.setObject(3, id);
            ResultSet rst = pstm.executeQuery();
            String EmpType = null;
            double BasicSal = 0;
            double OtRate = 0;
            double HourRate = 0;
            String ID = null;
            if (rst.next()) {
                ID = rst.getString(1);
                EmpType = rst.getString(2);
                BasicSal = rst.getDouble(3);
                OtRate = rst.getDouble(4);
                HourRate = rst.getDouble(5);


            }
            PreparedStatement pstm1 = connection.prepareStatement("SELECT sum(working_h),sum(ot_h) FROM restdb.attendance where YEAR(date)=? and month(date)=? and emp_id=?;");
            pstm1.setObject(1, year);
            pstm1.setObject(2, month);
            pstm1.setObject(3, id);
            ResultSet rst1 = pstm1.executeQuery();
            double OtHours = 0;
            double WorkHours = 0;
            if (rst1.next()) {
                WorkHours = rst1.getDouble(1);
                OtHours = rst1.getDouble(2);
            }

            System.out.println(EmpType);
            double TotalSal=0.0;
            TotalSal=BasicSal+(OtHours*OtRate)+(WorkHours*HourRate);
            Salary_cal salary = new Salary_cal(ID, EmpType, OtHours, WorkHours, BasicSal, OtRate, HourRate,TotalSal);
            allUser.add(salary);
        }

        return allUser;
    }

}
