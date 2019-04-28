package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "account_record")
public class AccountRecord {
    @Id
    @Column(name = "record_id")
    private String recordId;

    /**
     * 操作人
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 客户
     */
    @Column(name = "cust_id")
    private String custId;

    private String sfId;

    /**
     * 记录类型—消费，充值
     */
    @Column(name = "record_type")
    private String recordType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 金额
     */
    @Column(name = "record_money")
    private Double recordMoney;

    private String bank;

    /**
     * @return record_id
     */
    public String getRecordId() {
        return recordId;
    }

    /**
     * @param recordId
     */
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取操作人
     *
     * @return user_id - 操作人
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置操作人
     *
     * @param userId 操作人
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取客户
     *
     * @return cust_id - 客户
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置客户
     *
     * @param custId 客户
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getSfId() {
        return sfId;
    }

    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    /**
     * 获取记录类型—消费，充值
     *
     * @return record_type - 记录类型—消费，充值
     */
    public String getRecordType() {
        return recordType;
    }

    /**
     * 设置记录类型—消费，充值
     *
     * @param recordType 记录类型—消费，充值
     */
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取金额
     *
     * @return record_money - 金额
     */
    public Double getRecordMoney() {
        return recordMoney;
    }

    /**
     * 设置金额
     *
     * @param recordMoney 金额
     */
    public void setRecordMoney(Double recordMoney) {
        this.recordMoney = recordMoney;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }
}