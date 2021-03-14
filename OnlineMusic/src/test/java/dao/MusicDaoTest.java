package dao;

import Entity.Music;
import org.junit.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class MusicDaoTest {
    //测试数据库的插入音乐的操作
    @Test
    public void insert() {
    Music music = new Music();
    music.setTime(Date.from(Instant.now()));
    music.setSinger("程响");
    music.setTitle("四季予你");
    music.setUrl("music\\四季予你");
    music.setUserId(1);
    MusicDao musicDao = new MusicDao();
    musicDao.insert("四季予你","程响",Date.from(Instant.now()),"music\\四季予你",1);
    }

    @Test
    public void findMusic() {
        MusicDao musicDao = new MusicDao();
        List<Music> list = new ArrayList<>();
        list = musicDao.findMusic();
        System.out.println(list.size());
    }

    @Test
    public void findMusicById() {
        MusicDao musicDao = new MusicDao();
        Music music = musicDao.findMusicById(1);
        System.out.println(music);
    }

    @Test
    public void ifMusic() {
        MusicDao musicDao = new MusicDao();
        List<Music> list=musicDao.ifMusic("四季");
        System.out.println(list);

    }

    @Test
    public void deleteByMusicId() {
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.deleteByMusicId(1);
        System.out.println(ret);
    }
    @Test
    public void insertLoveMusic() {
        MusicDao musicDao = new MusicDao();
        boolean ret = musicDao.insertLoveMusic(1,2);
        System.out.println(ret);
    }

    @Test
    public void removeLoveMusicOnDel() {
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.removeLoveMusicOnDel(7);
        System.out.println(ret);
    }

    @Test
    public void findLoveMusicOnDel() {
        MusicDao musicDao = new MusicDao();
        boolean ret = musicDao.findLoveMusicOnDel(8);
        System.out.println(ret);
    }

    @Test
    public void findMusicByMusicId() {
        MusicDao musicDao = new MusicDao();
        boolean ret = musicDao.findMusicByMusicId(1,2);
        System.out.println(ret);
    }

    @Test
    public void removeLoveMusic() {
        MusicDao musicDao = new MusicDao();
        int ret = musicDao.removeLoveMusic(1,2);
        System.out.println(ret);
    }

    @Test
    public void findLoveMusic() {
        MusicDao musicDao = new MusicDao();
        List<Music> music = musicDao.findLoveMusic(1);
        System.out.println(music);
    }

    @Test
    public void ifMusicLove() {
        MusicDao musicDao = new MusicDao();
        List<Music> ret = musicDao.ifMusicLove("四季",1);
        System.out.println(ret);
    }
}