package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Item;
import lk.nnj.rms.fx.model.Order;

import java.util.List;

public interface IItemService {
    boolean add(Item item) throws Exception;
    boolean update(Item item) throws Exception;
    boolean delete(String id) throws Exception;
    Item find (String id) throws Exception;
    List<Item> findAll() throws Exception;
}
