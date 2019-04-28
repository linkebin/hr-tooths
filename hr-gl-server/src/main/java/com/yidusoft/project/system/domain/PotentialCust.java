package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "potential_cust")
public class PotentialCust {
    /**
     * id
     */
    @Id
    @Column(name = "potential_id")
    private String potentialId;

    /**
     * 潜在客户姓名
     */
    @Column(name = "potential_name")
    private String potentialName;

    /**
     * 联系方式
     */
    @Column(name = "potential_tel")
    private String potentialTel;

    /**
     * 录入时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 来源渠道
     */
    private String source;

    /**
     * 推广诊所
     */
    private String clinic;

    /**
     * 录入人
     */
    private String creator;

    /**
     * 消费金额
     */
    private Double money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取id
     *
     * @return potential_id - id
     */
    public String getPotentialId() {
        return potentialId;
    }

    /**
     * 设置id
     *
     * @param potentialId id
     */
    public void setPotentialId(String potentialId) {
        this.potentialId = potentialId;
    }

    /**
     * 获取潜在客户姓名
     *
     * @return potential_name - 潜在客户姓名
     */
    public String getPotentialName() {
        return potentialName;
    }

    /**
     * 设置潜在客户姓名
     *
     * @param potentialName 潜在客户姓名
     */
    public void setPotentialName(String potentialName) {
        this.potentialName = potentialName;
    }

    /**
     * 获取联系方式
     *
     * @return potential_tel - 联系方式
     */
    public String getPotentialTel() {
        return potentialTel;
    }

    /**
     * 设置联系方式
     *
     * @param potentialTel 联系方式
     */
    public void setPotentialTel(String potentialTel) {
        this.potentialTel = potentialTel;
    }

    /**
     * 获取录入时间
     *
     * @return create_time - 录入时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置录入时间
     *
     * @param createTime 录入时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取来源渠道
     *
     * @return source - 来源渠道
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源渠道
     *
     * @param source 来源渠道
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * 获取推广诊所
     *
     * @return clinic - 推广诊所
     */
    public String getClinic() {
        return clinic;
    }

    /**
     * 设置推广诊所
     *
     * @param clinic 推广诊所
     */
    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    /**
     * 获取录入人
     *
     * @return creator - 录入人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置录入人
     *
     * @param creator 录入人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取消费金额
     *
     * @return money - 消费金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置消费金额
     *
     * @param money 消费金额
     */
    public void setMoney(Double money) {
        this.money = money;
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
}