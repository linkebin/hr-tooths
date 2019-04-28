package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "stock_audit")
public class StockAudit {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "audit_id")
    private String auditId;

    @Column(name = "in_out_id")
    private String inOutId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "audit_time")
    private Date auditTime;

    @Column(name = "remark")
    private String remark;

    @Column(name = "is_pass")
    private int isPass;

    public String getAuditId() {
        return auditId;
    }

    public void setAuditId(String auditId) {
        this.auditId = auditId;
    }

    public String getInOutId() {
        return inOutId;
    }

    public void setInOutId(String inOutId) {
        this.inOutId = inOutId;
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

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }
}