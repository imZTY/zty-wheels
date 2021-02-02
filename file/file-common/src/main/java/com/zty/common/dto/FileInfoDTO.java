package com.zty.common.dto;

import java.util.Date;

/**
 * @author tianyi
 * @date 2020-05-22 19:55
 */
public class FileInfoDTO {

    private Integer id;

    private String name;

    private Short fileKind;

    private String publicUrl;

    private Integer createBy;

    private Date createTime;

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

    public Short getFileKind() {
        return fileKind;
    }

    public void setFileKind(Short fileKind) {
        this.fileKind = fileKind;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
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
}
