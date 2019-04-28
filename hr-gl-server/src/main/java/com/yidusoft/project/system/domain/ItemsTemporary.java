package com.yidusoft.project.system.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "items_temporary")
public class ItemsTemporary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "order_id")
    private String orderId;

    private String batch;

    private String code;

    private String name;

    @Column(name = "type_id")
    private String typeId;

    private String type;

    private String model;

    private String specifications;

    private String brand;

    @Column(name = "inventory_num")
    private Integer inventoryNum;

    @Column(name = "inventory_cost")
    private Double inventoryCost;

    private Date validity;

    @Column(name = "retail_price")
    private Double retailPrice;

    private Double money;

    private String remark;

    private String unit;

    private String note;

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
     * @return id
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
     * @return batch
     */
    public String getBatch() {
        return batch;
    }

    /**
     * @param batch
     */
    public void setBatch(String batch) {
        this.batch = batch;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return specifications
     */
    public String getSpecifications() {
        return specifications;
    }

    /**
     * @param specifications
     */
    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    /**
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * @param brand
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * @return inventory_num
     */
    public Integer getInventoryNum() {
        return inventoryNum;
    }

    /**
     * @param inventoryNum
     */
    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    /**
     * @return inventory_cost
     */
    public Double getInventoryCost() {
        return inventoryCost;
    }

    /**
     * @param inventoryCost
     */
    public void setInventoryCost(Double inventoryCost) {
        this.inventoryCost = inventoryCost;
    }

    /**
     * @return validity
     */
    public Date getValidity() {
        return validity;
    }

    /**
     * @param validity
     */
    public void setValidity(Date validity) {
        this.validity = validity;
    }

    /**
     * @return retail_price
     */
    public Double getRetailPrice() {
        return retailPrice;
    }

    /**
     * @param retailPrice
     */
    public void setRetailPrice(Double retailPrice) {
        this.retailPrice = retailPrice;
    }

    /**
     * @return money
     */
    public Double getMoney() {
        return money;
    }

    /**
     * @param money
     */
    public void setMoney(Double money) {
        this.money = money;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note
     */
    public void setNote(String note) {
        this.note = note;
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