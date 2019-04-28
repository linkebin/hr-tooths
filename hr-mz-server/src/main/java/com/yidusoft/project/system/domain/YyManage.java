package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "yy_manage")
public class YyManage {
    /**
     * id
     */
    @Id
    @Column(name = "yy_id")
    private String yyId;

    /**
     * 预约编号
     */
    @Column(name = "yy_code")
    private String yyCode;

    /**
     * 预约日期
     */
    @Column(name = "yy_date")
    private Date yyDate;

    /**
     * 预约时间
     */
    @Column(name = "yy_time")
    private String yyTime;


    /**
     * 客户姓名
     */
    @Column(name = "yy_cust_id")
    private String yyCustId;

    /**
     * 客户姓名
     */
    @Column(name = "yy_cust_name")
    private String yyCustName;

    /**
     * 性别
     */
    @Column(name = "yy_cust_sex")
    private String yyCustSex;

    /**
     * 联系电话
     */
    @Column(name = "yy_cust_tel")
    private String yyCustTel;

    /**
     * 预约诊所
     */
    @Column(name = "yy_clinic")
    private String yyClinic;

    /**
     * 预约医生
     */
    @Column(name = "yy_doctor")
    private String yyDoctor;

    /**
     * 预约项目
     */
    @Column(name = "yy_project")

    /**
     * 预约来源
     */    private String yyProject;

    @Column(name = "yy_source")
    private String yySource;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "yy_age")
    private Integer yyAge;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取id
     *
     * @return yy_id - id
     */
    public String getYyId() {
        return yyId;
    }

    /**
     * 设置id
     *
     * @param yyId id
     */
    public void setYyId(String yyId) {
        this.yyId = yyId;
    }

    /**
     * 获取预约编号
     *
     * @return yy_code - 预约编号
     */
    public String getYyCode() {
        return yyCode;
    }

    /**
     * 设置预约编号
     *
     * @param yyCode 预约编号
     */
    public void setYyCode(String yyCode) {
        this.yyCode = yyCode;
    }

    /**
     * 获取预约日期
     *
     * @return yy_date - 预约日期
     */
    public Date getYyDate() {
        return yyDate;
    }

    /**
     * 设置预约日期
     *
     * @param yyDate 预约日期
     */
    public void setYyDate(Date yyDate) {
        this.yyDate = yyDate;
    }

    /**
     * 获取预约时间
     *
     * @return yy_time - 预约时间
     */
    public String getYyTime() {
        return yyTime;
    }

    /**
     * 设置预约时间
     *
     * @param yyTime 预约时间
     */
    public void setYyTime(String yyTime) {
        this.yyTime = yyTime;
    }

    /**
     * 获取客户姓名
     *
     * @return yy_cust_name - 客户姓名
     */
    public String getYyCustName() {
        return yyCustName;
    }

    /**
     * 设置客户姓名
     *
     * @param yyCustName 客户姓名
     */
    public void setYyCustName(String yyCustName) {
        this.yyCustName = yyCustName;
    }

    /**
     * 获取性别
     *
     * @return yy_cust_sex - 性别
     */
    public String getYyCustSex() {
        return yyCustSex;
    }

    /**
     * 设置性别
     *
     * @param yyCustSex 性别
     */
    public void setYyCustSex(String yyCustSex) {
        this.yyCustSex = yyCustSex;
    }

    /**
     * 获取联系电话
     *
     * @return yy_cust_tel - 联系电话
     */
    public String getYyCustTel() {
        return yyCustTel;
    }

    /**
     * 设置联系电话
     *
     * @param yyCustTel 联系电话
     */
    public void setYyCustTel(String yyCustTel) {
        this.yyCustTel = yyCustTel;
    }

    /**
     * 获取预约诊所
     *
     * @return yy_clinic - 预约诊所
     */
    public String getYyClinic() {
        return yyClinic;
    }

    /**
     * 设置预约诊所
     *
     * @param yyClinic 预约诊所
     */
    public void setYyClinic(String yyClinic) {
        this.yyClinic = yyClinic;
    }

    /**
     * 获取预约医生
     *
     * @return yy_doctor - 预约医生
     */
    public String getYyDoctor() {
        return yyDoctor;
    }

    /**
     * 设置预约医生
     *
     * @param yyDoctor 预约医生
     */
    public void setYyDoctor(String yyDoctor) {
        this.yyDoctor = yyDoctor;
    }

    /**
     * 获取预约项目
     *
     * @return yy_project - 预约项目
     */
    public String getYyProject() {
        return yyProject;
    }

    /**
     * 设置预约项目
     *
     * @param yyProject 预约项目
     */
    public void setYyProject(String yyProject) {
        this.yyProject = yyProject;
    }

    /**
     * 获取预约来源
     *
     * @return yy_source - 预约来源
     */
    public String getYySource() {
        return yySource;
    }

    /**
     * 设置预约来源
     *
     * @param yySource 预约来源
     */
    public void setYySource(String yySource) {
        this.yySource = yySource;
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
     * @return yy_age
     */
    public Integer getYyAge() {
        return yyAge;
    }

    /**
     * @param yyAge
     */
    public void setYyAge(Integer yyAge) {
        this.yyAge = yyAge;
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

    public String getYyCustId() {
        return yyCustId;
    }

    public void setYyCustId(String yyCustId) {
        this.yyCustId = yyCustId;
    }
}