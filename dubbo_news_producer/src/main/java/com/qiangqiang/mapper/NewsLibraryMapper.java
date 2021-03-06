package com.qiangqiang.mapper;

import com.qiangqiang.entity.ExplainResult;
import com.qiangqiang.entity.NewsLibrary;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface NewsLibraryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    int insert(@Param("id") Long id ,
               @Param("newsTitle")String newsTitle ,
               @Param("newsContent")String newsContent ,
               @Param("includeTime") Date includeTime);


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    List<NewsLibrary> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news_library
     *
     * @mbggenerated Thu Dec 17 11:11:31 CST 2020
     */
    int updateByPrimaryKey(NewsLibrary record);

    List<NewsLibrary> selectByPage(@Param("page") int page, @Param("limit") int limit);

    ExplainResult selectCount();
}