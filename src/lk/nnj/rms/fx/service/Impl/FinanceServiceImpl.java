package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.service.IFinanceService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class FinanceServiceImpl implements IFinanceService {

    public double SumEx(int year,int month) throws Exception {
        int ID = 0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT sum(evalue) FROM expences WHERE YEAR(edate)=? and month(edate)=? ;");
        pstm.setObject(1, year);
        pstm.setObject(2, month);
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            ID = rst.getInt(1);

        }
        return ID;
    }

    public double SumIN(int year,int month) throws Exception {
        double ID = 0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT sum(tot_amount) FROM order1 WHERE YEAR(date_time)=? and month(date_time)=? ;");
        pstm.setObject(1, year);
        pstm.setObject(2, month);
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            ID = rst.getInt(1);

        }
        return ID;
    }


}
