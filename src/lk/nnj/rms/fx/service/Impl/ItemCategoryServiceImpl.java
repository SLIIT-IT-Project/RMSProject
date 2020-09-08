package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.ItemCategory;
import lk.nnj.rms.fx.service.IItemCategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

public class ItemCategoryServiceImpl implements IItemCategoryService {


    @Override
    public boolean add(ItemCategory itemCategory) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("INSERT INTO itemCategory VALUES (?,?)");
        pstm.setObject(1,itemCategory.getItem_id());
        pstm.setObject(2,itemCategory.getCategory_id());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("DELETE FROM itemCategory WHERE item_id=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate()>0;
    }

    @Override
    public ItemCategory find(String id) throws Exception {
        return null;
    }

    @Override
    public List<ItemCategory> findAll() throws Exception {
        return null;
    }
}
