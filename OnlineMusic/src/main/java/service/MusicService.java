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
    /*public  int findMusicById(int id){
        MusicDao musicDao = new MusicDao();
        if (musicDao.deleteByMusicId(id) == 1){
            if (musicDao.findLoveMusicOnDel(id)){
                int ret2 = musicDao.removeLoveMusicOnDel(id);
                if (ret2 == 1){
                    //表示删除loveMusic中的数据成功
                    return 1;
                }
            }
        }
        return 0;
    }*/
}
