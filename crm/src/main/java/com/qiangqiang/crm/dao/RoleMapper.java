package com.qiangqiang.crm.dao;



import com.qiangqiang.base.BaseMapper;
import com.qiangqiang.crm.vo.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role,Integer> {

    public List<Map<String,Object>> queryAllRoles(Integer userId);

    public Role  queryRoleByRoleName(String roleName);

}