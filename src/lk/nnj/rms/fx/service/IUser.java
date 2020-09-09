package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.User;

import java.util.List;

public interface IUser {
    boolean add(User user) throws Exception;
    boolean update(User user) throws Exception;
    boolean delete(String emp_id) throws Exception;
    User find(String emp_id) throws Exception;
    List<User> findAll() throws Exception;
}
