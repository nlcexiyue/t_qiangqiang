package com.qiangqiang.controller;

import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.service.NewsLibiaryService;
import com.qiangqiang.service.NewsLibraryService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/22
 * \* Time: 10:58
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@RestController
public class NewsController {
    @Autowired
    private NewsLibraryService newsLibraryService;

    @Reference(timeout = 60000,version = "1.0.0")
    private NewsLibiaryService newsLibiaryService;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    //创建索引
    @GetMapping(value = "/create")
    public void createIndex() {
        elasticsearchRestTemplate.indexOps(NewsLibrary.class);
    }

    //存入数据
    @GetMapping(value = "/save")
    public void save() {
        NewsLibrary newsLibrary = new NewsLibrary(1L, "中国将走向世界之巅了", "这是发展的最终结果", new Date());

        newsLibraryService.save(newsLibrary);
    }

    //存入数据
    @GetMapping(value = "/saveAll")
    public void saveAll() {
        List<NewsLibrary> all = newsLibraryService.findAll();
        System.out.println(all.size());
        for (NewsLibrary newsLibrary : all) {
            newsLibraryService.save(newsLibrary);
        }
    }

    //查找
    @GetMapping(value = "/find")
    public List<NewsLibrary> findByNewsTitle(String newsTitle) {

        List<NewsLibrary> byTitle = newsLibraryService.findByTitle(newsTitle);
        return byTitle;

    }

    //分页查找
    @GetMapping(value = "/findPage")
    public List<NewsLibrary> findByPage(String newsTitle){
        PageRequest of = PageRequest.of(0, 20);
        List<NewsLibrary> byNewsTitle = newsLibraryService.findByNewsTitle(newsTitle, of);
        return byNewsTitle;

    }


    /**
     * 查询所有的库的消息去往elsaticsearch中去写入
     *
     * @return
     */
    @RequestMapping(value = "/toES")
    public String toElasticSearch() {
        List<NewsLibrary> newsLibraries = newsLibiaryService.selectAll();
        for (NewsLibrary newsLibrary : newsLibraries) {
            newsLibraryService.save(newsLibrary);
        }
        return "数据库数据已全部加载到es中";
    }

}