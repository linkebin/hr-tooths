package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "operation_log")
public class OperationLog {
    /**
     * ID_
     */
    @Id
    @Column(name = "ID_")
    private String id;

    /**
     * 操作模块
     */
    @Column(name = "OPERATION_MODULE")
    private String operationModule;

    /**
     * 修改前内容
     */
    @Column(name = "TOP_CONTENT")
    private String topContent;

    /**
     * 修改后内容
     */
    @Column(name = "AFTER_CONTENT")
    private String afterContent;

    /**
     * 操作时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 操作人ID
     */
    @Column(name = "OPERATION_USER")
    private String operationUser;

    /**
     * 删除标识  0未删除 1 删除
     */
    @Column(name = "DELETED")
    private Integer deleted;

    /**
     * 操作人姓名
     */
    @Column(name = "OPERATION_NAME")
    private String operationName;

    /**
     * 获取ID_
     *
     * @return ID_ - ID_
     */
    public String getId() {
        return id;
    }

    /**
     * 设置ID_
     *
     * @param id ID_
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取操作模块
     *
     * @return OPERATION_MODULE - 操作模块
     */
    public String getOperationModule() {
        return operationModule;
    }

    /**
     * 设置操作模块
     *
     * @param operationModule 操作模块
     */
    public void setOperationModule(String operationModule) {
        this.operationModule = operationModule;
    }

    /**
     * 获取修改前内容
     *
     * @return TOP_CONTENT - 修改前内容
     */
    public String getTopContent() {
        return topContent;
    }

    /**
     * 设置修改前内容
     *
     * @param topContent 修改前内容
     */
    public void setTopContent(String topContent) {
        this.topContent = topContent;
    }

    /**
     * 获取修改后内容
     *
     * @return AFTER_CONTENT - 修改后内容
     */
    public String getAfterContent() {
        return afterContent;
    }

    /**
     * 设置修改后内容
     *
     * @param afterContent 修改后内容
     */
    public void setAfterContent(String afterContent) {
        this.afterContent = afterContent;
    }

    /**
     * 获取操作时间
     *
     * @return CREATE_TIME - 操作时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置操作时间
     *
     * @param createTime 操作时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取操作人ID
     *
     * @return OPERATION_USER - 操作人ID
     */
    public String getOperationUser() {
        return operationUser;
    }

    /**
     * 设置操作人ID
     *
     * @param operationUser 操作人ID
     */
    public void setOperationUser(String operationUser) {
        this.operationUser = operationUser;
    }

    /**
     * 获取删除标识  0未删除 1 删除
     *
     * @return DELETED - 删除标识  0未删除 1 删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置删除标识  0未删除 1 删除
     *
     * @param deleted 删除标识  0未删除 1 删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取操作人姓名
     *
     * @return OPERATION_NAME - 操作人姓名
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * 设置操作人姓名
     *
     * @param operationName 操作人姓名
     */
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}