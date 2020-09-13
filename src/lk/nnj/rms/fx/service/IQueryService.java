package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.ItemOrder;

import java.util.List;

public interface IQueryService {
    int getInvoiceNo() throws Exception;
    List<String> getAllItemNames() throws Exception;
    String getItemID(String name) throws Exception;
    double getItemPrice(String name) throws Exception;
    int getCusNo() throws Exception;
    String findOrderDetails(int id) throws Exception;
    int getPaymentNo() throws Exception;
    List<String> getAllItems(String category) throws Exception;
}
