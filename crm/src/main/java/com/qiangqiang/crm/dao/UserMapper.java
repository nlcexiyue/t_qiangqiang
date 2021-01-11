package com.qiangqiang.crm.dao;



import com.qiangqiang.base.BaseMapper;
import com.qiangqiang.crm.vo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper extends BaseMapper<User,Integer> {

    User  queryUserByUserName(String userName);


    public List<Map<String,Object>> queryAllSales();
}