package com.qiangqiang.service;

import com.qiangqiang.entity.NewsLibrary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface NewsLibraryService {
    NewsLibrary save(NewsLibrary newsLibrary);

    List<NewsLibrary> findByTitle();

    List<NewsLibrary> findByTitle(String newsTitle);

    List<NewsLibrary> findAll();

    List<NewsLibrary> findByNewsTitle(String newsTitle, PageRequest pageRequest);
}
