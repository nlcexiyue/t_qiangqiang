package com.qiangqiang.repository;

import com.qiangqiang.entity.NewsLibrary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2020/12/22
 * \* Time: 10:49
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public interface NewsResitory extends ElasticsearchRepository<NewsLibrary,String> {
    List<NewsLibrary> findByNewsTitle(String newsTitle);

    List<NewsLibrary> findByNewsTitle(String newsTitle, PageRequest pageRequest);


    @Override
    List<NewsLibrary> findAll();
}