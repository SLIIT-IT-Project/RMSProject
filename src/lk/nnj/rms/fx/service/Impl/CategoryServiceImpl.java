package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Category;
import lk.nnj.rms.fx.service.ICategoryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryServiceImpl implements ICategoryService{
    @Override
    public boolean add(Category category) throws Exception {

        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("INSERT INTO category VALUES (?,?,?,?)");
        pstm.setObject(1,category.getCategory_id());
        pstm.setObject(2,category.getCategory_name());
        pstm.setObject(3,category.getDescription());
        pstm.setObject(4,category.getNo_of_items());


        return pstm.executeUpdate()>0;

    }

    @Override
    public boolean update(Category category) throws Exception {

        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("UPDATE category SET category_name=?, description=?, no_of_items=? WHERE category_id=?");
        pstm.setObject(4,category.getCategory_id());
        pstm.setObject(1,category.getCategory_name());
        pstm.setObject(2,category.getDescription());
        pstm.setObject(3,category.getNo_of_items());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean updateNoOfItems(String id,int no) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("UPDATE category SET no_of_items=? WHERE category_id=?");
        pstm.setObject(2,id);
        pstm.setObject(1,no);
        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("DELETE FROM category WHERE category_id=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate()>0;
    }

    @Override
    public Category find(String id) throws Exception {
        Connection connection=DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM category WHERE category_id=?");
        pstm.setObject(1,id);

        ResultSet rst= pstm.executeQuery();

        if(rst.next())
        {
            return new Category(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)

            );
        }
        return null;
    }

    @Override
    public Category findId(String id) throws Exception {
        Connection connection=DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM category WHERE category_name=?");
        pstm.setObject(1,id);

        ResultSet rst= pstm.executeQuery();

        if(rst.next())
        {
            return new Category(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)

            );
        }
        return null;
    }

    @Override
    public List<Category> findAll() throws Exception {
        ArrayList<Category> allCategory= new ArrayList<>();
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM category");
        ResultSet rst=pstm.executeQuery();

        while(rst.next()){
            String id=rst.getString(1);
            String name=rst.getString(2);
            String description=rst.getString(3);
            int noOfItems=rst.getInt(4);

            Category category = new Category (id, name, description,noOfItems);
            allCategory.add(category);
        }
        return allCategory;
    }

    @Override
    public List<String> findAllCategory() throws Exception {
        ArrayList<String> allCategory= new ArrayList<>();
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT category_name FROM category");
        ResultSet rst=pstm.executeQuery();

        while (rst.next())
        {
            String name=rst.getString(1);
            allCategory.add(name);
        }
        return allCategory;
    }

}
