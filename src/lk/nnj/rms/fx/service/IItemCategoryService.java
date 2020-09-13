package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Customer;
import lk.nnj.rms.fx.model.ItemCategory;

import java.util.List;

public interface IItemCategoryService {

    boolean add(ItemCategory itemCategory) throws Exception;
    boolean delete(String itemId,String catID) throws Exception;
    ItemCategory find(String id) throws Exception;
    List<ItemCategory> findAll(String itemId) throws Exception;

}
