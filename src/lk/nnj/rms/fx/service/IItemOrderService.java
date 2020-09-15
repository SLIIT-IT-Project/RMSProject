package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.ItemOrder;

import java.util.List;

public interface IItemOrderService {
    boolean add(ItemOrder itemOrder) throws Exception;
    boolean delete(int oid) throws Exception;
    boolean delete(String id,int oid) throws Exception;
    List<ItemOrder> findAll(int id) throws Exception;
    ItemOrder getItemOrder(int oid, String itemId) throws  Exception;
    boolean update(ItemOrder itemOrder) throws Exception;
    ItemOrder find(String id,int oid) throws Exception;
}
