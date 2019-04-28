package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "stock_product")
public class StockProduct {
    /**
     * id
     */
    @Id
    @Column(name = "product_id")
    private String productId;

    /**
     * 物品编号
     */
    @Column(name = "product_code")
    private String productCode;

    /**
     * 物品名称
     */
    @Column(name = "product_name")
    private String productName;

    @Column(name = "type_id")
    private String typeId;

    /**
     * 物品类型
     */
    @Column(name = "product_type")
    private String productType;

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

    /**
     * 单位
     */
    @Column(name = "product_unit")
    private String productUnit;

    /**
     * 采购单价
     */
    @Column(name = "in_stock_price")
    private Double inStockPrice;

    /**
     * 出库价格
     */
    @Column(name = "out_stock_price")
    private Double outStockPrice;

    /**
     * 零售价格
     */
    @Column(name = "sell_price")
    private Double sellPrice;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "remark")
    private String remark;

    /**
     * 获取id
     *
     * @return product_id - id
     */
    public String getProductId() {
        return productId;
    }

    /**
     * 设置id
     *
     * @param productId id
     */
    public void setProductId(String productId) {
        this.productId = productId;
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
     * 获取物品名称
     *
     * @return product_name - 物品名称
     */
    public String getProductName() {
        return productName;
    }

    /**
     * 设置物品名称
     *
     * @param productName 物品名称
     */
    public void setProductName(String productName) {
        this.productName = productName;
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
     * 获取单位
     *
     * @return product_unit - 单位
     */
    public String getProductUnit() {
        return productUnit;
    }

    /**
     * 设置单位
     *
     * @param productUnit 单位
     */
    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    /**
     * 获取采购单价
     *
     * @return in_stock_price - 采购单价
     */
    public Double getInStockPrice() {
        return inStockPrice;
    }

    /**
     * 设置采购单价
     *
     * @param inStockPrice 采购单价
     */
    public void setInStockPrice(Double inStockPrice) {
        this.inStockPrice = inStockPrice;
    }

    /**
     * 获取出库价格
     *
     * @return out_stock_price - 出库价格
     */
    public Double getOutStockPrice() {
        return outStockPrice;
    }

    /**
     * 设置出库价格
     *
     * @param outStockPrice 出库价格
     */
    public void setOutStockPrice(Double outStockPrice) {
        this.outStockPrice = outStockPrice;
    }

    /**
     * 获取零售价格
     *
     * @return sell_price - 零售价格
     */
    public Double getSellPrice() {
        return sellPrice;
    }

    /**
     * 设置零售价格
     *
     * @param sellPrice 零售价格
     */
    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}