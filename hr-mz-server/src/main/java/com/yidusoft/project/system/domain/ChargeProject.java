package com.yidusoft.project.system.domain;

import javax.persistence.*;

@Table(name = "charge_project")
public class ChargeProject {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 项目名称
     */
    @Column(name = "project_name")
    private String projectName;

    /**
     * 单价
     */
    private Double price;

    /**
     * 备注
     */
    private String remark;

    /**
     * 诊所ID
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 类型
     */
    private String type;

    /**
     * 编号
     */
    private String code;

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
     * 获取诊所ID
     *
     * @return org_id - 诊所ID
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置诊所ID
     *
     * @param orgId 诊所ID
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    /**
     * 获取类型
     *
     * @return type - 类型
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型
     *
     * @param type 类型
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取编号
     *
     * @return code - 编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置编号
     *
     * @param code 编号
     */
    public void setCode(String code) {
        this.code = code;
    }
}