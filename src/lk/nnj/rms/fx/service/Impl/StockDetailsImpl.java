package lk.nnj.rms.fx.service.Impl;
import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Stock;
import lk.nnj.rms.fx.service.IStockService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class StockDetailsImpl implements IStockService {

    @Override
    public boolean add(Stock stock) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("INSERT INTO stock VALUE (?,?,?,?,?,?)");
        pstm.setObject(1,stock.getSTId());
        pstm.setObject(2,stock.getSTName());
        pstm.setObject(3,stock.getQuantity());
        pstm.setObject(4,stock.getType());
        pstm.setObject(5,stock.getDate_Time());
        pstm.setObject(6,stock.getTotalCost());



        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Stock stock) throws Exception {

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("UPDATE stock set STName = ?,Quantity = ?,Type = ?,Date_Time = ?,TotalCost = ?  WHERE STId=?");
        pstm.setObject(6,stock.getSTId());
        pstm.setObject(1,stock.getSTName());
        pstm.setObject(2,stock.getQuantity());
        pstm.setObject(3,stock.getType());
        pstm.setObject(4,stock.getDate_Time());
        pstm.setObject(5,stock.getTotalCost());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(int STId) throws Exception {

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM stock WHERE STId=?");
        pstm.setObject(1,STId);

        return pstm.executeUpdate()>0;
    }

    @Override
    public Stock find(int STId) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM stock WHERE STID=?");
        pstm.setObject(1,STId);


        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return new Stock(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getString(4),
                    rst.getDate(5),
                    rst.getInt(6)
            );
        }
        return null;
    }

    @Override
    public List<Stock> findAll() throws Exception {

        ArrayList<Stock> allStock = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM stock ");
        ResultSet rst = pstm.executeQuery();
        while(rst.next())
        {
            int STId = rst.getInt(1);
            String STItemName=rst.getString(2);
            int Quantity=rst.getInt(3);
            String Type=rst.getString(4);
            Date DateTime = rst.getDate(5);
            int TotalCost=rst.getInt(6);


            Stock stock = new Stock(STId,STItemName,Quantity,Type,DateTime,TotalCost);
            allStock.add(stock);
        }
        return allStock;

    }
}
