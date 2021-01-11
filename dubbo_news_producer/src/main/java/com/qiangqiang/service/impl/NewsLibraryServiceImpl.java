package com.qiangqiang.service.impl;

import com.github.pagehelper.PageInfo;
import com.qiangqiang.entity.ExplainResult;
import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.mapper.NewsLibraryMapper;
import com.qiangqiang.service.NewsLibiaryService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

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
//retries重试
@Service(timeout = 60000, retries = 1, version = "1.0.0")
public class NewsLibraryServiceImpl implements NewsLibiaryService {

    @Autowired
    private NewsLibraryMapper newsLibraryMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(Long id,
                      String newsTitle,
                      String newsContent,
                      Date includeTime) {
        return newsLibraryMapper.insert(id, newsTitle, newsContent, includeTime);
    }

    @Override
    public List<NewsLibrary> selectAll() {
        return newsLibraryMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(NewsLibrary record) {
        return 0;
    }

    //在dubbo的提供者上增加@HystrixConmand配置，这样调用就会经过Hystrix代理
    @Override
    public PageInfo<NewsLibrary> selectByPage(int page, int limit) {
        page = page * limit;
        List<NewsLibrary> newsLibraries = newsLibraryMapper.selectByPage(page, limit);
        PageInfo<NewsLibrary> pageInfo = new PageInfo<>(newsLibraries);
        ExplainResult explainResult = newsLibraryMapper.selectCount();
        int count = explainResult.getRows();
        pageInfo.setTotal(count);
        System.out.println("1.0.0");
        return pageInfo;
    }

    /**
     * 使用explain来大致查找全表的总数据量，但是结果比count(0)的结果要小一点，不要求精确的情景下使用
     *
     * @return
     */
    @Override
    public int selectCount() {
        ExplainResult explainResult = newsLibraryMapper.selectCount();
        int rows = explainResult.getRows();
        return rows;
    }
}