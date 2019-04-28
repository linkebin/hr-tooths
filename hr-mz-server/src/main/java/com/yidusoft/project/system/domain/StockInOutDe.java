package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "stock_in_out_de")
public class StockInOutDe {
    /**
     * id
     */
    @Id
    @Column(name = "in_out_de_id")
    private String inOutDeId;

    @Column(name = "clinic_id")
    private String clinicId;

    @Column(name = "in_out_code")
    private String inOutCode;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "p_type")
    private String pType;

    @Column(name = "p_model")
    private String pModel;

    @Column(name = "p_spe")
    private String pSpe;

    @Column(name = "price")
    private Double price;

    @Column(name = "num")
    private int num;

    @Column(name = "p_brand")
    private String pBrand;

    @Column(name = "p_unit")
    private String pUnit;

    @Column(name = "money")
    private Double money;

    @Column(name = "remark")
    private String remark;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "batch_id")
    private String batchId;

    @Column(name = "operation_type")
    private String operationType;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "cost")
    private Double cost;

    @Column(name = "allowance")
    private int allowance;

    public String getInOutDeId() {
        return inOutDeId;
    }

    public void setInOutDeId(String inOutDeId) {
        this.inOutDeId = inOutDeId;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getInOutCode() {
        return inOutCode;
    }

    public void setInOutCode(String inOutCode) {
        this.inOutCode = inOutCode;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getpType() {
        return pType;
    }

    public void setpType(String pType) {
        this.pType = pType;
    }

    public String getpModel() {
        return pModel;
    }

    public void setpModel(String pModel) {
        this.pModel = pModel;
    }

    public String getpSpe() {
        return pSpe;
    }

    public void setpSpe(String pSpe) {
        this.pSpe = pSpe;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getpBrand() {
        return pBrand;
    }

    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getAllowance() {
        return allowance;
    }

    public void setAllowance(int allowance) {
        this.allowance = allowance;
    }

    public String getpUnit() {
        return pUnit;
    }

    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }
}