package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "sys_accessory")
public class SysAccessory {
    /**
     * id
     */
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 类型
     */
    private String type;

    /**
     * 文件名
     */
    @Column(name = "acc_name")
    private String accName;

    /**
     * 上传人
     */
    private String creator;

    /**
     * 上传时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 路径
     */
    private String url;

    /**
     * 关联id
     */
    @Column(name = "re_id")
    private String reId;

    private String yw;
    private String remark;
    private String zl;

    public String getYw() {
        return yw;
    }

    public void setYw(String yw) {
        this.yw = yw;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    /**
     * 获取id
     *
     * @return id_ - id
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
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
     * 获取文件名
     *
     * @return acc_name - 文件名
     */
    public String getAccName() {
        return accName;
    }

    /**
     * 设置文件名
     *
     * @param accName 文件名
     */
    public void setAccName(String accName) {
        this.accName = accName;
    }

    /**
     * 获取上传人
     *
     * @return creator - 上传人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置上传人
     *
     * @param creator 上传人
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 获取上传时间
     *
     * @return create_time - 上传时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置上传时间
     *
     * @param createTime 上传时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取路径
     *
     * @return url - 路径
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置路径
     *
     * @param url 路径
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取关联id
     *
     * @return re_id - 关联id
     */
    public String getReId() {
        return reId;
    }

    /**
     * 设置关联id
     *
     * @param reId 关联id
     */
    public void setReId(String reId) {
        this.reId = reId;
    }
}