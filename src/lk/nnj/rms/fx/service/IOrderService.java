package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.Order;
import lk.nnj.rms.fx.model.OrderDel;

import java.time.LocalDate;
import java.util.List;

public interface IOrderService {
    boolean add(Order order) throws Exception;
    boolean update(Order order) throws Exception;
    boolean delete(int id) throws Exception;
    Order find (int id) throws Exception;
    List<Order> findAll() throws Exception;
    List<Order> findAll(LocalDate d1, LocalDate d2) throws Exception;
    List<OrderDel> findAllOrderDel(LocalDate d1) throws Exception;
    OrderDel findOrderDel(int oid) throws Exception;
}
