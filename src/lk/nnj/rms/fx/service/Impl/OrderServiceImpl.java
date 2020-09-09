package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.Order;
import lk.nnj.rms.fx.service.IOrderService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements IOrderService {

    @Override
    public boolean add(Order order) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Order1 VALUES(?,?,?,?,?,?,?,?,?)");
        pstm.setObject(1,order.getOid());
        pstm.setObject(2,order.getDate_tme());
        pstm.setObject(3,order.getDescription());
        pstm.setObject(4,order.getOrder_type());
        pstm.setObject(5,order.getOrder_amount());
        pstm.setObject(6,order.getService_charge());
        pstm.setObject(7,order.getTot_amount());
        pstm.setObject(8,order.getCid());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Order order) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE Order1 SET description=?,order_type=?,order_amount=?,service_charge=?" +
                "tot_amount=? WHERE order_id=?");
        pstm.setObject(6,order.getOid());
        pstm.setObject(1,order.getDescription());
        pstm.setObject(2,order.getOrder_type());
        pstm.setObject(3,order.getOrder_amount());
        pstm.setObject(4,order.getService_charge());
        pstm.setObject(5,order.getTot_amount());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Order1 WHERE order_id=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate()>0;
    }

    @Override
    public Order find(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Order1 WHERE order_id=?");
        pstm.setObject(1,id);

        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return  new Order(
                    rst.getInt(1),
                    rst.getTimestamp(2).toLocalDateTime(),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getDouble(6),
                    rst.getDouble(7),
                    rst.getInt(8)
            );
        }
        return null;
    }

    @Override
    public List<Order> findAll() throws Exception {
        ArrayList<Order> allOrder = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Order1");
        ResultSet rst = pstm.executeQuery();

        while(rst.next())
        {
            int oid = rst.getInt(1);
            LocalDateTime date_time = rst.getTimestamp(2).toLocalDateTime();
            String description = rst.getString(3);
            String order_type = rst.getString(4);
            double order_amount = rst.getDouble(5);
            double service_charge = rst.getDouble(6);
            double tot_amount = rst.getDouble(7);
            int cid = rst.getInt(8);

            allOrder.add(new Order(oid,date_time,description,order_type,order_amount,service_charge,tot_amount,cid));
        }
        return allOrder;
    }
}
