package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Salary_cal;

import java.util.List;

public interface ISalaryService {
    Salary_cal find(int id, int year, int month) throws Exception;
   // List<Salary> findAll() throws Exception;
  //  List<Salary_cal> findSalary() throws Exception;
    List<Salary_cal> findSalary(int year, int month) throws Exception;
    //Salary_cal GetRate(int id) throws Exception;
}
