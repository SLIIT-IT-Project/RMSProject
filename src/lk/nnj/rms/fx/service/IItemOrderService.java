package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.ItemOrder;

import java.util.List;

public interface IItemOrderService {
    boolean add(ItemOrder itemOrder) throws Exception;
    boolean delete(String id) throws Exception;
    List<ItemOrder> findAll(String id) throws Exception;
}
