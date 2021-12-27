package com.zty.pay.DO;

import java.util.Date;

import com.zty.framework.dto.DataDTO;

public class OrderInfoDO extends DataDTO {
    private Long id;

    private String thirdOrderNo;

    private Integer accountId;

    /**
     * 充值方式(1=支付宝转账,2=微信转账,3=银行卡转账)
     */
    private Byte orderMethod;

    /**
     * 充值类型(1=充值,2=退款)
     */
    private Byte orderType;

    private Long orderAmount;

    private Byte discountType;

    private Long discountAmount;

    private Long actualAmount;

    /**
     * 订单状态(0=初始,1=支付中,2=支付完成,3=支付关闭,4=支付失败,5=退款中.6=退款完成,7=冲正)
     */
    private Byte status;

    /**
     * 余额变化(在kdd是条数)
     */
    private Long balanceChange;

    private Integer createBy;

    private Date createTime;

    private Date updateTime;

    private String remarks;

    private String fldS1;

    private Long fldN1;

    private Integer fldN2;

    private Byte fldN3;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThirdOrderNo() {
        return thirdOrderNo;
    }

    public void setThirdOrderNo(String thirdOrderNo) {
        this.thirdOrderNo = thirdOrderNo;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Byte getOrderMethod() {
        return orderMethod;
    }

    public void setOrderMethod(Byte orderMethod) {
        this.orderMethod = orderMethod;
    }

    public Byte getOrderType() {
        return orderType;
    }

    public void setOrderType(Byte orderType) {
        this.orderType = orderType;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Byte getDiscountType() {
        return discountType;
    }

    public void setDiscountType(Byte discountType) {
        this.discountType = discountType;
    }

    public Long getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Long discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Long getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Long actualAmount) {
        this.actualAmount = actualAmount;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getBalanceChange() {
        return balanceChange;
    }

    public void setBalanceChange(Long balanceChange) {
        this.balanceChange = balanceChange;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFldS1() {
        return fldS1;
    }

    public void setFldS1(String fldS1) {
        this.fldS1 = fldS1;
    }

    public Long getFldN1() {
        return fldN1;
    }

    public void setFldN1(Long fldN1) {
        this.fldN1 = fldN1;
    }

    public Integer getFldN2() {
        return fldN2;
    }

    public void setFldN2(Integer fldN2) {
        this.fldN2 = fldN2;
    }

    public Byte getFldN3() {
        return fldN3;
    }

    public void setFldN3(Byte fldN3) {
        this.fldN3 = fldN3;
    }
}