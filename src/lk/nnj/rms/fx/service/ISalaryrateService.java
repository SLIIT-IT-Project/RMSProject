package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.EmployeeSalaryRate;

import java.util.List;

public interface ISalaryrateService {

        boolean add(EmployeeSalaryRate emprate) throws Exception;
        boolean update(EmployeeSalaryRate emprate) throws Exception;
        boolean delete(int id) throws Exception;
        boolean replace(String type) throws Exception;
        int getInvoiceNo() throws Exception;
        EmployeeSalaryRate find(int id) throws Exception;
        List<EmployeeSalaryRate> findAll() throws Exception;


}


