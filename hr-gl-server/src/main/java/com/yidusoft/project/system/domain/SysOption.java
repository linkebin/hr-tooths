package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "sys_option")
public class SysOption {
    @Id
    @Column(name = "OPTION_ID")
    private String optionId;

    /**
     * 父节点ID
     */
    @Column(name = "OPTION_FATHER")
    private String optionFather;

    /**
     * 编号
     */
    @Column(name = "OPTION_CODE")
    private String optionCode;

    /**
     * 名称
     */
    @Column(name = "OPTION_NAME")
    private String optionName;

    /**
     * 值
     */
    @Column(name = "OPTION_VALUE")
    private String optionValue;

    /**
     * 类型 (整形、字符型、浮点型')
     */
    @Column(name = "OPTION_TYPE")
    private Integer optionType;

    /**
     * 0-启用  1-停用 
     */
    @Column(name = "OPTION_STAUTS")
    private Integer optionStauts;

    /**
     * 创建人
     */
    @Column(name = "CREATOR")
    private String creator;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 是否删除标志 0:未删除 ，1:已删除
     */
    @Column(name = "DELETED")
    private Integer deleted;

    /**
     * 编码前缀
     */
    @Column(name = "CODE_PREFIX")
    private String codePrefix;

    /**
     * 选项类别
     */
    @Column(name = "OPTION_CATEGORY")
    private String optionCategory;

    /**
     * @return OPTION_ID
     */
    public String getOptionId() {
        return optionId;
    }

    /**
     * @param optionId
     */
    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    /**
     * 获取父节点ID
     *
     * @return OPTION_FATHER - 父节点ID
     */
    public String getOptionFather() {
        return optionFather;
    }

    /**
     * 设置父节点ID
     *
     * @param optionFather 父节点ID
     */
    public void setOptionFather(String optionFather) {
        this.optionFather = optionFather;
    }

    /**
     * 获取编号
     *
     * @return OPTION_CODE - 编号
     */
    public String getOptionCode() {
        return optionCode;
    }

    /**
     * 设置编号
     *
     * @param optionCode 编号
     */
    public void setOptionCode(String optionCode) {
        this.optionCode = optionCode;
    }

    /**
     * 获取名称
     *
     * @return OPTION_NAME - 名称
     */
    public String getOptionName() {
        return optionName;
    }

    /**
     * 设置名称
     *
     * @param optionName 名称
     */
    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    /**
     * 获取值
     *
     * @return OPTION_VALUE - 值
     */
    public String getOptionValue() {
        return optionValue;
    }

    /**
     * 设置值
     *
     * @param optionValue 值
     */
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }

    /**
     * 获取类型 (整形、字符型、浮点型')
     *
     * @return OPTION_TYPE - 类型 (整形、字符型、浮点型')
     */
    public Integer getOptionType() {
        return optionType;
    }

    /**
     * 设置类型 (整形、字符型、浮点型')
     *
     * @param optionType 类型 (整形、字符型、浮点型')
     */
    public void setOptionType(Integer optionType) {
        this.optionType = optionType;
    }

    /**
     * 获取0-启用  1-停用 
     *
     * @return OPTION_STAUTS - 0-启用  1-停用 
     */
    public Integer getOptionStauts() {
        return optionStauts;
    }

    /**
     * 设置0-启用  1-停用 
     *
     * @param optionStauts 0-启用  1-停用 
     */
    public void setOptionStauts(Integer optionStauts) {
        this.optionStauts = optionStauts;
    }

    /**
     * 获取创建人
     *
     * @return CREATOR - 创建人
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
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
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
     * 获取是否删除标志 0:未删除 ，1:已删除
     *
     * @return DELETED - 是否删除标志 0:未删除 ，1:已删除
     */
    public Integer getDeleted() {
        return deleted;
    }

    /**
     * 设置是否删除标志 0:未删除 ，1:已删除
     *
     * @param deleted 是否删除标志 0:未删除 ，1:已删除
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    /**
     * 获取编码前缀
     *
     * @return CODE_PREFIX - 编码前缀
     */
    public String getCodePrefix() {
        return codePrefix;
    }

    /**
     * 设置编码前缀
     *
     * @param codePrefix 编码前缀
     */
    public void setCodePrefix(String codePrefix) {
        this.codePrefix = codePrefix;
    }

    /**
     * 获取选项类别
     *
     * @return OPTION_CATEGORY - 选项类别
     */
    public String getOptionCategory() {
        return optionCategory;
    }

    /**
     * 设置选项类别
     *
     * @param optionCategory 选项类别
     */
    public void setOptionCategory(String optionCategory) {
        this.optionCategory = optionCategory;
    }
}