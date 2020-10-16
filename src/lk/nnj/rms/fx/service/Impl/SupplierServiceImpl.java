package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Supplier;
import lk.nnj.rms.fx.service.ISupplierService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceImpl implements ISupplierService {


    @Override
    public boolean add(Supplier supplier) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm= connection.prepareStatement("INSERT INTO supplier VALUE (?,?,?,?,?,?,?)");
        pstm.setObject(1,supplier.getSId());
        pstm.setObject(2,supplier.getSName());
        pstm.setObject(3,supplier.getEmail());
        pstm.setObject(4,supplier.getPhoneNo());
        pstm.setObject(5,supplier.getCompany());
        pstm.setObject(6,supplier.getAddress());
        pstm.setObject(7,supplier.getDiscreption());


        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean update(Supplier supplier) throws Exception {

        Connection connection =DBConnection.getConnection();
        PreparedStatement pstm =connection.prepareStatement("UPDATE supplier set SName =? ,company = ?,phoneNo = ?,Email = ?,address = ?,discreption = ?  WHERE SId=?");
        pstm.setObject(7,supplier.getSId());
        pstm.setObject(1,supplier.getSName());
        pstm.setObject(2,supplier.getCompany());
        pstm.setObject(3,supplier.getPhoneNo());
        pstm.setObject(4,supplier.getEmail());
        pstm.setObject(5,supplier.getAddress());
        pstm.setObject(6,supplier.getDiscreption());

        return pstm.executeUpdate()>0;
    }

    @Override
    public boolean delete(int SId) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM supplier WHERE SId=?");
        pstm.setObject(1,SId);

        return pstm.executeUpdate()>0;
    }

    @Override
    public Supplier find(int SId) throws Exception {

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier WHERE SId=?");
        pstm.setObject(1,SId);


        ResultSet rst = pstm.executeQuery();

        if(rst.next())
        {
            return new Supplier(
                    rst.getInt(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }
        return null;

    }

    @Override
    public List<Supplier> findAll() throws Exception {

        ArrayList<Supplier> allSupplier = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier ");
        ResultSet rst = pstm.executeQuery();

        while(rst.next())
        {
            int SId = rst.getInt(1);
            String SName=rst.getString(2);
            String Email=rst.getString(3);
            String Company=rst.getString(4);
            String PhoneNo= rst.getString(5);
            String Discreption=rst.getString(6);
            String Address=rst.getString(7);

            Supplier supplier = new Supplier(SId,SName,Company,PhoneNo,Email,Address,Discreption);
            allSupplier.add(supplier);
        }
        return allSupplier;

    }
    @Override
    public int totalItems() throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT COUNT(SId) FROM supplier");

        ResultSet rst = pstm.executeQuery();
        int count = 0;

        if(rst.next())
        {
            count = rst.getInt(1);
        }
        return count;
    }

}
