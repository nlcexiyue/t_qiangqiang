package com.qiangqiang.crm.dao;


import com.qiangqiang.base.BaseMapper;
import com.qiangqiang.crm.vo.UserRole;

public interface UserRoleMapper extends BaseMapper<UserRole,Integer> {

    public int countUserRoleByUserId(Integer userId);

    public int  deleteUserRoleByUserId(Integer userId);

    public int  countUserRoleByRoleId(Integer roleId);

    public int   deleteUserRoleByRoleId(Integer roleId);

}