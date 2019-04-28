package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_type")
public class SysType {
    /**
     * id
     */
    @Id
    @Column(name = "type_id")
    private String typeId;

    /**
     * 类型编号
     */
    private String code;

    /**
     * 类型值
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    private String creator;

    /**
     * 上级ID
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 分类
     */
    @Column(name = "type_type")
    private String typeType;

    /**
     * 描述
     */
    private String remark;

    /**
     * 获取id
     *
     * @return type_id - id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * 设置id
     *
     * @param typeId id
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取类型编号
     *
     * @return code - 类型编号
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置类型编号
     *
     * @param code 类型编号
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取类型值
     *
     * @return name - 类型值
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型值
     *
     * @param name 类型值
     */
    public void setName(String name) {
        this.name = name;
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
     * 获取上级ID
     *
     * @return parent_id - 上级ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 设置上级ID
     *
     * @param parentId 上级ID
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取分类
     *
     * @return type_type - 分类
     */
    public String getTypeType() {
        return typeType;
    }

    /**
     * 设置分类
     *
     * @param typeType 分类
     */
    public void setTypeType(String typeType) {
        this.typeType = typeType;
    }

    /**
     * 获取描述
     *
     * @return remark - 描述
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置描述
     *
     * @param remark 描述
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
}