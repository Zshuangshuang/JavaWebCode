package service;

import Entity.Music;
import org.junit.Test;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-03-13 23:15
 */
public class MusicServiceTest {

    @Test
    public void findMusic() {
        MusicService musicService = new MusicService();
        List<Music> musicList = musicService.findMusic();
        System.out.println(musicList);
    }

    @Test
    public void findMusicById() {
        MusicService musicService = new MusicService();
        Music music = musicService.findMusicById(1);
        //assertNull(music);
        System.out.println(music);
    }

    @Test
    public void ifMusic() {
        MusicService musicService = new MusicService();
        List<Music> musicList = musicService.ifMusic("红玫瑰");
        assertNull(musicList);
    }

    @Test
    public void insert() {
        MusicService musicService = new MusicService();
        int ret =musicService.insert("四季予你","程响", Date.from(Instant.now()),"music\\四季予你",1);
        assertEquals(1,ret);
    }

    @Test
    public void deleteByMusicId() {
        MusicService musicService = new MusicService();
        int ret = musicService.deleteByMusicId(1);
        assertEquals(1,ret);
    }

    @Test
    public void removeLoveMusicOnDel() {
        MusicService musicService = new MusicService();
        int ret = musicService.removeLoveMusicOnDel(1);
        assertEquals(0,ret);
    }

    @Test
    public void findLoveMusicOnDel() {
        MusicService musicService = new MusicService();
        boolean ret = musicService.findLoveMusicOnDel(1);
        System.out.println(ret);
    }

    @Test
    public void findMusicByMusicId() {
        MusicService musicService = new MusicService();
        boolean ret = musicService.findMusicByMusicId(1,1);
        System.out.println(ret);
    }

    @Test
    public void insertLoveMusic() {
        MusicService musicService = new MusicService();
        boolean ret = musicService.insertLoveMusic(1,1);
        System.out.println(ret);
    }

    @Test
    public void removeLoveMusic() {
        MusicService musicService = new MusicService();
        int ret = musicService.removeLoveMusic(1,1);
        System.out.println(ret);
    }

    @Test
    public void findLoveMusic() {
        MusicService musicService = new MusicService();
        List<Music> musicList = musicService.findLoveMusic(1);
        System.out.println(musicList);
    }

    @Test
    public void ifMusicLove() {
        MusicService musicService = new MusicService();
        List<Music> musicList = musicService.ifMusicLove("你好",1);
        System.out.println(musicList);
    }
}