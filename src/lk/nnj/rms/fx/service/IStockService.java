package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Stock;

import java.util.List;

public interface IStockService {
    boolean add(Stock stock) throws Exception;
    boolean update(Stock stock) throws Exception;
    boolean delete(int STId) throws Exception;
    Stock find (int STId) throws Exception;
    List<Stock> findAll() throws Exception;
    int totalItems() throws Exception;
}
