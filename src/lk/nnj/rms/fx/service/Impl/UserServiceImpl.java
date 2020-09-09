package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.User;
import lk.nnj.rms.fx.service.IUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class UserServiceImpl implements IUser {
    @Override
    public boolean add(User user) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?,?,?)");
        pstm.setObject(1,user.getEmp_id() );
        pstm.setObject(2,user.getFullname());
        pstm.setObject(3,user.getDob());
        pstm.setObject(4,user.getAddress());
        pstm.setObject(5,user.getMobile());
        pstm.setObject(6,user.getJsd());
        pstm.setObject(7,user.getPwd());
        pstm.setObject(8,user.getType());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(User user) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE employee SET full_name =?,date_of_birth=?,address=?,mobile=?,job_started_date=?,password=?,emp_type=? WHERE emp_id=?");
        pstm.setObject(8,user.getEmp_id() );
        pstm.setObject(1,user.getFullname());
        pstm.setObject(2,user.getDob());
        pstm.setObject(3,user.getAddress());
        pstm.setObject(4,user.getMobile());
        pstm.setObject(5,user.getJsd());
        pstm.setObject(6,user.getPwd());
        pstm.setObject(7,user.getType());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String emp_id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM employee WHERE emp_id=?");
        pstm.setObject(1,emp_id);

        return pstm.executeUpdate()>0;

    }

    @Override
    public User find(String emp_id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM employee WHERE emp_id=?");
        pstm.setObject(1,emp_id);

        ResultSet rst =pstm.executeQuery();

        if(rst.next()){
            return new User(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getString(4),
                    rst.getInt(5),
                    rst.getDate(6),
                    rst.getString(7),
                    rst.getString(8)
            );
        }
        return null;

    }


    @Override
    public List<User> findAll() throws Exception {
        ArrayList<User> allUser = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM employee");
        ResultSet rst = pstm.executeQuery();

        while (rst.next()){
            String emp_id = rst.getString(1);
            String fullname = rst.getString(2);
            Date dob = rst.getDate(3);
            String address = rst.getString(4);
            int mobile = rst.getInt(5);
            Date jsd = rst.getDate(6);
            String pwd = rst.getString(7);
            String type = rst.getString(8);

            User user = new User(emp_id,fullname,dob,address,mobile,jsd,pwd,type);
            allUser.add(user);
        }
        return allUser;

    }



}
