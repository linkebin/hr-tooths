package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Customer {
    /**
     * id
     */
    @Id
    @Column(name = "cust_id")
    private String custId;

    /**
     * 编号/病历号
     */
    @Column(name = "cust_code")
    private String custCode;

    /**
     * 姓名
     */
    @Column(name = "cust_name")
    private String custName;

    /**
     * 性别
     */
    @Column(name = "cust_sex")
    private String custSex;

    /**
     * 出生年月
     */
    @Column(name = "cust_birth")
    private Date custBirth;

    /**
     * 年龄
     */
    @Column(name = "cust_age")
    private String custAge;

    /**
     * 身份证号
     */
    @Column(name = "cust_num")
    private String custNum;

    /**
     * 联系电话
     */
    @Column(name = "cust_tell")
    private String custTell;

    /**
     * 联系邮箱
     */
    @Column(name = "cust_mail")
    private String custMail;

    /**
     * QQ
     */
    @Column(name = "cust_qq")
    private String custQq;

    /**
     * 微信
     */
    @Column(name = "cust_wechat")
    private String custWechat;

    /**
     * openId
     */
    @Column(name = "cust_open_id")
    private String custOpenId;

    /**
     * 职业
     */
    @Column(name = "cust_pro")
    private String custPro;

    /**
     * 学历
     */
    @Column(name = "cust_el")
    private String custEl;

    /**
     * 洁牙习惯
     */
    @Column(name = "cust_jyxg")
    private String custJyxg;

    /**
     * 就诊经历
     */
    @Column(name = "cust_jzjl")
    private String custJzjl;

    /**
     * 过敏史
     */
    @Column(name = "cust_gms")
    private String custGms;

    /**
     * 联系地址
     */
    @Column(name = "cust_addr")
    private String custAddr;

    /**
     * 客户等级
     */
    @Column(name = "cust_class")
    private String custClass;

    /**
     * 客户来源
     */
    @Column(name = "cust_source")
    private String custSource;

    /**
     * 客户分类
     */
    @Column(name = "cust_type")
    private String custType;

    /**
     * 备注
     */
    @Column(name = "cust_remark")
    private String custRemark;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "early_doctor")
    private String earlyDoctor;

    @Column(name = "early_clinic")
    private String earlyClinic;

    /**
     * 是否删除 是 1否0
     */
    private Integer deleted;

    /**
     * 初诊诊所
     */
    @Column(name = "cust_clinic")
    private String custClinic;

    /**
     * 医生
     */
    private String doctor;

    /**
     * 可用余额
     */
    private Double money;

    /**
     * 获取id
     *
     * @return cust_id - id
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置id
     *
     * @param custId id
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * 获取编号/病历号
     *
     * @return cust_code - 编号/病历号
     */
    public String getCustCode() {
        return custCode;
    }

    /**
     * 设置编号/病历号
     *
     * @param custCode 编号/病历号
     */
    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    /**
     * 获取姓名
     *
     * @return cust_name - 姓名
     */
    public String getCustName() {
        return custName;
    }

    /**
     * 设置姓名
     *
     * @param custName 姓名
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
     * 获取出生年月
     *
     * @return cust_birth - 出生年月
     */
    public Date getCustBirth() {
        return custBirth;
    }

    /**
     * 设置出生年月
     *
     * @param custBirth 出生年月
     */
    public void setCustBirth(Date custBirth) {
        this.custBirth = custBirth;
    }

    /**
     * 获取年龄
     *
     * @return cust_age - 年龄
     */
    public String getCustAge() {
        return custAge;
    }

    /**
     * 设置年龄
     *
     * @param custAge 年龄
     */
    public void setCustAge(String custAge) {
        this.custAge = custAge;
    }

    /**
     * 获取身份证号
     *
     * @return cust_num - 身份证号
     */
    public String getCustNum() {
        return custNum;
    }

    /**
     * 设置身份证号
     *
     * @param custNum 身份证号
     */
    public void setCustNum(String custNum) {
        this.custNum = custNum;
    }

    /**
     * 获取联系电话
     *
     * @return cust_tell - 联系电话
     */
    public String getCustTell() {
        return custTell;
    }

    /**
     * 设置联系电话
     *
     * @param custTell 联系电话
     */
    public void setCustTell(String custTell) {
        this.custTell = custTell;
    }

    /**
     * 获取联系邮箱
     *
     * @return cust_mail - 联系邮箱
     */
    public String getCustMail() {
        return custMail;
    }

    /**
     * 设置联系邮箱
     *
     * @param custMail 联系邮箱
     */
    public void setCustMail(String custMail) {
        this.custMail = custMail;
    }

    /**
     * 获取QQ
     *
     * @return cust_qq - QQ
     */
    public String getCustQq() {
        return custQq;
    }

    /**
     * 设置QQ
     *
     * @param custQq QQ
     */
    public void setCustQq(String custQq) {
        this.custQq = custQq;
    }

    /**
     * 获取微信
     *
     * @return cust_wechat - 微信
     */
    public String getCustWechat() {
        return custWechat;
    }

    /**
     * 设置微信
     *
     * @param custWechat 微信
     */
    public void setCustWechat(String custWechat) {
        this.custWechat = custWechat;
    }

    /**
     * 获取openId
     *
     * @return cust_open_id - openId
     */
    public String getCustOpenId() {
        return custOpenId;
    }

    /**
     * 设置openId
     *
     * @param custOpenId openId
     */
    public void setCustOpenId(String custOpenId) {
        this.custOpenId = custOpenId;
    }

    /**
     * 获取职业
     *
     * @return cust_pro - 职业
     */
    public String getCustPro() {
        return custPro;
    }

    /**
     * 设置职业
     *
     * @param custPro 职业
     */
    public void setCustPro(String custPro) {
        this.custPro = custPro;
    }

    /**
     * 获取学历
     *
     * @return cust_el - 学历
     */
    public String getCustEl() {
        return custEl;
    }

    /**
     * 设置学历
     *
     * @param custEl 学历
     */
    public void setCustEl(String custEl) {
        this.custEl = custEl;
    }

    /**
     * 获取洁牙习惯
     *
     * @return cust_jyxg - 洁牙习惯
     */
    public String getCustJyxg() {
        return custJyxg;
    }

    /**
     * 设置洁牙习惯
     *
     * @param custJyxg 洁牙习惯
     */
    public void setCustJyxg(String custJyxg) {
        this.custJyxg = custJyxg;
    }

    /**
     * 获取就诊经历
     *
     * @return cust_jzjl - 就诊经历
     */
    public String getCustJzjl() {
        return custJzjl;
    }

    /**
     * 设置就诊经历
     *
     * @param custJzjl 就诊经历
     */
    public void setCustJzjl(String custJzjl) {
        this.custJzjl = custJzjl;
    }

    /**
     * 获取过敏史
     *
     * @return cust_gms - 过敏史
     */
    public String getCustGms() {
        return custGms;
    }

    /**
     * 设置过敏史
     *
     * @param custGms 过敏史
     */
    public void setCustGms(String custGms) {
        this.custGms = custGms;
    }

    /**
     * 获取联系地址
     *
     * @return cust_addr - 联系地址
     */
    public String getCustAddr() {
        return custAddr;
    }

    /**
     * 设置联系地址
     *
     * @param custAddr 联系地址
     */
    public void setCustAddr(String custAddr) {
        this.custAddr = custAddr;
    }

    /**
     * 获取客户等级
     *
     * @return cust_class - 客户等级
     */
    public String getCustClass() {
        return custClass;
    }

    /**
     * 设置客户等级
     *
     * @param custClass 客户等级
     */
    public void setCustClass(String custClass) {
        this.custClass = custClass;
    }

    /**
     * 获取客户来源
     *
     * @return cust_source - 客户来源
     */
    public String getCustSource() {
        return custSource;
    }

    /**
     * 设置客户来源
     *
     * @param custSource 客户来源
     */
    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    /**
     * 获取客户分类
     *
     * @return cust_type - 客户分类
     */
    public String getCustType() {
        return custType;
    }

    /**
     * 设置客户分类
     *
     * @param custType 客户分类
     */
    public void setCustType(String custType) {
        this.custType = custType;
    }

    /**
     * 获取备注
     *
     * @return cust_remark - 备注
     */
    public String getCustRemark() {
        return custRemark;
    }

    /**
     * 设置备注
     *
     * @param custRemark 备注
     */
    public void setCustRemark(String custRemark) {
        this.custRemark = custRemark;
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
     * 获取是否删除 是 1否0
     *
     * @return deleted - 是否删除 是 1否0
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除 是 1否0
     *
     * @param deleted 是否删除 是 1否0
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取初诊诊所
     *
     * @return cust_clinic - 初诊诊所
     */
    public String getCustClinic() {
        return custClinic;
    }

    /**
     * 设置初诊诊所
     *
     * @param custClinic 初诊诊所
     */
    public void setCustClinic(String custClinic) {
        this.custClinic = custClinic;
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
     * 获取可用余额
     *
     * @return money - 可用余额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置可用余额
     *
     * @param money 可用余额
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    public String getEarlyDoctor() {
        return earlyDoctor;
    }

    public void setEarlyDoctor(String earlyDoctor) {
        this.earlyDoctor = earlyDoctor;
    }

    public String getEarlyClinic() {
        return earlyClinic;
    }

    public void setEarlyClinic(String earlyClinic) {
        this.earlyClinic = earlyClinic;
    }
}