package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Delivery;

import java.util.List;

public interface IManageDeliveryService {
    boolean add(Delivery manageDelivery) throws  Exception;
    boolean Edit(Delivery manageDelivery) throws  Exception;
    boolean delete(int id)throws Exception;
    Delivery find(int id) throws  Exception;
    List<Delivery> findAll() throws Exception;


}
