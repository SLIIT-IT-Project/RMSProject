package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.service.IItemService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemServiceImpl implements IItemService {


    @Override
    public boolean add(Item item) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES(?,?,?,?)");
        pstm.setObject(1,item.getItem_id());
        pstm.setObject(2,item.getItem_name());
        pstm.setObject(3,item.getDescription());
        pstm.setObject(4,item.getUnit_price());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Item item) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET item_name=?,description=?,unit_price=? WHERE item_id=?");
        pstm.setObject(4,item.getItem_id());
        pstm.setObject(1,item.getItem_name());
        pstm.setObject(2,item.getDescription());
        pstm.setObject(3,item.getUnit_price());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Item WHERE item_id=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate()>0;
    }

    @Override
    public Item find(String id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE item_id=?");
        pstm.setObject(1,id);

        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return  new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public List<Item> findAll() throws Exception {
        ArrayList<Item> allItems = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item");
        ResultSet rst = pstm.executeQuery();

        while(rst.next())
        {
            String item_id = rst.getString(1);
            String item_name = rst.getString(2);
            String description = rst.getString(3);
            Double unit_price = rst.getDouble(4);

            Item item = new Item(item_id,item_name,description,unit_price);
            allItems.add(item);
        }
        return allItems;
    }

    @Override
    public int totalItems() throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(item_id) FROM Item");

        ResultSet rst = pstm.executeQuery();
        int count = 0;

        if(rst.next())
        {
            count = rst.getInt(1);
        }
        return count;
    }
}
