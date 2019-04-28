package com.yidusoft.project.system.domain;

import javax.persistence.*;

@Table(name = "project_manage")
public class ProjectManage {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 项目介绍
     */
    @Column(name = "project_re")
    private String projectRe;

    /**
     * 诊所
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 价格
     */
    private Double price;

    /**
     * @return id_
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取项目名称
     *
     * @return project_name - 项目名称
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * 设置项目名称
     *
     * @param projectName 项目名称
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * 获取项目介绍
     *
     * @return project_re - 项目介绍
     */
    public String getProjecyRe() {
        return projectRe;
    }

    /**
     * 设置项目介绍
     *
     * @param projectRe 项目介绍
     */
    public void setProjecyRe(String projectRe) {
        this.projectRe = projectRe;
    }

    /**
     * 获取诊所
     *
     * @return org_id - 诊所
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置诊所
     *
     * @param orgId 诊所
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
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
     * 获取价格
     *
     * @return price - 价格
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置价格
     *
     * @param price 价格
     */
    public void setPrice(Double price) {
        this.price = price;
    }
}