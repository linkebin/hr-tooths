package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "items_temporary")
public class ItemsTemporary {
    /**
     * 主键ID
     */
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "batch")
    private String batch;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "type_id")
    private String typeId;

    @Column(name = "type")
    private String type;

    @Column(name = "model")
    private String model;

    @Column(name = "specifications")
    private String specifications;

    @Column(name = "brand")
    private String brand;

    @Column(name = "inventory_num")
    private int inventoryNum;

    @Column(name = "inventory_cost")
    private double inventoryCost;

    @Column(name = "validity")
    private Date validity;

    @Column(name = "retail_price")
    private double retailPrice;

    @Column(name = "money")
    private double money;

    @Column(name = "remark")
    private String remark;

    @Column(name = "order_id")
    private String orderId;

    @Column(name = "unit")
    private String unit;

    @Column(name = "note")
    private String note;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(int inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public double getInventoryCost() {
        return inventoryCost;
    }

    public void setInventoryCost(double inventoryCost) {
        this.inventoryCost = inventoryCost;
    }

    public Date getValidity() {
        return validity;
    }

    public void setValidity(Date validity) {
        this.validity = validity;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}