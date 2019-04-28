package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "tooth_order")
public class ToothOrder {
    /**
     * id
     */
    @Id
    @Column(name = "order_id")
    private String orderId;

    /**
     * 订单编号
     */
    @Column(name = "order_number")
    private String orderNumber;

    /**
     * 类型
     */
    @Column(name = "order_type")
    private String orderType;

    /**
     * 状态
     */
    @Column(name = "order_state")
    private String orderState;

    /**
     * 下单时间
     */
    @Column(name = "order_time")
    private Date orderTime;

    /**
     * 出货时间
     */
    @Column(name = "finish_time")
    private Date finishTime;

    /**
     * 寄件时间
     */
    @Column(name = "mail_time")
    private Date mailTime;

    /**
     * 诊所
     */
    private String clinic;

    /**
     * 医生
     */
    private String doctor;

    /**
     * cust_id
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 患者
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 性别
     */
    @Column(name = "cust_sex")
    private String custSex;

    /**
     * 材料类型
     */
    @Column(name = "cl_type")
    private String clType;

    /**
     * 附件
     */
    @Column(name = "att_type")
    private String attType;

    /**
     * 贴面矫正类型
     */
    @Column(name = "tmjz_type")
    private String tmjzType;

    /**
     * 咬合设计
     */
    @Column(name = "yh_type")
    private String yhType;

    /**
     * 种植体系统
     */
    private String zztxt;

    /**
     * 牙龈压迫程度
     */
    private String yyypcd;

    /**
     * margin设计
     */
    private String margin;

    /**
     * 牙位
     */
    private String tooth;

    /**
     * 形态设计-咬合面
     */
    @Column(name = "xtsj_yhm")
    private String xtsjYhm;

    /**
     * 形态设计-桥体设计
     */
    @Column(name = "xtsj_qtsj")
    private String xtsjQtsj;

    /**
     * 形态设计-三角牙缝
     */
    @Column(name = "xtsj_sjyf")
    private String xtsjSjyf;

    /**
     * 比色
     */
    private String bs;

    /**
     * 基牙色是否变色
     */
    @Column(name = "bs_is")
    private String bsIs;

    /**
     * 删除标识 0未删除 1已删除
     */
    private Integer deleted;

    /**
     * 其他说明
     */
    private String remark;

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
     * @return order_number - 订单编号
     */
    public String getOrderNumber() {
        return orderNumber;
    }

    /**
     * 设置订单编号
     *
     * @param orderNumber 订单编号
     */
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    /**
     * 获取类型
     *
     * @return order_type - 类型
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 设置类型
     *
     * @param orderType 类型
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取状态
     *
     * @return order_state - 状态
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * 设置状态
     *
     * @param orderState 状态
     */
    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    /**
     * 获取下单时间
     *
     * @return order_time - 下单时间
     */
    public Date getOrderTime() {
        return orderTime;
    }

    /**
     * 设置下单时间
     *
     * @param orderTime 下单时间
     */
    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    /**
     * 获取出货时间
     *
     * @return finish_time - 出货时间
     */
    public Date getFinishTime() {
        return finishTime;
    }

    /**
     * 设置出货时间
     *
     * @param finishTime 出货时间
     */
    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 获取寄件时间
     *
     * @return mail_time - 寄件时间
     */
    public Date getMailTime() {
        return mailTime;
    }

    /**
     * 设置寄件时间
     *
     * @param mailTime 寄件时间
     */
    public void setMailTime(Date mailTime) {
        this.mailTime = mailTime;
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
     * 获取cust_id
     *
     * @return cust_id - cust_id
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置cust_id
     *
     * @param custId cust_id
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * 获取患者
     *
     * @return cust_name - 患者
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置患者
     *
     * @param custName 患者
     */
    public void setCustName(String custName) {
        this.custName = custName;
    }

    /**
     * 获取性别
     *
     * @return cust_sex - 性别
     */
    public String getCustSex() {
        return custSex;
    }

    /**
     * 设置性别
     *
     * @param custSex 性别
     */
    public void setCustSex(String custSex) {
        this.custSex = custSex;
    }

    /**
     * 获取材料类型
     *
     * @return cl_type - 材料类型
     */
    public String getClType() {
        return clType;
    }

    /**
     * 设置材料类型
     *
     * @param clType 材料类型
     */
    public void setClType(String clType) {
        this.clType = clType;
    }

    /**
     * 获取附件
     *
     * @return att_type - 附件
     */
    public String getAttType() {
        return attType;
    }

    /**
     * 设置附件
     *
     * @param attType 附件
     */
    public void setAttType(String attType) {
        this.attType = attType;
    }

    /**
     * 获取贴面矫正类型
     *
     * @return tmjz_type - 贴面矫正类型
     */
    public String getTmjzType() {
        return tmjzType;
    }

    /**
     * 设置贴面矫正类型
     *
     * @param tmjzType 贴面矫正类型
     */
    public void setTmjzType(String tmjzType) {
        this.tmjzType = tmjzType;
    }

    /**
     * 获取咬合设计
     *
     * @return yh_type - 咬合设计
     */
    public String getYhType() {
        return yhType;
    }

    /**
     * 设置咬合设计
     *
     * @param yhType 咬合设计
     */
    public void setYhType(String yhType) {
        this.yhType = yhType;
    }

    /**
     * 获取种植体系统
     *
     * @return zztxt - 种植体系统
     */
    public String getZztxt() {
        return zztxt;
    }

    /**
     * 设置种植体系统
     *
     * @param zztxt 种植体系统
     */
    public void setZztxt(String zztxt) {
        this.zztxt = zztxt;
    }

    /**
     * 获取牙龈压迫程度
     *
     * @return yyypcd - 牙龈压迫程度
     */
    public String getYyypcd() {
        return yyypcd;
    }

    /**
     * 设置牙龈压迫程度
     *
     * @param yyypcd 牙龈压迫程度
     */
    public void setYyypcd(String yyypcd) {
        this.yyypcd = yyypcd;
    }

    /**
     * 获取margin设计
     *
     * @return margin - margin设计
     */
    public String getMargin() {
        return margin;
    }

    /**
     * 设置margin设计
     *
     * @param margin margin设计
     */
    public void setMargin(String margin) {
        this.margin = margin;
    }

    /**
     * 获取牙位
     *
     * @return tooth - 牙位
     */
    public String getTooth() {
        return tooth;
    }

    /**
     * 设置牙位
     *
     * @param tooth 牙位
     */
    public void setTooth(String tooth) {
        this.tooth = tooth;
    }

    /**
     * 获取形态设计-咬合面
     *
     * @return xtsj_yhm - 形态设计-咬合面
     */
    public String getXtsjYhm() {
        return xtsjYhm;
    }

    /**
     * 设置形态设计-咬合面
     *
     * @param xtsjYhm 形态设计-咬合面
     */
    public void setXtsjYhm(String xtsjYhm) {
        this.xtsjYhm = xtsjYhm;
    }

    /**
     * 获取形态设计-桥体设计
     *
     * @return xtsj_qtsj - 形态设计-桥体设计
     */
    public String getXtsjQtsj() {
        return xtsjQtsj;
    }

    /**
     * 设置形态设计-桥体设计
     *
     * @param xtsjQtsj 形态设计-桥体设计
     */
    public void setXtsjQtsj(String xtsjQtsj) {
        this.xtsjQtsj = xtsjQtsj;
    }

    /**
     * 获取形态设计-三角牙缝
     *
     * @return xtsj_sjyf - 形态设计-三角牙缝
     */
    public String getXtsjSjyf() {
        return xtsjSjyf;
    }

    /**
     * 设置形态设计-三角牙缝
     *
     * @param xtsjSjyf 形态设计-三角牙缝
     */
    public void setXtsjSjyf(String xtsjSjyf) {
        this.xtsjSjyf = xtsjSjyf;
    }

    /**
     * 获取比色
     *
     * @return bs - 比色
     */
    public String getBs() {
        return bs;
    }

    /**
     * 设置比色
     *
     * @param bs 比色
     */
    public void setBs(String bs) {
        this.bs = bs;
    }

    /**
     * 获取基牙色是否变色
     *
     * @return bs_is - 基牙色是否变色
     */
    public String getBsIs() {
        return bsIs;
    }

    /**
     * 设置基牙色是否变色
     *
     * @param bsIs 基牙色是否变色
     */
    public void setBsIs(String bsIs) {
        this.bsIs = bsIs;
    }

    /**
     * 获取删除标识 0未删除 1已删除
     *
     * @return deleted - 删除标识 0未删除 1已删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识 0未删除 1已删除
     *
     * @param deleted 删除标识 0未删除 1已删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取其他说明
     *
     * @return remark - 其他说明
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置其他说明
     *
     * @param remark 其他说明
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}