package org.example.util;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-25 11:24
 */

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
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
            //第一次出现的分词关键词，要new weight对象
            Map<String,Weight> cash = new HashMap<>();
            //对标题进行分词
            List<Term> titles = ToAnalysis.parse(doc.getTitle()).getTerms();
            //遍历处理标题分词
            for (Term title:titles){
                Weight w = cash.get(title.getName());//获取标题分词的键对应的weight对象
                if (w == null){
                    w = new Weight();
                    w.setDoc(doc);
                    w.setKeyWord(title.getName());
                    cash.put(title.getName(),w);
                }
                //标题权重加10
                w.setWeight(w.getWeight()+10);
            }
            List<Term> contents = ToAnalysis.parse(doc.getContent()).getTerms();
            //遍历处理正文内容
            for (Term content:contents){
                Weight w = cash.get(content.getName());//获取内容分词的关键词对应的weight对象
                if (w == null){
                    w = new Weight();
                    w.setDoc(doc);
                    w.setKeyWord(content.getName());
                    cash.put(content.getName(),w);
                }
                //内容权重加1
                w.setWeight(w.getWeight()+1);
            }
            //将临时保存的map数据,包含keyword和weight全部保存到倒排索引当中去
           for (Map.Entry<String,Weight> entry : cash.entrySet()){
               String keyword = entry.getKey();
               Weight weight = entry.getValue();
               //更新保存到正排索引
               //现在倒排索引中通过keyword获取已有的值
               List<Weight> weights = INVERTED_INDEX.get(keyword);
               if (weights == null){
                   weights = new ArrayList<>();
                   INVERTED_INDEX.put(keyword,weights);
               }
               weights.add(weight);
           }
        }
    }

    public static void main(String[] args) {
        //测试正排索引是否构建成功
        Index.buildForwardIndex();
        /*FORWARD_INDEX.stream()
                .forEach( System.out ::println);*/
        //测试倒排索引
        //①构建倒排索引
        Index.buildInvertedIndex();
        //②打印
        for (Map.Entry<String,List<Weight>> entry : INVERTED_INDEX.entrySet()){
            String keyword = entry.getKey();
            System.out.print(keyword+": ");
            List<Weight> weights = entry.getValue();
            weights.stream()
                    .map(weight -> {
                        return "("+ weight.getDoc().getId()+", "+ weight.getWeight()+")";
                    })
                    //.collect(Collectors.toList());
            .forEach(System.out ::print);
            System.out.println();
        }
    }
}
