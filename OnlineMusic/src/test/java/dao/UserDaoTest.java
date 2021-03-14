package dao;

import Entity.User;
import org.junit.Test;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-03-13 22:46
 */
public class UserDaoTest {

    @Test
    public void resister() {
        User user = new User();
        user.setPassword("123");
        user.setUsername("樱桃小丸子");
        user.setGender("女");
        user.setAge(12);
        user.setEmail("127890@qq.com");
        UserDao userDao = new UserDao();
        userDao.resister(user);
    }

    @Test
    public void login() {
        UserDao userDao = new UserDao();
        User loginUser = new User();
        loginUser.setPassword("123");
        loginUser.setUsername("樱桃小丸子");
        loginUser.setGender("女");
        loginUser.setAge(12);
        loginUser.setEmail("127890@qq.com");
        User user = userDao.login(loginUser);
        System.out.println(user);
    }

    @Test
    public void selectByName() {
        UserDao userDao = new UserDao();
        User user = userDao.selectByName("樱桃小丸子");
        System.out.println(user);
    }
}