package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.ItemCategory;
import lk.nnj.rms.fx.service.IItemCategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ItemCategoryServiceImpl implements IItemCategoryService {


    @Override
    public boolean add(ItemCategory itemCategory) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("INSERT INTO item_category VALUES (?,?,?)");
        pstm.setObject(1,itemCategory.getItem_id());
        pstm.setObject(2,itemCategory.getCategory_id());
        pstm.setObject(3,itemCategory.getCategoryName());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String itemid,String catId) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("DELETE FROM item_category WHERE item_id=? AND category_id=?");
        pstm.setObject(1,itemid);
        pstm.setObject(2,catId);

        return pstm.executeUpdate()>0;
    }

    @Override
    public ItemCategory find(String id) throws Exception {
        return null;
    }

    @Override
    public List<ItemCategory> findAll(String itemId) throws Exception {
        ArrayList<ItemCategory> allCategory= new ArrayList<>();
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM item_category WHERE item_id =?");
        pstm.setObject(1,itemId);
        ResultSet rst=pstm.executeQuery();

        while(rst.next()){
            String tid=rst.getString(1);
            String cid=rst.getString(2);
            String cname=rst.getString(3);

            ItemCategory category = new ItemCategory(tid,cid,cname);
            allCategory.add(category);
        }
        return allCategory;
    }
}
