package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.MangeDelivery;

import java.util.List;

public interface IManageDeliveryService {
    boolean add(MangeDelivery manageDelivery) throws  Exception;
    boolean Edit(MangeDelivery manageDelivery) throws  Exception;
    boolean delete(int id)throws Exception;
    MangeDelivery find(int id) throws  Exception;
    List<MangeDelivery> findAll() throws Exception;


}
