package service;

import Entity.Music;
import dao.MusicDao;

import java.util.Date;
import java.util.List;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-04 11:01
 */
public class MusicService {
    public List<Music> findMusic(){
        MusicDao musicDao = new MusicDao();
        List<Music> musicList = musicDao.findMusic();
        return musicList;
    }
    public Music findMusicById(int id){
        MusicDao musicDao = new MusicDao();
        Music music = musicDao.findMusicById(id);
        return music;
    }
    public  List<Music> ifMusic(String str){
        MusicDao musicDao = new MusicDao();
        List<Music> list = musicDao.ifMusic(str);
        return list;
    }
    public int insert(String title, String singer,Date time, String url, int userId){
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.insert(title, singer, time, url, userId);
        return ret;
    }
    public  int deleteByMusicId(int id){
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.deleteByMusicId(id);
        return ret;
    }
    public int removeLoveMusicOnDel(int id) {
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.removeLoveMusicOnDel(id);
        return ret;
    }
    public  boolean findLoveMusicOnDel(int id) {
        MusicDao musicDao = new MusicDao();
        boolean ret = musicDao.findLoveMusicOnDel(id);
        return ret;

    }
    public boolean findMusicByMusicId(int userId,int musicId){
        MusicDao musicDao = new MusicDao();
        boolean ret = musicDao.findMusicByMusicId(userId, musicId);
        return ret;
    }
    public  boolean insertLoveMusic(int userId,int musicId){
        MusicDao musicDao = new MusicDao();
        boolean ret = musicDao.insertLoveMusic(userId, musicId);
        return ret;
    }
    public  int removeLoveMusic(int userId,int musicId){
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.removeLoveMusic(userId, musicId);
        return ret;
    }
    public  List<Music> findLoveMusic(int userId){
        MusicDao musicDao = new MusicDao();
        List<Music> list = musicDao.findLoveMusic(userId);
        return list;
    }
    public  List<Music> ifMusicLove(String str,int userId){
        MusicDao musicDao = new MusicDao();
        List<Music> list = musicDao.ifMusicLove(str, userId);
        return list;
    }
}
