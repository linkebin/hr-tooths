package com.yidusoft.project.system.domain;

import java.util.Date;
import javax.persistence.*;

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

    /**
     * 物品编号
     */
    @Column(name = "in_out_code")
    private String inOutCode;

    /**
     * 物品名称
     */
    @Column(name = "p_name")
    private String pName;

    @Column(name = "type_id")
    private String typeId;

    /**
     * 物品类型
     */
    @Column(name = "p_type")
    private String pType;

    /**
     * 型号
     */
    @Column(name = "p_model")
    private String pModel;

    /**
     * 规格
     */
    @Column(name = "p_spe")
    private String pSpe;

    /**
     * 单价
     */
    private Double price;

    /**
     * 数量
     */
    private Integer num;

    /**
     * 品牌
     */
    @Column(name = "p_brand")
    private String pBrand;

    /**
     * 金额
     */
    private Double money;

    /**
     * 备注
     */
    private String remark;

    /**
     * 有效期至
     */
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

    private Double cost;

    private Integer allowance;

    @Column(name = "p_unit")
    private String pUnit;

    /**
     * 生产日期
     */
    @Column(name = "start_time")
    private Date startTime;

    /**
     * 生产批次
     */
    @Column(name = "production_code")
    private String productionCode;

    /**
     * 供应商
     */
    private String supplier;

    /**
     * 保质期
     */
    @Column(name = "expiration_time")
    private String expirationTime;

    /**
     * 获取id
     *
     * @return in_out_de_id - id
     */
    public String getInOutDeId() {
        return inOutDeId;
    }

    /**
     * 设置id
     *
     * @param inOutDeId id
     */
    public void setInOutDeId(String inOutDeId) {
        this.inOutDeId = inOutDeId;
    }

    /**
     * @return clinic_id
     */
    public String getClinicId() {
        return clinicId;
    }

    /**
     * @param clinicId
     */
    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    /**
     * 获取物品编号
     *
     * @return in_out_code - 物品编号
     */
    public String getInOutCode() {
        return inOutCode;
    }

    /**
     * 设置物品编号
     *
     * @param inOutCode 物品编号
     */
    public void setInOutCode(String inOutCode) {
        this.inOutCode = inOutCode;
    }

    /**
     * 获取物品名称
     *
     * @return p_name - 物品名称
     */
    public String getpName() {
        return pName;
    }

    /**
     * 设置物品名称
     *
     * @param pName 物品名称
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * @return type_id
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * 获取物品类型
     *
     * @return p_type - 物品类型
     */
    public String getpType() {
        return pType;
    }

    /**
     * 设置物品类型
     *
     * @param pType 物品类型
     */
    public void setpType(String pType) {
        this.pType = pType;
    }

    /**
     * 获取型号
     *
     * @return p_model - 型号
     */
    public String getpModel() {
        return pModel;
    }

    /**
     * 设置型号
     *
     * @param pModel 型号
     */
    public void setpModel(String pModel) {
        this.pModel = pModel;
    }

    /**
     * 获取规格
     *
     * @return p_spe - 规格
     */
    public String getpSpe() {
        return pSpe;
    }

    /**
     * 设置规格
     *
     * @param pSpe 规格
     */
    public void setpSpe(String pSpe) {
        this.pSpe = pSpe;
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
     * 获取数量
     *
     * @return num - 数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 设置数量
     *
     * @param num 数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 获取品牌
     *
     * @return p_brand - 品牌
     */
    public String getpBrand() {
        return pBrand;
    }

    /**
     * 设置品牌
     *
     * @param pBrand 品牌
     */
    public void setpBrand(String pBrand) {
        this.pBrand = pBrand;
    }

    /**
     * 获取金额
     *
     * @return money - 金额
     */
    public Double getMoney() {
        return money;
    }

    /**
     * 设置金额
     *
     * @param money 金额
     */
    public void setMoney(Double money) {
        this.money = money;
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
     * 获取有效期至
     *
     * @return end_time - 有效期至
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置有效期至
     *
     * @param endTime 有效期至
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * @return user_name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
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
     * @return batch_id
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * @param batchId
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    /**
     * @return operation_type
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * @return order_id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * @param orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * @return cost
     */
    public Double getCost() {
        return cost;
    }

    /**
     * @param cost
     */
    public void setCost(Double cost) {
        this.cost = cost;
    }

    /**
     * @return allowance
     */
    public Integer getAllowance() {
        return allowance;
    }

    /**
     * @param allowance
     */
    public void setAllowance(Integer allowance) {
        this.allowance = allowance;
    }

    /**
     * @return p_unit
     */
    public String getpUnit() {
        return pUnit;
    }

    /**
     * @param pUnit
     */
    public void setpUnit(String pUnit) {
        this.pUnit = pUnit;
    }

    /**
     * 获取生产日期
     *
     * @return start_time - 生产日期
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 设置生产日期
     *
     * @param startTime 生产日期
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 获取生产批次
     *
     * @return production_code - 生产批次
     */
    public String getProductionCode() {
        return productionCode;
    }

    /**
     * 设置生产批次
     *
     * @param productionCode 生产批次
     */
    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    /**
     * 获取供应商
     *
     * @return supplier - 供应商
     */
    public String getSupplier() {
        return supplier;
    }

    /**
     * 设置供应商
     *
     * @param supplier 供应商
     */
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    /**
     * 获取保质期
     *
     * @return expiration_time - 保质期
     */
    public String getExpirationTime() {
        return expirationTime;
    }

    /**
     * 设置保质期
     *
     * @param expirationTime 保质期
     */
    public void setExpirationTime(String expirationTime) {
        this.expirationTime = expirationTime;
    }
}