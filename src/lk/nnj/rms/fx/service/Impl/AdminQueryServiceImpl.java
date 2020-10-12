package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.service.IAdminQueryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class AdminQueryServiceImpl implements IAdminQueryService {
    @Override
    public int findTotalOrders(LocalDate d1, LocalDate d2) throws Exception {
        int noOfOrders=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(order_id) FROM Order1 WHERE date_time>? AND date_time<?");
        pstm.setObject(1,d1);
        pstm.setObject(2,d2);
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            noOfOrders = rst.getInt(1);

        }
        return noOfOrders;
    }

    @Override
    public double findTotalSales(LocalDate d1, LocalDate d2) throws Exception {
        double totSales=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT SUM(tot_amount) FROM Order1 WHERE date_time>? AND date_time<?");
        pstm.setObject(1,d1);
        pstm.setObject(2,d2);
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            totSales = rst.getInt(1);

        }
        return totSales;
    }

    @Override
    public int findTotalItems(LocalDate d1, LocalDate d2) throws Exception {
        int totItems=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(oi.order_id) FROM Order1 o,Order_Item oi WHERE o.order_id = oi.order_id AND o.date_time>? AND o.date_time<?");
        pstm.setObject(1,d1);
        pstm.setObject(2,d2);
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            totItems = rst.getInt(1);

        }
        return totItems;
    }

    @Override
    public int findTotalCustomers(LocalDate d1, LocalDate d2) throws Exception {
        int totCustomers=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(DISTINCT cid) FROM Order1 WHERE date_time>? AND date_time<?");
        pstm.setObject(1,d1);
        pstm.setObject(2,d2);
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            totCustomers = rst.getInt(1);

        }
        return totCustomers;
    }

    @Override
    public int findTotalSalesPerDay(LocalDate d1) throws Exception {
        int noOfOrders=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(order_id) FROM Order1 WHERE date_time Like ?");
        pstm.setObject(1,d1+"%");
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            noOfOrders = rst.getInt(1);

        }
        return noOfOrders;
    }

    @Override
    public int findDineInSalesPerDay(LocalDate d1) throws Exception {
        int noOfOrders=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(order_id) FROM Order1 WHERE order_type=? AND date_time Like ?");
        pstm.setObject(1,"Dine Inn");
        pstm.setObject(2,d1+"%");
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            noOfOrders = rst.getInt(1);

        }
        return noOfOrders;
    }

    @Override
    public int findTakeAwaySalesPerDay(LocalDate d1) throws Exception {
        int noOfOrders=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(order_id) FROM Order1 WHERE order_type=? AND date_time Like ?");
        pstm.setObject(1,"Take Away");
        pstm.setObject(2,d1+"%");
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            noOfOrders = rst.getInt(1);

        }
        return noOfOrders;
    }

    @Override
    public int findDeliverSalesPerDay(LocalDate d1) throws Exception {
        int noOfOrders=0;
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(order_id) FROM Order1 WHERE order_type=? AND date_time Like ?");
        pstm.setObject(1,"Deliver");
        pstm.setObject(2,d1+"%");
        ResultSet rst = pstm.executeQuery();
        if(rst.next())
        {
            noOfOrders = rst.getInt(1);

        }
        return noOfOrders;
    }
}
