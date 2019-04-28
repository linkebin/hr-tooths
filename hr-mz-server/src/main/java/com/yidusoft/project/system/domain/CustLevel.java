package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cust_level")
public class CustLevel {
    /**
     * id
     */
    @Id
    @Column(name = "level_id")
    private String levelId;

    /**
     * 潜在客户姓名
     */
    @Column(name = "level_name")
    private String levelName;

    /**
     * 联系方式
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 录入时间
     */
    @Column(name = "add_time")
    private Date addTime;

    /**
     * 来源渠道
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 推广诊所
     */
    @Column(name = "user_name")
    private String userName;

    @Column(name = "remark")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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