package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cust_sfjl")
public class CustSfjl {
    /**
     * id
     */
    @Id
    @Column(name = "sf_id")
    private String sfId;

    /**
     * 状态
     */
    @Column(name = "sf_stat")
    private String sfStat;

    /**
     * 收费时间
     */
    @Column(name = "sf_time")
    private Date sfTime;

    /**
     * 客户id
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 客户姓名
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 订单金额
     */
    @Column(name = "sf_money")
    private String sfMoney;

    /**
     * 医生ID
     */
    @Column(name = "doctor_id")
    private String doctorId;

    /**
     * 医生
     */
    private String doctor;

    /**
     * 收款员
     */
    private String cashier;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除标识 0未删除  1已删除
     */
    private Integer deleted;

    /**
     * 收费类型
     */
    @Column(name = "charge_type")
    private String chargeType;

    private Double arrears;

    /**
     * 获取id
     *
     * @return sf_id - id
     */
    public String getSfId() {
        return sfId;
    }

    /**
     * 设置id
     *
     * @param sfId id
     */
    public void setSfId(String sfId) {
        this.sfId = sfId;
    }

    /**
     * 获取状态
     *
     * @return sf_stat - 状态
     */
    public String getSfStat() {
        return sfStat;
    }

    /**
     * 设置状态
     *
     * @param sfStat 状态
     */
    public void setSfStat(String sfStat) {
        this.sfStat = sfStat;
    }

    /**
     * 获取收费时间
     *
     * @return sf_time - 收费时间
     */
    public Date getSfTime() {
        return sfTime;
    }

    /**
     * 设置收费时间
     *
     * @param sfTime 收费时间
     */
    public void setSfTime(Date sfTime) {
        this.sfTime = sfTime;
    }

    /**
     * 获取客户id
     *
     * @return cust_id - 客户id
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置客户id
     *
     * @param custId 客户id
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * 获取客户姓名
     *
     * @return cust_name - 客户姓名
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置客户姓名
     *
     * @param custName 客户姓名
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * 获取订单金额
     *
     * @return sf_money - 订单金额
     */
    public String getSfMoney() {
        return sfMoney;
    }

    /**
     * 设置订单金额
     *
     * @param sfMoney 订单金额
     */
    public void setSfMoney(String sfMoney) {
        this.sfMoney = sfMoney;
    }

    /**
     * 获取医生ID
     *
     * @return doctor_id - 医生ID
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * 设置医生ID
     *
     * @param doctorId 医生ID
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * 获取医生
     *
     * @return doctor - 医生
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * 设置医生
     *
     * @param doctor 医生
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * 获取收款员
     *
     * @return cashier - 收款员
     */
    public String getCashier() {
        return cashier;
    }

    /**
     * 设置收款员
     *
     * @param cashier 收款员
     */
    public void setCashier(String cashier) {
        this.cashier = cashier;
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
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取删除标识 0未删除  1已删除
     *
     * @return deleted - 删除标识 0未删除  1已删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识 0未删除  1已删除
     *
     * @param deleted 删除标识 0未删除  1已删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取收费类型
     *
     * @return charge_type - 收费类型
     */
    public String getChargeType() {
        return chargeType;
    }

    /**
     * 设置收费类型
     *
     * @param chargeType 收费类型
     */
    public void setChargeType(String chargeType) {
        this.chargeType = chargeType;
    }

    public Double getArrears() {
        return arrears;
    }

    public void setArrears(Double arrears) {
        this.arrears = arrears;
    }
}