package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Attendance;
import lk.nnj.rms.fx.service.IAttendance;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class AttendanceServiceImpl implements IAttendance {
    @Override
    public boolean add(Attendance attendance) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO attendance VALUES(?,?,?,?,?)");
        pstm.setObject(1,attendance.getEmp_id() );
        pstm.setObject(2,attendance.getFullname());
        pstm.setObject(3,attendance.getDate());
        pstm.setObject(4,attendance.getWorking_h());
        pstm.setObject(5,attendance.getOt_h());

        return pstm.executeUpdate()>0;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public boolean update(Attendance attendance) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE attendance SET full_name =?,date=?,working_h=?,ot_h=? WHERE emp_id=?");
        pstm.setObject(5,attendance.getEmp_id() );
        pstm.setObject(1,attendance.getFullname());
        pstm.setObject(2,attendance.getDate());
        pstm.setObject(3,attendance.getWorking_h());
        pstm.setObject(4,attendance.getOt_h());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String emp_id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM attendance WHERE emp_id=?");
        pstm.setObject(1,emp_id);

        return pstm.executeUpdate()>0;

    }

    @Override
    public Attendance find(String emp_id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM attendance WHERE emp_id=?");
        pstm.setObject(1,emp_id);

        ResultSet rst =pstm.executeQuery();

        if(rst.next()){
            return new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getInt(4),
                    rst.getInt(5)
            );
        }
        return null;

    }

    @Override
    public List<Attendance> findAll() throws Exception {
        ArrayList<Attendance> allAttendance = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM attendance");
        ResultSet rst = pstm.executeQuery();

        while (rst.next()){
            String emp_id = rst.getString(1);
            String fullname = rst.getString(2);
            Date date = rst.getDate(3);
            int working_h = rst.getInt(4);
            int ot_h = rst.getInt(5);


            Attendance attendance = new Attendance(emp_id,fullname,date,working_h,ot_h);
            allAttendance.add(attendance);
        }
        return allAttendance;

    }

    @Override
    public int findWorking_h(String id,int year,int month) throws Exception{
        System.out.println(year+"  "+month);
        Connection connection=DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT sum(working_h) FROM attendance WHERE emp_id=? AND YEAR(date)=? AND month(date)=?");
        pstm.setObject(1,id);
        pstm.setObject(2,year);
        pstm.setObject(3,month);

        ResultSet rst=pstm.executeQuery();
        int tot=0;
        if(rst.next()){
            tot = rst.getInt(1);
        }
        return tot;
    }

    @Override
    public int findOt_h(String id,int year,int month) throws Exception{
        Connection connection=DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT sum(ot_h) FROM attendance WHERE emp_id=? AND YEAR(date)=? AND month(date)=?");
        pstm.setObject(1,id);
        pstm.setObject(2,year);
        pstm.setObject(3,month);

        ResultSet rst=pstm.executeQuery();
        int tot=0;
        if(rst.next()){
            tot = rst.getInt(1);
        }
        return tot;
    }




}



