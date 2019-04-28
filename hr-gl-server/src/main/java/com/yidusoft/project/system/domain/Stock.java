package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.util.Date;

public class Stock {
    /**
     * id
     */
    @Id
    @Column(name = "stock_id")
    private String stockId;

    /**
     * 诊所id
     */
    @Column(name = "clinic_id")
    private String clinicId;

    /**
     * 物品类型
     */
    @Column(name = "product_type")
    private String productType;

    /**
     * 物品编号
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 名称
     */
    @Column(name = "product_name")
    private String productName;

    /**
     * 型号
     */
    @Column(name = "product_model")
    private String productModel;

    /**
     * 规格
     */
    @Column(name = "product_spec")
    private String productSpec;

    /**
     * 品牌
     */
    @Column(name = "product_brand")
    private String productBrand;

    @Column(name = "product_unit")
    private String productUnit;

    /**
     * 单位
     */
    @Column(name = "type_id")
    private String typeId;

    /**
     * 库存数量
     */
    @Column(name = "stock_num")
    private int stockNum;

    /**
     * 有效期至
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 平均单价
     */
    private String sprice;

    /**
     * 金额
     */
    private Double money;

    private String remark;

    /**
     * 获取id
     *
     * @return stock_id - id
     */
    public String getStockId() {
        return stockId;
    }

    /**
     * 设置id
     *
     * @param stockId id
     */
    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    /**
     * 获取诊所id
     *
     * @return clinic_id - 诊所id
     */
    public String getClinicId() {
        return clinicId;
    }

    /**
     * 设置诊所id
     *
     * @param clinicId 诊所id
     */
    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    /**
     * 获取物品类型
     *
     * @return product_type - 物品类型
     */
    public String getProductType() {
        return productType;
    }

    /**
     * 设置物品类型
     *
     * @param productType 物品类型
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * 获取物品编号
     *
     * @return product_code - 物品编号
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * 设置物品编号
     *
     * @param productCode 物品编号
     */
    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取名称
     *
     * @return product_name - 名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置名称
     *
     * @param productName 名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 获取型号
     *
     * @return product_model - 型号
     */
    public String getProductModel() {
        return productModel;
    }

    /**
     * 设置型号
     *
     * @param productModel 型号
     */
    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    /**
     * 获取规格
     *
     * @return product_spec - 规格
     */
    public String getProductSpec() {
        return productSpec;
    }

    /**
     * 设置规格
     *
     * @param productSpec 规格
     */
    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    /**
     * 获取品牌
     *
     * @return product_brand - 品牌
     */
    public String getProductBrand() {
        return productBrand;
    }

    /**
     * 设置品牌
     *
     * @param productBrand 品牌
     */
    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
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
     * 获取平均单价
     *
     * @return sprice - 平均单价
     */
    public String getSprice() {
        return sprice;
    }

    /**
     * 设置平均单价
     *
     * @param sprice 平均单价
     */
    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public int getStockNum() {
        return stockNum;
    }

    public void setStockNum(int stockNum) {
        this.stockNum = stockNum;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}