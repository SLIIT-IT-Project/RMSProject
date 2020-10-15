package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Expences;
import lk.nnj.rms.fx.service.IExpencesService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExpencesServiceImpl implements IExpencesService {


    @Override
    public int GetID() throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT max(id) FROM expences");


        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {

             if(rst.getString(1)==null)
             {
                 return 1;
             }
             else
             {
                 return rst.getInt(1);
             }

        }
        return 0;
    }

    public int getInvoiceNo() throws Exception {
        int inID = 100000;
        int ID;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT id FROM expences ORDER BY id DESC LIMIT 1");
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            ID = rst.getInt(1);
            inID = ID+1;

        }
        return inID;
    }


    @Override
    public boolean add(Expences expences) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO expences VALUES(?,?,?,?,?)");
        pstm.setObject(1, expences.getId());
        pstm.setObject(2, expences.getExpenType());
        pstm.setObject(3, expences.getDesc());
        pstm.setObject(4, expences.getValue());
        pstm.setObject(5, expences.getDate());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(Expences expences) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE expences SET expenType=?,description=?,evalue=?,edate=? WHERE id=?");
        pstm.setObject(5,expences.getId());
        pstm.setObject(1, expences.getExpenType());
        pstm.setObject(2, expences.getDesc());
        pstm.setObject(3, expences.getValue());
        pstm.setObject(4, expences.getDate());

        System.out.println("ok sir");
        return pstm.executeUpdate() > 0;
    }









    @Override
    public boolean delete(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM expences WHERE id=?");
        pstm.setObject(1, id);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public Expences find(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expences WHERE id=?");
        pstm.setObject(1, id);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            return new Expences(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5)
            );
        }
        return null;
    }

   /* @Override
    public List<Expences> findAll() throws Exception {
        ArrayList<Expences> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expences ");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            int id = rst.getInt(1);
            String ExpenType = rst.getString(2);
            String Desc = rst.getString(3);
            String Value = rst.getString(4);
            Date Date = rst.getDate(5);

            Expences expences = new Expences(id,ExpenType,Desc,Value,Date);
            allUser.add(expences);

        }
        return allUser;
    }*/

    @Override
    public List<Expences> findAll(int year, int month) throws Exception {
        ArrayList<Expences> allUser = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expences WHERE YEAR(edate)=? and month(edate)=? ");
        pstm.setObject(1,year);
        pstm.setObject(2,month);
//PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expences ");

        ResultSet rst = pstm.executeQuery();

        while (rst.next()) {
            int id = rst.getInt(1);
            String ExpenType = rst.getString(2);
            String Desc = rst.getString(3);
            String Value = rst.getString(4);
            Date Date = rst.getDate(5);

            Expences expences = new Expences(id,ExpenType,Desc,Value,Date);
            allUser.add(expences);

        }
        return allUser;
    }

   /* @Override
    public Expences im_bwd(int id,String lbDate) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM expences WHERE edate=lbDate");
        pstm.setObject(1, id);

        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            return new Expences(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDate(5)
            );
        }
        return null;
    }*/

}
