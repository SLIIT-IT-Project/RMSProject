package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.MangeDelivery;
import lk.nnj.rms.fx.service.IManageDeliveryService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ManageDeliveryServiceimp implements IManageDeliveryService {
    @Override
    public boolean add(MangeDelivery manageDelivery) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO Track VALUES(?,?,?,?,?,?)");
        pstm.setObject(1, manageDelivery.getTrack_id());
        pstm.setObject(2, manageDelivery.getDel_date_time());
        pstm.setObject(3, manageDelivery.getStatus());
        pstm.setObject(4, manageDelivery.getNote());
        pstm.setObject(5, manageDelivery.getDelivered_by());
        pstm.setObject(6, manageDelivery.getOrder_id());

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean Edit (MangeDelivery manageDelivery) throws Exception {
            Connection connection = DBConnection.getConnection();
            PreparedStatement pstm = connection.prepareStatement("update track set del_date=?,status=?,note=?,delivered_by=?,oid=? where track_id=?");
            pstm.setObject(6,manageDelivery.getTrack_id());
            pstm.setObject(1,manageDelivery.getDel_date_time());
            pstm.setObject(2,manageDelivery.getStatus());
            pstm.setObject(3,manageDelivery.getNote());
            pstm.setObject(4,manageDelivery.getDelivered_by());
            pstm.setObject(5,manageDelivery.getOrder_id());

            return pstm.executeUpdate()>0;
        }



    @Override
    public boolean delete(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM Track WHERE track_id=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate()>0;

    }

    @Override
    public MangeDelivery find(int id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM track WHERE track_id=?");
        pstm.setObject(1,id);

        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return  new MangeDelivery(
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

    @Override
    public List<MangeDelivery> findAll() throws Exception {
        ArrayList<MangeDelivery> allDelivery = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Track");
        ResultSet rst = pstm.executeQuery();

        while(rst.next())
        {
            int Track_id = rst.getInt(1);
            Timestamp Del_date_time = rst.getTimestamp(2);
            String Status = rst.getString(3);
            String Note = rst.getString(4);
            String Delivered_by = rst.getString(5);
            int Order_id = rst.getInt(6);

            MangeDelivery mangeDelivery = new MangeDelivery(Track_id,Del_date_time,Status,Note,Delivered_by,Order_id);
            allDelivery.add(mangeDelivery);
        }
        return allDelivery;
    }
}
