package com.qiangqiang.NewsJsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/17
 * \* Time: 10:18
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class ChinaNetNews {
    public static void main(String[] args) throws Exception {
        getWuMaoW();
    }

    // 获取http://www.ltaaa.com/
    public static void getWuMaoW() {
        String url = "http://news.china.com.cn/node_7183219.htm";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements listDiv = doc.getElementsByAttributeValue("class", "Headlines");
            for (Element element : listDiv) {
                Elements texts = element.getElementsByTag("li");
                for (Element text : texts) {

                    // 取所有文本
                    // String ptext = text.text();
                    Elements a = text.getElementsByTag("a");
                    String text1 = a.text();
                    if(!text1.equals("")){
                        System.out.println("标题：" + text1);
                        String href = a.attr("href");
                        StringBuffer stringBuffer = new StringBuffer();
                        Document doc1 = Jsoup.connect(href).get();
                        Elements listDiv1 = doc1.getElementsByAttributeValue("class", "leftBox");
                        for (Element element01 : listDiv1){
                            Elements elementsByAttributeValue = element01.getElementsByAttributeValue("class", "articleBody");
                            for (Element element1 : elementsByAttributeValue) {
                                Elements p = element1.getElementsByTag("p");
                                for (Element element2 : p) {
                                    String text2 = element2.text();
                                    stringBuffer.append(text2);
                                }

                            }
                        }
                        System.out.println(stringBuffer.toString());

                    }
//                    String ptext = text.attr("title");
                }
            }
            Elements elementsByAttributeValue = doc.getElementsByAttributeValue("id", "autopage");
            for (Element element : elementsByAttributeValue) {
                Elements center = element.getElementsByTag("center");
                for (Element element1 : center) {
                    Elements a = element1.getElementsByTag("a");
                    for (Element element2 : a) {
                        String href = element2.attr("href");
                        Document doc1 = Jsoup.connect(href).get();
                        Elements listDiv1 = doc1.getElementsByAttributeValue("class", "Headlines");
                        for (Element element01 : listDiv1) {
                            Elements texts = element01.getElementsByTag("li");
                            for (Element text : texts) {

                                // 取所有文本
                                // String ptext = text.text();
                                Elements a01 = text.getElementsByTag("a");
                                String text1 = a01.text();
                                if(!text1.equals("")){
                                    System.out.println("标题：" + text1);
                                    String href2 = a01.attr("href");
                                    StringBuffer stringBuffer = new StringBuffer();
                                    Document doc12 = Jsoup.connect(href2).get();
                                    Elements listDiv12 = doc12.getElementsByAttributeValue("class", "leftBox");
                                    for (Element element012 : listDiv12){
                                        Elements elementsByAttributeValue2 = element012.getElementsByAttributeValue("class", "articleBody");
                                        for (Element element12 : elementsByAttributeValue2) {
                                            Elements p = element12.getElementsByTag("p");
                                            for (Element element22 : p) {
                                                String text2 = element22.text();
                                                stringBuffer.append(text2);
                                            }

                                        }
                                    }
                                    System.out.println(stringBuffer.toString());
                                }
//                    String ptext = text.attr("title");
                            }
                        }

                    }

                }
            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}