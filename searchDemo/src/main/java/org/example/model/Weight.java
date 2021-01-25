package org.example.model;

/**
 * Author:ZouDouble
 * Description:
 * 天气：晴天
 * 目标：Good Offer
 * Date    2021-01-25 12:04
 */

/**
 * 倒排索引Map<String,List<Weight>>中，关键词对应的信息
 * */
public class Weight {
    private DocInfo doc;
    private int weight;//权重值
    private String keyWord;//关键词

    public DocInfo getDoc() {
        return doc;
    }

    public void setDoc(DocInfo doc) {
        this.doc = doc;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "Weight{" +
                "doc=" + doc +
                ", weight=" + weight +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
