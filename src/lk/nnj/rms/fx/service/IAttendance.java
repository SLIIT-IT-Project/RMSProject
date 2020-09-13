package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.Attendance;
import lk.nnj.rms.fx.model.User;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public interface IAttendance {
    void initialize(URL location, ResourceBundle resources);

    boolean update(Attendance attendance) throws Exception;
    boolean delete(String emp_id) throws Exception;
    boolean add(Attendance attendance) throws Exception;
    Attendance find(String emp_id) throws Exception;

    List<Attendance> findAll() throws Exception;
}
