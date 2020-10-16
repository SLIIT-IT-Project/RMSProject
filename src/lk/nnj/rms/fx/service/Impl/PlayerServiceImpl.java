package lk.nnj.rms.fx.service.Impl;

import lk.nnj.rms.fx.db.DBConnection;
import lk.nnj.rms.fx.model.Player;
import lk.nnj.rms.fx.service.IPlayerService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements IPlayerService {
    @Override
    public boolean add(Player player) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO playerTable VALUES(?,?,?)");
        pstm.setObject(1,player.getPlayerID());
        pstm.setObject(2,player.getPlayerName());

//        String x = player.getPlayerID();
//
//        int total = 0;
//        double avg = 0;
//        int count = 0;
//
//        PreparedStatement pstm2 = connection.prepareStatement("SELECT m.Score FROM playerTable p,playermachineTable m WHERE ?=m.PlayerID ");
//        pstm2.setObject(1,x);
//        ResultSet score = pstm2.executeQuery();
//
//        while(score.next()){
//            String temp =  score.getString(1);
//            int temp2 = Integer.parseInt(temp);
//            total += temp2;
//            count -= -1;
//        }
//
//        avg = total / (count * 1.0);

        pstm.setObject(3, 0.0);

        return pstm.executeUpdate() > 0;

    }

    @Override
    public boolean update(Player player) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE playerTable SET Namename=?,pRank=? WHERE PlayerID=?");
        pstm.setObject(1,player.getPlayerName());

        pstm.setObject(3,player.getPlayerID());

        String pid = player.getPlayerID();

        double avg = CheckRank(pid);

        /*int total = 0;
        double avg = 0;
        int count = 0;
        int temp2 = 0;

        PreparedStatement pstm2 = connection.prepareStatement("SELECT m.Score FROM playerTable p,playermachineTable m WHERE ?=m.PlayerID ");
        pstm2.setObject(1,x);
        ResultSet score = pstm2.executeQuery();

        while(score.next()){
            String temp =  score.getString(1);
            temp2 = Integer.parseInt(temp);
            total += temp2;
            count += 1;
            System.out.println(temp2);
            //System.out.println(temp2);
        }

        avg = total / (count * 1.0);
        System.out.println(avg);*/

        pstm.setObject(2, avg);


        return pstm.executeUpdate() > 0;

    }

    @Override
    public boolean delete(String id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM playerTable WHERE PlayerID=?");
        pstm.setObject(1,id);

        return pstm.executeUpdate() > 0;
    }

    @Override
    public Player find(String id) throws Exception {
        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM playerTable WHERE PlayerID=?");
        pstm.setObject(1,id);

        ResultSet rst = pstm.executeQuery();

        if(rst.next()){
            return new Player(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDouble(3)
            );
        }
       return null;

    }

    @Override
    public List<Player> findAll() throws Exception {
        ArrayList<Player> allPlayer = new ArrayList<>();

        Connection connection = DBConnection.getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM playerTable");

        ResultSet rst = pstm.executeQuery();

        while(rst.next()){
            String id = rst.getString(1);
            String name = rst.getString(2);
            double rank = rst.getDouble(3);


            Player player = new Player(id,name,rank);
            allPlayer.add(player);

        }
        return allPlayer;
    }

    @Override
    public double CheckRank(String pid) throws Exception {
        Connection connection = DBConnection.getConnection();

        int total = 0, count = 0, scoreInt;
        double avg;
        String scoreStr;

        PreparedStatement pstm2 = connection.prepareStatement("SELECT m.Score FROM playerTable p,playermachineTable m WHERE ?=m.PlayerID ");
        pstm2.setObject(1,pid);
        ResultSet score = pstm2.executeQuery();

        while(score.next()){
            scoreStr =  score.getString(1);
            scoreInt = Integer.parseInt(scoreStr);
            total += scoreInt;
            count += 1;
            System.out.println(scoreInt);
        }

        avg = total / (count * 1.0);

        double rank;

        if(avg <= 100.0){
            rank = 0.0;
        }else if(avg <= 300.0){
            rank = 1.0;
        }else if(avg <= 500.0){
            rank = 2.0;
        }else if(avg <= 700.0){
            rank = 3.0;
        }else if(avg <= 900.0){
            rank = 4.0;
        }else{
            rank = 5.0;
        }

        System.out.println(avg);

        if(total == 0){
            return 0;
        }else{
            return rank;
        }

    }


}
