package com.qiangqiang.NewsJsoup;

import com.google.common.collect.Lists;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/18
 * \* Time: 9:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class WorldNetNews {


    public static void main(String[] args)  {

        getYearDate();
    }
    //获取某个时间段中的所有的月份
    public static List<String> getYearDate() {
        String dateStart="20201216";
        String dateEnd="20210107";
        SimpleDateFormat date=new SimpleDateFormat("yyyyMMdd");
        long startTime = 0;//start
        try {
            startTime = date.parse(dateStart).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long endTime = 0;//end
        try {
            endTime = date.parse(dateEnd).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long day=1000*60*60*24;
        ArrayList<String> list = Lists.newArrayList();
        //日期正序
//        for(long i=startTime;i<=endTime;i+=day) {
//            String format = date.format(new Date(i));
//            list.add(format);
//        }
        //日期倒序
        for(long i=endTime;i>=startTime;i-=day) {
            String format = date.format(new Date(i));
            list.add(format);
        }
        return list;
    }

    public static void getWorldNetNews(){
        String netName = "http://sputniknews.cn/";
        String titleName = "archive/";
        List<String> yearDate = getYearDate();
        for (String year : yearDate) {
            String url = netName + titleName + year + "/";
            Document doc = null;
            try {
                doc = Jsoup.connect(url).get();
                Elements aClass = doc.getElementsByAttributeValue("class", "b-plainlist__item");
                for (Element element : aClass) {
                    Elements aClass1 = element.getElementsByAttributeValue("class", "b-plainlist__title");
                    for (Element element1 : aClass1) {
                        Elements a = element1.getElementsByTag("a");
                        for (Element element2 : a) {
                            String text = element2.text();
                            System.out.println("标题：" + text);
                        }
                    }
                    Elements aClass2 = element.getElementsByAttributeValue("class", "b-plainlist__announce");
                    for (Element element1 : aClass2) {
                        Elements a = element1.getElementsByTag("a");
                        for (Element element2 : a) {
                            String text = element2.text();
                            System.out.println("主体：" + text);
                        }
                    }

                }




            } catch (IOException e) {
                e.printStackTrace();
            }

        }









    }
}