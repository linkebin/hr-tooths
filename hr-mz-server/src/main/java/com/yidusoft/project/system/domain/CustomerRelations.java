package com.yidusoft.project.system.domain;

import javax.persistence.*;

@Table(name = "customer_relations")
public class CustomerRelations {
    @Id
    @Column(name = "id_")
    private String id;

    /**
     * 客户id
     */
    @Column(name = "cust_id")
    private String custId;

    /**
     * 被绑定客户id
     */
    @Column(name = "cust_id_bd")
    private String custIdBd;

    /**
     * 关系
     */
    private String relation;

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
     * 获取客户id
     *
     * @return cust_id - 客户id
     */
    public String getCustId() {
        return custId;
    }

    /**
     * 设置客户id
     *
     * @param custId 客户id
     */
    public void setCustId(String custId) {
        this.custId = custId;
    }

    /**
     * 获取被绑定客户id
     *
     * @return cust_id_bd - 被绑定客户id
     */
    public String getCustIdBd() {
        return custIdBd;
    }

    /**
     * 设置被绑定客户id
     *
     * @param custIdBd 被绑定客户id
     */
    public void setCustIdBd(String custIdBd) {
        this.custIdBd = custIdBd;
    }

    /**
     * 获取关系
     *
     * @return relation - 关系
     */
    public String getRelation() {
        return relation;
    }

    /**
     * 设置关系
     *
     * @param relation 关系
     */
    public void setRelation(String relation) {
        this.relation = relation;
    }
}