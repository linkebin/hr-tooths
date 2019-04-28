package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "stock_product_type")
public class StockProductType {
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
     * 类型名称
     */
    private String name;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 父亲节点
     */
    @Column(name = "parent_id")
    private String parentId;

    /**
     * 创建人
     */
    private String creator;

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
     * 获取类型名称
     *
     * @return name - 类型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置类型名称
     *
     * @param name 类型名称
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}