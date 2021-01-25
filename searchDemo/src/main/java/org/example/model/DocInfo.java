package org.example.model;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-25 9:08
 */
 //本地的每一个html文件对应一个文档对象
public class DocInfo {
    private Integer id;//类似于数据库中的主键，用来识别不同的文档
    private String title;//html文件名作为标题
    private String url;//oracle官网api下html的url
    private String content;//网页内容:html(<标签>内容</标签>),内容是正文


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "DocInfo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
