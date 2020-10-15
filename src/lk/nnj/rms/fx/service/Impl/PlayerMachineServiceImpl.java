package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.PlayerMachine;
import lk.nnj.rms.fx.service.IPlayerMachineService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PlayerMachineServiceImpl implements IPlayerMachineService {
    @Override
    public boolean add(PlayerMachine playerMachine) throws Exception {

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO playermachineTable VALUES(?,?,?,?,?,?)");
        pstm.setObject(1,playerMachine.getPMID());
        pstm.setObject(2,playerMachine.getPlayerID());
        pstm.setObject(3,playerMachine.getMachineID());
        pstm.setObject(4,playerMachine.getDateTime());
        pstm.setObject(5,playerMachine.getScore());


        String mid = playerMachine.getMachineID();

        int scorenew2 = CheckScore(mid);


        if(playerMachine.getScore() >= scorenew2) {
            pstm.setObject(6, "Yes");
        }else {
            pstm.setObject(6, "No");
        }

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean update(PlayerMachine playerMachine) throws Exception {

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE playermachineTable SET PlayerID=?,MachineID=?,datetime=?,Score=?,PriceEligibility=? WHERE PMID=?");
        pstm.setObject(3,playerMachine.getDateTime());
        pstm.setObject(4,playerMachine.getScore());
        pstm.setObject(6,playerMachine.getPMID());
        pstm.setObject(2,playerMachine.getMachineID());
        pstm.setObject(1,playerMachine.getPlayerID());

        String mid = playerMachine.getMachineID();

        int scorenew2 = CheckScore(mid);

        /*
        PreparedStatement pstm2 = connection.prepareStatement("SELECT DISTINCT m.scoreLimit FROM machineTable m,playermachineTable p WHERE ?=m.machineID ");
        pstm2.setObject(1,x);
        ResultSet score = pstm2.executeQuery();

        while(score.next()){
            String scorenew = score.getString(1);
            scorenew2 = Integer.parseInt(scorenew);
            System.out.println(score.getString(1));
        } */

        if(playerMachine.getScore() >= scorenew2) {
            pstm.setObject(5, "Yes");
        }else {
            pstm.setObject(5, "No");
        }

        return pstm.executeUpdate() > 0;
    }

    @Override
    public boolean delete(String pmid) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM playermachineTable WHERE PMID=?");
        pstm.setObject(1,pmid);


        return pstm.executeUpdate() > 0;
    }

    @Override
    public PlayerMachine find(String pmid) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM playermachineTable WHERE PMID=?");
        pstm.setObject(1,pmid);

        ResultSet rst = pstm.executeQuery();

        if(rst.next()){
            return new PlayerMachine(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getTimestamp(4),
                    rst.getInt(5),
                    rst.getString(6)

            );
        }
        return null;
    }

    @Override
    public List<PlayerMachine> findAll() throws Exception {
        ArrayList<PlayerMachine> allPlayerMachine = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM playermachineTable");

        ResultSet rst = pstm.executeQuery();

        while(rst.next()){
            String pmid= rst.getString(1);
            String pid = rst.getString(2);
            String mid = rst.getString(3);
            Timestamp datetime = rst.getTimestamp(4);
            int score = rst.getInt(5);
            String eligibility = rst.getString(6);


            PlayerMachine playerMachine = new PlayerMachine(pmid,pid,mid,datetime,score,eligibility);
            allPlayerMachine.add(playerMachine);

        }
        return allPlayerMachine;
    }

    @Override
    public int CheckScore(String mid) throws Exception {
        String scoreStr;
        int scoreInt = 0;

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm2 = connection.prepareStatement("SELECT DISTINCT m.scoreLimit FROM machineTable m,playermachineTable p WHERE ?=m.machineID ");
        pstm2.setObject(1,mid);
        ResultSet score = pstm2.executeQuery();

        while(score.next()){
            scoreStr = score.getString(1);
            scoreInt = Integer.parseInt(scoreStr);
            System.out.println(score.getString(1));
        }

        return scoreInt;
    }


}
