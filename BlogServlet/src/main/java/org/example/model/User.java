package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-28 11:23
 */
@Getter
@Setter
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String nickname;
    private String sex;
    private Date birthday;
    private String head;
}
