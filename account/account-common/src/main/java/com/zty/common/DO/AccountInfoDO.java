package com.zty.common.DO;

import java.util.Date;

public class AccountInfoDO {
    private Integer id;

    private String account;

    private Byte accountType;

    private String name;

    private String phone;

    private String openid;

    private String password;

    private Byte disabled;

    private Integer createBy;

    private Date createTime;

    private Date updateTime;

    private Integer fldN1;

    private Integer fldN2;

    private Long fldL1;

    private String fldS1;

    private String fldS2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Byte getAccountType() {
        return accountType;
    }

    public void setAccountType(Byte accountType) {
        this.accountType = accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getDisabled() {
        return disabled;
    }

    public void setDisabled(Byte disabled) {
        this.disabled = disabled;
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

    public Integer getFldN1() {
        return fldN1;
    }

    public void setFldN1(Integer fldN1) {
        this.fldN1 = fldN1;
    }

    public Integer getFldN2() {
        return fldN2;
    }

    public void setFldN2(Integer fldN2) {
        this.fldN2 = fldN2;
    }

    public Long getFldL1() {
        return fldL1;
    }

    public void setFldL1(Long fldL1) {
        this.fldL1 = fldL1;
    }

    public String getFldS1() {
        return fldS1;
    }

    public void setFldS1(String fldS1) {
        this.fldS1 = fldS1;
    }

    public String getFldS2() {
        return fldS2;
    }

    public void setFldS2(String fldS2) {
        this.fldS2 = fldS2;
    }
}