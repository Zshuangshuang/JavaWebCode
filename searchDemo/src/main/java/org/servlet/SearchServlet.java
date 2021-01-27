package org.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.example.model.Result;
import org.example.model.Weight;
import org.example.util.Index;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-27 14:39
 */
@WebServlet(value = "/search",loadOnStartup = 0)
public class SearchServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        //初始化操作:先构建正排索引,在根据正排索引构建倒排索引
        Index.buildForwardIndex();
        Index.buildInvertedIndex();
        System.out.println("init OK");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");//ajax请求，响应json格式
        //构造返回前端的内容:使用对象，之后再序列化为json对象
        Map<String,Object> map = new HashMap<>();
        //解析请求数据
        String query = req.getParameter("query");//搜索框的内容
        List<Result> results = new ArrayList<>();
        try{
            //根据搜索内容处理搜索业务
            //校验请求数据(搜索内容)
                if (query == null || query.trim().length() == 0){
                    map.put("OK",false);
                    map.put("msg","未知错误");
                }else {
                    //①根据搜索内容进行分词，遍历每个分词
                    for (Term t : ToAnalysis.parse(query).getTerms()){
                        String word = t.getName();//搜索的分词
                        //②每个分词，在倒排中查找对应的文档
                        List<Weight> weights = Index.get(word);
                        for (Weight weight: weights){
                            //先转换weight为result
                            //③一个文档转化为一个result
                            Result result = new Result();
                            result.setId(weight.getDoc().getId());
                            result.setTitle(weight.getDoc().getTitle());
                            result.setWeight(weight.getWeight());
                            result.setUrl(weight.getDoc().getUrl());
                            //自己规定内容超过90就隐藏超过90的部分
                            String content = weight.getDoc().getContent();
                            result.setDesc(content.length() <= 90 ? content : content.substring(0,90)+"...");
                            results.add(result);
                        }

                    }

                    //④对result进行排序:权重降序排序
                    results.sort(new Comparator<Result>() {
                        @Override
                        public int compare(Result o1, Result o2) {
                            //return Integer.compare(o1.getWeight(),o2.getWeight());//权重升序排列
                            return Integer.compare(o2.getWeight(),o1.getWeight());//权重升序排列
                        }
                    });

                    map.put("OK",true);
                    map.put("data",results);
                }



        }catch (Exception e){
            e.printStackTrace();
            map.put("OK",false);
        }
        PrintWriter pw = resp.getWriter();
        //设置响应体内容:map对象序列化为json字符串
        pw.write(new ObjectMapper().writeValueAsString(map));
    }
}
