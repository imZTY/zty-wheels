package com.zty.common.DO;

import java.util.Date;

public class FileInfoDO {
    private Integer id;

    private String name;

    private Byte fileKind;

    private String publicUrl;

    private String privateUrl;

    private Integer downloadTime;

    private Integer likeTime;

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

    public Byte getFileKind() {
        return fileKind;
    }

    public void setFileKind(Byte fileKind) {
        this.fileKind = fileKind;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public String getPrivateUrl() {
        return privateUrl;
    }

    public void setPrivateUrl(String privateUrl) {
        this.privateUrl = privateUrl;
    }

    public Integer getDownloadTime() {
        return downloadTime;
    }

    public void setDownloadTime(Integer downloadTime) {
        this.downloadTime = downloadTime;
    }

    public Integer getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(Integer likeTime) {
        this.likeTime = likeTime;
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