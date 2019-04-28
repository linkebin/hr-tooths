package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "cust_jz")
public class CustJz {
    /**
     * id
     */
    @Id
    @Column(name = "jz_id")
    private String jzId;

    /**
     * 就诊时间
     */
    @Column(name = "jz_time")
    private Date jzTime;

    /**
     * 就诊客户
     */
    @Column(name = "jz_cust")
    private String jzCust;

    /**
     * 项目
     */
    @Column(name = "jz_project")
    private String jzProject;

    /**
     * 主治医生
     */
    @Column(name = "jz_doctor")
    private String jzDoctor;

    /**
     * 就诊类型
     */
    @Column(name = "jz_type")
    private String jzType;

    /**
     * 费用
     */
    @Column(name = "jz_cost")
    private Double jzCost;

    /**
     * 就诊诊所
     */
    @Column(name = "jz_clinic")
    private String jzClinic;

    /**
     * 获取id
     *
     * @return jz_id - id
     */
    public String getJzId() {
        return jzId;
    }

    /**
     * 设置id
     *
     * @param jzId id
     */
    public void setJzId(String jzId) {
        this.jzId = jzId;
    }

    /**
     * 获取就诊时间
     *
     * @return jz_time - 就诊时间
     */
    public Date getJzTime() {
        return jzTime;
    }

    /**
     * 设置就诊时间
     *
     * @param jzTime 就诊时间
     */
    public void setJzTime(Date jzTime) {
        this.jzTime = jzTime;
    }

    /**
     * 获取就诊客户
     *
     * @return jz_cust - 就诊客户
     */
    public String getJzCust() {
        return jzCust;
    }

    /**
     * 设置就诊客户
     *
     * @param jzCust 就诊客户
     */
    public void setJzCust(String jzCust) {
        this.jzCust = jzCust;
    }

    /**
     * 获取项目
     *
     * @return jz_project - 项目
     */
    public String getJzProject() {
        return jzProject;
    }

    /**
     * 设置项目
     *
     * @param jzProject 项目
     */
    public void setJzProject(String jzProject) {
        this.jzProject = jzProject;
    }

    /**
     * 获取主治医生
     *
     * @return jz_doctor - 主治医生
     */
    public String getJzDoctor() {
        return jzDoctor;
    }

    /**
     * 设置主治医生
     *
     * @param jzDoctor 主治医生
     */
    public void setJzDoctor(String jzDoctor) {
        this.jzDoctor = jzDoctor;
    }

    /**
     * 获取就诊类型
     *
     * @return jz_type - 就诊类型
     */
    public String getJzType() {
        return jzType;
    }

    /**
     * 设置就诊类型
     *
     * @param jzType 就诊类型
     */
    public void setJzType(String jzType) {
        this.jzType = jzType;
    }

    /**
     * 获取费用
     *
     * @return jz_cost - 费用
     */
    public Double getJzCost() {
        return jzCost;
    }

    /**
     * 设置费用
     *
     * @param jzCost 费用
     */
    public void setJzCost(Double jzCost) {
        this.jzCost = jzCost;
    }

    /**
     * 获取就诊诊所
     *
     * @return jz_clinic - 就诊诊所
     */
    public String getJzClinic() {
        return jzClinic;
    }

    /**
     * 设置就诊诊所
     *
     * @param jzClinic 就诊诊所
     */
    public void setJzClinic(String jzClinic) {
        this.jzClinic = jzClinic;
    }
}