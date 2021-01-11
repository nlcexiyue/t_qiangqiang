package com.qiangqiang.crm.dao;


import com.qiangqiang.base.BaseMapper;
import com.qiangqiang.crm.query.CusDevPlanQuery;
import com.qiangqiang.crm.vo.CusDevPlan;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CusDevPlanMapper extends BaseMapper<CusDevPlan,Integer> {

    List<CusDevPlan> selectBySearch(CusDevPlanQuery cusDevPlanQuery);

}