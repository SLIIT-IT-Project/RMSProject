package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Category;
import lk.nnj.rms.fx.model.Customer;
import lk.nnj.rms.fx.service.ICustomerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService{

    @Override
    public boolean add(Customer customer) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?)");
        pstm.setObject(1,customer.getCustomer_id());
        pstm.setObject(2,customer.getCname());
        pstm.setObject(3,customer.getMobile());
        pstm.setObject(4,customer.getAddress());
        pstm.setObject(5,customer.getNo_of_orders());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("UPDATE customer SET cname=?, mobile=?, address=?, no_of_orders=? WHERE customer_id=?");
        pstm.setObject(5,customer.getCustomer_id());
        pstm.setObject(1,customer.getCname());
        pstm.setObject(2,customer.getMobile());
        pstm.setObject(3,customer.getAddress());
        pstm.setObject(4,customer.getNo_of_orders());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("DELETE FROM customer WHERE customer_id=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate()>0;
    }

    @Override
    public Customer find(int id) throws Exception {
        Connection connection=DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM customer WHERE customer_id=?");
        pstm.setObject(1,id);

        ResultSet rst= pstm.executeQuery();

        if(rst.next())
        {
            return new Customer(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getInt(5)

            );
        }
        return null;
    }

    @Override
    public List<Customer> findAll() throws Exception {
        ArrayList<Customer> allCustomer= new ArrayList<>();
        Connection connection= DBConnection.getConnection();
        PreparedStatement pstm=connection.prepareStatement("SELECT * FROM customer");
        ResultSet rst=pstm.executeQuery();

        while(rst.next()){
            int id=rst.getInt(1);
            String cname=rst.getString(2);
            String mobile=rst.getString(3);
            String address=rst.getString(4);
            int no_of_orders=rst.getInt(5);

            Customer customer = new Customer (id, cname, mobile, address, no_of_orders);
            allCustomer.add(customer);
        }
        return allCustomer;
    }
}
