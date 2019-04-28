package com.yidusoft.project.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "stock_in_out")
public class StockInOut {
    /**
     * id
     */
    @Id
    @Column(name = "in_out_id")
    private String inOutId;

    /**
     * 编号
     */
    @Column(name = "in_out_code")
    private String inOutCode;

    /**
     * 时间
     */
    @Column(name = "in_out_time")
    private Date inOutTime;

    /**
     * 创建人
     */
    @Column(name = "creator_id")
    private String creatorId;

    private String creator;

    /**
     * 金额
     */
    private Double money;

    /**
     * 合计总数
     */
    private int num;

    /**
     * 入库对象
     */
    @Column(name = "in_obj")
    private String inObj;

    /**
     * 出库对象
     */
    @Column(name = "out_obj")
    private String outObj;

    /**
     * 类型
     */
    private String type;

    private double state;


    private String clinicId;

    /**
     * 获取id
     *
     * @return in_out_id - id
     */
    public String getInOutId() {
        return inOutId;
    }

    /**
     * 设置id
     *
     * @param inOutId id
     */
    public void setInOutId(String inOutId) {
        this.inOutId = inOutId;
    }

    /**
     * 获取编号
     *
     * @return in_out_code - 编号
     */
    public String getInOutCode() {
        return inOutCode;
    }

    /**
     * 设置编号
     *
     * @param inOutCode 编号
     */
    public void setInOutCode(String inOutCode) {
        this.inOutCode = inOutCode;
    }

    /**
     * 获取时间
     *
     * @return in_out_time - 时间
     */
    public Date getInOutTime() {
        return inOutTime;
    }

    /**
     * 设置时间
     *
     * @param inOutTime 时间
     */
    public void setInOutTime(Date inOutTime) {
        this.inOutTime = inOutTime;
    }

    /**
     * 获取创建人
     *
     * @return creator - 创建人
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 设置创建人
     *
     * @param creator 创建人
     */
    public void setCreator(String creator) {
        this.creator = creator;
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
     * 获取合计总数
     *
     * @return num - 合计总数
     */
    public int getNum() {
        return num;
    }

    /**
     * 设置合计总数
     *
     * @param num 合计总数
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * 获取入库对象
     *
     * @return in_obj - 入库对象
     */
    public String getInObj() {
        return inObj;
    }

    /**
     * 设置入库对象
     *
     * @param inObj 入库对象
     */
    public void setInObj(String inObj) {
        this.inObj = inObj;
    }

    /**
     * 获取出库对象
     *
     * @return out_obj - 出库对象
     */
    public String getOutObj() {
        return outObj;
    }

    /**
     * 设置出库对象
     *
     * @param outObj 出库对象
     */
    public void setOutObj(String outObj) {
        this.outObj = outObj;
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

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }
}