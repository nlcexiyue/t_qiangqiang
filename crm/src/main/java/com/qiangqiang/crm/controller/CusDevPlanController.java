package com.qiangqiang.crm.controller;


import com.qiangqiang.base.BaseController;
import com.qiangqiang.base.ResultInfo;
import com.qiangqiang.crm.query.CusDevPlanQuery;
import com.qiangqiang.crm.query.SaleChanceQuery;
import com.qiangqiang.crm.service.CusDevPlanService;
import com.qiangqiang.crm.service.SaleChanceService;
import com.qiangqiang.crm.utils.LoginUserUtil;
import com.qiangqiang.crm.vo.CusDevPlan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("cus_dev_plan")
public class CusDevPlanController extends BaseController {

    @Resource
    private SaleChanceService saleChanceService;

    @Resource
    private CusDevPlanService cusDevPlanService;

    @RequestMapping("index")
    public String index(){
        return "cusDevPlan/cus_dev_plan";
    }


    @RequestMapping("toCusDevPlanDataPage")
    public String toCusDevPlanDataPage(Integer sid,Model model){
        model.addAttribute("saleChance",saleChanceService.selectByPrimaryKey(sid));
        return "cusDevPlan/cus_dev_plan_data";
    }

    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> queryCusDevPlansByParams(CusDevPlanQuery cusDevPlanQuery){
        return cusDevPlanService.queryCusDevPlansByParams(cusDevPlanQuery);
    }

    @GetMapping("listBySearch")
    @ResponseBody
    public Map<String,Object> queryCusDevPlansBySearch(CusDevPlanQuery cusDevPlanQuery){
        return cusDevPlanService.queryCusDevPlansBySearch(cusDevPlanQuery);
    }


    @RequestMapping("save")
    @ResponseBody
    public ResultInfo saveCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.saveCusDevPlan(cusDevPlan);
        return success("计划项数据添加成功");
    }

    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateCusDevPlan(CusDevPlan cusDevPlan){
        cusDevPlanService.updateCusDevPlan(cusDevPlan);
        return success("计划项数据更新成功");
    }

    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCusDevPlan(Integer id){
        cusDevPlanService.deleteCusDevPlan(id);
        return success("计划项数据删除成功");
    }


    @RequestMapping("addOrUpdateCusDevPlanPage")
    public String addOrUpdateCusDevPlanPage(Integer sid,Integer id,Model model){
        model.addAttribute("sid",sid);
        model.addAttribute("cusDevPlan",cusDevPlanService.selectByPrimaryKey(id));
        return "cusDevPlan/add_update";
    }





}
