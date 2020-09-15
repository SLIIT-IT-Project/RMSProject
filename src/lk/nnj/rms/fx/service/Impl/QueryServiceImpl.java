package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Delivery;
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
    public int getCusNo() throws Exception {
        int inID = 100;
        int cID;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT customer_id FROM Customer ORDER BY customer_id DESC LIMIT 1");
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            cID = rst.getInt(1);
            inID = cID+1;

        }
        return inID;
    }

    @Override
    public int getDelNo() throws Exception {
        int inID = 8000;
        int cID;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT track_id FROM Track ORDER BY track_id DESC LIMIT 1");
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            cID = rst.getInt(1);
            inID = cID+1;

        }
        return inID;
    }

    @Override
    public int getPaymentNo() throws Exception {
        int inID = 1000000;
        int pID;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT PID FROM PaymentTable ORDER BY PID DESC LIMIT 1");
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            pID = rst.getInt(1);
            inID = pID+1;

        }
        return inID;
    }

    @Override
    public List<String> getAllItems(String category) throws Exception {
        ArrayList<String> itemList= new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT item_name FROM ItemView WHERE category_name=?");
        pstm.setObject(1,category);
        ResultSet rst = pstm.executeQuery();

        while (rst.next())
        {
            itemList.add(rst.getString(1));
        }
        return itemList;
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
    @Override
    public String findOrderDetails(int id) throws Exception {
        String allItems="";
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT item_name, qty FROM Order_Item WHERE order_id=?");
        pstm.setObject(1,id);
        ResultSet rst = pstm.executeQuery();

        while(rst.next())
        {
            String itemName = rst.getString(1);
            String qty = rst.getString(2);

            allItems = allItems +" "+itemName + " X  "+qty +",";
        }

        return allItems.substring(0,allItems.length()-1);
    }

    @Override
    public Delivery findDeliver(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM track WHERE oid=?");
        pstm.setObject(1,id);

        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return  new Delivery(
                    rst.getInt(1),
                    rst.getTimestamp(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getInt(6)
            );
        }
        return null;

    }
}
