package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cust_sfjl_de")
public class CustSfjlDe {
    /**
     * id
     */
    @Id
    @Column(name = "sf_de_id")
    private String sfDeId;

    /**
     * 名称
     */
    private String name;

    /**
     * 数量
     */
    private String num;

    /**
     * 单次用量
     */
    @Column(name = "once_num")
    private String onceNum;

    /**
     * 用法
     */
    private String usages;

    /**
     * 频度
     */
    private String freq;

    /**
     * 总数量
     */
    @Column(name = "all_num")
    private String allNum;

    /**
     * 单价
     */
    private String price;

    /**
     * 金额
     */
    private String money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 原金额
     */
    @Column(name = "o_money")
    private String oMoney;
    /**
     * 折扣率
     */
    @Column(name = "discount")
    private String discount;

    public String getoMoney() {
        return oMoney;
    }

    public void setoMoney(String oMoney) {
        this.oMoney = oMoney;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    /**
     * 关联收费记录id
     */
    @Column(name = "sfjl_id")
    private String sfjlId;

    /**
     * 获取id
     *
     * @return sf_de_id - id
     */
    public String getSfDeId() {
        return sfDeId;
    }

    /**
     * 设置id
     *
     * @param sfDeId id
     */
    public void setSfDeId(String sfDeId) {
        this.sfDeId = sfDeId;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public String getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(String num) {
        this.num = num;
    }

    /**
     * 获取单次用量
     *
     * @return once_num - 单次用量
     */
    public String getOnceNum() {
        return onceNum;
    }

    /**
     * 设置单次用量
     *
     * @param onceNum 单次用量
     */
    public void setOnceNum(String onceNum) {
        this.onceNum = onceNum;
    }

    /**
     * 获取用法
     *
     * @return usages - 用法
     */
    public String getUsages() {
        return usages;
    }

    /**
     * 设置用法
     *
     * @param usages 用法
     */
    public void setUsages(String usages) {
        this.usages = usages;
    }

    /**
     * 获取频度
     *
     * @return freq - 频度
     */
    public String getFreq() {
        return freq;
    }

    /**
     * 设置频度
     *
     * @param freq 频度
     */
    public void setFreq(String freq) {
        this.freq = freq;
    }

    /**
     * 获取总数量
     *
     * @return all_num - 总数量
     */
    public String getAllNum() {
        return allNum;
    }

    /**
     * 设置总数量
     *
     * @param allNum 总数量
     */
    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public String getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(String price) {
        this.price = price;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public String getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(String money) {
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

    /**
     * 获取关联收费记录id
     *
     * @return sfjl_id - 关联收费记录id
     */
    public String getSfjlId() {
        return sfjlId;
    }

    /**
     * 设置关联收费记录id
     *
     * @param sfjlId 关联收费记录id
     */
    public void setSfjlId(String sfjlId) {
        this.sfjlId = sfjlId;
    }
}