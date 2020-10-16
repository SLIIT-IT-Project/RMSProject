package lk.nnj.rms.fx.service;

import lk.nnj.rms.fx.model.PlayerMachine;

import java.util.List;

public interface IPlayerMachineService {
    boolean add(PlayerMachine playerMachine) throws Exception;
    boolean update(PlayerMachine playerMachine) throws Exception;
    boolean delete(String pmid) throws Exception;
    PlayerMachine find(String pid) throws Exception;
    List<PlayerMachine> findAll() throws Exception;
    int CheckScore(String mid) throws Exception;
}
