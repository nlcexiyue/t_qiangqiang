package com.qiangqiang.Boot.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.service.NewsLibiaryService;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQMessageProducer;
import org.apache.activemq.AsyncCallback;
import org.apache.dubbo.config.annotation.Reference;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/14
 * \* Time: 13:20
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
@RequestMapping("/publish")
public class PublishController {
    @Autowired
    private JmsMessagingTemplate template;

    @Autowired
    private Queue queue;

    @Autowired
    private Queue elasticSearchQueue;

    @Autowired
    private Topic topic;
    @Autowired
    private Queue newsQueue;

    @Autowired
    private ActiveMQConnectionFactory connectionFactory;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public final String url = "http://news.china.com.cn/node_7247300.htm";

    public static final String division = "@_@";

    public final String worldNetName = "http://sputniknews.cn/";
    public final String dateStart = "20201219";
    public final String dateEnd = "20201221";

    @Reference(timeout = 60000, retries = 1, version = "*", stub = "com.qiangqiang.Boot.service.NewsLibraryServiceImpl1")
    private NewsLibiaryService newsLibiaryService;




    @RequestMapping("/page")
    public PageInfo getPageNews(int page, int limit) {
        PageInfo<NewsLibrary> newsLibraryPageInfo = newsLibiaryService.selectByPage(page, limit);
        return newsLibraryPageInfo;
    }


    @RequestMapping("/worldNews")
    public void jsoupWorldNewsToMQ() throws JMSException {
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(newsQueue);
        TextMessage textMessage = session.createTextMessage();
        String titleName = "archive/";
        List<String> yearDate = getYearDate(dateStart, dateEnd);
        for (String year : yearDate) {
            SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
            Date parse = null;
            try {
                parse = date.parse(year);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateTime = dateFormat.format(parse);


            String urlWorld = worldNetName + titleName + year + "/";
            Document doc = null;
            try {
                doc = Jsoup.connect(urlWorld).get();
                Elements aClass = doc.getElementsByAttributeValue("class", "b-plainlist__item");

                for (Element element : aClass) {
                    StringBuffer sb = new StringBuffer();
                    Elements aClass1 = element.getElementsByAttributeValue("class", "b-plainlist__title");
                    for (Element element1 : aClass1) {
                        Elements a = element1.getElementsByTag("a");
                        for (Element element2 : a) {
                            String text = element2.text();
                            sb.append(text).append(division);
                        }
                    }
                    Elements aClass2 = element.getElementsByAttributeValue("class", "b-plainlist__announce");
                    for (Element element1 : aClass2) {
                        Elements a = element1.getElementsByTag("a");
                        for (Element element2 : a) {
                            String text = element2.text();
                            System.out.println("主体：" + text);
                            sb.append(text).append(division).append(dateTime);
                        }
                    }
                    String info = sb.toString();
                    textMessage.setText(info);
                    producer.send(textMessage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @RequestMapping("/chinaNews")
    public void jsoupNewsToMQ() throws JMSException {

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(newsQueue);
        TextMessage textMessage = session.createTextMessage();


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
                    if (!text1.equals("")) {
                        StringBuffer sb = new StringBuffer();
                        System.out.println("标题：" + text1);
                        String href = a.attr("href");
                        StringBuffer stringBuffer = new StringBuffer();
                        Document doc1 = Jsoup.connect(href).get();
                        Elements listDiv1 = doc1.getElementsByAttributeValue("class", "leftBox");
                        for (Element element01 : listDiv1) {
                            Elements elementsByAttributeValue = element01.getElementsByAttributeValue("class", "articleBody");
                            for (Element element1 : elementsByAttributeValue) {
                                Elements p = element1.getElementsByTag("p");
                                for (Element element2 : p) {
                                    String text2 = element2.text();
                                    stringBuffer.append(text2);
                                }

                            }
                        }

                        String dateTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);


                        StringBuffer append = sb.append(text1).append(division).append(stringBuffer.toString()).append(division).append(dateTime);
                        String info = append.toString();

                        textMessage.setText(info);
                        producer.send(textMessage);

                    }
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
                                Elements a01 = text.getElementsByTag("a");
                                String text1 = a01.text();
                                if (!text1.equals("")) {
                                    StringBuffer sb = new StringBuffer();
                                    System.out.println("标题：" + text1);
                                    String href2 = a01.attr("href");
                                    StringBuffer stringBuffer = new StringBuffer();
                                    Document doc12 = Jsoup.connect(href2).get();
                                    Elements listDiv12 = doc12.getElementsByAttributeValue("class", "leftBox");
                                    for (Element element012 : listDiv12) {
                                        Elements elementsByAttributeValue2 = element012.getElementsByAttributeValue("class", "articleBody");
                                        for (Element element12 : elementsByAttributeValue2) {
                                            Elements p = element12.getElementsByTag("p");
                                            for (Element element22 : p) {
                                                String text2 = element22.text();
                                                stringBuffer.append(text2);
                                            }

                                        }
                                    }
                                    String dateTime = LocalDateTime.now().format(DATE_TIME_FORMATTER);


                                    StringBuffer append = sb.append(text1).append(division).append(stringBuffer.toString()).append(division).append(dateTime);
                                    String info = append.toString();

                                    textMessage.setText(info);
                                    producer.send(textMessage);
                                }
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


    @PostMapping(value = "/queue")
    public String queue(HttpServletRequest request, @RequestBody Book book) throws JMSException {
        System.out.println(book);
        String msg1 = book.getMsg();
        System.out.println(msg1);

        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        ActiveMQMessageProducer producer = (ActiveMQMessageProducer) session.createProducer(queue);
        TextMessage textMessage = session.createTextMessage();
        for (int i = 0; i < 10; i++) {
            textMessage.setText("queue" + i);
            producer.send(textMessage, new AsyncCallback() {
                @Override
                public void onSuccess() {
                    System.out.println("chenggong");
                }

                @Override
                public void onException(JMSException exception) {

                }
            });
        }


        return "queue发送成功";
    }

    @JmsListener(destination = "out.queue")
    public void comsumerMsg(String msg) {
        System.out.println(msg);
    }

    @RequestMapping("/topic")
    public String topic() {

        for (int i = 0; i < 10; i++) {
            template.convertAndSend(topic, "topic" + i);
        }
        return "topic发送成功";
    }


    //获取某个时间段中的所有的月份
    public static List<String> getYearDate(String dateStart, String dateEnd) {

        SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
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
        long day = 1000 * 60 * 60 * 24;
        ArrayList<String> list = Lists.newArrayList();
        //日期正序
//        for(long i=startTime;i<=endTime;i+=day) {
//            String format = date.format(new Date(i));
//            list.add(format);
//        }
        //日期倒序
        for (long i = endTime; i >= startTime; i -= day) {
            String format = date.format(new Date(i));
            list.add(format);
        }
        return list;
    }

}