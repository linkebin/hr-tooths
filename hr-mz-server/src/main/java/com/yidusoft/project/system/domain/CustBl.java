package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cust_bl")
public class CustBl {
    /**
     * id
     */
    @Id
    @Column(name = "bl_id")
    private String blId;

    /**
     * 客户id
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 关联就诊记录ID
     */
    @Column(name = "jz_id")
    private String jzId;

    /**
     * 时间
     */
    @Column(name = "bl_time")
    private Date blTime;

    /**
     * 主诉
     */
    @Column(name = "bl_zs")
    private String blZs;

    /**
     * 现病史
     */
    @Column(name = "bl_xbs")
    private String blXbs;

    /**
     * 既往史
     */
    @Column(name = "bl_jws")
    private String blJws;

    /**
     * 医嘱
     */
    @Column(name = "bl_yz")
    private String blYz;

    /**
     * 备注
     */
    @Column(name = "bl_bz")
    private String blBz;

    /**
     * 删除标识 0未删除 1已删除
     */
    private Integer deleted;

    /**
     * 类型 初诊 复诊
     */
    private String type;

    /**
     * 复诊
     */
    private String visit;

    /**
     * 医生
     */
    private String doctor;

    @Column(name = "bl_gms")
    private String blGms;

    @Column(name = "add_user")
    private String addUser;

    /**
     * 获取id
     *
     * @return bl_id - id
     */
    public String getBlId() {
        return blId;
    }

    /**
     * 设置id
     *
     * @param blId id
     */
    public void setBlId(String blId) {
        this.blId = blId;
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
     * 获取关联就诊记录ID
     *
     * @return jz_id - 关联就诊记录ID
     */
    public String getJzId() {
        return jzId;
    }

    /**
     * 设置关联就诊记录ID
     *
     * @param jzId 关联就诊记录ID
     */
    public void setJzId(String jzId) {
        this.jzId = jzId;
    }

    /**
     * 获取时间
     *
     * @return bl_time - 时间
     */
    public Date getBlTime() {
        return blTime;
    }

    /**
     * 设置时间
     *
     * @param blTime 时间
     */
    public void setBlTime(Date blTime) {
        this.blTime = blTime;
    }

    /**
     * 获取主诉
     *
     * @return bl_zs - 主诉
     */
    public String getBlZs() {
        return blZs;
    }

    /**
     * 设置主诉
     *
     * @param blZs 主诉
     */
    public void setBlZs(String blZs) {
        this.blZs = blZs;
    }

    /**
     * 获取现病史
     *
     * @return bl_xbs - 现病史
     */
    public String getBlXbs() {
        return blXbs;
    }

    /**
     * 设置现病史
     *
     * @param blXbs 现病史
     */
    public void setBlXbs(String blXbs) {
        this.blXbs = blXbs;
    }

    /**
     * 获取既往史
     *
     * @return bl_jws - 既往史
     */
    public String getBlJws() {
        return blJws;
    }

    /**
     * 设置既往史
     *
     * @param blJws 既往史
     */
    public void setBlJws(String blJws) {
        this.blJws = blJws;
    }

    /**
     * 获取医嘱
     *
     * @return bl_yz - 医嘱
     */
    public String getBlYz() {
        return blYz;
    }

    /**
     * 设置医嘱
     *
     * @param blYz 医嘱
     */
    public void setBlYz(String blYz) {
        this.blYz = blYz;
    }

    /**
     * 获取备注
     *
     * @return bl_bz - 备注
     */
    public String getBlBz() {
        return blBz;
    }

    /**
     * 设置备注
     *
     * @param blBz 备注
     */
    public void setBlBz(String blBz) {
        this.blBz = blBz;
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
     * 获取类型 初诊 复诊
     *
     * @return type - 类型 初诊 复诊
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型 初诊 复诊
     *
     * @param type 类型 初诊 复诊
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取复诊
     *
     * @return visit - 复诊
     */
    public String getVisit() {
        return visit;
    }

    /**
     * 设置复诊
     *
     * @param visit 复诊
     */
    public void setVisit(String visit) {
        this.visit = visit;
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

    public String getBlGms() {
        return blGms;
    }

    public void setBlGms(String blGms) {
        this.blGms = blGms;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }
}