package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "cust_return")
public class CustReturn {
    @Id
    @Column(name = "id_")
    private String id;

    @Column(name = "return_state")
    private String returnState;

    private String doctor;

    @Column(name = "return_time")
    private Date returnTime;

    @Column(name = "return_content")
    private String returnContent;

    @Column(name = "return_result")
    private String returnResult;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "jz_time")
    private Date jzTime;

    private String creator;

    private String deleted;

    @Column(name = "cust_id")
    private String custId;

    public String getDeleted() {
        return deleted;
    }

    public void setDeleted(String deleted) {
        this.deleted = deleted;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

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
     * @return return_state
     */
    public String getReturnState() {
        return returnState;
    }

    /**
     * @param returnState
     */
    public void setReturnState(String returnState) {
        this.returnState = returnState;
    }

    /**
     * @return doctor
     */
    public String getDoctor() {
        return doctor;
    }

    /**
     * @param doctor
     */
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    /**
     * @return return_time
     */
    public Date getReturnTime() {
        return returnTime;
    }

    /**
     * @param returnTime
     */
    public void setReturnTime(Date returnTime) {
        this.returnTime = returnTime;
    }

    /**
     * @return return_content
     */
    public String getReturnContent() {
        return returnContent;
    }

    /**
     * @param returnContent
     */
    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    /**
     * @return return_result
     */
    public String getReturnResult() {
        return returnResult;
    }

    /**
     * @param returnResult
     */
    public void setReturnResult(String returnResult) {
        this.returnResult = returnResult;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return jz_time
     */
    public Date getJzTime() {
        return jzTime;
    }

    /**
     * @param jzTime
     */
    public void setJzTime(Date jzTime) {
        this.jzTime = jzTime;
    }

    /**
     * @return creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * @param creator
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }
}