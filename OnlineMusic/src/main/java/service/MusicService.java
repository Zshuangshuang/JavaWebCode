package service;

import Entity.Music;
import dao.MusicDao;

import java.util.ArrayList;
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

}
