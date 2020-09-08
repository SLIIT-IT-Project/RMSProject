package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Category;

import java.util.List;

public interface ICategoryService {

    boolean add(Category category) throws Exception;
    boolean update(Category category) throws Exception;
    boolean delete(String id) throws Exception;
    Category find(String id) throws Exception;
    List<Category> findAll() throws Exception;
    List<String> findAllCategory() throws Exception;
}
