package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "scheduling_plan")
public class SchedulingPlan {
    /**
     * id
     */
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 日期
     */
    private Integer date;

    /**
     * 排班日期
     */
    private Date planDate;

    /**
     * 上班人员
     */
    private String doctors;

    /**
     * 状态
     */
    private String state;

    /**
     * 诊所
     */
    private String clinic;

    /**
     * 计划历史ID
     */
    private String historyId;


    public Date getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Date planDate) {
        this.planDate = planDate;
    }

    public String getHistoryId() {
        return historyId;
    }

    public void setHistoryId(String historyId) {
        this.historyId = historyId;
    }

    /**
     * 获取id
     *
     * @return id_ - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    /**
     * 获取上班人员
     *
     * @return doctors - 上班人员
     */
    public String getDoctors() {
        return doctors;
    }

    /**
     * 设置上班人员
     *
     * @param doctors 上班人员
     */
    public void setDoctors(String doctors) {
        this.doctors = doctors;
    }

    /**
     * 获取状态
     *
     * @return state - 状态
     */
    public String getState() {
        return state;
    }

    /**
     * 设置状态
     *
     * @param state 状态
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 获取诊所
     *
     * @return clinic - 诊所
     */
    public String getClinic() {
        return clinic;
    }

    /**
     * 设置诊所
     *
     * @param clinic 诊所
     */
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }
}