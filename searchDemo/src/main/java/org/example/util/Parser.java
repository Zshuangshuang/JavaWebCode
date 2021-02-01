package org.example.util;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-25 9:04
 */

import org.example.model.DocInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 步骤1：
 * 从本地api目录，遍历静态html文件
 * 每一个html需要构建正文索引：本地某个文件
 * 正文索引信息:List<DocInfo>
 * DocInfo(id,title,content,url)
 * */
public class Parser {
    //api目录
    public static final String API_PATH = "D:\\jdk-8u261-docs-all\\docs\\api";
    //构建的本地文件正排索引
    public static final String RAW_DATA = "/root/raw_data.txt";
    //官方api文档根路径
    public static final String API_BASEPATH = "https://docs.oracle.com/javase/8/docs/api";

    public static void main(String[] args) throws IOException {
        //现将本地api路径下所有的html文件找到
        List<File> htmls = listHtml(new File(API_PATH));
        FileWriter fileWriter = new FileWriter(new File(RAW_DATA));
        //BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter printWriter = new PrintWriter(fileWriter,true);//打印输出流时可以自动刷新缓冲区
        for (File html : htmls){
            //测试截取的目录有没有问题
            //System.out.println(html.getAbsolutePath());
            //一个html解析为DocInfo有的属性,将这些属性保存到本地的正排索引中去
            DocInfo doc = parseHtml(html);
           // System.out.println(doc);
            //保存正排索引文件:使用输出流输出到目标文件
            //输出格式为:一行为一个doc：title+\3+url+\3+content+\3
            String uri = html.getAbsolutePath().substring(API_PATH.length());
            System.out.println("Parse："+uri);
            printWriter.println(doc.getTitle()+"\3"+doc.getUrl()+
                    "\3"+doc.getContent());

        }

    }

    private static DocInfo parseHtml(File html) {
        DocInfo doc = new DocInfo();
        doc.setTitle(html.getName().substring(0,html.getName().length()-".html".length()));
        //获取相对路径
        String uri = html.getAbsolutePath().substring(API_PATH.length());
        doc.setUrl(API_BASEPATH+uri);
        doc.setContent(parseContent(html));
        return doc;
    }

    /**
     * 解析html
     * <标签>内容</标签>
     * 只取内容,有多个内容就拼接
     *
     * */
    private static String parseContent(File html) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(html);
            int i;
            boolean isContent = true;
            //一个字符一个字符来读取
            while((i = fileReader.read()) != -1){
                char c = (char) i;
                if (isContent){
                    if (c == '<'){
                        isContent = false;
                        continue;
                    } else if (c == '\n' || c == '\r'){
                       c = ' ';
                    }
                    stringBuilder.append(c);
                }else {
                    if (c == '>'){
                        isContent = true;
                    }
                }

            }
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stringBuilder.toString().trim();
    }

    //递归遍历html文件
    private static List<File> listHtml(File dir){
        List<File> list = new ArrayList<>();
        File[] children = dir.listFiles();//子文件和子文件夹
        for (File child : children){
            if (child.isDirectory()){//子文件夹:递归调用获取子文件夹中的html文件
               list.addAll(listHtml(child));
            }else if (child.getName().endsWith(".html")){
                list.add(child);
            }

        }
        return list;
    }
}
