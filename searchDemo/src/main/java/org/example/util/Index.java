package org.example.util;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-25 11:24
 */

import org.example.model.DocInfo;
import org.example.model.Weight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 构建索引:
 * 正排索引:从本地文件数据中读取到Java内存中(类似数据库中保存的数据)
 * 倒排索引:构建Map<String,List<信息>>(类似数据库hash索引)
 * Map-键：关键词(分词来做)
 * Map值-信息:
 * (1）docInfo对象引用或者是docInfo的id
 * (2)权重 (标题对应关键词数量*10+正文对应关键词*1)
 *
 * */
public class Index {
    //正排索引
    private static final List<DocInfo> FORWARD_INDEX = new ArrayList<>();
    //倒排索引
    private static final Map<String,List<Weight>> INVERTED_INDEX = new HashMap<>();
    /**
     * 构建正派索引的内容:从本地raw_data.txt中读取并保存
     * */
    public static  void buildForwardIndex(){
        try {
            FileReader fileReader = new FileReader(Parser.RAW_DATA);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int id = 0;//将行号设置为docInfo的id
            String line;
            while((line = bufferedReader.readLine()) != null){
                if (line.trim().equals("")){
                    continue;
                }
                //一行对应一个docInfo对象
                DocInfo docInfo = new DocInfo();
                docInfo.setId(id++);
                String[] parts = line.split("\3");
                docInfo.setTitle(parts[0]);
                docInfo.setUrl(parts[1]);
                docInfo.setContent(parts[2]);
                //添加到正排索引
                FORWARD_INDEX.add(docInfo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //构建倒排索引:从Java内存中正排索引中获取文档的信息来构建
    public static void buildInvertedIndex(){

        for (DocInfo doc : FORWARD_INDEX){
            //一个doc,分别对标题和正文分词，每一个分词生成一个weight对象，需要计算权重

        }
    }

    public static void main(String[] args) {
        //测试正排索引是否构建成功
        Index.buildForwardIndex();
        FORWARD_INDEX.stream()
                .forEach( System.out ::println);
    }
}
