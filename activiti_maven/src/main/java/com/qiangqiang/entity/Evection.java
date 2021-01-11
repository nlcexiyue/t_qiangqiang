package com.qiangqiang.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * \* Created with IntelliJ IDEA.
 * \* @author: xiyue
 * \* Date: 2021/1/5
 * \* Time: 16:16
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */
public class Evection implements Serializable {

    /**
     * 主键id
     */
    private Long id;
    /**
     * 出差天数
     */
    private Double days;

    /**
     * 出差单的名字
     */
    private String exectionName;

    /**
     * 开始时间
     */
    private Date beginDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 目的地
     */
    private String address;

    public Evection() {
    }

    public Evection(Long id, Double days, String exectionName, Date beginDate, Date endDate, String address) {
        this.id = id;
        this.days = days;
        this.exectionName = exectionName;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDays() {
        return days;
    }

    public void setDays(Double days) {
        this.days = days;
    }

    public String getExectionName() {
        return exectionName;
    }

    public void setExectionName(String exectionName) {
        this.exectionName = exectionName;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}