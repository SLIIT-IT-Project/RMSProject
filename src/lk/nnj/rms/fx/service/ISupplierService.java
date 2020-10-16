package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Supplier;

import java.util.List;

public interface ISupplierService {
    boolean add(Supplier supplier) throws Exception;
    boolean update(Supplier supplier) throws Exception;
    boolean delete(int SId) throws Exception;
    Supplier find (int SId) throws Exception;
    List<Supplier> findAll() throws Exception;
    int totalItems() throws Exception;
}
