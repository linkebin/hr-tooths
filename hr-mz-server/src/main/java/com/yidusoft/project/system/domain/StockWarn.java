package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "stock_warn")
public class StockWarn {
    /**
     * id
     */
    @Id
    @Column(name = "id")
    private String id;

    /**
     * 预警物品ID
     */
    @Column(name = "warn_pro_id")
    private String warnProId;

    /**
     * 预警物品名称
     */
    @Column(name = "warn_name")
    private String warnName;

    /**
     * 预警物品剩余数量
     */
    @Column(name = "warn_num")
    private int warnNum;

    /**
     * 预警时间
     */
    @Column(name = "warn_time")
    private Date warnTime;

    /**
     * 预警诊所id
     */
    @Column(name = "clinic_id")
    private String clinicId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

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

    /**
     * 获取预警物品ID
     *
     * @return warn_pro_id - 预警物品ID
     */
    public String getWarnProId() {
        return warnProId;
    }

    /**
     * 设置预警物品ID
     *
     * @param warnProId 预警物品ID
     */
    public void setWarnProId(String warnProId) {
        this.warnProId = warnProId;
    }

    /**
     * 获取预警物品名称
     *
     * @return warn_name - 预警物品名称
     */
    public String getWarnName() {
        return warnName;
    }

    /**
     * 设置预警物品名称
     *
     * @param warnName 预警物品名称
     */
    public void setWarnName(String warnName) {
        this.warnName = warnName;
    }

    public int getWarnNum() {
        return warnNum;
    }

    public void setWarnNum(int warnNum) {
        this.warnNum = warnNum;
    }

    /**
     * 获取预警时间
     *
     * @return warn_time - 预警时间
     */
    public Date getWarnTime() {
        return warnTime;
    }

    /**
     * 设置预警时间
     *
     * @param warnTime 预警时间
     */
    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }

    /**
     * 获取预警诊所id
     *
     * @return clinic_id - 预警诊所id
     */
    public String getClinicId() {
        return clinicId;
    }

    /**
     * 设置预警诊所id
     *
     * @param clinicId 预警诊所id
     */
    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}