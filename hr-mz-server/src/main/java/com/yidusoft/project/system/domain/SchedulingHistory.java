package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "scheduling_history")
public class SchedulingHistory {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 诊所ID
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 年份
     */
    private Integer year;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 编制人
     */
    private String creator;

    /**
     * 编制时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 状态
     */
    private Integer state;

    /**
     * @return id_
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取诊所ID
     *
     * @return org_id - 诊所ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置诊所ID
     *
     * @param orgId 诊所ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取年份
     *
     * @return year - 年份
     */
    public Integer getYear() {
        return year;
    }

    /**
     * 设置年份
     *
     * @param year 年份
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * 获取月份
     *
     * @return month - 月份
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * 设置月份
     *
     * @param month 月份
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * 获取编制人
     *
     * @return creator - 编制人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置编制人
     *
     * @param creator 编制人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取编制时间
     *
     * @return create_time - 编制时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置编制时间
     *
     * @param createTime 编制时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(Integer state) {
        this.state = state;
    }
}