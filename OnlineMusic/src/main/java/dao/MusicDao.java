package dao;

import Entity.Music;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-02 13:41
 */
public class MusicDao {
    public  List<Music> findMusic(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Music> list = new ArrayList<>();
        Music music = null;
        try {
            String sql = "select * from music";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                music = new Music();
                music.setId(resultSet.getInt("id"));
                music.setSinger(resultSet.getString("singer"));
                music.setTime(resultSet.getDate("time"));
                music.setTitle(resultSet.getString("title"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userId"));
                list.add(music);
                return list;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return null;
    }
    public  Music findMusicById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Music music = null;
        try{
           String sql = "select * from music where id=?";
           connection = DBUtils.getConnection();
           statement = connection.prepareStatement(sql);
           statement.setInt(1,id);
           resultSet = statement.executeQuery();
           if (resultSet.next()){
               music = new Music();
               music.setId(resultSet.getInt("id"));
               music.setUrl(resultSet.getString("url"));
               music.setUserId(resultSet.getInt("userId"));
               music.setTitle(resultSet.getString("title"));
               music.setTime(resultSet.getDate("time"));
               music.setSinger(resultSet.getString("singer"));
               return music;
           }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return null;
    }
    public  List<Music> ifMusic(String str){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Music> list = new ArrayList<>();
        try {
            String sql = "select * from music where title like'%"+str+"%'";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while(resultSet.next()){
                Music music = new Music();
                music.setSinger(resultSet.getString("singer"));
                music.setId(resultSet.getInt("id"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userId"));
                music.setTitle(resultSet.getString("title"));
                music.setTime(resultSet.getDate("time"));
                list.add(music);
                return list;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return null;
    }
    public static int insert(String title,String singer,String time,String url,int userId){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String sql = "insert into music(title,singer,time,url,userId) values (?,?,?,?,?)";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,singer);
            statement.setString(3,time);
            statement.setString(4,url);
            statement.setInt(5,userId);
            int ret = statement.executeUpdate();
            if (ret == 1){
                System.out.println("插入成功!");
                return 1;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement);
        }
        return 0;
    }


}
