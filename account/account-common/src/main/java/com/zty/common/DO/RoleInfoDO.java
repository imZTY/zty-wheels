package com.zty.common.DO;

import java.util.Date;

import com.zty.framework.dto.DataDTO;

public class RoleInfoDO extends DataDTO {
    private Integer id;

    private String name;

    private String rights;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
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