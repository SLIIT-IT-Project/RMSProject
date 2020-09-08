package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.service.IQueryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class QueryServiceImpl implements IQueryService {
    @Override
    public int getInvoiceNo() throws Exception {
        int inID = 100000;
        int orderID;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT order_id FROM Order1 ORDER BY order_id DESC LIMIT 1");
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            orderID = rst.getInt(1);
            inID = orderID+1;

        }
        return inID;
    }

    @Override
    public List<String> getAllItemNames() throws Exception {
        ArrayList<String> itemList= new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT item_name FROM Item");
        ResultSet rst = pstm.executeQuery();

        while (rst.next())
        {
            itemList.add(rst.getString(1));
        }
        return itemList;
    }

    @Override
    public String getItemID(String name) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT item_id FROM Item WHERE item_name=?");
        pstm.setObject(1,name);
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return rst.getString(1);
        }
        return null;
    }

    @Override
    public double getItemPrice(String name) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT unit_price FROM Item WHERE item_name=?");
        pstm.setObject(1,name);
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return rst.getDouble(1);
        }
        return 0;
    }
}
