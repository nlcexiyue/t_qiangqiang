package com.qiangqiang.Boot.service;

import com.github.pagehelper.PageInfo;
import com.qiangqiang.entity.ExplainResult;
import com.qiangqiang.entity.NewsLibrary;

import com.qiangqiang.service.NewsLibiaryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/17
 * \* Time: 11:22
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
//本地存根的方法，要写构造函数
public class NewsLibraryServiceImpl1 implements NewsLibiaryService {

    private final NewsLibiaryService newsLibiaryService;

    // 构造函数传入真正的远程代理对象
    public NewsLibraryServiceImpl1(NewsLibiaryService newsLibiaryService){
        this.newsLibiaryService = newsLibiaryService;
    }

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Long id,
                      String newsTitle,
                      String newsContent,
                      Date includeTime) {
        System.out.println("报错了");
        return 1;
    }

    @Override
    public List<NewsLibrary> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(NewsLibrary record) {
        return 0;
    }

    @Override
    public PageInfo<NewsLibrary> selectByPage(int page, int limit) {
        page = page * limit;

        PageInfo<NewsLibrary> pageInfo = new PageInfo<>();



        System.out.println("2.0.0");
        return pageInfo;
    }

    /**
     * 使用explain来大致查找全表的总数据量，但是结果比count(0)的结果要小一点，不要求精确的情景下使用
     * @return
     */
    @Override
    public int selectCount() {
        System.out.println("报错了");
        return 1;
    }
}