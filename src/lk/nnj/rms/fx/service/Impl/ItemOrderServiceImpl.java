package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.ItemOrder;
import lk.nnj.rms.fx.service.IItemOrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemOrderServiceImpl implements IItemOrderService {

    @Override
    public boolean add(ItemOrder itemOrder) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Order_Item VALUES(?,?,?,?,?)");
        pstm.setObject(1,itemOrder.getOid());
        pstm.setObject(2,itemOrder.getItemID());
        pstm.setObject(3,itemOrder.getItemName());
        pstm.setObject(4,itemOrder.getQty());
        pstm.setObject(5,itemOrder.getUnitPrice());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(int oid) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Order_Item WHERE order_id=?");
        pstm.setObject(1,oid);
        return pstm.executeUpdate()>0;
    }


    @Override
    public boolean delete(String id,int oid) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Order_Item WHERE item_id=? AND order_id=?");
        pstm.setObject(1,id);
        pstm.setObject(2,oid);
        return pstm.executeUpdate()>0;
    }
    @Override
    public ItemOrder find(String id,int oid) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Order_Item WHERE item_id=? AND order_id=?");
        pstm.setObject(1,id);
        pstm.setObject(2,oid);
        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            int order_id = rst.getInt(1);
            String item_id = rst.getString(2);
            String item_name = rst.getString(3);
            int qty = rst.getInt(4);
            double unit_price =rst.getDouble(5);

            ItemOrder item = new ItemOrder(order_id,item_id,item_name,qty,unit_price);
            return item;
        }
        return null;
    }

    @Override
    public List<ItemOrder> findAll(int id) throws Exception {
        ArrayList<ItemOrder> allItems = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Order_Item WHERE order_id=?");
        pstm.setObject(1,id);
        ResultSet rst = pstm.executeQuery();

        while(rst.next())
        {
            int order_id = rst.getInt(1);
            String item_id = rst.getString(2);
            String item_name = rst.getString(3);
            int qty = rst.getInt(4);
            double unit_price =rst.getDouble(5);

            ItemOrder item = new ItemOrder(order_id,item_id,item_name,qty,unit_price);
            allItems.add(item);
        }
        return allItems;
    }

    @Override
    public ItemOrder getItemOrder(int oid, String itemId) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Order_Item WHERE order_id=? and item_id=?");
        pstm.setObject(1,oid);
        pstm.setObject(2,itemId);

        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return  new ItemOrder(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)
            );
        }
        return null;
    }

    @Override
    public boolean update(ItemOrder itemOrder) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Order_Item SET qty=? WHERE order_id=? and item_id=?");
        pstm.setObject(2,itemOrder.getOid());
        pstm.setObject(3,itemOrder.getItemID());
        pstm.setObject(1,itemOrder.getQty());

        return pstm.executeUpdate()>0;
    }
}
