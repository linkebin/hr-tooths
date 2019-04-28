package com.yidusoft.project.system.domain;

import javax.persistence.*;

@Table(name = "in_order_in")
public class InOrderIn {
    /**
     * id
     */
    @Id
    @Column(name = "order_de_id")
    private String orderDeId;

    /**
     * 关联订单id
     */
    @Column(name = "order_id")
    private String orderId;

    /**
     * 物品编号
     */
    @Column(name = "p_code")
    private String pCode;

    /**
     * 物品名称
     */
    @Column(name = "p_name")
    private String pName;

    /**
     * 物品类型
     */
    @Column(name = "p_type")
    private String pType;

    /**
     * 型号
     */
    @Column(name = "p_model")
    private String pModel;

    /**
     * 规格
     */
    @Column(name = "p_spe")
    private String pSpe;

    /**
     * 品牌
     */
    @Column(name = "p_brand")
    private String pBrand;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 单价
     */
    private Double price;

    /**
     * 金额
     */
    private Double money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 获取id
     *
     * @return order_de_id - id
     */
    public String getOrderDeId() {
        return orderDeId;
    }

    /**
     * 设置id
     *
     * @param orderDeId id
     */
    public void setOrderDeId(String orderDeId) {
        this.orderDeId = orderDeId;
    }

    /**
     * 获取关联订单id
     *
     * @return order_id - 关联订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置关联订单id
     *
     * @param orderId 关联订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取物品编号
     *
     * @return p_code - 物品编号
     */
    public String getpCode() {
        return pCode;
    }

    /**
     * 设置物品编号
     *
     * @param pCode 物品编号
     */
    public void setpCode(String pCode) {
        this.pCode = pCode;
    }

    /**
     * 获取物品名称
     *
     * @return p_name - 物品名称
     */
    public String getpName() {
        return pName;
    }

    /**
     * 设置物品名称
     *
     * @param pName 物品名称
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * 获取物品类型
     *
     * @return p_type - 物品类型
     */
    public String getpType() {
        return pType;
    }

    /**
     * 设置物品类型
     *
     * @param pType 物品类型
     */
    public void setpType(String pType) {
        this.pType = pType;
    }

    /**
     * 获取型号
     *
     * @return p_model - 型号
     */
    public String getpModel() {
        return pModel;
    }

    /**
     * 设置型号
     *
     * @param pModel 型号
     */
    public void setpModel(String pModel) {
        this.pModel = pModel;
    }

    /**
     * 获取规格
     *
     * @return p_spe - 规格
     */
    public String getpSpe() {
        return pSpe;
    }

    /**
     * 设置规格
     *
     * @param pSpe 规格
     */
    public void setpSpe(String pSpe) {
        this.pSpe = pSpe;
    }

    /**
     * 获取品牌
     *
     * @return p_brand - 品牌
     */
    public String getpBrand() {
        return pBrand;
    }

    /**
     * 设置品牌
     *
     * @param pBrand 品牌
     */
    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    /**
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取单价
     *
     * @return price - 单价
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置单价
     *
     * @param price 单价
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
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