package service;

import Entity.User;
import dao.UserDao;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-04 10:58
 */
public class UserService {
    public void register(User user){
        UserDao userDao = new UserDao();
        userDao.resister(user);
    }
    public User login(User loginUser){
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);
        return user;
    }

    public User selectByName(String username){
        UserDao userDao = new UserDao();
        User user = userDao.selectByName(username);
        return user;
    }

}
