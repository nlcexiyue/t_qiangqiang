package com.qiangqiang.crm.controller;


import com.qiangqiang.base.BaseController;
import com.qiangqiang.base.ResultInfo;
import com.qiangqiang.crm.query.RoleQuery;
import com.qiangqiang.crm.service.RoleService;
import com.qiangqiang.crm.vo.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

    @Resource
    private RoleService roleService;


    @RequestMapping("index")
    public String index(){
        return "role/role";
    }


    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(Integer userId){
        return roleService.queryAllRoles(userId);
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryRolesByParams(RoleQuery roleQuery){
        return roleService.queryRolesByParams(roleQuery);
    }


    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveRole(Role role){
        roleService.saveRole(role);
        return success("角色记录添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("角色记录更新成功");
    }


    @RequestMapping("addOrUpdateRolePage")
    public  String addOrUpdateRolePage(Integer id, Model model){
        model.addAttribute("role",roleService.selectByPrimaryKey(id));
        return "role/add_update";
    }



    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Integer id){
        roleService.deleteRole(id);
        return success("角色记录删除成功");
    }
}
