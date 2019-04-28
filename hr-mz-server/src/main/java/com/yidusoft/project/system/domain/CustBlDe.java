package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "cust_bl_de")
public class CustBlDe {
    /**
     * id
     */
    @Id
    @Column(name = "bl_de_id")
    private String blDeId;

    /**
     * 关联病历ID
     */
    @Column(name = "bl_id")
    private String blId;

    /**
     * 牙位
     */
    @Column(name = "tooth_addr")
    private String toothAddr;

    /**
     * 类型（1、口腔检查 2、辅助检查3、诊断4、治疗计划5、处置）
     */
    private String type;

    /**
     * 内容
     */
    private String content;

    /**
     * 排序
     */
    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * 获取id
     *
     * @return bl_de_id - id
     */
    public String getBlDeId() {
        return blDeId;
    }

    /**
     * 设置id
     *
     * @param blDeId id
     */
    public void setBlDeId(String blDeId) {
        this.blDeId = blDeId;
    }

    /**
     * 获取关联病历ID
     *
     * @return bl_id - 关联病历ID
     */
    public String getBlId() {
        return blId;
    }

    /**
     * 设置关联病历ID
     *
     * @param blId 关联病历ID
     */
    public void setBlId(String blId) {
        this.blId = blId;
    }

    /**
     * 获取牙位
     *
     * @return tooth_addr - 牙位
     */
    public String getToothAddr() {
        return toothAddr;
    }

    /**
     * 设置牙位
     *
     * @param toothAddr 牙位
     */
    public void setToothAddr(String toothAddr) {
        this.toothAddr = toothAddr;
    }

    /**
     * 获取类型（1、口腔检查 2、辅助检查3、诊断4、治疗计划5、处置）
     *
     * @return type - 类型（1、口腔检查 2、辅助检查3、诊断4、治疗计划5、处置）
     */
    public String getType() {
        return type;
    }

    /**
     * 设置类型（1、口腔检查 2、辅助检查3、诊断4、治疗计划5、处置）
     *
     * @param type 类型（1、口腔检查 2、辅助检查3、诊断4、治疗计划5、处置）
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取内容
     *
     * @return content - 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}