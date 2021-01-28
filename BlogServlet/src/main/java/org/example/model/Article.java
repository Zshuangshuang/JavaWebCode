package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-28 11:25
 */
@Getter
@Setter
public class Article {
    private Integer id;
    private String title;
    private String content;
    private Date createTime;
    private Integer viewCount;
    private Integer userId;
}
