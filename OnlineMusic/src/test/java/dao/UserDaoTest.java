package dao;

import Entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-02-15 15:12
 */
public class UserDaoTest {
    @After
    public void after(){
        System.out.println("=======after=====");
    }
    @Before
    public void before(){
        System.out.println("=====before====");
    }

    @Test
    @Ignore
    public void resister() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setEmail("123@qq.com");
        user.setAge(19);
        user.setGender("女");
        user.setUsername("萱萱");
        user.setPassword("166.888");
        userDao.resister(user);
    }
    @Ignore
    @Test
    public void login() {
        UserDao userDao = new UserDao();
        User user = new User();
        user.setEmail("123@qq.com");
        user.setAge(19);
        user.setGender("女");
        user.setUsername("萱萱");
        user.setPassword("166.888");
        userDao.login(user);
    }

    @Test
    public void selectByName() {
        UserDao userDao = new UserDao();
        String username = "邹双双";
        userDao.selectByName(username);
        System.out.println(username);
    }
}