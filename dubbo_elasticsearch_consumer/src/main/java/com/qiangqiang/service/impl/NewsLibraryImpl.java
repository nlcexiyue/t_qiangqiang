package com.qiangqiang.service.impl;

import com.qiangqiang.entity.NewsLibrary;
import com.qiangqiang.repository.NewsResitory;
import com.qiangqiang.service.NewsLibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/22
 * \* Time: 10:54
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
@Service
public class NewsLibraryImpl implements NewsLibraryService {

    @Autowired
    private NewsResitory newsResitory;
    @Override
    public NewsLibrary save(NewsLibrary newsLibrary) {
        NewsLibrary save = newsResitory.save(newsLibrary);
        return save;
    }


    @Override
    public List<NewsLibrary> findByTitle() {
        return null;
    }

    @Override
    public List<NewsLibrary> findByTitle(String newsTitle) {
        List<NewsLibrary> byTitle = newsResitory.findByNewsTitle(newsTitle);
        return byTitle;
    }

    @Override
    public List<NewsLibrary> findAll() {
        return newsResitory.findAll();
    }

    @Override
    public List<NewsLibrary> findByNewsTitle(String newsTitle, PageRequest pageRequest) {
        return newsResitory.findByNewsTitle(newsTitle, pageRequest);
    }
}