package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Expences;

import java.util.List;

public interface IExpencesService {
    boolean add(Expences expences) throws Exception;
    boolean update(Expences expences) throws Exception;
    boolean delete(int id) throws Exception;
    Expences  find(int id) throws Exception;
    List<Expences> findAll(int year,int month) throws Exception;
    //List<Expences> im_fwd() throws Exception;
    //Expences  im_bwd(String lbDate) throws Exception;
}
