package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.Order;

import java.util.List;

public interface IOrderService {
    boolean add(Order order) throws Exception;
    boolean update(Order order) throws Exception;
    boolean delete(int id) throws Exception;
    Order find (int id) throws Exception;
    List<Order> findAll() throws Exception;
}
