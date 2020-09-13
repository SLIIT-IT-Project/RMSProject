package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Customer;

import java.util.List;

public interface ICustomerService {

    boolean add(Customer customer) throws Exception;
    boolean update(Customer customer) throws Exception;
    boolean update(int noOfOrder , int cid) throws Exception;
    boolean delete(int id) throws Exception;
    Customer find(int id) throws Exception;
    List<Customer> findAll() throws Exception;
    Customer find(String mobile) throws Exception;
}
