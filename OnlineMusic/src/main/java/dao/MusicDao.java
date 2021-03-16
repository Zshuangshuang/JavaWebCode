package dao;

import Entity.Music;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MusicDao {
    /**
     * 查询音乐列表中所有的音乐
     * */
    public  List<Music> findMusic(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Music> musicList = new ArrayList<>();
        try {
            String sql = "select * from music";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Music music = new Music();
                music.setId(resultSet.getInt("id"));
                music.setTitle(resultSet.getString("title"));
                music.setSinger(resultSet.getString("singer"));
                music.setTime(resultSet.getDate("time"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userId"));
                musicList.add(music);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return musicList;
    }
    /**
     * 根据id查询音乐
     * */
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
    /**
     * 模糊查询音乐，随意输入与标题相关的关键字，看音乐是否存在,不存在则返回Null
     * */
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
    /**
     * 插入音乐
     * */
    public  int insert(String title, String singer, Date time, String url, int userId){
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            String sql = "insert into music(title,singer,time,url,userId) values (?,?,?,?,?)";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1,title);
            statement.setString(2,singer);
            statement.setDate(3, new java.sql.Date(time.getTime()));
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
    /**
     * 根据id删除音乐
     * */
    public  int deleteByMusicId(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "delete  from music where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            int ret = statement.executeUpdate();
            if (ret == 1){
                //表示删除music表中的数据成功
                if (findLoveMusicOnDel(id)){
                    int ret2 = removeLoveMusicOnDel(id);
                    if (ret2 == 1){
                        //表示删除loveMusic中的数据成功
                        return 1;
                    }
                }
            }
            return 1;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement);
        }
        return 0;
    }
/**
 * 根据喜欢列表中的id来删除音乐
 * */
    public int removeLoveMusicOnDel(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            connection = DBUtils.getConnection();
            String sql = "delete from lovemusic where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            ret = statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement);
        }
        return ret;
    }
/**
 * 根据喜欢列表的id来查找对应id的音乐是否存在
 * */
    public  boolean findLoveMusicOnDel(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DBUtils.getConnection();
            String sql = "select  * from lovemusic where id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                return true;
            }

        }catch (SQLException e){
            e.printStackTrace();

        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return false;
    }
    //判断当前用户是否已经添加过喜欢的音乐
    public boolean findMusicByMusicId(int userId,int musicId){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String sql = "select * from lovemusic where user_id=? and music_id=?";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1,userId);
            statement.setInt(2,musicId);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return false;
    }
    //添加到喜欢的音乐
    public  boolean insertLoveMusic(int userId,int musicId){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            String sql = "insert into lovemusic(user_id, music_id) values (?,?)";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,musicId);
            ret = statement.executeUpdate();
            if(ret == 1) {
                return true;
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement);
        }
        return false;
    }

    public  int removeLoveMusic(int userId,int musicId){
        Connection connection = null;
        PreparedStatement statement = null;
        int ret = 0;
        try {
            connection = DBUtils.getConnection();
            String sql = "delete from lovemusic where user_id=? and music_id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            statement.setInt(2,musicId);
            ret = statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement);
        }
        return ret;
    }
    //查询当前用户的全部喜欢
    public  List<Music> findLoveMusic(int userId){
        List<Music> musicList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String sql = "select m.id as music_id,title,singer,time,url,userid from lovemusic lm,music m where lm.music_id=m.id and lm.user_id=?";
            connection = DBUtils.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1,userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Music music = new Music();
                music.setId(resultSet.getInt("music_id"));
                music.setTitle(resultSet.getString("title"));
                music.setSinger(resultSet.getString("singer"));
                music.setTime(resultSet.getDate("time"));
                music.setUrl(resultSet.getString("url"));
                music.setUserId(resultSet.getInt("userid"));
                musicList.add(music);
            }
            System.out.println("dao : " + musicList);
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return musicList;
    }
    //根据关键字查询喜欢的音乐
    public  List<Music> ifMusicLove(String str,int userId){
        List<Music> musicList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try{
           connection = DBUtils.getConnection();
            String sql = "select m.id as music_id,title,singer,time,url,userId from lovemusic lm,music m " +
                    "where lm.music_id=m.id and user_Id=? and title like '%"+str+"%'";
           statement = connection.prepareStatement(sql);
           statement.setInt(1,userId);
           resultSet = statement.executeQuery();
           while(resultSet.next()){
               Music music = new Music();
               music.setId(resultSet.getInt("music_id"));
               music.setUserId(resultSet.getInt("userId"));
               music.setTitle(resultSet.getString("title"));
               music.setUrl(resultSet.getString("url"));
               music.setSinger(resultSet.getString("singer"));
               music.setTime(resultSet.getDate("time"));
               musicList.add(music);

               System.out.println("musicList: "+musicList);
           }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBUtils.getClose(connection,statement,resultSet);
        }
        return musicList;
    }


}
