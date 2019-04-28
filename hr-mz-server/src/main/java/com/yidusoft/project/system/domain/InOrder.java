package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "in_order")
public class InOrder {
    /**
     * id
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 订单编号
     */
    @Column(name = "order_code")
    private String orderCode;

    /**
     * 订单时间
     */
    @Column(name = "order_time")
    private Date orderTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 金额
     */
    @Column(name = "order_money")
    private Double orderMoney;

    /**
     * 合计总数
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 获取id
     *
     * @return order_id - id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置id
     *
     * @param orderId id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 获取订单编号
     *
     * @return order_code - 订单编号
     */
    public String getOrderCode() {
        return orderCode;
    }

    /**
     * 设置订单编号
     *
     * @param orderCode 订单编号
     */
    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * 获取订单时间
     *
     * @return order_time - 订单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 设置订单时间
     *
     * @param orderTime 订单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取金额
     *
     * @return order_money - 金额
     */
    public Double getOrderMoney() {
        return orderMoney;
    }

    /**
     * 设置金额
     *
     * @param orderMoney 金额
     */
    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    /**
     * 获取合计总数
     *
     * @return order_num - 合计总数
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置合计总数
     *
     * @param orderNum 合计总数
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}